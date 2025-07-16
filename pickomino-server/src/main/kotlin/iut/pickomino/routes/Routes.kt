package iut.pickomino.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.pickomino.controls.*

/**
 * Configure the routing of the application
 * @receiver the application
 *
 * @author Arnaud Lanoix Brauer
 */
fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, are you ready to play @ Pickomino !")
        }
        route("/game") {
            get() {
                GiveCurrentGames().action(call)
                //Picko.donneParties(call)
            }
            get("/{id}/{key}") {
                GiveGameById().action(call)
            }
            get("/new/{players}") {
                CreateNewGame().action(call)
            }
        }
        route("/roll") {
            get("/{id}/{key}") {
                RollDices().action(call)
            }
            post("/debug/{id}/{key}") {
                DebugRollDices().action(call)
            }
        }
        get("/keep/{id}/{key}/{dice}") {
                KeepDices().action(call)
        }
        get("/take/{id}/{key}/{picko}") {
                TakePickomino().action(call)
        }
    }
}