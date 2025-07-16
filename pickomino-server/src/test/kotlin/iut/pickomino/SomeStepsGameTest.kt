package iut.pickomino

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class SomeStepsGameTest {

    @Test
    fun testRollDebugDicesAndKeepdicesOK() = testApplication {
        var response = client.get("/game/new/2")
        assertEquals(HttpStatusCode.Created, response.status)
        var results = response.bodyAsText()
        val id = results.substringAfter("id\":").substringBefore(",").toInt()
        val key = results.substringAfter("key\":").substringBefore("}").toInt()
        assertTrue(results.contains("\"id\":$id"))
        assertTrue(results.contains("\"key\":$key"))

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))


        //joueur 0 prend picko 28

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\",\"d1\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/d5")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d4\",\"d4\",\"d3\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.Conflict, response.status)

        response = client.get("/keep/$id/$key/d4")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE_OR_TAKE_PICKOMINO\""))


        response = client.get("/take/$id/$key/28")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":1,\"status\":\"ROLL_DICE\""))


        // # joueur 1 prend picko 28 chez joueur 0

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\",\"d1\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/d5")
        assertEquals(HttpStatusCode.OK, response.status)

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d5\",\"d4\",\"worm\",\"d5\",\"d4\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.OK, response.status)

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"d4\",\"d4\",\"d3\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.Conflict, response.status)
        response = client.get("/keep/$id/$key/d4")
        assertEquals(HttpStatusCode.OK, response.status)
        response = client.get("/take/$id/$key/28")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))


        // # joueur 0 prend picko 36

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.OK, response.status)
        response = client.get("/take/$id/$key/36")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":1,\"status\":\"ROLL_DICE\""))


        //# joueur 1 prend picko 25

client.post {
    url("/roll/debug/$id/$key")
    contentType(ContentType.Application.Json)
    setBody("[\"d5\",\"d5\",\"d5\",\"d5\",\"d1\",\"d1\",\"d1\",\"d1\"]")
}
response = client.get("/keep/$id/$key/d5")
assertEquals(HttpStatusCode.OK, response.status)

client.post {
    url("/roll/debug/$id/$key")
    contentType(ContentType.Application.Json)
    setBody("[\"d1\",\"worm\",\"d1\",\"d1\"]")
}
response = client.get("/keep/$id/$key/worm")
assertEquals(HttpStatusCode.OK, response.status)
        response = client.get("/take/$id/$key/25")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))


        // joueur 0 prend picko 27 (en scorant 28)

client.post {
url("/roll/debug/$id/$key")
contentType(ContentType.Application.Json)
setBody("[\"worm\",\"worm\",\"worm\",\"worm\",\"d1\",\"d1\",\"d1\",\"d1\"]")
}
response = client.get("/keep/$id/$key/worm")
assertEquals(HttpStatusCode.OK, response.status)

client.post {
url("/roll/debug/$id/$key")
contentType(ContentType.Application.Json)
setBody("[\"d4\",\"d4\",\"d1\",\"d1\"]")
}
response = client.get("/keep/$id/$key/d4")
assertEquals(HttpStatusCode.OK, response.status)
        response = client.get("/take/$id/$key/27")
        assertEquals(HttpStatusCode.OK, response.status)

        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":1,\"status\":\"ROLL_DICE\""))

        // joueur 1 prend picko 35

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\",\"worm\"]")
        }
        response = client.get("/keep/$id/$key/worm")
        assertEquals(HttpStatusCode.OK, response.status)
        response = client.get("/take/$id/$key/35")
        assertEquals(HttpStatusCode.OK, response.status)


        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":0,\"status\":\"ROLL_DICE\""))


        // joueur 0 perd

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"d1\",\"d1\",\"d1\",\"d1\",\"d1\",\"d1\",\"d1\",\"d2\"]")
        }
        response = client.get("/keep/$id/$key/d1")
        assertEquals(HttpStatusCode.OK, response.status)

        client.post {
            url("/roll/debug/$id/$key")
            contentType(ContentType.Application.Json)
            setBody("[\"d3\"]")
        }
        response = client.get("/keep/$id/$key/d3")
        assertEquals(HttpStatusCode.OK, response.status)


        response = client.get("/game/$id/$key")
        assertTrue(response.bodyAsText().contains("\"current\":{\"player\":1,\"status\":\"ROLL_DICE\""))

    }

}
