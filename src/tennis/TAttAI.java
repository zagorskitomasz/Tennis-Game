package tennis;

import java.awt.event.ActionEvent;

public class TAttAI extends TStopper{
	public TAttAI(Tennis t){
		super(t);
	}
	
	public void actionPerformed(ActionEvent event){
		tennis.raquetAI.setAttPos(true);
	}
}
