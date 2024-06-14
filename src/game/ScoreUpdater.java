package game;

/**
 * The ScoreUpdater interface is a functional interface for updating player 
 * scores based on the value of collected coins in the game.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
@FunctionalInterface
public interface ScoreUpdater {

    /**
     * Updates the player score based on the value of the collected coin.
     *
     * @param coinVal The value of the collected coin.
     */
    void updateScore(int coinVal);
}
