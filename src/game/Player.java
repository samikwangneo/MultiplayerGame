package game;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The Player class represents a player in the Survivor game. 
 * It extends the Polygon class and implements the KeyListener interface 
 * to handle keyboard input for player movement and rotation.
 * 
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
public class Player extends Polygon implements KeyListener {
	// Instance variables for the forward, backward, and rotations and speed
	// of the player movement
    protected boolean forward, backward, rotateRight, rotateLeft;
    protected int stepSize = 4;

    /**
     * Constructs a Player object with the specified shape, position, and 
     * rotation.
     *
     * @param inShape     The array of points defining the shape of the player.
     * @param inPosition  The initial position of the player.
     * @param inRotation  The initial rotation angle of the player.
     */
    public Player(Point[] inShape, Point inPosition, double inRotation) {
        super(inShape, inPosition, inRotation);
    }

    /**
     * Paints the player on the cannvas using the fillPolygon method.
     *
     * @param brush The graphics object used to paint the player.
     */
    public void paint(Graphics brush) {

        int[] xCoords = new int[getPoints().length];
        int[] yCoords = new int[getPoints().length];

        int i = 0, j = 0;
        for (Point p : getPoints()) {
            xCoords[i++] = (int) p.getX();
            yCoords[j++] = (int) p.getY();
        }

        brush.fillPolygon(xCoords, yCoords, getPoints().length);
    }

    /**
     * Moves the player based on the keyboard input and ensures that the player
     * stays within the game window boundaries.
     */
    public void move() {
        // Boundaries for the game window
        if (position.x < 15) {
            position.x = 15;
        } else if (position.x > 765) {
            position.x = 765;
        }

        if (position.y < 20) {
            position.y = 20;
        } else if (position.y > 535) {
            position.y = 535;
        }

        // Move forward
        if (forward) {
            position.x -= stepSize * Math.cos(Math.toRadians(rotation));
            position.y -= stepSize * Math.sin(Math.toRadians(rotation));
        }

        // Move backward
        if (backward) {
            position.x += stepSize * Math.cos(Math.toRadians(rotation));
            position.y += stepSize * Math.sin(Math.toRadians(rotation));
        }

        // Rotate right
        if (rotateRight) {
            rotation += stepSize;
        }

        // Rotate left
        if (rotateLeft) {
            rotation -= stepSize;
        }
    }

    /**
     * Handles the key typed event (unused in this implementation).
     *
     * @param e The KeyEvent object representing the key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // Left empty in our implementation
    }

    /**
     * Handles the key pressed event to set movement and rotation based on
     * the pressed keys.
     *
     * @param e The KeyEvent object representing the key pressed event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                forward = true;
                break;
            case KeyEvent.VK_DOWN:
                backward = true;
                break;
            case KeyEvent.VK_RIGHT:
                rotateRight = true;
                break;
            case KeyEvent.VK_LEFT:
                rotateLeft = true;
                break;
        }
    }

    /**
     * Handles the key released event to reset movement and rotation when 
     * keys are released.
     *
     * @param e The KeyEvent object representing the key released event.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                forward = false;
                break;
            case KeyEvent.VK_DOWN:
                backward = false;
                break;
            case KeyEvent.VK_RIGHT:
                rotateRight = false;
                break;
            case KeyEvent.VK_LEFT:
                rotateLeft = false;
                break;
        }
    }
}
