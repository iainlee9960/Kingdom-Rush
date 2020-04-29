package enemies;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Goblin extends Enemy{
	int health = 20;
	String name = "goblin";
	String description = "bad troop";
	boolean flying = false;
	int speed = 2;
	int xpos;
	int ypos;
	int checkpointNum = 0;
	Point nextPoint;
	Image[] imgs;
	int current = 0;
	public Goblin(int xStart, int yStart) {
		imgs = new Image[1];
		xpos = xStart;
		ypos = yStart;
		compileImages();
	}
	public void compileImages() {
		Image a = new ImageIcon("images/enemies/goblin1.png").getImage();
		imgs[0] = a.getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING);
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
		int x = Math.abs(nextPoint.x-xpos);
		int y = Math.abs(ypos-nextPoint.y);
		double theta = Math.atan(y/x);
		if(ypos<nextPoint.y) {
			ypos = ypos+(int)(speed*Math.sin(theta));
		} else {
			ypos = ypos-(int)(speed*Math.sin(theta));
		}
		if(xpos<nextPoint.x) {
			xpos = xpos+(int)(speed*Math.cos(theta));
		} else {
			xpos = xpos-(int)(speed*Math.cos(theta));
		}
	}
	public Image getImage() {
		return imgs[current];
		//return img;
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
