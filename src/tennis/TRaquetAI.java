package tennis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import ddf.minim.AudioPlayer;

public class TRaquetAI extends TRaquet implements ActionListener{
	Ball ball;
	int legendWidth;
	float speed=1;
	boolean attPos;
	AudioPlayer wind;
	Timer timer;
	
	public TRaquetAI(float a, float b, float c, float d, Ball ba, int legW, Timer t, AudioPlayer h, AudioPlayer w){
		super(a, b, c, d, h);
		ball = ba;
		wind=w;
		timer=t;
		legendWidth = legW;
	}
	
	public void setAttPos(boolean at){
		attPos = at;
	}
	
	public void actionPerformed(ActionEvent event){
		if(ball.getYDir()>0){
			if(getX()+getWidth()/2>575 && getX()>legendWidth)
				setX(getX()-3*speed);
			else if(getX()+getWidth()/2<525)
				setX(getX()+3*speed);
		}
		else{
			if(getX()+getWidth()/2>ball.getX()+20 && getX()>legendWidth)
				setX(getX()-6*speed);
			else if(getX()+getWidth()/2<ball.getX()-20)
				setX(getX()+6*speed);
		}
	}
	
	public void setSpeed(float sp){
		speed=sp*1.6f;
	}
	
	public int checkCollision(Ball ball){
		if(ball.getX()+ball.getSize()/2>getX() && ball.getX()-ball.getSize()/2<(getX()+getWidth()) &&
				(ball.getY()-ball.getSize()/2)<=getY()+getHeight()){
			if(ball.wasReversed()<=0){
				hit.rewind();
				hit.play();
			}
			ball.reverseYDir();
			if(ball.getX()<getX()+getWidth()*0.15)
				ball.setXDir(-5);
			else if(ball.getX()<getX()+getWidth()*0.23)
				ball.setXDir(-4);
			else if(ball.getX()<getX()+getWidth()*0.33)
				ball.setXDir(-3);
			else if(ball.getX()<getX()+getWidth()*0.43)
				ball.setXDir(-2);
			else if(ball.getX()<getX()+getWidth()*0.47)
				ball.setXDir(-1);
			else if(ball.getX()<getX()+getWidth()*0.53)
				ball.setXDir(0);
			else if(ball.getX()<getX()+getWidth()*0.57)
				ball.setXDir(1);
			else if(ball.getX()<getX()+getWidth()*0.67)
				ball.setXDir(2);
			else if(ball.getX()<getX()+getWidth()*0.77)
				ball.setXDir(3);
			else if(ball.getX()<getX()+getWidth()*0.85)
				ball.setXDir(4);
			else
				ball.setXDir(5);
			if(attPos){
				attPos=false;
				timer.restart();
				ball.attack(this, false, null, wind);
			}
			ball.unSpeed();
			return 0;
		}
		else if(ball.getY()+ball.getSize()<getY()+getHeight())
			return 1;
		else
			return 0;
	}
}
