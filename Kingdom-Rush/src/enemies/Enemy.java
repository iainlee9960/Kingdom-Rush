package enemies;

import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public abstract class Enemy {
	int health;
	int maxHealth;
	int coins;
	int value;
	String name;
	String description;
	boolean flying;
	boolean dead;
	boolean inMotion;
	double speed;
	int xpos;
	int ypos;
	int time;
	public int size;
	int checkpointNum = 0;
	Point nextPoint;
	Image[] imgs = {};
	int current = 0;
	String direction="right";
	abstract void compileImages();
	public String getName() {
		return name;
	}
	public boolean isTime(int time) {
		if(!inMotion && this.time == time) {
			inMotion = true;
			return true;
		} 
		if(inMotion) {
			return true;
		}
		return false;
	}
	public int getX() {
		return xpos;
	}
	public int getY() {
		return ypos;
	}
	public Point getNextPoint() {
		return nextPoint;
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
		if(checkpointNum==0) {
			return true;
		}
		if(Math.sqrt(Math.pow((nextPoint.getX()-xpos), 2)+Math.pow((nextPoint.getY()-ypos), 2))<5) {
			return true;
		}
		return false;
	}
	public double getHealthPercent() {
		return health/maxHealth;
	}
	public void changeDestination(Point p) {
		nextPoint = p;
		checkpointNum++; 
	}
	public boolean isDead() {
		return dead;
	}
	public void die() {
		dead = true;
	}
	public void takeDamage(int damage) {
		this.health-=damage;
	}
	public int getHealth() {
		return health;
	}
	public int coins() {
		return coins;
	}
	public int getValue() {
		return value;
	}
	public int getSize() {
		return size;
	}
	public String getDirection() {
		return direction;
	}
	public boolean inMotion() {
		return inMotion;
	}
}



















