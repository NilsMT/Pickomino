package iut.pickomino.models

import kotlinx.serialization.Serializable

/**
 * Pickomino class
 * @param value value of the pickomino
 * @param nbWorms number of worms on the pickomino
 * @constructor creates a pickomino
 *
 * @author Arnaud Lanoix Brauer
 */
@Serializable
data class Pickomino(val value: Int, val nbWorms: Int) {

    companion object {

        /**
         * Initialize the pickominos of a game
         * @return a list of pickominos
         */
        fun initPickominos() = mutableListOf(
            Pickomino(21, 1),
            Pickomino(22, 1),
            Pickomino(23, 1),
            Pickomino(24, 1),
            Pickomino(25, 2),
            Pickomino(26, 2),
            Pickomino(27, 2),
            Pickomino(28, 2),
            Pickomino(29, 3),
            Pickomino(30, 3),
            Pickomino(31, 3),
            Pickomino(32, 3),
            Pickomino(33, 4),
            Pickomino(34, 4),
            Pickomino(35, 4),
            Pickomino(36, 4),
        )
    }
}