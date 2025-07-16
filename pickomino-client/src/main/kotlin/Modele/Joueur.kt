package Modele

import iut.info1.pickomino.data.Pickomino

class Joueur(id : Int) {
    var id : Int
    private var topPickomino = Pickomino(0,0)
    private var score = 0
//    private var blacklistJoueur = mutableListOf<>()
    init {
        this.id=id
    }

    fun prendrePickomino(pick : Pickomino){
        topPickomino=pick
        score+=pick.value
    }

    fun retirerPickomino(){
        score-=topPickomino.value
        topPickomino=Pickomino(0,0)
    }
}