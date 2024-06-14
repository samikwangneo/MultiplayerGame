package game;

import java.awt.Graphics;

/**
 * The Coin interface defines the methods that should be implemented by coin 
 * classes in the Survivor game. Coins are objects that can be painted on the 
 * screen and have an certain value.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public interface Coin {

    /**
     * Paints the coin on the graphics object using the paint method.
     *
     * @param brush The graphics object used to paint the coin.
     */
    public void paint(Graphics brush);

    /**
     * Gets the value associated with the coin.
     *
     * @return The value of the coin.
     */
    public int getCoinValue();

}
