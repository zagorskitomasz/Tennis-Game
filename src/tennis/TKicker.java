package tennis;

import processing.core.PApplet;

public abstract class TKicker extends TObject{
	private float height;
	private float width;
	
	public TKicker(float a, float b, float c, float d){
		setX(a);
		setY(b);
		width=c;
		height=d;
	}
	
	public void setWidth(float w){
		width = w;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void draw(PApplet pa){
		pa.stroke(0);
		pa.fill(0);
		pa.rect(getX(), getY(), width, height);
	}
	
	public abstract int checkCollision(Ball ball);
}
