package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import iut.pickomino.models.DICE
import iut.pickomino.models.STATUS

/**
 * Control to roll dices step in debug mode
 *  @constructor    creates a DebugRollDices
 *
 * @author Arnaud Lanoix Brauer

 */

class DebugRollDices : CheckGameById() {


    /**
     * Action to  roll dices step in debug mode
     * @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        super.action(call)
        if (gameByIdOk()) {
            if (gameById.current.status == STATUS.ROLL_DICE
                || gameById.current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO
            ) {
                val dices: List<DICE> = call.receive()
                call.respond(gameById.rollDices(dices))
                //call.respond(HttpStatusCode.NotAcceptable, jsonError("bad debug dices format"))
            }
            call.respond(HttpStatusCode.MethodNotAllowed, jsonError("No roll dice step"))
        }
    }
}