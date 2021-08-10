package Handler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import App.Main;


public class PlayHandler implements ActionListener {

	private Main main;
	
	public PlayHandler(Main main) {
		this.main = main;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		main.startPlay();
		
	}

}
