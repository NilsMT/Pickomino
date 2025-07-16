package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.Game
import iut.pickomino.models.Game.Companion.ACTIVE_GAMES

/**
 * Control to check if a game exists with the given id and key
 * @property gameById the game with the given id and key
 * @constructor creates a CheckGameById
 *
 * @author Arnaud Lanoix Brauer
 */
abstract class CheckGameById : Control() {

    lateinit var gameById: Game

    fun gameByIdOk() = ::gameById.isInitialized

    /**
     * Check if a game exists with the given id and key
     * @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        val id = call.parameters["id"]
        val key = call.parameters["key"]
        if (id == null || key == null) {
            call.respond(HttpStatusCode.BadRequest, jsonError("missing id and/or key"))
        } else {
            try {
                val aGame = ACTIVE_GAMES.find { it.id == id.toInt() }
                if (aGame == null)
                    call.respond(HttpStatusCode.NotFound, jsonError("no game with id $id"))
                else if (aGame.key != key.toInt())
                    call.respond(HttpStatusCode.Unauthorized, jsonError("incorrect key"))
                else
                    gameById = aGame
            } catch (ex: java.lang.NumberFormatException) {
                call.respond(HttpStatusCode.NotAcceptable, jsonError("bad id and/or key format"))
            }
        }
    }
}