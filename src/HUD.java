import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * HUD DISPLAY
 * @author OUR TEAM NAME
 *
 */
public class HUD extends JFrame implements KeyListener  {

	// LOCALS
	private int hudWidth, hudHeight;
	
	
	public HUD() {
		
		// Init the HUD
		hudHeight = hudWidth = 500;
		frameHUD();
		
	}
	
    /*
     * Scratchpad
     * 
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
        int x = (dim.width-w)/2-(hudWidth/2); // Correct for 1/2 of HUD width
        int y = ((dim.height-h)/2)-40;
		
        // Disappear the background
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        

        // set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(2, 2, 10, 10);
        // what layout we want to use for our frame
        frame.setLayout(grid);
        
        // HUD BUTTONS
        JButton topleft = new JButton();
        topleft.addKeyListener(this);
        frame.add(topleft);
        
        JButton topright = new JButton();
        topright.addKeyListener(this);
        frame.add(topright);

        JButton bottomleft = new JButton();
        bottomleft.addKeyListener(this);
        frame.add(bottomleft);
        
        JButton bottomright = new JButton();
        bottomright.addKeyListener(this);
        frame.add(bottomright);      
 
		//frame.pack(); // take pack off later. Might be too bunched
		frame.setSize(hudWidth, hudHeight);
		frame.setLocation(x,y); // location [just above taskbar and horiz centered]
		frame.setVisible(true); // make frame visible
	}	
	

	/**
	 * Controller
	 */
	
	public void keyPressed(KeyEvent evt) { 
		   
		   int key = evt.getKeyCode();  // keyboard code for the pressed key
		   
		   if (key == KeyEvent.VK_LEFT) {  // move the square left

			  System.out.println("left");
			  
			  // jTextField2.requestFocusInWindow();
		      repaint();
		   }
		   else if (key == KeyEvent.VK_RIGHT) {  // move the square right
			  System.out.println("right");
			  
		      repaint();
		   }
		   else if (key == KeyEvent.VK_UP) {  // move the squre up
			  System.out.println("up");
		      repaint();
		   }
		   else if (key == KeyEvent.VK_DOWN) {  // move the square down
			  System.out.println("down");
		      repaint();
		   }
		   
		}  // end keyPressed()

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}