package Vue

import Modele.Jeu
import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.MouseEvent
import javafx.scene.layout.FlowPane
import javafx.scene.layout.VBox

interface Vue_jeu {

    val desActif : VBox
    val lanceDes : Button
    val desChoisi : VBox
    val pouleCommune : FlowPane

    var listeDominoJoueurs : List<ImageView>

    fun updatePouleCommune(listDomino:List<Int>)
    fun updatePouleCommune(listDomino:List<Int>,modele: Jeu,connect: Connector)

    fun updateDice(listDe:List<DICE>,target : VBox)

    fun updateDominoJoueurs(listDomino:List<Int>,modele: Jeu,connect: Connector)

    fun updateScoresJoueurs(listeScores : List<Int>)

    fun fixeBouton(bouton : Button, ecouteur : EventHandler<ActionEvent>)

    fun fixeDes(box : VBox, ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector)

    fun fixePickos(box : FlowPane,ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector)
    fun fixePickos(el : ImageView, ecouteur: EventHandler<MouseEvent>, modele : Jeu, connect : Connector)
}