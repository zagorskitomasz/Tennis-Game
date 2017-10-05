package tennis;

import javax.swing.Timer;
import ddf.minim.*;
import processing.core.PApplet;
import processing.event.KeyEvent;

public class Tennis extends PApplet {
	float mode = 0;
	int starting=2;
	int point=0;
	boolean sound = true;
	
	Ball ball;
	TKicker wallLeft;
	TKicker wallRight;
	TRaquet raquet;
	TRaquetAI raquetAI;
	
	int gameWidth = 500;
	int legendWidth = 300;
	
	int scorePl = 0;
	int scoreAI = 0;
	String score, diff;
	
	boolean attPos = false;
	float diffLvl = 1;
	
	Timer tBall, tAI, tAtt, tAttAI, tUp, tUpSt;
	
	PowerUp powerUp;
	
	Minim minim;
	AudioPlayer ovation, hitPL, hitAI, attack, serve, fanfare, click;
	
	public void settings() {
		  size(gameWidth+legendWidth, 600, "processing.opengl.PGraphics3D");
		}
	
	public void setup(){
		ball = new Ball();
		
		minim = new Minim(this);
		ovation = minim.loadFile("ovation.mp3");
		hitPL = minim.loadFile("hitPL.mp3");
		hitAI = minim.loadFile("hitAI.mp3");
		attack = minim.loadFile("attack.mp3");
		fanfare = minim.loadFile("fanfare.mp3");
		serve = minim.loadFile("serve.mp3");
		click = minim.loadFile("click.mp3");
		
		wallLeft = new TKickerHor(legendWidth-80, 0, 100, height, true);
		wallRight = new TKickerHor(width-20, 0, 100, height, false);
		
		tAttAI = new Timer(5000, event -> this.raquetAI.setAttPos(true));
		
		int raquetSize = 80;
		raquet = new TRaquet(legendWidth+(gameWidth/2-raquetSize/2), height-20, raquetSize, 100, hitPL);
		raquetAI = new TRaquetAI(legendWidth+(gameWidth/2-raquetSize/2), -80, raquetSize, 100, ball, legendWidth, tAttAI, hitAI, attack);
	
		tBall = new Timer(20, ball);
		
		tAtt = new Timer(5000, event -> this.attPos = true);
		tAI = new Timer(20, raquetAI);
		
		tUp = new Timer(20, powerUp);
		tUpSt = new Timer(8000, event -> {
			this.powerUp = new PowerUp(this);
			this.tUp.stop();
			this.tUp = new Timer(20, this.powerUp);
			this.tUp.start();
		});
	}
	
	public void draw(){
		if(mode==0){
			background(50,50,100);
			fill(0,255,0);
			textSize(50);
			textAlign(CENTER);
			text("Tennis Game", width/2, 100);
			fill(255,0,0);
			textSize(25);
			text("Collect 5 points to win.", width/2, 160);
			text("Move your raquet by moving mouse.", width/2, 200);
			text("Use edges of your raquet to cach better angles.", width/2, 240);
			text("Press SPACE while hitting the ball to attack it.", width/2, 280);
			text("Attacking is possible every 5 seconds.", width/2, 320);
			text("Collect some power-ups.", width/2, 360);
			text("Press S to turn sound on/off.", width/2, 400);
			textSize(35);
			fill(0,255,0);
			text("Click to continue...", width/2, 490);
			textSize(16);
			fill(255,0,0);
			text("Author: Tomasz Zagorski (zagorskitomasz@gmail.com)", width/2, 550);
		}
		else if(mode==1){
			background(50,50,100);
			diffMenu();
		}
		else if(mode==2){
			if(starting==2){
				background(230,100,0);
				ball.draw(this);
				wallLeft.draw(this);
				wallRight.draw(this);
				raquetAI.draw(this);
				raquet.draw(this);
				drawLegend();
				textAlign(CENTER);
				textSize(40);
				fill(0);
				if(point==1){
					text("You won a point!", legendWidth+gameWidth/2, 350);
					starting--;
				}
				else if(point==-1){
					text("You lost a point!", legendWidth+gameWidth/2, 350);
					starting--;
				}
				else
					text("Click to start game...", legendWidth+gameWidth/2, 350);
			}
			else if(starting==1){
				delay(2800);
				if(scorePl>4 || scoreAI>4){
					if(scorePl>scoreAI){
						fanfare.rewind();
						fanfare.play();
					}
					mode=3;
				}
				else{
					serve.rewind();
					serve.play();
					startTimers();
					starting--;
				}
			}
			else{
				background(230,100,0);
				
				point = ball.checkCollisions(wallLeft, wallRight, raquetAI, raquet);
					
				if(point!=0)
					addPoint(point);
				
				ball.draw(this);
				if(powerUp!=null){
					powerUp.checkCollision(raquet, click);
					powerUp.draw(this);
				}
				wallLeft.draw(this);
				wallRight.draw(this);
				raquetAI.draw(this);
				raquet.draw(this);
				
				drawLegend();
			}
		}
		else if(mode==3){
			background(50,50,100);
			fill(0,255,0);
			textSize(50);
			textAlign(CENTER);
			text("Tennis Game", width/2, 120);
			fill(255,0,0);
			textSize(70);
			if(scorePl>scoreAI){
				text("You won " + scorePl + " : " + scoreAI, width/2, 250);
				textSize(40);
				text("Congratulations!", width/2, 330);
			}
			else{
				text("You lost " + scorePl + " : " + scoreAI, width/2, 250);
				textSize(40);
				text("Try again!", width/2, 330);
			}
			textSize(35);
			fill(0,255,0);
			text("Click to start new game, ESC for exit...", width/2, 450);
		}
	}
	
