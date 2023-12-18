package Vue

import Controleur.ControleurChoisirDes
import Controleur.ControleurPrendrePickomino
import Modele.Jeu
import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.layout.*
import javafx.scene.control.*
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.stage.Stage
import java.io.FileInputStream

class Vue_3j(theme:String,id:Int,key:Int,stage:Stage): VBox(),Vue_jeu {
    val theme = theme
    override lateinit var listeDominoJoueurs : List<ImageView>
    private var stage = stage

    //ligne principals
    val ligneHaut = HBox()
    val ligneMilieu = HBox()
    val ligneBas = HBox()


    //ligne haut
    //info jeu
    val salon = Label("ID du salon : ${id}")
    val cle = Label("Clé du salon : ${key}")
    //joueur3
    val joueur3 = HBox()
    val domino3 : ImageView
    val score3 = Label("0")

    //ligne milieu
    override val desChoisi = VBox()
    override val pouleCommune = FlowPane()
    override val desActif = VBox()

    //ligne bas
    //joueur2
    val joueur2 = HBox()
    val domino2 : ImageView
    val score2 = Label("0")
    //lancer des
    override val lanceDes = Button("Lancer le(s) dé(s)")
    //joueur1
    val joueur1 = HBox()
    val domino1 : ImageView
    val score1 = Label("0")
    init {
        domino1= ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/EmptyPicko.png")))
        domino2= ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/EmptyPicko.png")))
        domino3= ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/EmptyPicko.png")))

        domino1.fitWidth = 70.0
        domino1.fitHeight = 140.0
        domino2.fitWidth = 70.0
        domino2.fitHeight = 140.0
        domino3.fitWidth = 70.0
        domino3.fitHeight = 140.0

        listeDominoJoueurs = listOf(domino1,domino2,domino3)

        //init domino poule commune
        val simulatedDomino = MutableList(16) { index -> index + 21 }
        updatePouleCommune(simulatedDomino)

        //ajout items
        this.children.addAll(ligneHaut,ligneMilieu,ligneBas)

        joueur3.children.addAll(domino3,score3)
        joueur2.children.addAll(domino2,score2)
        joueur1.children.addAll(domino1,score1)

        ligneHaut.children.addAll(salon,joueur3,cle)

        ligneMilieu.children.addAll(desChoisi,pouleCommune,desActif)

        ligneBas.children.addAll(joueur2,lanceDes,joueur1)
        //style
        this.styleClass.addAll("vue-jeu")
        ligneHaut.styleClass.addAll("ligneHaut")
        ligneMilieu.styleClass.addAll("ligneMilieu")
        ligneBas.styleClass.addAll("ligneBas")
        salon.styleClass.addAll("salon-3j","grosTexte")
        cle.styleClass.addAll("cle-3j","grosTexte")
        joueur3.styleClass.addAll("joueur3","joueur")
        domino3.styleClass.addAll("domino3","domino")
        score3.styleClass.addAll("score3","score","grosTexte")
        desChoisi.styleClass.addAll("desChoisi","tapisMilieu")
        pouleCommune.styleClass.addAll("pouleCommune","tapisMilieu")
        desActif.styleClass.addAll("desActif","tapisMilieu")
        joueur2.styleClass.addAll("joueur2","joueur")
        domino2.styleClass.addAll("domino2","domino")
        score2.styleClass.addAll("score2","score","grosTexte")
        lanceDes.styleClass.addAll("lanceDes","grosTexte")
        joueur1.styleClass.addAll("joueur1","joueur")
        domino1.styleClass.addAll("domino1","domino")
        score1.styleClass.addAll("score1","score","grosTexte")
    }

    override fun updatePouleCommune(listDomino: List<Int>) {
        pouleCommune.children.removeAll(pouleCommune.children)
        for (i in listDomino) {
            val picko = ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/$i.png")))
            picko.userData=i
            picko.fitWidth = 80.0
            picko.fitHeight = 160.0
            picko.opacity=0.3
            pouleCommune.children.add(picko)
        }
    }

    override fun updatePouleCommune(listDomino: List<Int>,modele: Jeu ,connect: Connector ) {
        println(listDomino)
        pouleCommune.children.removeAll(pouleCommune.children)
        for (i in listDomino) {
            val picko = ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/$i.png")))
            picko.userData=i
            picko.fitWidth = 80.0
            picko.fitHeight = 160.0
            picko.opacity=0.3
            pouleCommune.children.add(picko)
            fixePickos(pouleCommune,ControleurPrendrePickomino(this,modele, connect, stage),modele,connect)
        }
    }

    override fun updateDice(listDe: List<DICE>, target: VBox) {
        var cpt = 0
        target.children.removeAll(target.children)

        for (i in listDe) {
            val de = ImageView(Image(FileInputStream("src/main/resources/GameAssets/$theme/Dice/$i.png")))
            de.userData=i.name
            de.fitWidth = 60.0
            de.fitHeight = 60.0
            target.children.add(de)
            cpt++
        }
    }

    override fun updateDominoJoueurs(listDomino: List<Int>, modele: Jeu,connect: Connector) {
        domino1.userData=listDomino[0]
        domino2.userData=listDomino[1]
        domino3.userData=listDomino[2]

        var picko : Image
        var listPickos : MutableList<Image> = mutableListOf()
        for (i in listDomino.indices){
            if (listDomino[i]==0){
                picko = Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/EmptyPicko.png"))
            }else{
                picko = Image(FileInputStream("src/main/resources/GameAssets/$theme/Pickomino/${listDomino[i]}.png"))
            }
            listPickos.add(picko)
        }

        domino1.image=listPickos[0]
        domino2.image=listPickos[1]
        domino3.image=listPickos[2]

        fixePickos(domino1,ControleurPrendrePickomino(this,modele, connect, stage),modele,connect)
        fixePickos(domino2,ControleurPrendrePickomino(this,modele, connect, stage),modele,connect)
        fixePickos(domino3,ControleurPrendrePickomino(this,modele, connect, stage),modele,connect)
    }

    override fun updateScoresJoueurs(listeScores : List<Int>){
        score1.text="Score : ${listeScores[0]}"
        score2.text="Score : ${listeScores[1]}"
        score3.text="Score : ${listeScores[2]}"
    }

    override fun fixeBouton(bouton : Button,ecouteur : EventHandler<ActionEvent>){
        bouton.onAction=ecouteur
    }

    override fun fixeDes(box : VBox,ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector){
        box.children.forEach{
            it.onMouseClicked=ControleurChoisirDes(this,modele,connect, stage)
        }
    }

    override fun fixePickos(box : FlowPane,ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector){
        box.children.forEach{
            it.onMouseClicked= ControleurPrendrePickomino(this,modele,connect, stage)
        }
    }

    override fun fixePickos(el : ImageView,ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector){
        el.onMouseClicked=ControleurPrendrePickomino(this,modele,connect, stage)
    }
}