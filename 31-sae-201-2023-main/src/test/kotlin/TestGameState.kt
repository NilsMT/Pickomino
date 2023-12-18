import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.IncorrectKeyException
import iut.info1.pickomino.exceptions.UnknownIdException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestGameState {

    val connect = Connector.factory("172.26.82.76", "8080", true)
    val game = connect.newGame(2)
    val id = game.first
    val key = game .second

    @Test
    fun CT1() {
        org.junit.jupiter.api.assertThrows<UnknownIdException> {
            connect.gameState(-1, key)
        }
    }

    @Test
    fun CT2() {
        org.junit.jupiter.api.assertThrows<IncorrectKeyException> {
            connect.gameState(id, -1)
        }
    }

    @Test
    fun CT3() {
        assertEquals(true, (connect.gameState(id, key) is iut.info1.pickomino.data.Game))
    }
}