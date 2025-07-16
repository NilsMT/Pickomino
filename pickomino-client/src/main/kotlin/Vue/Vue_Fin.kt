package Vue
import iut.info1.pickomino.Connector
import javafx.scene.control.Label
import javafx.scene.effect.DropShadow
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.*
import javafx.scene.paint.Color
import javafx.scene.text.Text
import javafx.stage.Stage
import java.io.FileInputStream

class Vue_Fin(score : List<Int>,stacktop: List<Int>):VBox() {
    val classement = VBox()
    val stacktop = stacktop
    val score = score
    val im = Image(FileInputStream("src/main/resources/GameAssets/Logo.png"),960.0, 256.0, true, true)
    val titre = ImageView(im)
    val titre_container = VBox()
    init {
        this.children.addAll(titre_container,classement)
        titre_container.children.addAll(titre)

        titre_container.styleClass.add("titre_container")
        titre.styleClass.add("titre")
        classement.styleClass.add("section-fin")
        this.styleClass.addAll("vue-fin")
        createClassement()

    }
    fun createClassement() {
        val colorList = mutableListOf("#69C5FF","#F73434","#58D938","#FAE06B")
        val index = listOf(1, 2, 3, 4)
        //tri
        val sortedIndices = index.indices.sortedWith(compareByDescending<Int> { score[it] }.thenByDescending { stacktop[it] })

        val sortedScore = sortedIndices.map { score[it] }
        val sortedIndex = sortedIndices.map { index[it] }
        val sortedColorList = sortedIndices.map { colorList[it] }
        val sortedStacktop = sortedIndices.map { stacktop[it] }

        //clear classement
        for (i in classement.children) {
            classement.children.remove(i)
        }

        //ajout
        val listClassement = mutableListOf<Label>()
        //creation labels
        for (i in score.indices) {
            val carrecouleur = Label("Joueur ${sortedIndex[i]} : ${sortedScore[i]}")

            carrecouleur.style = "-fx-border-color: black;-fx-border-width: 1px;-fx-border-radius:17;-fx-font-weight: bold;-fx-font-size: 40.0;-fx-background-color: ${sortedColorList[i]};-fx-alignment: center;-fx-background-radius: 20.0; -fx-text-fill: #FFFFFF;;-fx-padding: 20.0;"
            listClassement.add(carrecouleur)
        }
        //ajout
        val textclassement = Text("Joueur ${sortedIndex[0]} gagne la partie !")
        textclassement.style = "-fx-font-weight: bold;-fx-font-size: 80.0;-fx-stroke: white; -fx-stroke-width: 4;-fx-fill: linear-gradient(to top, rgba(255,165,0,1), rgba(255,218,0,1));"
        classement.children.add(textclassement)
        for (i in listClassement) {
            classement.children.add(i)
        }
    }
}