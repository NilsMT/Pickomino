package iut.pickomino.models

import iut.pickomino.models.exceptions.AlreadyKeptDiceException
import iut.pickomino.models.exceptions.BadPickominoTakenException
import iut.pickomino.models.exceptions.MissingDiceInCurrentRollException
import kotlinx.serialization.Serializable
import kotlin.random.Random

/**
 * Game class representing a Pickomino's game
 * @param players number of players
 * @constructor creates a game
 * @property id id of the game
 * @property key key of the game
 * @property accessiblePickominos list of pickominos accessible to the players
 * @property pickominoStacks map of pickominos stacks of each player
 * @property current the current state of the game
 *
 * @author Arnaud Lanoix Brauer
 *
 */
@Serializable
class Game(private val players: Int) {
    val id = ID++
    val key = Random.nextInt(100, 300)
    private val accessiblePickominos: MutableList<Pickomino> = Pickomino.initPickominos()
    private val pickominoStacks: MutableMap<Int, MutableList<Pickomino>> = mutableMapOf()
    var current = State(0)

    init {
        for (i in 0 until players)
            pickominoStacks[i] = mutableListOf()
    }

    /**
     * action to roll the dices
     * @param dices list of dices to roll
     * @return list of dices rolled
     */
    fun rollDices(dices: List<DICE>?): List<DICE> {
        val nb_dices = 8 - current.keptDices.size
        lateinit var newRolls: List<DICE>
        newRolls = if (dices == null)
            DICE.roll(nb_dices)
        else
            dices.take(nb_dices)
        println(newRolls)
        if (newRolls.minus(current.keptDices).isEmpty()) {
            lostRound()
        } else {
            current.status = STATUS.KEEP_DICE
            current.rolls = newRolls
        }
        return newRolls
    }

    /**
     * indicates that the player has lost the round and possibly the end of the game
     */
    private fun lostRound() {
        if (pickominoStacks[current.player]!!.isNotEmpty()) {
            val restoredPicko = pickominoStacks[current.player]!!.removeLast()
            accessiblePickominos.add(restoredPicko)
            accessiblePickominos.sortBy { it.value }
            if (restoredPicko != accessiblePickominos.last())
                accessiblePickominos.removeLast()

        } else // pas de pickomino à rendre
            accessiblePickominos.removeLast()
        current = current.next(players)
        if (accessiblePickominos.isEmpty())
            current.status = STATUS.GAME_FINISHED
    }

    /**
     * action to keep a dice
     *  @param keptDice the dice to keep
     *  @return the total of the kept dices
     */
    fun keepDices(keptDice: DICE): Int {
        if (current.keptDices.contains(keptDice)) {
            throw AlreadyKeptDiceException("dice already in kept dices")
        }
        if (!current.rolls.contains(keptDice)) {
            throw MissingDiceInCurrentRollException("dice not in current roll")
        }
        val newKeptDices = current.rolls.filter { it == keptDice }
        current.keptDices += newKeptDices
        current.total += newKeptDices.sumOf { it.value }
        current.rolls = listOf()
        current.possiblePickominos = possiblePickominoToBeTaken()
        if (current.possiblePickominos.isNotEmpty()) { // il y a un domino possible à prendre
            if (current.keptDices.size < 8) // il reste des dés à lancer
                current.status = STATUS.ROLL_DICE_OR_TAKE_PICKOMINO
            else // plus de dés
                current.status = STATUS.TAKE_PICKOMINO
        } else {
            if (current.keptDices.size < 8) // il reste des dés à lancer
                current.status = STATUS.ROLL_DICE
            else {
                lostRound()
            }
        }
        return current.total
    }


    /**
     * action to take a pickomino
     * @param pickoValue the value of the pickomino to take
     * @return the pickomino taken
     */
    fun takePickomino(pickoValue: Int): Pickomino {
        val picko : Pickomino? = current.possiblePickominos.find { it.value == pickoValue }
        if (picko == null)
            throw BadPickominoTakenException("not the correct pickomino")
        else {
            if (accessiblePickominos.contains(picko))
                accessiblePickominos.remove(picko)
            else {
                for (i in 0 until players) {
                    if (i != current.player) {
                        if (pickominoStacks[i]!!.contains(picko)) {
                            pickominoStacks[i]!!.remove(picko)
                        }
                    }
                }
            }
            pickominoStacks[current.player]!!.add(picko)
            current = current.next(players)
            if (accessiblePickominos.isEmpty()) {
                current.status = STATUS.GAME_FINISHED
            }
            return picko
        }
    }

    /**
     * gives the possible pickominos to be taken
     */
// le total correspond à un domino accessible sur la table
    private fun possiblePickominoToBeTaken(): List<Pickomino> {
        val result = mutableListOf<Pickomino>()
        // Les dés gardés contiennent au moins un ver
        if (current.keptDices.contains(DICE.worm)) {
            var picko: Pickomino? = this.possiblePickominoFromStacks()
            if (picko != null)
                result += picko
            picko = possiblePickominoFromAccessiblePickos()
            if (picko != null)
                result += picko
        }
        return result
    }

    /**
     * gives the possible pickomino to be taken from the accessible pickominos
     */
    // le total correspond à un domino accessible sur la table
    // ou il y a un domino accessible sur la table avec une valeur inférieure
    private fun possiblePickominoFromAccessiblePickos(): Pickomino? {
        val selectedPickominos = accessiblePickominos.filter { it.value <= current.total }
        if (selectedPickominos.isNotEmpty()) {
            return selectedPickominos.last()
        }
        return null
    }


    /**
     * gives the possible pickomino to be taken from the stacks of pickominos of each player
     */
    // le total correspond à un domino d'un autre joueur
    private fun possiblePickominoFromStacks(): Pickomino? {
        for (i in 0 until players) {
            if (i != current.player) {
                if (pickominoStacks[i]!!.isNotEmpty()) {
                    val lastPicko = pickominoStacks[i]!!.last()
                    if (lastPicko.value == current.total)
                        return lastPicko
                }
            }
        }
        return null
    }

    companion object {
        var ID = 42
        val ACTIVE_GAMES: MutableList<Game> = mutableListOf(Game(2), Game(4))
    }
}

