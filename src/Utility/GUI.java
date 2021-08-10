package Utility;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.io.File;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

import App.Main;
import Handler.LoadHandler;
import Handler.PlayHandler;
import Handler.SaveHandler;

public class GUI {
	
	// general
	public JFrame frame = new JFrame("Pizaa-Hat");
	public JPanel panel = new JPanel();
	public JProgressBar fullBar = new JProgressBar();

	// images
	public ImageIcon pizzaEnemy = new ImageIcon(getClass().getResource("../Images/pizza_normal.png"));
	public ImageIcon pizzaTitle = new ImageIcon(getClass().getResource("../Images/pizza_title.png"));
	
	// labels
	public JLabel pizzaImageEnemy = new JLabel(pizzaEnemy);
	public JLabel gameTitle = new JLabel("Pizaa-Hat");
	public JLabel pizzaImageTitle = new JLabel(pizzaTitle);
	public JLabel pizzaHp = new JLabel();
	public JLabel pizzaCounter;
	public JLabel powerCounter;
	public JLabel stageCounter;
	public JLabel xpCounter;
	
	// buttons
	public JButton playButton = new JButton("PLAY");
	private JButton loadButton = new JButton("LOAD");
	private JButton saveButton = new JButton("SAVE");
	
	// extra
	private int fullTreshold = 100;
	

	public GUI(Main main, File saveFile) {
		initStartMenu(main, saveFile);
		initAttribute(main.pizzaCount, main.power, main.currStage, main.xpTreshold, main.xp);
	}
	
	// initialize starting menu
	public void initStartMenu(Main main, File saveFile) {
		panel.setBackground(Color.black);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		gameTitle.setForeground(Color.red);
		gameTitle.setFont(new Font("Arial", Font.PLAIN, 60));
		gameTitle.setAlignmentX(panel.CENTER_ALIGNMENT);
		
		pizzaImageTitle.setMaximumSize(new Dimension(370,300));
		pizzaImageTitle.setAlignmentX(Frame.CENTER_ALIGNMENT);
		pizzaImageTitle.setBorder(new EmptyBorder(6,0,0,0));
		
		initStartButtons();
		handleStartButtons(main, saveFile);
		checkSaveFile(saveFile);
		
		addToStartPanel();
		addToFrame();
	}
	
