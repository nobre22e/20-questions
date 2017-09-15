package GUI;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Controls an image
 * @author emnob
 *
 */
public class ImageController extends JPanel {
	
	/**
	 * Constructs an instance of the Image controller
	 */
	public ImageController(){
		super();
	}

	/**
	 * paints the image using ImageIcon
	 */
	public void paint(Graphics g){
		ImageIcon picture = new ImageIcon("isms.jpg");
		picture.paintIcon(this, g, getWidth()/2 - 216, 0);
	}
}
