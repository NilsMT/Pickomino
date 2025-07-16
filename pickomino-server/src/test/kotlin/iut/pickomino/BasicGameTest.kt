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


class BasicGameTest {

    @Test
    fun testGame() = testApplication {
        val response = client.get("/game")
        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(response.bodyAsText().contains("[{\"players\":2,\"id\":42,\"key\":"))
    }

    @Test
    fun testGameEmpty() = testApplication {
        val response = client.get("/game/")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @ParameterizedTest
    @ValueSource(ints = [42, 43])
    fun testGameIdOknoKey(id : Int) = testApplication {
        val response = client.get("/game/$id")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @ParameterizedTest
    @ValueSource(ints = [10, 0, 1, 41, 44, 9999999])
    fun testGameBadId(id : Int) = testApplication {
        val response = client.get("/game/$id")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }

    @Test
    fun testGameUno() = testApplication {
        val response = client.get("/game/uno")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }



}
