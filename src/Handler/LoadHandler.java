package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import App.Main;


public class LoadHandler implements ActionListener{

	private Main main;
	private File saveFile;
	
	public LoadHandler(Main main, File saveFile) {
		this.main = main;
		this.saveFile = saveFile;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Scanner scan = null;
		
		try {
			scan = new Scanner(saveFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String[] strarr = new String[10];
		int counter = 0;
		
		while(scan.hasNext()) {
			strarr[counter] = scan.next();
			counter++;
		}
		
		int[] inarr = new int[10];
		
		for(int i = 0 ; i < counter; i++) {
			inarr[i] = Integer.parseInt(strarr[i]);
		}
		
		main.loadFile(inarr[0], inarr[1], inarr[2], inarr[3], inarr[4], inarr[5], inarr[6]);
		
	}
	

}
