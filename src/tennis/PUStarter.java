package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class PUStarter implements ActionListener{
	Tennis tennis;
	
	public PUStarter(Tennis t){
		tennis=t;
	}
	
	public void actionPerformed(ActionEvent e) {
		tennis.powerUp = new PowerUp(tennis);
		tennis.tUp.stop();
		tennis.tUp = new Timer(20, tennis.powerUp);
		tennis.tUp.start();
	}

}
