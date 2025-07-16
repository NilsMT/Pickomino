package Vue
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.layout.*
import javafx.scene.control.*

import javafx.scene.control.RadioButton
import javafx.scene.control.ToggleGroup
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.FileInputStream

class Vue_menu():VBox() {
    var theme_value = "Light"
    val titre : ImageView
    val titre_container : VBox
    val partie : VBox
    val creation_menu : VBox
    val create_game : Button
    val setting_game : VBox
    val player_number_container :HBox
    val number_label : Label
    val player_number_game : ChoiceBox<Int>
    val type_game : HBox
    val radio_group :ToggleGroup
    val local_game : RadioButton
    val online_game : RadioButton
    val join_menu : VBox
    val join_game : Button
    val join_setting_game : VBox
    val container_id : HBox
    val container_key : HBox
    val label_id : Label
    val number_id : TextField
    val label_key: Label
    val number_key : TextField
    var optionspl : ObservableList<Int>
    val optionsth : ObservableList<String>
    val regle : Button
    val theme : ChoiceBox<String>
    val theme_label : Label
    val theme_container : HBox
    val plus_setting : HBox
    val plus_container : HBox

    init {
        val im = Image(FileInputStream("src/main/resources/GameAssets/Logo.png"),960.0, 256.0, true, true)
        titre = ImageView(im)
        titre_container = VBox()
        partie = VBox()
        //dans partie
        creation_menu = VBox()
        //dans creation_menu
        create_game = Button("Créer la partie")
        setting_game = VBox()
        //dans setting_game
            //dans player_number_container
        player_number_container = HBox()
        number_label = Label("Nombre de joueurs :")
        optionspl = FXCollections.observableArrayList(2,3,4)
        player_number_game = ChoiceBox(optionspl)

        type_game = HBox()
            //dans type_game
        radio_group = ToggleGroup()
        local_game = RadioButton("En local")
        online_game = RadioButton("En ligne")
        local_game.toggleGroup = radio_group
        online_game.toggleGroup = radio_group

        join_menu = VBox()
        //dans join_menu
        join_game = Button("Rejoindre la partie en ligne :")
        join_setting_game = VBox()
        //dans join_setting_game
        container_id = HBox()

            //dans id
        label_id = Label("Numéro du salon :")
        number_id = TextField()

        container_key = HBox()
            //dans key
        label_key = Label("Clé du salon :")
        number_key = TextField()

        regle = Button("Règles du jeu")
        optionsth = FXCollections.observableArrayList("Clair️","Sombre")
        theme = ChoiceBox(optionsth)
        theme_container = HBox()
        theme_label = Label("Thème des pièces :")
        plus_setting = HBox()
        plus_container = HBox()
        //ajout elements
        type_game.children.addAll(local_game,online_game)
        player_number_container.children.addAll(number_label,player_number_game)
        setting_game.children.addAll(type_game,player_number_container)
        creation_menu.children.addAll(create_game,setting_game)
        partie.children.addAll(creation_menu,join_menu,plus_setting)
        container_id.children.addAll(label_id,number_id)
        container_key.children.addAll(label_key,number_key)
        join_setting_game.children.addAll(container_id,container_key)
        join_menu.children.addAll(join_game,join_setting_game)
        titre_container.children.addAll(titre)
        theme_container.children.addAll(theme_label,theme)
        plus_container.children.addAll(theme_container,regle)
        plus_setting.children.addAll(plus_container)
        this.children.addAll(titre_container,partie)
        /////////////////////////////////////////////////////////////////////////////////////////////////
        this.styleClass.addAll("vue-menu")
        titre_container.styleClass.addAll("titre_container")
        titre.styleClass.addAll("titre")
        partie.styleClass.addAll("partie")
        creation_menu.styleClass.addAll("creation_menu","border","section")
        join_menu.styleClass.addAll("join_menu","border","section")
        setting_game.styleClass.addAll("setting_game")
        create_game.styleClass.addAll("button")
        type_game.styleClass.addAll("type_game","sous_section")
        player_number_container.styleClass.addAll("player_number_container","sous_section")
        number_label.styleClass.addAll("number_label")
        player_number_game.styleClass.addAll("player_number_game")
        local_game.styleClass.addAll("local_game")
        online_game.styleClass.addAll("online_game")
        join_game.styleClass.addAll("button")
        join_setting_game.styleClass.addAll("join_setting_game")
        container_id.styleClass.addAll("container_id","sous_section")
        container_key.styleClass.addAll("container_key","sous_section")
        label_id.styleClass.addAll("label_id")
        number_id.styleClass.addAll("number_id")
        label_key.styleClass.addAll("label_key")
        number_key.styleClass.addAll("number_key")
        plus_setting.styleClass.addAll("plus_setting","border","section")
        plus_container.styleClass.addAll("plus_container","sous_section")
        regle.styleClass.addAll("regle","button")
        theme.styleClass.addAll("theme")
        theme_container.styleClass.addAll("theme_container")
        theme_label.styleClass.addAll("theme_label")
    }
}