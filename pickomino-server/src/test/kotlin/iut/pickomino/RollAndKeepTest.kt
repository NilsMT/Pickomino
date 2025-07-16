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

class RollAndKeepTest {



    @Test
    fun testRollDices() = testApplication {
         var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))

        response = client.get("/game/$id/$key")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/roll/$id/$key")
        //assertEquals(HttpStatusCode.MethodNotAllowed, response.status)
        results = response.bodyAsText()
        println(results)
        assertTrue(results.contains("[\""))
    }

    @Test
    fun testRollDebugDices() = testApplication {
        var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))

        response = client.get("/game/$id/$key")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        //assertEquals(HttpStatusCode.OK, response.status)
        results = response.bodyAsText()
        println(results)
        assertEquals("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]", results)
    }


    @ParameterizedTest
    @ValueSource(strings = ["worm","d5","d4"])
    fun testRollDebugDicesAndKeepdicesOK(dice : String) = testApplication {
        var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))
        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/$dice")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @ParameterizedTest
    @ValueSource(strings = ["d1","d2","d3"])
    fun testRollDebugDicesAndKeepBadDices(dice : String) = testApplication {
        var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))
        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/$dice")
        assertEquals(HttpStatusCode.NoContent, response.status)
    }

    @ParameterizedTest
    @ValueSource(strings = ["D4","WORM","5", "Worm",])
    fun testRollDebugDicesAndKeepBadFormatDices(dice : String) = testApplication {
        var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))
        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/$dice")
        assertEquals(HttpStatusCode.NotAcceptable, response.status)
    }
}
