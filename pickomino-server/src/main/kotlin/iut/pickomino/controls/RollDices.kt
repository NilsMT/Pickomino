package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.STATUS

/**
 * Control to roll dices step
 * @constructor creates a RollDices
 *
 * @author Arnaud Lanoix
 */
class RollDices : CheckGameById() {

    override suspend fun action(call: ApplicationCall) {
        super.action(call)
        if (gameByIdOk()) {
            if (gameById.current.status == STATUS.ROLL_DICE
                || gameById.current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO
            ) {
                call.respond(gameById.rollDices(null))
            }
            call.respond(HttpStatusCode.MethodNotAllowed, jsonError("No roll dice step"))
        }
    }
}

