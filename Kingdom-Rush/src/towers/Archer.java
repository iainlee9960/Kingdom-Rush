package towers;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Archer extends Tower {
	String towerType = "archer";
	int damage = 3;
	int level = 0;
	int range = 200;
	int price;
	int x;
	int y;
	Image[] imgs;
	public Archer(int xpos, int ypos) {
		this.imgs = new Image[5];
		this.x = xpos;
		this.y = ypos;
		compileImages();
	}
	public void compileImages() {
		Image b = new ImageIcon("images/towers/archer.png").getImage();
		imgs[0] = b.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image a = new ImageIcon("images/towers/Archer2.png").getImage();
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
		damage+=3;
		range+=25;
	}
	public int getRange() {
		return range;
	}
	public int getLevel() {
		return level;
	}
}
