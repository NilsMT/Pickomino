package iut.pickomino

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CreateGameTest {

    @Test
    fun testNewGameEmpty() = testApplication {
        val response = client.get("/game/new")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 0, 1, 5, 41, 44, 9999999])
    fun testNewGameBadNbPlayers(nb : Int) = testApplication {
        val response = client.get("/game/new/$nb")
        assertEquals(HttpStatusCode.Forbidden, response.status)
    }

    @Test
    fun testNewGameBadNumber() = testApplication {
        val response = client.get("/game/new/number")
        assertEquals(HttpStatusCode.NotAcceptable, response.status)
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4])
    fun testNewGameNbPlayersOk(nb :Int) = testApplication {
        val response = client.get("/game/new/$nb")
        assertEquals(HttpStatusCode.Created, response.status)
        assertTrue(response.bodyAsText().contains("{\"id\":"))
    }




}
