package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.DICE
import iut.pickomino.models.STATUS
import iut.pickomino.models.exceptions.AlreadyKeptDiceException
import iut.pickomino.models.exceptions.MissingDiceInCurrentRollException

/**
 * Control to keep dices
 * @constructor creates a KeepDices
 *
 * @author Arnaud Lanoix Brauer
 */
class KeepDices : CheckGameById() {

    /**
     * Action to keep dices
     *  @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        super.action(call)
        if (gameByIdOk()) {
            val keptDice = call.parameters["dice"]
            if (keptDice == null)
                call.respondText("missing dice", status = HttpStatusCode.BadRequest)
            else {
                try {
                    val dice = DICE.valueOf(keptDice) // IllegalArgumentException
                    if (gameById.current.status == STATUS.KEEP_DICE) {
                        val total = gameById.keepDices(dice) // DéNonPrésentException || DéDéjàGardéException
                        call.respond(mapOf("total" to total))
                    } else
                        call.respond(HttpStatusCode.MethodNotAllowed, jsonError("not keep dice step"))
                } catch (ex: java.lang.IllegalArgumentException) {
                    call.respond(HttpStatusCode.NotAcceptable, jsonError("bad dice format"))
                } catch (ex: MissingDiceInCurrentRollException) {
                    call.respond(HttpStatusCode.NoContent, jsonError("dice not into roll"))
                } catch (ex: AlreadyKeptDiceException) {
                    call.respond(HttpStatusCode.Conflict, jsonError("dice already kept"))
                }
            }
        }
    }
}