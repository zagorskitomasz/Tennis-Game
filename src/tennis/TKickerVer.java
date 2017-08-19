package tennis;

public class TKickerVer extends TKicker{
	public TKickerVer(float a, float b, float c, float d){
		super(a, b, c, d);
	}
	
	public int checkCollision(Ball ball){
		if( 	(ball.getY()-ball.getSize()/2) < (getY()+getHeight()) && (ball.getY()-ball.getSize()/2) > (getY()) ||
				(ball.getY()+ball.getSize()/2) < (getY()+getHeight()) && (ball.getY()+ball.getSize()/2) > (getY())) 
			ball.reverseYDir();
		return 0;
	}
}
