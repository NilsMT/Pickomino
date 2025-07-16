package Controleur

import Modele.Jeu
import Vue.Vue_jeu
import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.STATUS
import iut.info1.pickomino.exceptions.BadPickominoChosenException
import iut.info1.pickomino.exceptions.BadStepException
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.stage.Stage


class ControleurPrendrePickomino(vue : Vue_jeu, modele: Jeu, connect : Connector, stage : Stage) : EventHandler<MouseEvent> {

    private val connect = connect
    private val vue = vue
    private val modele = modele
    private var stage = stage
    override fun handle(event: MouseEvent?) {
        if (connect.gameState(modele.id, modele.key).current.status == STATUS.TAKE_PICKOMINO || connect.gameState(modele.id, modele.key).current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO) {
            //Selectionner valeur clické dans pouleCommune
            var ev = event?.source as ImageView
            var pick = 0
            for (i in connect.gameState(modele.id,modele.key).accessiblePickos()) {
                if (i.toString() == ev.userData.toString()) {
                    pick = i
                }
            }

            //s'il n'est pas dans PouleCommune cherche dans les PickosJoueurs
            if((pick==0) and (connect.gameState(modele.id,modele.key).pickosStackTops().contains(ev.userData))){
                pick = ev.userData as Int
            }

            //choisis dans la pouleCommune la bonne valeur(exacte ou inferieure)
            var pickoPoule = connect.gameState(modele.id,modele.key).accessiblePickos().maxByOrNull { number -> if (number <= modele.sommeDes(connect.gameState(modele.id,modele.key).current.keptDices) ) number else 0 }!!
            //verifier si un joueur possede la valeur exacte de la somme des dés
            var pickoJoueur = 0
            if (connect.gameState(modele.id,modele.key).pickosStackTops().contains(modele.sommeDes(connect.gameState(modele.id,modele.key).current.keptDices))){
                if (connect.gameState(modele.id,modele.key).pickosStackTops().indexOf(pick)!=connect.gameState(modele.id,modele.key).current.player)
                    pickoJoueur=modele.sommeDes(connect.gameState(modele.id,modele.key).current.keptDices)
            }

            //verifie que le picko selectionnable dans la main est superieur au picko dans le jeu
            if ((pickoPoule>pickoJoueur) and (pickoJoueur!=0)){
                pickoPoule=0
            }

            //Si le picko clické est le bon a selectionner
            if ( ((pick == pickoPoule) or (pick == pickoJoueur)) and (pick!=0)){

                //Mettre a jour vue en consequence(surbrillance picko selectionnable PouleCommune)
                vue.pouleCommune.children.forEach {
                    it.opacity = 0.3
                    if (it.userData.toString() == pick.toString()) {
                        it.opacity = 1.0
                    }
                }
                //Mettre a jour vue en consequence(surbrillance picko selectionnable DominoJoueurs)
                vue.listeDominoJoueurs.forEach {
                    it.opacity = 0.3
                    if ((it.userData.toString() == pick.toString()) and (it.userData!=0) ) {
                        it.opacity = 1.0
                    }
                }

                //Boite de dialogue
                var al = Alert(Alert.AlertType.CONFIRMATION)
                al.contentText = "Etes vous sur de vouloir ce Pickomino"
                al.showAndWait()

                if (al.result == ButtonType.OK){
                    var actual = connect.gameState(modele.id,modele.key).current.player
                    vue.listeDominoJoueurs.get(actual).opacity=0.3
                    var b = connect.takePickomino(modele.id,modele.key,pick)
                    if (!b){ throw BadPickominoChosenException() }

                    modele.ajouteScore(connect.gameState(modele.id,modele.key).pickosStackTops(),actual)

                    vue.updatePouleCommune(connect.gameState(modele.id,modele.key).accessiblePickos(),modele, connect)
                    vue.updateDominoJoueurs(connect.gameState(modele.id,modele.key).pickosStackTops(),modele, connect)
                    vue.updateScoresJoueurs(connect.gameState(modele.id,modele.key).score())

                    //Test si le jeu est fini
                    if (connect.gameState(modele.id, modele.key).current.status == STATUS.GAME_FINISHED){
                        modele.jeu_termine(stage)
                    }

                    //Setup le tour du nouveau joueur
                    vue.updateDice(mutableListOf(), vue.desActif)
                    vue.updateDice(connect.gameState(modele.id,modele.key).current.keptDices, vue.desChoisi)
                    vue.lanceDes.isDisable = false

                    val al = Alert(Alert.AlertType.INFORMATION)
                    al.contentText="Au tour du joueur ${connect.gameState(modele.id,modele.key).current.player+1}"
                    al.headerText="Vous avez récupéré un Pickomino au centre"
                    al.show()

                }else{
                    //Mettre a jour vue en consequence(surbrillance picko clické PouleCommune)
                    vue.pouleCommune.children.forEach {
                        it.opacity = 0.3
                        if (it.userData.toString() == pickoPoule.toString()) {
                            it.opacity = 1.0
                        }
                    }
                    //Mettre a jour vue en consequence(surbrillance picko clické DominoJoueurs)
                    vue.listeDominoJoueurs.forEach {
                        it.opacity = 0.3
                        if ((it.userData.toString() == pickoJoueur.toString()) and (it.userData!=0)) {
                            it.opacity = 1.0
                        }
                    }
                }
            }
        }else {
            throw BadStepException()
        }
    }
}