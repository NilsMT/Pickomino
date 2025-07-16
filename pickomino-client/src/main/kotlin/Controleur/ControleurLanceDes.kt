package Controleur

import Modele.Jeu
import MusicPlayerOnce
import Vue.Vue_jeu
import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.STATUS
import iut.info1.pickomino.exceptions.BadStepException
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Alert
import javafx.stage.Stage

class ControleurLanceDes(vue : Vue_jeu, modele: Jeu, connect : Connector, stage: Stage):EventHandler<ActionEvent> {

    private val connect = connect
    private val vue = vue
    private val modele = modele
    private var stage = stage
    override fun handle(event: ActionEvent?) {
        //Lancement des

        if (connect.gameState(modele.id, modele.key).current.status == STATUS.ROLL_DICE || connect.gameState(modele.id, modele.key).current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO){
            val u = MusicPlayerOnce("src/main/resources/GameAssets/roll.mp3")
            u.start()
            connect.rollDices(modele.id, modele.key)

            //mise a jour vue en consequence
            vue.updateDice(connect.gameState(modele.id,modele.key).current.rolls, vue.desActif)
            vue.updatePouleCommune(connect.gameState(modele.id,modele.key).accessiblePickos(),modele, connect)
            vue.updateDominoJoueurs(connect.gameState(modele.id,modele.key).pickosStackTops(),modele, connect)
            vue.updateScoresJoueurs(connect.gameState(modele.id,modele.key).score())

            //surbrillance dés selectionnables
            vue.desActif.children.forEach{
                if (modele.listeDesStr2(connect.gameState(modele.id,modele.key).current.keptDices).contains(it.userData)) {
                    it.opacity = 0.3
                }else{
                    it.opacity = 1.0
                }
            }
            //bind des des
            vue.fixeDes(vue.desActif,ControleurChoisirDes(vue,modele,connect, stage),modele,connect)
            vue.lanceDes.isDisable = true

            //Test si le jeu est fini
            if (connect.gameState(modele.id, modele.key).current.status == STATUS.GAME_FINISHED){
                modele.jeu_termine(stage)
            }

            //si tour perdu changement de joueur
            if (connect.gameState(modele.id,modele.key).current.keptDices.containsAll(connect.gameState(modele.id,modele.key).current.rolls)){
                vue.lanceDes.isDisable = false
                vue.updateDice(listOf(),vue.desChoisi)
                val al = Alert(Alert.AlertType.INFORMATION)
                al.contentText="Au tour du joueur ${connect.gameState(modele.id,modele.key).current.player+1}"
                al.headerText="Vous ne pouviez selectionné aucun dé"
                al.show()
            }

        }else {
           throw BadStepException()
        }
    }
}