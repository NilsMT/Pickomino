package iut.pickomino.models

/**
 * class state represents the part of a game that change at each step  of the game
 *
 * @author Arnaud Lanoix Brauer
 *
 * @param  player the player who is playing
 * @property status the current status of the game
 * @property rolls the dices that have been rolled
 * @keptDices the dices that have been kept
 * @property total the total of the kept dice's values
 * @property possiblePickominos the pickominos that can be taken
 */
@kotlinx.serialization.Serializable
class State(var player: Int) {

    var status: STATUS = STATUS.ROLL_DICE
    var rolls: List<DICE> = listOf()
    var keptDices: MutableList<DICE> = mutableListOf()
    var total = 0
    var possiblePickominos: List<Pickomino> = listOf()

    fun next(players: Int): State {
        var nextPlayer = player + 1
        if (nextPlayer >= players)
            nextPlayer = 0
        return State(nextPlayer)
    }

}