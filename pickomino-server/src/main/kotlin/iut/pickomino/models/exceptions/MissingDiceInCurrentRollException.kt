package iut.pickomino.models.exceptions

/**
 * Exception thrown when a dice is missing in the current roll
 * @param msg the message of the exception
 * @constructor creates a MissingDiceInCurrentRollException
 *
 * @author Arnaud Lanoix Brauer
 */
class MissingDiceInCurrentRollException(msg: String) : Exception(msg)