package towers;

import java.awt.Image;

public abstract class Tower {
	String towerType;
	int damage;
	int level;
	int range;
	int price;
	int x;
	int y;
	Image img;
	public Tower() {
		
	}
	abstract void compileImages();
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImg() {
		return img;
	}
	public int getDamage() {
		return damage;
	}
	public String getType() {
		return towerType;
	}
	abstract public void upgrade();
	public int getRange() {
		return range;
	}
	public int getLevel() {
		return level;
	}
}
