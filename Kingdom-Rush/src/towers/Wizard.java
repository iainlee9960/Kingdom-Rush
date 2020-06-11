package towers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Wizard extends Tower {
	String towerType = "wizard";
	int damage = 12;
	int level = 0;
	int range = 150;
	int price;
	int x;
	int y;
	Image[] imgs;
	public Wizard(int xpos, int ypos) {
		this.imgs = new Image[5];
		this.x = xpos;
		this.y = ypos;
		compileImages();
	}
	public void compileImages() {
		Image b = new ImageIcon("images/towers/mage.png").getImage();
		imgs[0] = b.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image a = new ImageIcon("images/towers/Wizard2.png").getImage();
		imgs[1] = a.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING) ;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImg() {
		return imgs[level];
	}
	public int getDamage() {
		return damage;
	}
	public String getType() {
		return towerType;
	}
	public void upgrade() {
		level++;
		damage+=10;
		range+=25;
	}
	public int getRange() {
		return range;
	}
	public int getLevel() {
		return level;
	}
}
