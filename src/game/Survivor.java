package game;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The Survivor class represents the control center of the game, extending the 
 * Game class.
 * It manages players, coins, the scoreboard, and game logic.
 *
 * @author Samik Wangneo, Purab Shah
 * @version 1.0
 */
@SuppressWarnings("serial")
class Survivor extends Game {
    // Static variables to keep track of player scores and pre-made counter
    static int p1Score = 0;
    static int p2Score = 0;
    static int counter = 0;

    // Instance variables for game components
    private Player player1;
    private Player player2;
    private Scoreboard scoreboard;
    private ArrayList<Coin> coins;
    Random random;

    // Constants defining relative points for coins, players, and scoreboard
    private static final Point[] COIN_POINTS = {new Point(0, -5), new Point(5, 0),
            new Point(0, 5), new Point(-5, 0)};
    private static final Point[] PLAYER_POINTS = {new Point(0, -20), new Point(5, -5),
            new Point(20, 0), new Point(5, 5), new Point(0, 20), new Point(-5, 5),
            new Point(-30, 0), new Point(-5, -5)};
    private static final Point[] SCOREBOARD_POINTS = {new Point(0, -30), 
    		new Point(0, 0), new Point(400, 0), new Point(400, -30)};

    /**
     * Constructs a new Survivor instance, initializing the game components
     * including the coins, players, and scoreboard
     */
    public Survivor() {
        super("Survivor!", 800, 600);
        this.setFocusable(true);
        this.requestFocus();
        coins = new ArrayList<Coin>();
        random = new Random();

        // Initialize 10 default coins randomly across the map
        for (int i = 0; i < 10; i++) {
            coins.add(new DefaultCoin(COIN_POINTS, new Point(random.nextInt(801),
                    random.nextInt(601)), 0));
        }

        // Initialize 1 speed coin randomly across the map
        coins.add(new SpeedCoin(COIN_POINTS, new Point(random.nextInt(801),
                random.nextInt(601)), 0));

        // Initialize player 1 and allows it to use keyboard functions
        player1 = new Player(PLAYER_POINTS, new Point(250, 250), 0);
        this.addKeyListener(player1);

        // Initialize player 2 through an anonymous class using the Player class
        player2 = new Player(PLAYER_POINTS, new Point(450, 250), 0) {
         
        	/**
        	 * Handles the key pressed event for the Player, updating movement 
        	 * based on the keys pressed.
        	 *
        	 * @param e The KeyEvent object representing the key pressed event.
        	 */
        	@Override
        	public void keyPressed(KeyEvent e) {
        	    switch (e.getKeyCode()) {
        	        case KeyEvent.VK_W:
        	            forward = true;
        	            break;
        	        case KeyEvent.VK_S:
        	            backward = true;
        	            break;
        	        case KeyEvent.VK_D:
        	            rotateRight = true;
        	            break;
        	        case KeyEvent.VK_A:
        	            rotateLeft = true;
        	            break;
        	    }
        	}

        	/**
        	 * Handles the key released event for the Player, updating movement 
        	 * based on the keys released.
        	 *
        	 * @param e The KeyEvent object representing the key released event.
        	 */
        	@Override
        	public void keyReleased(KeyEvent e) {
        	    switch (e.getKeyCode()) {
        	        case KeyEvent.VK_W:
        	            forward = false;
        	            break;
        	        case KeyEvent.VK_S:
        	            backward = false;
        	            break;
        	        case KeyEvent.VK_D:
        	            rotateRight = false;
        	            break;
        	        case KeyEvent.VK_A:
        	            rotateLeft = false;
        	            break;
        	    }
        	}
        };

        this.addKeyListener(player2);

        // Initializes the scoreboard at the top of the screen
        scoreboard = new Scoreboard(SCOREBOARD_POINTS, new Point(300, 0), 0);
    }

