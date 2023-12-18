import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.BadStepException
import iut.info1.pickomino.exceptions.IncorrectKeyException
import iut.info1.pickomino.exceptions.UnknownIdException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestChoiceDice {

    val connect = Connector.factory("172.26.82.76", "8080", true)
    val game = connect.newGame(2)
    val id = game.first
    val key = game .second
    val listDice = listOf<DICE>(DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm, DICE.worm)

    @Test
    fun CT1() {
        org.junit.jupiter.api.assertThrows<UnknownIdException> {
            connect.choiceDices(-1, key, listDice)
        }
    }

    @Test
    fun CT2() {
        org.junit.jupiter.api.assertThrows<IncorrectKeyException> {
            connect.choiceDices(id, -1, listDice)
        }
    }

    @Test
    fun CT3() {
        connect.rollDices(id, key)
        org.junit.jupiter.api.assertThrows<BadStepException> {
            connect.choiceDices(id, key, listDice)
        }
    }

    @Test
    fun CT4(){
        assertEquals(listDice, connect.choiceDices(id, key, listDice))
    }
}