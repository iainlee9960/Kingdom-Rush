package enemies;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class DarkKnight extends Enemy{
	int health = 20;
	String name = "Dark Knight";
	String description = "heavily armored troop";
	boolean flying = false;
	boolean inMotion = false;
	int speed = 2;
	int xpos;
	int ypos;
	int time;
	int checkpointNum = 0;
	Point nextPoint;
	Image[] imgs;
	int current = 0;
	public DarkKnight(int xStart, int yStart, int time) {
		this.time = time;
		dead = false;
		imgs = new Image[8];
		xpos = xStart;
		ypos = yStart;
		compileImages();
	}
	public void compileImages() {
		for(int i = 0; i<4; i++ ) {
			Image a = new ImageIcon("images/enemies/darkknight1.png").getImage();
			imgs[i] = a.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
		}
		for(int i = 0; i<4; i++ ) {
			Image a = new ImageIcon("images/enemies/darkknight2.png").getImage();
			imgs[i+4] = a.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
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
		} else {
			ypos = ypos-(int)Math.round(speed*Math.sin(theta));
		}
		if(xpos<nextPoint.x) {
			xpos = xpos+(int)Math.round(speed*Math.cos(theta));
		} else {
			xpos = xpos-(int)Math.round(speed*Math.cos(theta));
		}
	}
	public Image getImage() {
		return imgs[current];
	}
	public boolean reachedCheckpoint() {
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
}
