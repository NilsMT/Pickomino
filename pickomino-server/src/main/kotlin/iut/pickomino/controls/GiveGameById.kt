package iut.pickomino.controls

import io.ktor.server.application.*
import io.ktor.server.response.*

/**
 * Control to give the game by id
 *
 * @constructor creates a GiveGameById
 *
 * @author  Arnaud Lanoix Brauer
 */
class GiveGameById : CheckGameById() {

    /**
     * Action to do
     *  @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        super.action(call)
        if (gameByIdOk())
            call.respond(gameById)
    }
}