    /**
     * The main paint method responsible for rendering the game elements on the 
     * canvas.
     *
     * @param brush The graphics object used for painting.
     */
    public void paint(Graphics brush) {
        // Initializes BackgroundManager object
    	BackgroundManager bm = new BackgroundManager();

        // Update background color based on game state, draws corresponding 
    	// text if the background is updated
        bm.updateBackgroundColor();
        brush.setColor(bm.getCurrentColor());
        brush.fillRect(0, 0, width, height);

        brush.setColor(Color.BLACK);
        brush.setFont(new Font("Arial", Font.BOLD, 20));
        if (bm.getCurrentColor() == Color.MAGENTA) {
            brush.drawString("A Player has 20 points!", 293, 50);
        }
        if (bm.getCurrentColor() == Color.ORANGE) {
            brush.drawString("Both Players have 20 points!", 275, 50);
        }

        // Lambda expressions that initializes the functional interface
        // ScoreUpdater and adds the value of the coin to the current score
        // for each player
        ScoreUpdater scoreUpdater1 = (coinVal) -> {
            p1Score += coinVal;
        };
        ScoreUpdater scoreUpdater2 = (coinVal) -> {
            p2Score += coinVal;
        };

        // Paints the player 1 on the screen in red
        brush.setColor(Color.RED);
        player1.move();
        player1.paint(brush);

        // Paints the player 2 on the screen in blue
        brush.setColor(Color.BLUE);
        player2.move();
        player2.paint(brush);

        // Paints the scoreboard on the screen
        scoreboard.paint(brush);

        // Paints all the coins on the screen
        for (Coin c : coins) {
            c.paint(brush);
        }

        for (Coin c : new ArrayList<Coin>(coins)) {

            // Check for player1 collisions with default coins
            if (c instanceof DefaultCoin) {
                Coin coin = (DefaultCoin) c;
                if (player1.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player 1 score and choose the next coin type based
                    // on a random variable that takes a value from 0-7 and
                    // there is a 1/7 chance for a speed/slow coin to spawn
                    scoreUpdater1.updateScore(coin.getCoinValue());
                    int coinRandom = random.nextInt(8);
                    if (coinRandom == 0) {
                        coins.add(new SpeedCoin(COIN_POINTS,
                                new Point(random.nextInt(730) + 35,
                                        random.nextInt(515) + 35), 0));
                        break;
                    }

                    if (coinRandom == 1) {
                        coins.add(new SlowCoin(COIN_POINTS,
                                new Point(random.nextInt(730) + 35,
                                        random.nextInt(515) + 35), 0));
                        break;
                    }

                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }

                // Check for player2 collisions with default coins
                if (player2.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player2 score and choose the next coin type based
                    // on a random variable that takes a value from 0-7 and
                    // there is a 1/7 chance for a speed/slow coin to spawn
                    scoreUpdater2.updateScore(coin.getCoinValue());
                    int coinRandom = random.nextInt(8);
                    if (coinRandom == 0) {
                        coins.add(new SpeedCoin(COIN_POINTS,
                                new Point(random.nextInt(730) + 55,
                                        random.nextInt(515) + 35), 0));
                        break;
                    }
                    
                    if (coinRandom == 1) {
                        coins.add(new SlowCoin(COIN_POINTS,
                                new Point(random.nextInt(730) + 35,
                                        random.nextInt(515) + 35), 0));
                        break;
                    }
                    
                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }
            }

            // Check for player1 collisions with speed coins
            if (c instanceof SpeedCoin) {
                Coin coin = (SpeedCoin) c;
                if (player1.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player 1 score, increase player1 speed, and add a
                    // default coin to the map
                    scoreUpdater1.updateScore(coin.getCoinValue());
                    player1.stepSize = (player1.stepSize >= 4 ? 6 : 4);
                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }

                // Check for player2 collisions with speed coins
                if (player2.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player 2 score, increase player2 speed, and add a
                    // default coin to the map
                    scoreUpdater2.updateScore(coin.getCoinValue());
                    player2.stepSize = (player2.stepSize >= 4 ? 6 : 4);
                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }
            }

            // Check for player1 collisions with slow coins
            if (c instanceof SlowCoin) {
                Coin coin = (SlowCoin) c;
                if (player1.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player 1 score, decrease player1 speed, and add a
                    // default coin to the map
                    scoreUpdater1.updateScore(coin.getCoinValue());
                    player1.stepSize = (player1.stepSize <= 4 ? 2 : 4);
                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }

                // Check for player2 collisions with slow coins
                if (player2.collides((Polygon) coin)) {
                    coins.remove(c);
                    // Update player2 score, decrease player2 speed, and add a
                    // default coin to the mapp
                    scoreUpdater2.updateScore(coin.getCoinValue());
                    player2.stepSize = (player2.stepSize <= 4 ? 2 : 4);
                    coins.add(new DefaultCoin(COIN_POINTS,
                            new Point(random.nextInt(730) + 35,
                                    random.nextInt(515) + 35), 0));
                    break;
                }
            }
        }

        // Initializes a GameOverChecker object and checks to see if the game
        // is over, if it is then it clears the map and displays GameOver screen
        GameOverChecker gameOverChecker = new GameOverChecker();
        if (gameOverChecker.isGameOver()) {
            coins.clear();
            gameOverChecker.displayGameOver(brush);
        }
    }

