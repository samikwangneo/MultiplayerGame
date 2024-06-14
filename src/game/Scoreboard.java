package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * The Scoreboard class represents the scoreboard displayed in the Survivor game.
 * It extends the Polygon class and is responsible for showing the player scores 
 * on the top of the screen.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public class Scoreboard extends Polygon {

    /**
     * Constructs a Scoreboard object with the specified shape, position, and 
     * rotation.
     *
     * @param inShape     The array of points for the shape of the scoreboard.
     * @param inPosition  The initial position of the scoreboard.
     * @param inRotation  The initial rotation angle of the scoreboard.
     */
    public Scoreboard(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
    }

    /**
     * Paints the Scoreboard on the canvas using brush, displays player scores 
     * and and has the title for the scoreboard.
     *
     * @param brush The graphics object used to paint the Scoreboard.
     */
    public void paint(Graphics brush) {
        int[] xCords = new int[getPoints().length];
        int[] yCords = new int[getPoints().length];

        int i = 0, j = 0;
        for (Point p : getPoints()) {
            xCords[i++] = (int) p.getX();
            yCords[j++] = (int) p.getY();
        }

        // Fill the scoreboard polygon with gray color
        brush.setColor(Color.GRAY);
        brush.fillPolygon(xCords, yCords, getPoints().length);

        // Display Scoreboard title
        brush.setColor(Color.WHITE);
        brush.setFont(new Font("Arial", Font.BOLD, 12));
        brush.drawString("| Scoreboard |", 365, 15);

        // Display Player 1 score in red color
        brush.setColor(Color.RED);
        brush.drawString("| Player 1: " + Survivor.p1Score + " |", 205, 15);

        // Display Player 2 score in blue color
        brush.setColor(Color.BLUE);
        brush.drawString("| Player 2: " + Survivor.p2Score + " |", 515, 15);
    }
}
 