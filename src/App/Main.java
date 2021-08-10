package App;

import Entities.BossPizza;
import Entities.NormalPizza;
import Entities.Pizza;
import Utility.FullThread;
import Utility.GUI;
import java.io.File;


public class Main implements Runnable{

	File saveFile = new File("src\\save\\playerfile.txt");
	
	// our attribute
	public int pizzaCount = 0;
	public int power = 10;
	public int xp = 0;
	public int currStage = 1;
	public int tresBase = 1;
	public int xpTreshold = 100 + (tresBase * 5);
	public int full = 0;
	public int fullTreshold = 100;
	public int currPizzaHealth = 0;
	public int tempProgVal = 0;
	
	// pizza
	private Pizza enemy;
	
	// gui
	private GUI gui;
	
	// thread
	private Thread playThread = new Thread(this);
	
	// constructor
	public Main() {
//		System.out.println(saveFile.getAbsoluteFile());
//		for(String filename : saveFile.list()) System.out.println(filename);
		gui = new GUI(this, saveFile);
		gui.initStartMenu(this, saveFile);
		
	}
	
	public void loadFile(int currStage, int currPizzaHealth, int xp, int xpTreshold, int full, int pizzaCount, int power) {
		this.currStage = currStage;
		this.currPizzaHealth = currPizzaHealth;
		this.xp = xp;
		this.xpTreshold = xpTreshold;
		this.full = full;
		this.pizzaCount = pizzaCount;
		this.power = power;
		
		gui.render(pizzaCount, power, currStage, xpTreshold, xpTreshold);
		
		startPlay();
	}
	
	public void startPlay() {
		
		gui.initPlay(this, saveFile);
		playThread.start();
		
	}
	
	
	// playThread
	@Override
	public void run() {	
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// progress bar thread
		FullThread f = new FullThread(full, gui.fullBar);
		Thread fullThread = new Thread(f);
		fullThread.start();

		
		while(true) {
			
			// boss mode
			if(currStage % 10 == 0) {
				enemy = new BossPizza(currStage);
				gui.setBossPizza();
			}
			// normal mode
			else {
				enemy = new NormalPizza(currStage);
				gui.setNormalPizza();
			}
			
			if(currPizzaHealth == 0) currPizzaHealth = enemy.getPizzaHealth();
			
			// main loop in thread
			while(currPizzaHealth > 0) {
				
				tempProgVal = f.getProg().getValue();
				
				// if full
				if(f.getProg().getValue() >= fullTreshold) {
					f.setFullness(0);
					f.getProg().setValue(0);
					
					pizzaCount += currStage;
					gui.renderFull(pizzaCount);
				}
				
				currPizzaHealth -= power;
				if(currPizzaHealth < 0) currPizzaHealth = 0;
				gui.pizzaHp.setText("HP:   " + currPizzaHealth + " / " + enemy.getPizzaHealth());
				
				try {
					Thread.sleep(600);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			

			if(enemy instanceof BossPizza) {
				xp += ((BossPizza) enemy).getEnemyXp();
			}
			else if(enemy instanceof NormalPizza){
				xp += ((NormalPizza) enemy).getEnemyXp();
			}
			
			// if level up
			if(xp >= xpTreshold) {
				
				power += power * 0.2;
				xp = 0;
				tresBase *= 2;
				xpTreshold = 100 + (tresBase * 5); // increase xpTreshold
			}
			
			currStage++;
			pizzaCount++;
			
			gui.render(pizzaCount, power, currStage, xpTreshold, xp);
			
		}
		
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
	
	

}
