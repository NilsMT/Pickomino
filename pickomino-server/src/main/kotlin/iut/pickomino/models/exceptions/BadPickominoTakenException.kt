package iut.pickomino.models.exceptions

/**
 * Exception thrown when the pickomino that wille be taken cannot be taken
 * @param msg the message of the exception
 * @constructor creates an BadPickominoTakenException
 *
 * @author Arnaud Lanoix Brauer
 */
class BadPickominoTakenException(msg: String) : Exception(msg)
