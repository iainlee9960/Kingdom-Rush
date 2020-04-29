package enemies;

import java.awt.Image;
import java.awt.Point;

public abstract class Enemy {
	int health;
	String name;
	String description;
	boolean flying;
	int speed;
	int xpos;
	int ypos;
	int checkpointNum = 0;
	Point nextPoint;
	Image[] imgs = {};
	int current = 0;
	
	abstract void compileImages();
	public String getName() {
		return name;
	}
	public int getX() {
		return xpos;
	}
	public int getY() {
		return ypos;
	}
	public int getCheckpointNum() {
		return checkpointNum;
	}
	public void move() {
		double theta = Math.atan(nextPoint.getY()/nextPoint.getX());
		xpos = (int)(speed*Math.cos(theta));
		ypos = (int)(speed*Math.sin(theta));
	}
	public Image getImage() {
		return imgs[current];
	}
	public boolean reachedCheckpoint() {
		if(Math.sqrt(Math.pow((nextPoint.getX()-xpos), 2)+Math.pow((nextPoint.getY()-ypos), 2))<5) {
			return true;
		}
		return false;
	}
	public void changeDestination(Point p) {
		nextPoint = p;
		checkpointNum++; 
	}
}
