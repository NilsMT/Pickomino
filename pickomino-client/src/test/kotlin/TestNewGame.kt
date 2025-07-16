import iut.info1.pickomino.Connector
import iut.info1.pickomino.data.DICE
import iut.info1.pickomino.exceptions.UnknownIdException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TestNewGame {

    val connect = Connector.factory("172.26.82.76", "8080", true)

    @Test
    fun CT1() {
        assertEquals(Pair(-1, -1), connect.newGame(1))
    }

    @Test
    fun CT2() {
        assertNotEquals(Pair(-1, -1), connect.newGame(3))
    }

    @Test
    fun CT3() {
        assertEquals(Pair(-1, -1), connect.newGame(5))
    }
}