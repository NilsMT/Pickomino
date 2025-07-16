import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.BadPickominoChosenException
import iut.info1.pickomino.exceptions.BadStepException
import iut.info1.pickomino.exceptions.IncorrectKeyException
import iut.info1.pickomino.exceptions.UnknownIdException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestTakePicko {

    val connect = Connector.factory("172.26.82.76", "8080", true)
    val game = connect.newGame(2)
    val id = game.first
    val key = game .second
    val listDice = listOf<DICE>(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm)
    val dice = DICE.worm

    @Test
    fun CT1() {
        org.junit.jupiter.api.assertThrows<UnknownIdException> {
            connect.takePickomino(-1, key, 21)
        }
    }

    @Test
    fun CT2() {
        org.junit.jupiter.api.assertThrows<IncorrectKeyException> {
            connect.takePickomino(id, -1, 21)
        }
    }

    @Test
    fun CT3() {
        connect.rollDices(id, key)
        org.junit.jupiter.api.assertThrows<BadStepException> {
            connect.takePickomino(id, key, 21)
        }
    }

    @Test
    fun CT4() {
        connect.choiceDices(id, key, listDice)
        connect.keepDices(id, key, dice)
        org.junit.jupiter.api.assertThrows<BadPickominoChosenException> {
            connect.takePickomino(id, key, 35)
        }
    }

    @Test
    fun CT5() {
        connect.choiceDices(id, key, listDice)
        connect.keepDices(id, key, dice)
        assertEquals(true, connect.takePickomino(id, key, 36))
    }


}