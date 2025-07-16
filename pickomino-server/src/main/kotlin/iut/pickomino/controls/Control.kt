package iut.pickomino.controls

import io.ktor.server.application.*

/**
 * Abstract class to define a control
 *
 * @constructor creates a Control
 *
 * @author Arnaud Lanoix Brauer
 */
abstract class Control {

    /**
     * Action to do
     * @param call the application call
     */
    abstract suspend fun action(call: ApplicationCall)

    companion object {
        fun jsonError(msg: String) = mapOf("error" to msg)
    }
}