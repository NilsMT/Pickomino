package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.STATUS
import iut.pickomino.models.exceptions.BadPickominoTakenException

/**
 * Control to take a pickomino
 * @constructor creates a TakePickomino
 *
 * @author Arnaud Lanoix Brauer

 */
class TakePickomino : CheckGameById() {

    /**
     * Action to do
     *  @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        super.action(call)
        if (gameByIdOk()) {
            val picko = call.parameters["picko"]
            if (picko == null)
                call.respondText("missing pickomino", status = HttpStatusCode.BadRequest)
            else {
                try {
                    val pickomino: Int = picko.toInt()
                    if (gameById.current.status == STATUS.TAKE_PICKOMINO
                        || gameById.current.status == STATUS.ROLL_DICE_OR_TAKE_PICKOMINO
                    ) {
                        call.respond(mapOf("pickomino taken" to gameById.takePickomino(pickomino)))
                    } else
                        call.respond(HttpStatusCode.MethodNotAllowed, jsonError("not take pickomino step"))
                } catch (ex: java.lang.NumberFormatException) {
                    call.respond(HttpStatusCode.NotAcceptable, jsonError("bad pickomino format"))
                } catch (ex: BadPickominoTakenException) {
                    call.respond(HttpStatusCode.Conflict, jsonError("bad pickomino takens"))
                }
            }
        }
    }
}