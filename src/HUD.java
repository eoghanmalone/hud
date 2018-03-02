import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

/**
 * HUD DISPLAY
 * @author 2227056m
 *
 */
public class HUD extends JFrame implements ActionListener {

	// LOCALS
	
	
	public HUD() {
		
		// Init the HUD
		System.out.print("HUD");
		frameHUD();
		
	}
	
    /*
	    BufferedImage buttonIcon = ImageIO.read(new File("myImage.png"));
	    button = new JButton(new ImageIcon(buttonIcon));
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false);
    */


	
	public void frameHUD() {
		
		JFrame frame = new JFrame("HUD");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        // Get the size of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = this.getSize().width;
        int h = this.getSize().height;
        int x = (dim.width-w)/2-250;
        int y = ((dim.height-h)/2);
		
		
		frame.pack();
		frame.setSize(500, 500);
		frame.setLocation(x,y);
		frame.setVisible(true);
	}	
	

	/**
	 * Controller
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Regroup with Lewis and establish controller movements
		
		// left [if we keep pressing left or right will it just go clockwise / anti-clockwise]
		
		// right
		
		// up
		
		// down
		
	
	}

}