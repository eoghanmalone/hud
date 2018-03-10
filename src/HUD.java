import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;



/**
 * HUD DISPLAY
 * @author OUR TEAM NAME
 *
 */
public class HUD extends JFrame implements KeyListener  {

	// LOCALS
	private int hudWidth, hudHeight;
	private JButton topleft, topright, bottomleft, bottomright, playListA,playListB,playListC,playListD,playListE;
	Dimension dim;
	
	// width, height, x pos, y pos
	int w, h, x, y;	
	
	

    
	public HUD() {
		
		// Init the HUD
		hudHeight = hudWidth = 500;
		
        // Get the size of the screen
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        w = this.getSize().width;
        h = this.getSize().height;
        x = (dim.width-w)/2-(hudWidth/2); // Correct for 1/2 of HUD width
        y = ((dim.height-h)/2)-80;
        
        // Fire main hud on load
		//frameHUD();
		
		framePlayList();
		
	}


	public void framePlayList() {
		
		JFrame frame = new JFrame("HUD");
		
        // Disappear the background
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
		
        // set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(5, 1, 5, 5);
        // what layout we want to use for our frame
        frame.setLayout(grid);
        
        // Playlist A
        playListA = new JButton();
        playListA.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498, 98, java.awt.Image.SCALE_SMOOTH)));
        playListA.setMargin(new Insets(0, 0, 0, 0));
        playListA.setBorderPainted(false);
        playListA.setFocusPainted(false);
        playListA.setContentAreaFilled(false);    
        playListA.addKeyListener(this);
        playListA.setName("playListA");
        frame.add(playListA);

        // Playlist B
        playListB = new JButton();
        playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98, java.awt.Image.SCALE_SMOOTH)));
        playListB.setMargin(new Insets(0, 0, 0, 0));
        playListB.setBorderPainted(false);
        playListB.setFocusPainted(false);
        playListB.setContentAreaFilled(false);    
        playListB.addKeyListener(this);
        playListB.setName("playlistB");
        frame.add(playListB);

        // Playlist C
        playListC = new JButton();
        playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98, java.awt.Image.SCALE_SMOOTH)));
        playListC.setMargin(new Insets(0, 0, 0, 0));
        playListC.setBorderPainted(false);
        playListC.setFocusPainted(false);
        playListC.setContentAreaFilled(false);    
        playListC.addKeyListener(this);
        playListC.setName("playlistC");
        frame.add(playListC);

        // Playlist D
        playListD = new JButton();
        playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98, java.awt.Image.SCALE_SMOOTH)));
        playListD.setMargin(new Insets(0, 0, 0, 0));
        playListD.setBorderPainted(false);
        playListD.setFocusPainted(false);
        playListD.setContentAreaFilled(false);    
        playListD.addKeyListener(this);
        playListD.setName("playlistD");
        frame.add(playListD);

        // Playlist E
        playListE = new JButton();
        playListE.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98, java.awt.Image.SCALE_SMOOTH)));
        playListE.setMargin(new Insets(0, 0, 0, 0));
        playListE.setBorderPainted(false);
        playListE.setFocusPainted(false);
        playListE.setContentAreaFilled(false);    
        playListE.addKeyListener(this);
        playListE.setName("playlistE");
        frame.add(playListE);

		frame.setSize(hudWidth, hudHeight);
		frame.setLocation(x,y); // location [just above taskbar and horiz centered]
		frame.setVisible(true); // make frame visible
	
	}

	public void frameHUD() {
		
		JFrame frame = new JFrame("HUD");
		
        // Disappear the background
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        frame.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
       
        // set the rows and cols of the grid, as well the distances between them
        GridLayout grid = new GridLayout(2, 2, 10, 10);
        // what layout we want to use for our frame
        frame.setLayout(grid);
        
        // HUD BUTTONS
        topleft = new JButton();
        topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
        topleft.setMargin(new Insets(0, 0, 0, 0));
        topleft.setBorderPainted(false);
        topleft.setFocusPainted(false);
        topleft.setContentAreaFilled(false);    
        topleft.addKeyListener(this);
        topleft.setName("TOPLEFT");
        frame.add(topleft);
        
        topright = new JButton();
        topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
        topright.setMargin(new Insets(0, 0, 0, 0));
        topright.setBorderPainted(false);
        topright.setFocusPainted(false);
        topright.setContentAreaFilled(false);
        topright.addKeyListener(this);
        topright.setName("TOPRIGHT");
        frame.add(topright);

        bottomleft = new JButton();
        bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
        bottomleft.setMargin(new Insets(0, 0, 0, 0));
        bottomleft.setBorderPainted(false);
        bottomleft.setFocusPainted(false);
        bottomleft.setContentAreaFilled(false);
        bottomleft.addKeyListener(this);
        bottomleft.setName("BOTTOMLEFT");
        frame.add(bottomleft);
        
        bottomright = new JButton();
        bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
        bottomright.setMargin(new Insets(0, 0, 0, 0));
        bottomright.setBorderPainted(false);
        bottomright.setFocusPainted(false);
        bottomright.setContentAreaFilled(false);
        bottomright.addKeyListener(this);
        bottomright.setName("BOTTOMRIGHT");
        frame.add(bottomright);      
 
		frame.pack(); // take pack off later. Might be too bunched
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

	   System.out.println(KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().getName()); // Debug
	
	   String component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().getName();
	   
	   // Key pressed
	   int key = evt.getKeyCode();
	   
	   if (key == KeyEvent.VK_LEFT) {

		  System.out.println("left");

		  if(component == "TOPRIGHT") {
			  
			  topleft.requestFocusInWindow();
			  topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  System.out.println("FOCUS NOW IN TOP LEFT WINDOW");
		  }
		  
		  if(component == "BOTTOMRIGHT") {
			  
			  bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomleft.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN BOTTOM LEFT WINDOW");
			  
		  }
		  
		 
		  
	      repaint();
	      
	   } else if (key == KeyEvent.VK_RIGHT) {
		   
		  System.out.println("right");
		  
		  if(component == "TOPLEFT") {
			  topright.setIcon(new ImageIcon(((new ImageIcon("topright_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topright.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN TOP RIGHT WINDOW");
		  }
		  
		  if(component == "BOTTOMLEFT") {
			  
			  bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN BOTTOM RIGHT WINDOW");
		  }
		  
	      repaint();
	      
	   }  else if (key == KeyEvent.VK_UP) {
		   
		  System.out.println("up");
		  
		  if(component == "BOTTOMLEFT") {
			  			  
			  topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topleft.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN TOP LEFT WINDOW");
		  }
		  
		  if(component == "BOTTOMRIGHT") {
			  
			  topright.setIcon(new ImageIcon(((new ImageIcon("topright_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topright.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN TOP RIGHT WINDOW");
		  }
		  
	      repaint();
	      
	   } else if (key == KeyEvent.VK_DOWN) {
		   
		  System.out.println("down");
		  
		  if(component == "TOPLEFT") {
			  bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomleft.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN BOTTOM LEFT WINDOW");
		  }
		  if(component == "TOPRIGHT") {
			  topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_on.png")).getImage()).getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
			  bottomright.requestFocusInWindow();
			  System.out.println("FOCUS NOW IN BOTTOM RIGHT WINDOW");
		  }
		  
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