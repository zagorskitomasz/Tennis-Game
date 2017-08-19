package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import ddf.minim.AudioPlayer;
import processing.core.PApplet;

public class PowerUp extends TObject implements ActionListener{
	private float speed = 2;
	private int type;
	
	public PowerUp(Tennis t){
		Random gen = new Random();
		setX(t.legendWidth+40+(gen.nextInt(t.gameWidth-80)));
		setY(20);
		type = gen.nextInt(4);
		speed=3*t.diffLvl;
	}
	
	public void checkCollision(TRaquet raquet, AudioPlayer click){
		if(getX()>raquet.getX() && 
				getX()<raquet.getX()+raquet.getWidth() &&
				getY()>raquet.getY() &&
				getY()<raquet.getY()+20){
			click.rewind();
			click.play();
			setY(getY()+50);
			if(type==1)
				raquet.fastIt();
			else if(type==0)
				raquet.slowIt();
			else if(type==2)
				raquet.bigIt();
			else if(type==3)
				raquet.smallIt();
		}	
	}
	
	public int getType(){
		return type;
	}
	
	public void move(){
		setY(getY()+speed);
	}
	
	public void draw(PApplet pa) {
		if(type==1)
			pa.fill(255,255,0);
		else if(type==2)
			pa.fill(180,0,180);
		else if(type==3)
			pa.fill(0,0,255);
		else if(type==0)
			pa.fill(180,180,180);
		pa.strokeWeight(3);
		pa.quad(getX()-8, getY(), getX(), getY()-10, getX()+8, getY(), getX(), getY()+10);
	}

	public void actionPerformed(ActionEvent e) {
		move();
	}

}
