package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.Game.Companion.ACTIVE_GAMES

/**
 * Control to give the current games
 * @constructor creates a GiveCurrentGames
 *
 * @author Arnaud Lanoix Brauer
 *
 */
class GiveCurrentGames : Control() {

    /**
     * Action to do
     * @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        if (ACTIVE_GAMES.isNotEmpty()) {
            println("******")
            call.respond(ACTIVE_GAMES)
        } else {
            call.respond(HttpStatusCode.NotFound, jsonError("no current games"))
        }
    }
}