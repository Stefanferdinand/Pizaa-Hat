package Utility;
import javax.swing.JProgressBar;

public class FullThread implements Runnable{

	private int fullness = 0;
	private JProgressBar prog;
	
	public int getFullness() {
		return fullness;
	}

	public void setFullness(int fullness) {
		this.fullness = fullness;
	}

	public JProgressBar getProg() {
		return prog;
	}

	public void setProg(JProgressBar prog) {
		this.prog = prog;
	}

	public FullThread(int f, JProgressBar b) {
		fullness = f;
		prog = b;
	}

	@Override
	public void run() {
		
		while(true) {
			
			prog.setValue(fullness);
			fullness++;
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	

}
