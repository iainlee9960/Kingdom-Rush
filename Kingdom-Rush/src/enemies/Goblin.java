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
		imgs[0] = a.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
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
		double theta = Math.atan((nextPoint.getY()-ypos)/(nextPoint.getX()-xpos));
		xpos = xpos-(int)(speed*Math.cos(theta));
		ypos = ypos-(int)(speed*Math.sin(theta));
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
