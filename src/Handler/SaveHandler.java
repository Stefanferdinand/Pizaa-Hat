package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import App.Main;

public class SaveHandler implements ActionListener{

	private Main main;
	private File saveFile;
	
	
	public SaveHandler(Main main, File saveFile) {
		this.main = main;
		this.saveFile = saveFile;
		try {
			saveFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Formatter newFile = null; 
		
		try {
			newFile = new Formatter(saveFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		newFile.format("%s %s %s %s %s %s %s", main.currStage, main.currPizzaHealth, main.xp, main.xpTreshold, main.tempProgVal, main.pizzaCount, main.power);
		
		newFile.close();
	}

}
