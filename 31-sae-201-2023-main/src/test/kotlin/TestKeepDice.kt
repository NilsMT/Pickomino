import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TestKeepDice {

    val connect = Connector.factory("172.26.82.76", "8080", true)
    val game = connect.newGame(2)
    val id = game.first
    val key = game .second
    val dice = DICE.worm
    val listDice = listOf<DICE>(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm)

    @Test
    fun CT1() {
        assertThrows<UnknownIdException> {
            connect.keepDices(-1, key, dice)
        }
    }

    @Test
    fun CT2() {
        assertThrows<IncorrectKeyException> {
            connect.keepDices(id, -1, dice)
        }
    }

    @Test
    fun CT3() {
        assertThrows<BadStepException> {
            connect.keepDices(id, key, dice)
        }
    }

    @Test
    fun CT4() {
        assertThrows<DiceNotInRollException> {
            connect.choiceDices(id, key, listDice)
            connect.keepDices(id, key, DICE.d1)
        }
    }

    @Test
    fun CT5() {
        assertThrows<DiceAlreadyKeptException> {
            connect.choiceDices(id, key, listOf<DICE>(DICE.worm, DICE.d2, DICE.d3, DICE.worm, DICE.d5, DICE.d2, DICE.d2, DICE.d2))
            connect.keepDices(id, key, DICE.worm)
            connect.choiceDices(id, key, listOf<DICE>(DICE.worm, DICE.d3, DICE.d5, DICE.worm, DICE.d1, DICE.d4))
            connect.keepDices(id, key, DICE.worm)
        }
    }

    @Test
    fun CT6() {
        connect.choiceDices(id, key , listOf<DICE>(DICE.worm, DICE.d1))
        assertEquals(true, connect.keepDices(id, key, dice))
    }
}