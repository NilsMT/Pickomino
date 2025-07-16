package Controleur

import Modele.Jeu
import Modele.Menu
import MusicPlayer
import Vue.Vue_2j
import Vue.Vue_3j
import Vue.Vue_4j
import Vue.Vue_menu
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage

class ControleurJoinGame(vue:Vue_menu,stage: Stage,modmenu : Menu,mu : MusicPlayer):EventHandler<ActionEvent> {
    var vue = vue
    val stage = stage
    val mu = mu
    val modmenu = modmenu
    val connect = modmenu.connector
    override fun handle(event: ActionEvent?) {
        mu.stopMusic()
        modmenu.nbjoueur=connect.gameState(modmenu.id.value,modmenu.key.value).score().size
        if (modmenu.isLocal==false) {
            stage.hide()
            //set view
            if (modmenu.nbjoueur==4) {
                val v = Vue_4j(vue.theme_value,modmenu.id.value,modmenu.key.value, stage)
                val modjeu = Jeu(modmenu,v)
                stage.scene.root=v

            } else if (modmenu.nbjoueur==3) {
                val v = Vue_3j(vue.theme_value,modmenu.id.value,modmenu.key.value, stage)
                val modjeu = Jeu(modmenu,v)
                stage.scene.root=v
            } else if (modmenu.nbjoueur==2) {
                val v = Vue_2j(vue.theme_value,modmenu.id.value,modmenu.key.value, stage)
                val modjeu = Jeu(modmenu,v)
                stage.scene.root=v
            }
            stage.isMaximized=true
            stage.show()
        }
    }
}