    /**
     * The main method to start the Survivor game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        Survivor a = new Survivor();
        a.repaint();
    }

    /**
     * The BackgroundManager is an inner class that manages the background color
     * based on the the scores of each of the players.
     * It changes the background color to magenta if one player hit 20 points,
     * orange if both players hit 20 points, and black if the game is over.
     */
    public class BackgroundManager {
    	// Instance variable for the current color of the background
        private Color currColor = Color.BLACK;

        /**
         * Updates the background color based on the game state as described
         * above.
         */
        public void updateBackgroundColor() {
            GameOverChecker checker = new GameOverChecker();
            if (checker.isGameOver()) {
                currColor = Color.BLACK;
            } 
            else if (p1Score >= 20 && p2Score >= 20) {
                currColor = Color.ORANGE;
            } 
            else if (p1Score >= 20 || p2Score >= 20) {
                currColor = Color.MAGENTA;
            }
        }

        /**
         * Gets the current background color.
         *
         * @return The current background color.
         */
        public Color getCurrentColor() {
            return currColor;
        }

    }

    /**
     * The GameOverChecker inner class checks if the game has reached a win 
     * condition which is when a player hits the score limit.
     * It also displays the game over message and winner information.
     */
    public class GameOverChecker {
        // Static final constant defining the score limit for a win
        public static final int SCORE_LIMIT = 25;

        /**
         * Checks if the game has reached a win condition based on score limit.
         *
         * @return True if the game is over, false otherwise.
         */
        public boolean isGameOver() {
            return p1Score >= SCORE_LIMIT || p2Score >= SCORE_LIMIT;
        }

        /**
         * Displays the game over message and winner information on the screen.
         *
         * @param brush The graphics object used for painting.
         */
        public void displayGameOver(Graphics brush) {
            if (isGameOver()) {
                brush.setColor(Color.BLACK);
                brush.fillRect(0, 30, width, height);
                brush.setColor(Color.WHITE);
                brush.setFont(new Font("Arial", Font.BOLD, 50));
                brush.drawString("   GAME OVER", 200, 230);

                Color c = p1Score >= SCORE_LIMIT ? Color.RED : Color.BLUE;
                brush.setColor(c);
                String gameWinner = p1Score >= SCORE_LIMIT ? "PLAYER 1 WINS!" :
                        "PLAYER 2 WINS";
                brush.drawString(gameWinner, 200, 280);

                brush.setFont(new Font("Arial", Font.BOLD, 30));
                brush.drawString("Restart Game To Play Again",
                        +200, 325);
            }
        }
    }
}
