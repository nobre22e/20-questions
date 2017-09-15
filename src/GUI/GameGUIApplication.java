package GUI;
import javax.swing.JFrame;

/**
 * An application that displays the Twenty Questions Game.
 * @author emnob
 *
 */
class GameGUIApplication {

	/**
	 * Sets the width of the frame to 400 pixels
	 */
	public static int FRAME_WIDTH = 700;

	/**
	 * Sets the height of the frame to 700 pixels
	 */
	public static int FRAME_HEIGHT = 700;

	/**
	 * Creates and draws the frame for the ice cream cone
	 * 
	 * @param args
	 *            not used
	 */
	public static void main(String[] args) {
		JFrame questions = new JFrame("20 Questions");
		// Adds a 20 questions game to the window.
		questions.add(new GameController(args[0]));
		questions.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		questions.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		questions.setVisible(true);
	}

}
