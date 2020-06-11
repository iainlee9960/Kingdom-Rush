package enemies;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Crab extends Enemy{
	int health = 600;
	int maxHealth = 600;
	int coins = 100;
	int value = 5;
	String name = "crab";
	String description = "bad troop";
	boolean flying = false;
	boolean inMotion = false;
	double speed = 1;
	int xpos;
	int ypos;
	int time;
	int size=55;
	int checkpointNum = 0;
	Point nextPoint;
	Image[] imgs;
	int current = 0;
	String direction="right";
	public Crab(Point start, int time) {
		this.time = time;
		dead = false;
		xpos = start.x;
		ypos = start.y;
		compileImages();
	}
	public void compileImages() {
		imgs = new Image[8];
		for(int i = 0; i<4; i++ ) {
			Image a = new ImageIcon("images/enemies/crab1.png").getImage();
			imgs[i] = a.getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
		}
		for(int i = 0; i<4; i++ ) {
			Image a = new ImageIcon("images/enemies/crab2.png").getImage();
			imgs[i+4] = a.getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
		}
	}
	public Point getNextPoint() {
		return nextPoint;
	}
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
		current = (current+1)%imgs.length;
		int x = Math.abs(nextPoint.x-xpos);
		int y = Math.abs(ypos-nextPoint.y);
		double theta = 0;
		try {
			theta = Math.atan(y/x);
		} catch(Exception e) {
			theta = Math.atan(y);
		}
		if(ypos<nextPoint.y) {
			ypos = ypos+(int)Math.round(speed*Math.sin(theta));
			direction="right";
		} else {
			ypos = ypos-(int)Math.round(speed*Math.sin(theta));
			direction="left";
		}
		if(xpos<nextPoint.x) {
			xpos = xpos+(int)Math.round(speed*Math.cos(theta));
			direction = "right";
		} else {
			xpos = xpos-(int)Math.round(speed*Math.cos(theta));
			direction="left";
		}
	}
	public Image getImage() {
		return imgs[current];
	}
	public boolean reachedCheckpoint() {
		if(checkpointNum==0) {
			return true;
		}
		if(Math.sqrt(Math.pow((nextPoint.getX()-xpos), 2)+Math.pow((nextPoint.getY()-ypos), 2))<2) {
			return true;
		}
		return false;
	}
	public void changeDestination(Point p) {
		nextPoint = p;
		checkpointNum++; 
	}
	public boolean isDead() {
		return dead;
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
	public double getHealthPercent() {
		return (double)health/(double)maxHealth;
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