	public void addPoint(int point){
		ovation.rewind();
		ovation.play();
		stopTimers();
		ball.setAttacked(false);
		ball.setX(legendWidth+gameWidth/2);
		ball.setY(150);
		ball.setXDir(0);
		ball.setYDir(5);
		ball.reset();
		raquet.reset();
		attPos=false;
		raquetAI.setAttPos(false);
		if(point==1)
			scorePl++;
		else if(point==-1)
			scoreAI++;
		starting=2;
	}
	
	public void diffMenu(){
		fill(0,255,0);
		textSize(40);
		textAlign(CENTER);
		text("Choose difficulty level:", width/2, 130);
		
		strokeWeight(5);
		fill(0,255,0);
		rect(width/2-180, 200, 360, 60);
		fill(200,255,0);
		rect(width/2-180, 290, 360, 60);
		fill(255,200,0);
		rect(width/2-180, 380, 360, 60);
		fill(255,0,0);
		rect(width/2-180, 470, 360, 60);
		
		fill(0);
		text("AMATEUR", width/2, 245);
		text("SEMI-PRO", width/2, 335);
		text("PROFESSIONAL", width/2, 425);
		text("ROGER FEDERER", width/2, 515);
	}
	
	public void drawLegend(){
		textAlign(CENTER);
		fill(50,50,100);
		rect(0, 0, legendWidth, height);
		
		fill(0,255,0);
		textSize(30);
		text("Tennis game", legendWidth/2, 80);
		textSize(25);
		text(">>>>><<<<<", legendWidth/2, 110);
		textSize(25);
		fill(255, 255, 0);
		text("SCORE:", legendWidth/2, 155);
		text("YOU  vs  comp", legendWidth/2, 190);
		score = scorePl + " : " + scoreAI;
		fill(255, 0, 0);
		textSize(50);
		text(score, legendWidth/2, 245);
		
		textSize(20);
		fill(0,255,0);
		text("Difficulty: " + diff, legendWidth/2, 300);
		
		if(attPos)
			fill(0,255,0);
		else
			fill(255,0,0);
		
		strokeWeight(4);
		ellipse(legendWidth/2, 440, 100, 100);
		
		fill(0);
		textSize(20);
		text("ATTACK", legendWidth/2, 445);
		if(attPos){
			fill(0,255,0);
			text("PRESS SPACE", legendWidth/2, 530);
		}
	}
	
	
	public void mouseMoved(){
		if(mouseX>legendWidth+raquet.getWidth()/2 && mouseX<legendWidth+gameWidth-raquet.getWidth()/2)
			raquet.setX(mouseX-raquet.getWidth()/2);
	}
	
	public void mouseClicked(){
		if(mode==0){
			mode=1;
		}
		else if(mode==1){
			if(mouseX>width/2-180 && mouseX<width/2+180){
				if(mouseY>200 && mouseY<260){
					diffLvl=0.8f;
					diff="Amateur";
					runGame();
				}
				else if(mouseY>290 && mouseY<350){
					diffLvl=1f;
					diff="Semi-Pro";
					runGame();
				}
				else if(mouseY>380 && mouseY<440){
					diffLvl=1.5f;
					diff="Professional";
					runGame();
				}
				else if(mouseY>470 && mouseY<530){
					diffLvl=2;
					diff="Roger Federer";
					runGame();
				}
			}
				
		}
		else if(mode==2){
			if(starting==2){
				serve.rewind();
				serve.play();
				startTimers();
				starting=0;
			}
		}
		else if(mode==3){
			mode=1;
		}
	}
	
	public void keyPressed(KeyEvent e){
		if(key==' '){
			if(attPos){
				attPos=false;
				tAtt.restart();
				if(ball.getY()>height-100 &&
					(ball.getX()+ball.getSize()/2>raquet.getX()-20 &&
					ball.getX()-ball.getSize()/2<(raquet.getX()+raquet.getWidth()+20) && 
					!(ball.getY()-ball.getSize()>raquet.getY())))
					ball.attack(raquet, ball.getYDir()>0, hitPL, attack);
			}
		}
		
		if(mode==3 && e.getKeyCode()==27){
			System.exit(0);
		}
		
		if(key=='s' || key=='S'){
			if(sound){
				sound=false;
				ovation.mute();
				hitPL.mute();
				hitAI.mute();
				attack.mute();
				fanfare.mute();
				serve.mute();
				click.mute();
			}
			else{
				sound=true;
				ovation.unmute();
				hitPL.unmute();
				hitAI.unmute();
				attack.unmute();
				fanfare.unmute();
				serve.unmute();
				click.unmute();
			}
		}
	}
	
	public void startTimers(){
		tBall.start();
		tAI.start();
		tAtt.start();
		tAttAI.start();
		tUp.start();
		tUpSt.start();
	}
	
	public void stopTimers(){
		tBall.stop();
		tAI.stop();
		tAtt.stop();
		tAttAI.stop();
		tUp.stop();
		powerUp = null;
		tUp = new Timer(20, powerUp);
		tUpSt.stop();
	}
	
	public void runGame(){
		mode=2;
		ball.setBaseSpeed(2*diffLvl);
		raquetAI.setSpeed(diffLvl);
		ball.setX(legendWidth+gameWidth/2);
		ball.setY(150);
		ball.setXDir(0);
		ball.setYDir(5);
		scorePl=0;
		scoreAI=0;
		point=0;
		starting=2;
	}
	
	public static void main (String[] args) {
		PApplet.main(new String[] {"tennis.Tennis"});
	}
}
