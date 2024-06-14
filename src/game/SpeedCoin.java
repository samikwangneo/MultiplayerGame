package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The SpeedCoin class represents a specific type of coin in the Survivor game.
 * It extends the Polygon class and implements the Coin interface. Speed coins
 * are green, increase the speed by 2, and have a fixed coin value of 2.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public class SpeedCoin extends Polygon implements Coin {

    // The fixed coin value associated with a SpeedCoin
    private static final int COIN_VALUE = 2;

    /**
     * Constructs a SpeedCoin object with the specified shape, position, and 
     * rotation.
     *
     * @param inShape     The array of points representing the shape of the coin.
     * @param inPosition  The initial position of the coin.
     * @param inRotation  The initial rotation angle of the coin.
     */
    public SpeedCoin(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
    }

    /**
     * Paints the SpeedCoin on the graphics object using the specified color 
     * (green).
     *
     * @param brush The graphics object used to paint the SpeedCoin.
     */
    @Override
    public void paint(Graphics brush) {
        brush.setColor(Color.GREEN);

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
     * Gets the certain coin value of a SpeedCoin.
     *
     * @return The coin value of the SpeedCoin.
     */
    @Override
    public int getCoinValue() {
        return COIN_VALUE;
    }
}
