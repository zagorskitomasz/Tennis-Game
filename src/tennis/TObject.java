package tennis;

import processing.core.PApplet;

public abstract class TObject {
	private float xPos = 0;
	private float yPos = 0;
	
	public void setX(float x){
		this.xPos=x;
	}
	public void setY(float y){
		this.yPos=y;
	}
	
	public float getX(){
		return this.xPos;
	}
	public float getY(){
		return this.yPos;
	}
	
	public abstract void draw(PApplet pa);
}
