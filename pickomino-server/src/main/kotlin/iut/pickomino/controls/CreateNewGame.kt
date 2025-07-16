package iut.pickomino.controls

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import iut.pickomino.models.Game
import iut.pickomino.models.Game.Companion.ACTIVE_GAMES
/**
 * Control to create a new game
 * @constructor creates a CreateNewGame
 *
 * @author Arnaud Lanoix Brauer
 */
class CreateNewGame : Control() {

    /**
     * create a new game
     * @param call the application call
     */
    override suspend fun action(call: ApplicationCall) {
        val players = call.parameters["players"]
        if (players == null) {
            call.respond(HttpStatusCode.BadRequest, jsonError("missing players"))
        } else {
            try {
                val nbi = players.toInt()
                if (nbi < 2 || nbi > 4)
                    call.respond(HttpStatusCode.Forbidden, jsonError("bad players value"))
                else {
                    val nouvellePartie = Game(nbi)
                    ACTIVE_GAMES.add(nouvellePartie)
                    call.respond(HttpStatusCode.Created, mapOf("id" to nouvellePartie.id, "key" to nouvellePartie.key))
                }
            } catch (ex: java.lang.NumberFormatException) {
                call.respond(HttpStatusCode.NotAcceptable, jsonError("bad players format"))
            }
        }
    }
}