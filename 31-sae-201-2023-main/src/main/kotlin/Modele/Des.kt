package Modele

import iut.info1.pickomino.data.DICE

class Des(id : Int, face : DICE = DICE.worm, theme : String = "Light") {
    var id : Int
    var image : String
    var face : DICE
        private set
    var valeur : Int

    var selected = false
        private set


    init {
        this.face=face
        valeur=face.ordinal
        this.id=id
        image="src/main/resources/GameAssets/$theme/Dice/${valeur+1}.png"
    }

    fun assignDe(face: DICE){
        this.face = face

        valeur = face.ordinal
    }

    fun select(a : Boolean = true) {
        selected = a
    }
}