	private void addToFrame() {
		frame.add(panel);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(700, 700);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void addToStartPanel() {
		panel.add(gameTitle);
		panel.add(Box.createRigidArea(new Dimension(0, 10)));
		panel.add(pizzaImageTitle);
		panel.add(playButton);
		panel.add(Box.createRigidArea(new Dimension(0, 50)));
		panel.add(loadButton);
	}
	
	private void handleStartButtons(Main main, File saveFile) {
		PlayHandler playHandler = new PlayHandler(main);
		playButton.addActionListener(playHandler);
		
		LoadHandler loadHandler = new LoadHandler(main, saveFile);
		loadButton.addActionListener(loadHandler);
	}
	
	private void initStartButtons() {
		playButton.setAlignmentX(panel.CENTER_ALIGNMENT);
		playButton.setSize(300,80);
		playButton.setMaximumSize(playButton.getSize());
		playButton.setBackground(Color.yellow);
		playButton.setFont(new Font("Calibri", Font.BOLD, 50));
		
		loadButton.setAlignmentX(panel.CENTER_ALIGNMENT);
		loadButton.setSize(300,80);
		loadButton.setMaximumSize(playButton.getSize());
		loadButton.setFont(new Font("Calibri", Font.BOLD, 50));
	}
	
	private void checkSaveFile(File saveFile) {
		if(saveFile.exists()) {
			loadButton.setBackground(Color.yellow);
			loadButton.setForeground(Color.DARK_GRAY);
		}
		else {
			loadButton.setBackground(Color.DARK_GRAY);
			loadButton.setForeground(Color.white);
		}
	}
	
	private void initAttribute(int pizzaCount, int power, int currStage, int xpTreshold, int xp) {
		pizzaCounter = new JLabel("Pizza count: " + pizzaCount);
		powerCounter = new JLabel("Power: " + power);
		stageCounter = new JLabel("Stage " + currStage);
		xpCounter = new JLabel("XP: " + xp + " / " + xpTreshold);
	}
	
	public void render(int pizzaCount, int power, int currStage, int xpTreshold, int xp) {
		pizzaCounter.setText("Pizza count: " + pizzaCount);
		powerCounter.setText("Power: " + power);
		stageCounter.setText("Stage " + currStage);
		xpCounter.setText("XP: " + xp + " / " + xpTreshold);
		
		
		panel.revalidate();
		panel.repaint();
	}
	
	public void renderFull(int pizzaCount) {
		pizzaCounter.setText("Pizza count: " + pizzaCount);
		panel.revalidate();
		panel.repaint();
	}
	
	// initialize play interface
	public void initPlay(Main main, File saveFile) {
		// remove start menu to go to play menu
		panel.removeAll();
		panel.revalidate();
		panel.repaint();
		panel.setBackground(Color.DARK_GRAY);
		
		initPizza();
		initAttributes();
		initPlayButtons();
		handlePlayButtons(main, saveFile);
		
		addToPlayPanel();

	}
	
	private void initPizza() {
		pizzaImageEnemy.setAlignmentX(frame.CENTER_ALIGNMENT);
		pizzaImageEnemy.setAlignmentY(frame.CENTER_ALIGNMENT);
		
		pizzaHp.setAlignmentX(frame.CENTER_ALIGNMENT);
		pizzaHp.setAlignmentY(frame.CENTER_ALIGNMENT);
		pizzaHp.setFont( new Font("Arial", Font.PLAIN, 40));
		pizzaHp.setForeground(Color.white);
		
		pizzaCounter.setForeground(Color.yellow);
		pizzaCounter.setFont(new Font("Arial", Font.BOLD, 30));
		pizzaCounter.setAlignmentX(frame.CENTER_ALIGNMENT);
		pizzaCounter.setAlignmentY(frame.CENTER_ALIGNMENT);
	}
	
	private void initAttributes() {
		stageCounter.setAlignmentX(frame.CENTER_ALIGNMENT);
		stageCounter.setAlignmentY(frame.CENTER_ALIGNMENT);
		stageCounter.setForeground(Color.white);
		stageCounter.setFont(new Font("Arial", Font.PLAIN, 30));
		stageCounter.setBorder( new EmptyBorder(30, 0, 10, 0));
		
		powerCounter.setAlignmentX(frame.CENTER_ALIGNMENT);
		powerCounter.setAlignmentY(frame.CENTER_ALIGNMENT);
		powerCounter.setForeground(Color.red);
		powerCounter.setFont(new Font("Arial", Font.PLAIN, 30));
		
		xpCounter.setAlignmentX(frame.CENTER_ALIGNMENT);
		xpCounter.setAlignmentY(frame.CENTER_ALIGNMENT);
		xpCounter.setForeground(Color.white);
		xpCounter.setFont(new Font("Arial", Font.PLAIN, 20));
		
		fullBar.setAlignmentX(frame.CENTER_ALIGNMENT);
		fullBar.setAlignmentY(frame.CENTER_ALIGNMENT);
		fullBar.setMaximum(fullTreshold);
		fullBar.setMinimum(0);
		fullBar.setValue(0);
		fullBar.setStringPainted(true);
		fullBar.setForeground(Color.BLUE);
		fullBar.setMaximumSize(new Dimension(350,30));
		
	}
	
	private void initPlayButtons() {
		saveButton.setAlignmentX(frame.CENTER_ALIGNMENT);
		saveButton.setAlignmentY(frame.CENTER_ALIGNMENT);
		saveButton.setSize(300,100);
		saveButton.setBackground(Color.yellow);	
	}
	
	private void handlePlayButtons(Main main, File saveFile) {
		SaveHandler saveHandler = new SaveHandler(main, saveFile);
		saveButton.addActionListener(saveHandler);
	}
	
	private void addToPlayPanel() {
		panel.add(stageCounter);
		panel.add(pizzaImageEnemy);
		panel.add(pizzaHp);
		panel.add(xpCounter);
		panel.add(fullBar);
		panel.add(pizzaCounter);
		panel.add(powerCounter);
		panel.add(saveButton);
	}
	
	public void setBossPizza() {
		pizzaEnemy = new ImageIcon(getClass().getResource("../Images/pizza_boss.png"));
		pizzaImageEnemy.setIcon(pizzaEnemy);
	}
	
	public void setNormalPizza() {
		pizzaEnemy = new ImageIcon(getClass().getResource("../Images/pizza_normal.png"));
		pizzaImageEnemy.setIcon(pizzaEnemy);
	}
	

}
