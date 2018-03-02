import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;
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
        topleft.setName("TOPLEFT");
        frame.add(topleft);
        
        JButton topright = new JButton();
        topright.addKeyListener(this);
        topright.setName("TOPRIGHT");
        frame.add(topright);

        JButton bottomleft = new JButton();
        bottomleft.addKeyListener(this);
        bottomleft.setName("BOTTOMLEFT");
        frame.add(bottomleft);
        
        JButton bottomright = new JButton();
        bottomright.addKeyListener(this);
        bottomright.setName("BOTTOMRIGHT");
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
		
		// We need to know the name of the button that is in play.
		// Some shennanigans here. We need to shift the focus to the next component.
		// i.e. it we move the bottom right, we need to shift focus to the button
		// so the key listener will be associated with that button.
		// Sorted most of it now.
	   System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().getName()); // Debug
	
	   // Key pressed
	   int key = evt.getKeyCode();
	   
	   if (key == KeyEvent.VK_LEFT) {

		  System.out.println("left");
		  
		  // jTextField2.requestFocusInWindow();
	      repaint();
	   }
	   else if (key == KeyEvent.VK_RIGHT) {
		  System.out.println("right");
		  
	      repaint();
	   }
	   else if (key == KeyEvent.VK_UP) {
		  System.out.println("up");
	      repaint();
	   }
	   else if (key == KeyEvent.VK_DOWN) {
		  System.out.println("down");
	      repaint();
	   }
		   
	} 

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}