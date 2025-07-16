package iut.pickomino.models

/**
 * Enum class representing the faces of a dice
 * @author Arnaud Lanoix Brauer
 */
@kotlinx.serialization.Serializable
enum class DICE(val value: Int) {
    /**
     * Face 1 d'un dé
     */
    d1(1),
    /**
     * Face 2 d'un dé
     */
    d2(2),
    /**
     * Face 3 d'un dé
     */
    d3(3),
    /**
     * Face 4 d'un dé
     */
    d4(4),
    /**
     * Face 5 d'un dé
     */
    d5(5),
    /**
     * Face "Vers" d'un dé
     */
    worm(5);

    companion object {
        fun roll(nb_dices: Int): List<DICE> {
            val result = mutableListOf<DICE>()
            for (i in 0 until nb_dices) {
                result.add(values().random())
            }
            return result
        }
    }
}

