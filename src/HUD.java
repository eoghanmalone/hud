import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.KeyboardFocusManager;

import java.io.File;

import java.util.Map;
import java.util.HashMap;

import javax.swing.*;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;


/**
 * HUD DISPLAY
 * 
 * @author OUR TEAM NAME
 *
 */
public class HUD extends JFrame implements KeyListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	final static String MOVE_AUDIO_FILE_NAME = "/sounds/messageSend.wav";

	// LOCALS
	private int hudWidth, hudHeight;
	private JButton topleft, topright, bottomleft, bottomright, playListA, playListB, playListC, playListD, playListE,bParticipant;
	private JTextArea participantName;
	private JLabel participantLabel;
	private Dimension dim;
	private JFrame frameHUD, framePLayList, frameParticipant;

	// width, height, x pos, y pos
	private int w, h, x, y;

	// DATA
	private int totalKeyPresses, wrongSelections; // Track keypresses
	private long startTime, endTime, totalTime;	// Track start, end and total time	 
	private String nameOfParticipant; // who is taking part
	private boolean tracking;
	
	static /* Audio Feedback */
	// AudioInputStream moveAudio, selectAudio;
	AudioInputStream[] audioStreams = new AudioInputStream[15];
	// static Clip[] soundClips = new Clip[15];
	static Map<String, Clip> soundClips = new HashMap<String, Clip>();

	public HUD() {

		// Init the HUD
		hudHeight = hudWidth = 500;

		// Get the size of the screen
		dim = Toolkit.getDefaultToolkit().getScreenSize();
		w = this.getSize().width;
		h = this.getSize().height;
		x = (dim.width - w) / 2 - (hudWidth / 2); // Correct for 1/2 of HUD
		y = ((dim.height - h) / 2) - 80;	// width
		
		// Set data to zero
		totalKeyPresses = wrongSelections = 0;
		tracking = false;
		
		// Load system sounds
		loadSounds();

		// first frame captures details
		frameParticipant();
		

	}
	
	/**
	 * Load sounds
	 */
	public static void loadSounds() {

		File[] files = new File(System.getProperty("user.dir") + "/sounds").listFiles();

		for (int i = 0; i < files.length; i++) {
			try {
				String theFileName = System.getProperty("user.dir") + "/sounds/" + files[i].getName();
				if (theFileName.endsWith(".wav")) {
					audioStreams[i] = AudioSystem.getAudioInputStream(new File(theFileName));
					soundClips.put(files[i].getName(), AudioSystem.getClip());
					soundClips.get(files[i].getName()).open(audioStreams[i]); // Buffer
																				// sound.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * Playlist participant
	 */
	public void frameParticipant() {

		frameParticipant = new JFrame("Participant capture");

		// set the rows and cols of the grid, as well the distances between them
		GridLayout grid = new GridLayout(3, 1);
		
		// Layout
		frameParticipant.setLayout(grid);
		
		
		// Label
		participantLabel = new JLabel("Please enter your name click SAVE");
		participantLabel.setFont(participantLabel.getFont().deriveFont(20f));
		participantLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frameParticipant.add(participantLabel);
		
		// Text area for participant name
		participantName = new JTextArea();
		participantName.setName("participant");
		participantName.setFont(participantName.getFont().deriveFont(20f));
		participantName.getDocument().putProperty("filterNewlines", Boolean.TRUE);
		participantName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frameParticipant.add(participantName);
		
		// save button
		bParticipant = new JButton("SAVE");
		bParticipant.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		bParticipant.setName("bParticipant");
		bParticipant.addActionListener(this);
		frameParticipant.add(bParticipant);
		
		// position and display
		frameParticipant.setSize(500, 200);
		frameParticipant.setLocation(x, y); // location [just above taskbar and horizontal centered]
		frameParticipant.setVisible(true); // make frame visible
	
	}
	
	/**
	 * Playlist frame
	 */
	public void framePlayList() {

		framePLayList = new JFrame("HUD");

		// Disappear the background
		framePLayList.setUndecorated(true);
		framePLayList.getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		framePLayList.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

		// set the rows and cols of the grid, as well the distances between them
		GridLayout grid = new GridLayout(5, 1, 5, 5);
		// what layout we want to use for our frame
		framePLayList.setLayout(grid);

		// Playlist A
		playListA = new JButton();
		playListA.setText("Playlist A");
		playListA.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498, 98,
				java.awt.Image.SCALE_SMOOTH)));
		playListA.setMargin(new Insets(0, 0, 0, 0));
		playListA.setBorderPainted(false);
		playListA.setFocusPainted(false);
		playListA.setContentAreaFilled(false);
		playListA.addKeyListener(this);
		playListA.setHorizontalTextPosition(JButton.CENTER);
		playListA.setVerticalTextPosition(JButton.CENTER);
		playListA.setName("playlistA");
		framePLayList.add(playListA);

		// Playlist B
		playListB = new JButton();
		playListB.setText("Playlist B");
		playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98,
				java.awt.Image.SCALE_SMOOTH)));
		playListB.setMargin(new Insets(0, 0, 0, 0));
		playListB.setBorderPainted(false);
		playListB.setFocusPainted(false);
		playListB.setContentAreaFilled(false);
		playListB.addKeyListener(this);
		playListB.setHorizontalTextPosition(JButton.CENTER);
		playListB.setVerticalTextPosition(JButton.CENTER);
		playListB.setName("playlistB");
		framePLayList.add(playListB);

		// Playlist C
		playListC = new JButton();
		playListC.setText("Playlist C");
		playListC.setHorizontalTextPosition(JButton.CENTER);
		playListC.setVerticalTextPosition(JButton.CENTER);
		playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98,
				java.awt.Image.SCALE_SMOOTH)));
		playListC.setMargin(new Insets(0, 0, 0, 0));
		playListC.setBorderPainted(false);
		playListC.setFocusPainted(false);
		playListC.setContentAreaFilled(false);
		playListC.addKeyListener(this);
		playListC.setName("playlistC");
		framePLayList.add(playListC);

		// Playlist D
		playListD = new JButton();
		playListD.setText("Playlist D");
		playListD.setHorizontalTextPosition(JButton.CENTER);
		playListD.setVerticalTextPosition(JButton.CENTER);
		playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98,
				java.awt.Image.SCALE_SMOOTH)));
		playListD.setMargin(new Insets(0, 0, 0, 0));
		playListD.setBorderPainted(false);
		playListD.setFocusPainted(false);
		playListD.setContentAreaFilled(false);
		playListD.addKeyListener(this);
		playListD.setName("playlistD");
		framePLayList.add(playListD);

		// Playlist E
		playListE = new JButton();
		playListE.setText("Playlist E");
		playListE.setHorizontalTextPosition(JButton.CENTER);
		playListE.setVerticalTextPosition(JButton.CENTER);
		playListE.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498, 98,
				java.awt.Image.SCALE_SMOOTH)));
		playListE.setMargin(new Insets(0, 0, 0, 0));
		playListE.setBorderPainted(false);
		playListE.setFocusPainted(false);
		playListE.setContentAreaFilled(false);
		playListE.addKeyListener(this);
		playListE.setName("playlistE");
		framePLayList.add(playListE);

		framePLayList.setSize(hudWidth, hudHeight);
		framePLayList.setLocation(x, y); // location [just above taskbar and horiz centered]
		framePLayList.setVisible(true); // make frame visible

	}

	public void frameHUD() {

		frameHUD = new JFrame("HUD");

		// Disappear the background
		frameHUD.setUndecorated(true);
		frameHUD.getContentPane().setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));
		frameHUD.setBackground(new Color(1.0f, 1.0f, 1.0f, 0.0f));

		// set the rows and cols of the grid, as well the distances between them
		GridLayout grid = new GridLayout(2, 2, 10, 10);
		// what layout we want to use for our frame
		frameHUD.setLayout(grid);

		// HUD BUTTONS
		topleft = new JButton();
		topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245, 245,
				java.awt.Image.SCALE_SMOOTH)));
		topleft.setMargin(new Insets(0, 0, 0, 0));
		topleft.setBorderPainted(false);
		topleft.setFocusPainted(false);
		topleft.setContentAreaFilled(false);
		topleft.addKeyListener(this);
		topleft.setName("TOPLEFT");
		frameHUD.add(topleft);

		topright = new JButton();
		topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245, 245,
				java.awt.Image.SCALE_SMOOTH)));
		topright.setMargin(new Insets(0, 0, 0, 0));
		topright.setBorderPainted(false);
		topright.setFocusPainted(false);
		topright.setContentAreaFilled(false);
		topright.addKeyListener(this);
		topright.setName("TOPRIGHT");
		frameHUD.add(topright);

		bottomleft = new JButton();
		bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage()).getScaledInstance(245, 245,
				java.awt.Image.SCALE_SMOOTH)));
		bottomleft.setMargin(new Insets(0, 0, 0, 0));
		bottomleft.setBorderPainted(false);
		bottomleft.setFocusPainted(false);
		bottomleft.setContentAreaFilled(false);
		bottomleft.addKeyListener(this);
		bottomleft.setName("BOTTOMLEFT");
		frameHUD.add(bottomleft);

		bottomright = new JButton();
		bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage()).getScaledInstance(245,
				245, java.awt.Image.SCALE_SMOOTH)));
		bottomright.setMargin(new Insets(0, 0, 0, 0));
		bottomright.setBorderPainted(false);
		bottomright.setFocusPainted(false);
		bottomright.setContentAreaFilled(false);
		bottomright.addKeyListener(this);
		bottomright.setName("BOTTOMRIGHT");
		frameHUD.add(bottomright);

		frameHUD.pack(); // take pack off later. Might be too bunched
		frameHUD.setSize(hudWidth, hudHeight);
		frameHUD.setLocation(x, y); // location [just above taskbar and horiz
									// centered]
		frameHUD.setVisible(true); // make frame visible
	}

	/**
	 * Controller
	 */
	public void keyPressed(KeyEvent evt) {

		// increment the key press counter
		if(tracking) { totalKeyPresses++;}
		
		// component
		String component = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner().getName();

		// Key pressed
		int key = evt.getKeyCode();

		// Escape will start the task
		if(key == KeyEvent.VK_ESCAPE) {
			System.out.println("start clock");
			startTime = System.currentTimeMillis();
			tracking = true; 
		}
		
		// Fire the play list if the cursor is on the bottom right and space
		// or enter is pressed.
		if ((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_ENTER)) {
			switch (component) {
			case "BOTTOMRIGHT":
				/* Play select sound. */
				playSound("musicSelected");

				// Hide the main HUD window
				frameHUD.setVisible(false);

				// Open the play list window
				framePlayList();

				// set the focus the middle window
				playListA.requestFocusInWindow();
				break;
			case "TOPLEFT":
				/* Play select sound. */
				playSound("phoneSelected");
				
				if(tracking) { wrongSelections++;}
				
				break;
			case "TOPRIGHT":
				/* Play select sound. */
				playSound("GPSselected");
				if(tracking) { wrongSelections++;}
				break;
			case "BOTTOMLEFT":
				/* Play select sound. */
				playSound("contactsSelected");
				if(tracking) { wrongSelections++;}
				break;
			case "playlistA":
				/* Play select sound. */
				playSound("playlistSelected");
				if(tracking) { wrongSelections++;}
				break;
			case "playlistB":
				/* Play select sound. */
				playSound("playlistSelected");
				if(tracking) { wrongSelections++;}
				break;
			case "playlistC":
				/* Play select sound. */
				playSound("playlistSelected");
				if(tracking) { wrongSelections++;}
				break;
			case "playlistD":
				/* Play select sound. */
				playSound("playlistSelected");
				if(tracking) { wrongSelections++;}
				break;
			case "playlistE":
				
				if(tracking) { 
	
					// Log the end time
					endTime = System.currentTimeMillis();
					// Update the total time to complete
					totalTime = endTime - startTime;
		
					System.out.println("Total time taken: " + (double)totalTime/1000 +  " seconds");
				}
	
				/* Play select sound. */
				playSound("playlistSelected");
				try {
					Thread.sleep(1600);
					playSound("whatIsLoveFadeOut");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}
		}

		if (key == KeyEvent.VK_LEFT) {

			/* Play move sound. */
			playSound("messageSend");

			if (component == "TOPRIGHT") {

				topleft.requestFocusInWindow();
				topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_on.png")).getImage()).getScaledInstance(245, 245,
						java.awt.Image.SCALE_SMOOTH)));
			}

			if (component == "BOTTOMRIGHT") {

				bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_on.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				bottomleft.requestFocusInWindow();

			}

			repaint();

		} else if (key == KeyEvent.VK_RIGHT) {

			/* Play move sound. */
			playSound("messageSend");

			if (component == "TOPLEFT") {
				topright.setIcon(new ImageIcon(((new ImageIcon("topright_on.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				topright.requestFocusInWindow();
				
			}

			if (component == "BOTTOMLEFT") {

				bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_on.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.requestFocusInWindow();
			}

			repaint();

		} else if (key == KeyEvent.VK_UP) {

			/* Play move sound. */
			playSound("messageSend");

			if (component == "playlistA") {

			}

			if (component == "playlistB") {
				playSound("playlistA");
				playListA.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListA.requestFocusInWindow();
			}

			if (component == "playlistC") {
				playSound("playlistB");
				playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListB.requestFocusInWindow();
			}

			if (component == "playlistD") {
				playSound("playlistC");
				playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListC.requestFocusInWindow();
			}

			if (component == "playlistE") {
				playSound("playlistD");
				playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListE.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListD.requestFocusInWindow();
			}

			if (component == "BOTTOMLEFT") {

				topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_on.png")).getImage()).getScaledInstance(245, 245,
						java.awt.Image.SCALE_SMOOTH)));
				bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_off.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				topleft.requestFocusInWindow();
				//System.out.println("FOCUS NOW IN TOP LEFT WINDOW");
			}

			if (component == "BOTTOMRIGHT") {

				topright.setIcon(new ImageIcon(((new ImageIcon("topright_on.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_off.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				topright.requestFocusInWindow();
				//System.out.println("FOCUS NOW IN TOP RIGHT WINDOW");
			}

			repaint();

		} else if (key == KeyEvent.VK_DOWN) {

			/* Play move sound. */
			playSound("messageSend");

			if (component == "playlistA") {
				//playSound("playlistB");
				playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListA.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListB.requestFocusInWindow();
				playListA.setText("Playlist A");

			}

			if (component == "playlistB") {
				//playSound("playlistC");
				playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListB.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListC.requestFocusInWindow();
			}

			if (component == "playlistC") {
				//playSound("playlistD");
				playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListC.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListD.requestFocusInWindow();
			}

			if (component == "playlistD") {
				//playSound("playlistE");
				playListE.setIcon(new ImageIcon(((new ImageIcon("playlist_on.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListD.setIcon(new ImageIcon(((new ImageIcon("playlist_off.png")).getImage()).getScaledInstance(498,
						98, java.awt.Image.SCALE_SMOOTH)));
				playListE.requestFocusInWindow();
			}

			if (component == "playlistE") {


			}

			if (component == "TOPLEFT") {
				bottomleft.setIcon(new ImageIcon(((new ImageIcon("bottomleft_on.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				topleft.setIcon(new ImageIcon(((new ImageIcon("topleft_off.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				bottomleft.requestFocusInWindow();

			}
			if (component == "TOPRIGHT") {
				topright.setIcon(new ImageIcon(((new ImageIcon("topright_off.png")).getImage()).getScaledInstance(245,
						245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.setIcon(new ImageIcon(((new ImageIcon("bottomright_on.png")).getImage())
						.getScaledInstance(245, 245, java.awt.Image.SCALE_SMOOTH)));
				bottomright.requestFocusInWindow();

			}

			repaint();
		}

		
		// Debug
		if(tracking) { 
			System.out.println("Total key presses:" + totalKeyPresses);
			System.out.println("Total wrong key presses: " + wrongSelections);
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

	/**
	 * Play sound clip
	 * @param theSoundName
	 */
	public void playSound(String theSoundName) {
		// moveClip.stop();
		soundClips.get(theSoundName + ".wav").setMicrosecondPosition(0);
		soundClips.get(theSoundName + ".wav").start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
			if(participantName.getText().length() < 5) {
			   System.out.println("Nope");
			} else {
				
				frameParticipant.setVisible(false);
				nameOfParticipant = participantName.getText().trim();
				System.out.println("Participant" + participantName.getText());
				playSound("messageSend");
				frameHUD();
			}

	}
}