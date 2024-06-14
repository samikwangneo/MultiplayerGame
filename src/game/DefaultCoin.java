package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The DefaultCoin class represents a basic type of coin in the Survivor game.
 * It extends the Polygon class and implements the Coin interface. Default coins
 * are yellow and have a fixed coin value of 1.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public class DefaultCoin extends Polygon implements Coin {

    // The fixed coin value associated with a DefaultCoin
    private static final int COIN_VALUE = 1;

    /**
     * Constructs a DefaultCoin object with the specified shape, position, and 
     * rotation.
     *
     * @param inShape     The array of points representing the shape of the coin.
     * @param inPosition  The initial position of the coin.
     * @param inRotation  The initial rotation angle of the coin.
     */
    public DefaultCoin(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
    }

    /**
     * Paints the DefaultCoin on the canvas with brush using the specified color 
     * (yellow).
     *
     * @param brush The graphics object that paints the DefaultCoin
     */
    @Override
    public void paint(Graphics brush) {
        brush.setColor(Color.YELLOW);

        int[] xCords = new int[getPoints().length];
        int[] yCords = new int[getPoints().length];

        int i = 0, j = 0;
        for (Point p : getPoints()) {
            xCords[i++] = (int) p.getX();
            yCords[j++] = (int) p.getY();
        }

        brush.fillPolygon(xCords, yCords, getPoints().length);
    }

    /**
     * Gets the fixed coin value associated with a DefaultCoin.
     *
     * @return The coin value of the DefaultCoin.
     */
    @Override
    public int getCoinValue() {
        return COIN_VALUE;
    }
}
