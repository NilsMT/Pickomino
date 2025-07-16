package iut.pickomino.models.exceptions

/**
 * Exception thrown when a dice is already kept
 * @param msg the message of the exception
 * @constructor creates an AlreadyKeptDiceException
 *
 * @author Arnaud Lanoix Brauer
 */
class AlreadyKeptDiceException(msg: String) : Exception(msg)