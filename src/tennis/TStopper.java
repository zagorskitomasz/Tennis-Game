package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TStopper implements ActionListener{
	protected Tennis tennis;
	
	public TStopper(Tennis t){
		tennis = t;
	}
	
	public void actionPerformed(ActionEvent event){
		tennis.attPos=true;
	}
}
