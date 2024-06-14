package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * The SlowCoin class is a type of coin that if picked up it decreases the 
 * player's step size, making their movement slower by 2 in the Survivor game.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public class SlowCoin extends Polygon implements Coin {
	
	// Fixed coin value associated with a Slow Coin, which decreases the score
    private static final int COIN_VALUE = -1;

    /**
     * Constructs a SlowCoin with the specified shape, position, and rotation.
     *
     * @param inShape      The shape of the SlowCoin.
     * @param inPosition   The position of the SlowCoin.
     * @param inRotation   The rotation of the SlowCoin.
     */
    public SlowCoin(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
    }

    /**
     * Paints the SlowCoin on the canvas using the graphics brush in a specific
     * color (red).
     *
     * @param brush The graphics brush used for painting the SlowCoin.
     */
    @Override
    public void paint(Graphics brush) {
        brush.setColor(Color.RED);
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
     * Gets the value of the SlowCoin, which is used to update the player's 
     * score when collected.
     *
     * @return The value of the SlowCoin.
     */
    @Override
    public int getCoinValue() {
        return COIN_VALUE;
    }
}
