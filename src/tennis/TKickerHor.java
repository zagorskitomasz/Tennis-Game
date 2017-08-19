package tennis;

import processing.core.PApplet;

public class TKickerHor extends TKicker{
	boolean isLeft = false;
	
	public TKickerHor(float a, float b, float c, float d, boolean i){
		super(a, b, c, d);
		isLeft = i;
	}
	
	public void draw(PApplet pa){
		if(isLeft){
			pa.stroke(0);
			pa.fill(0);
			pa.rect(getX()+80, getY(), getWidth()-80, getHeight());
		}
		else
			super.draw(pa);
	}
	
	public int checkCollision(Ball ball){
		if( (isLeft && ball.getX()-ball.getSize()/2 < getX()+getWidth()) ||
				(!isLeft && ball.getX()+ball.getSize()/2 > getX())) 
			ball.reverseXDir();
		return 0;
	}
}
