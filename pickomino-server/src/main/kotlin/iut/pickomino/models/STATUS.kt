package iut.pickomino.models

/**
 * some possible stages of the game
 * @author Arnaud Lanoix Brauer
 */
enum class STATUS {
    /**
     * Etape "dés à lancer" du tour courant
     */
    ROLL_DICE,
    /**
     * Etape "dés à lancer ou pickomino à prendre" du tour courant
     */
    ROLL_DICE_OR_TAKE_PICKOMINO,
    /**
     * Etape "dés à prendre" du tour courant
     */
    KEEP_DICE,
    /**
     * Etape "Pickomo à prendre" du tour courant
     */
    TAKE_PICKOMINO,
    /**
     * Etape "Partie terminée"
     */
    GAME_FINISHED;
}