package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ddf.minim.AudioPlayer;
import processing.core.PApplet;

public class Ball extends TObject implements ActionListener{
	private float xDir = 0;
	private float yDir = 5;
	private float speed = 2;
	private int size = 30;
	private float baseSpeed = 2;
	private int reversedX=0;
	private int reversedY=0;
	private boolean attacked = false;
	private boolean fasted = false;
	private boolean slowed = false;
	
	public int wasReversed(){
		return reversedY;
	}
	
	public void fastIt(){
		fasted = true;
	}
	
	public void slowIt(){
		slowed = true;
	}
	
	public void reset(){
		slowed = false;
		fasted = false;
	}
	
	public void actionPerformed(ActionEvent event){
		move();
	}
	
	public void setSpeed(float sp){
		speed=sp;
	}
	
	public void setBaseSpeed(float sp){
		baseSpeed = sp;
		setSpeed(baseSpeed);
	}
	
	public void unSpeed(){
		slowed = false;
		fasted = false;
	}
	
	public void draw(PApplet pa){
		pa.strokeWeight(3);
		pa.stroke(0);
		if(attacked)
			pa.fill(255,0,0);
		else
			pa.fill(100,255,30);
		pa.ellipse(getX(), getY(), size, size);
	}
	
	public int getSize(){
		return size;
	}
	
	public void move(){
	if(fasted){
		setX(getX()+xDir*speed*1.5f);
		setY(getY()+yDir*speed*1.5f);
	}
	else if(slowed){
		setX(getX()+xDir*speed*0.6f);
		setY(getY()+yDir*speed*0.6f);
	}
	else{
		setX(getX()+xDir*speed);
		setY(getY()+yDir*speed);
	}
		reversedX--;
		reversedY--;
	}
	
	public void setXDir(float x){
		xDir=x;
	}
	
	public void setYDir(float y){
		yDir=y;
	}
	
	public float getXDir(){
		return xDir;
	}
	
	public float getYDir(){
		return yDir;
	}
	
	public void reverseXDir(){
		if(reversedX<=0){
			reversedX=15;
			reversedY=0;
			xDir = xDir*(-1);
		}
	}
	
	public void setAttacked(boolean at){
		attacked = at;
		if(at==false)
			speed=baseSpeed;
	}
	
	public void reverseYDir(){
		if(reversedY<=0){
			reversedY=15;
			reversedX=0;
			yDir = yDir*(-1);
			if(attacked){
				speed=baseSpeed;
				attacked=false;
			}
		}
	}
	
	public int checkCollisions(TKicker...kickers){
		int point=0;
		for(TKicker kicker : kickers){
			point = kicker.checkCollision(this);
			if(point!=0)
				return point;
		}
		return 0;	
	}
	
	public void attack(TKicker raquet, boolean rev, AudioPlayer hit, AudioPlayer wind){
		speed = baseSpeed*1.5f;
		wind.rewind();
		wind.play();
		attacked = true;
		if(rev){
			hit.rewind();
			hit.play();
			reversedY=15;
			reversedX=0;
			yDir = yDir*(-1);
			if(getX()<raquet.getX()+raquet.getWidth()*0.05)
				setXDir(-5);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.13)
				setXDir(-4);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.25)
				setXDir(-3);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.33)
				setXDir(-2);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.45)
				setXDir(-1);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.55)
				setXDir(0);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.67)
				setXDir(1);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.75)
				setXDir(2);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.87)
				setXDir(3);
			else if(getX()<raquet.getX()+raquet.getWidth()*0.95)
				setXDir(4);
			else
				setXDir(5);
		}
		
	}
}
