package tennis;

import ddf.minim.AudioPlayer;
import processing.core.PApplet;

public class TRaquet extends TKicker{
	private int big = 0;
	private int small = 0;
	private int fast = 0;
	private int slow = 0;
	private float baseWidth;
	protected AudioPlayer hit;
	
	public void bigIt(){
		reset();
		setWidth(baseWidth*2f);
		big = 3;
	}
	
	public void smallIt(){
		reset();
		setWidth(baseWidth*0.5f);
		small = 3;
	}
	
	public void fastIt(){
		reset();
		fast = 3;
	}
	
	public void slowIt(){
		reset();
		slow = 3;
	}
	
	public void decreaseMods(){
		if(small==1 || big==1)
			setWidth(baseWidth);
		small--;
		big--;
		fast--;
		slow--;
	}
	
	public void reset(){
		small = 0;
		big = 0;
		fast = 0;
		slow = 0;
		setWidth(baseWidth);
	}
	
	public TRaquet(float a, float b, float c, float d, AudioPlayer h){
		super(a, b, c, d);
		hit = h;
		baseWidth=getWidth();
	}
	
	public void draw(PApplet pa){
		pa.stroke(0);
		pa.fill(0);
		pa.rect(getX(), getY(), getWidth(), getHeight());
		if(big>0){
			pa.fill(180,0,180);
			pa.rect(getX()+5, getY()+3, getWidth()-10, 14);
		}
		else if(small>0){
			pa.fill(0,0,255);
			pa.rect(getX()+5, getY()+3, getWidth()-10, 14);
		}
		else if(fast>0){
			pa.fill(255,255,0);
			pa.rect(getX()+5, getY()+3, getWidth()-10, 14);
		}
		else if(slow>0){
			pa.fill(180,180,180);
			pa.rect(getX()+5, getY()+3, getWidth()-10, 14);
		}
	}
	
	public int checkCollision(Ball ball){
		if(ball.getX()+ball.getSize()/2>getX() && ball.getX()-ball.getSize()/2<(getX()+getWidth()) &&
				(ball.getY()+ball.getSize()/2)>=getY()){
			if(ball.wasReversed()<=0){
				hit.rewind();
				hit.play();
				if(fast>0)
					ball.fastIt();
				else if(slow>0)
					ball.slowIt();
				decreaseMods();
			}
			
			ball.reverseYDir();
			if(ball.getX()<getX()+getWidth()*0.05)
				ball.setXDir(-5);
			else if(ball.getX()<getX()+getWidth()*0.13)
				ball.setXDir(-4);
			else if(ball.getX()<getX()+getWidth()*0.25)
				ball.setXDir(-3);
			else if(ball.getX()<getX()+getWidth()*0.33)
				ball.setXDir(-2);
			else if(ball.getX()<getX()+getWidth()*0.45)
				ball.setXDir(-1);
			else if(ball.getX()<getX()+getWidth()*0.55)
				ball.setXDir(0);
			else if(ball.getX()<getX()+getWidth()*0.67)
				ball.setXDir(1);
			else if(ball.getX()<getX()+getWidth()*0.75)
				ball.setXDir(2);
			else if(ball.getX()<getX()+getWidth()*0.87)
				ball.setXDir(3);
			else if(ball.getX()<getX()+getWidth()*0.95)
				ball.setXDir(4);
			else
				ball.setXDir(5);
			
			return 0;
		}
		else if(ball.getY()-ball.getSize()>getY())
			return -1;
		else
			return 0;
	}
}
