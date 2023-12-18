import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.BadStepException
import iut.info1.pickomino.exceptions.IncorrectKeyException
import iut.info1.pickomino.exceptions.UnknownIdException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestFinalScore{

    val connect = Connector.factory("172.26.82.76", "8080", true)
    val game = connect.newGame(2)
    val id = game.first
    val key = game .second
    val listDice = listOf<DICE>(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm)
    val dice = DICE.worm


    @Test
    fun CT1() {
        org.junit.jupiter.api.assertThrows<UnknownIdException> {
            connect.finalScore(-1, key)
        }
    }

    @Test
    fun CT2() {
        org.junit.jupiter.api.assertThrows<IncorrectKeyException> {
            connect.finalScore(id, -1)
        }
    }

    @Test
    fun CT3() {
        connect.rollDices(id, key)
        org.junit.jupiter.api.assertThrows<BadStepException> {
            connect.finalScore(id, key)
        }
    }

    @Test
    fun CT4(){

        for (i in 36 downTo 21) {

            connect.choiceDices(id, key, listDice)
            connect.keepDices(id, key, dice)
            connect.takePickomino(id, key, i)
        }

        assertEquals(listOf<Int>(20, 20), (connect.finalScore(id, key)))
    }
}