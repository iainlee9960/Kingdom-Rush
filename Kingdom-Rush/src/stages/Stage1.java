package stages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import enemies.*;
import main.*;

public class Stage1 extends Stage implements MouseListener, MouseMotionListener, ActionListener{
	ArrayList<Checkpoint> checkpoints;
	ArrayList<Wave> waves;
	Timer timer = new Timer(5, this);
	Timer troopTimer = new Timer(20, this);
	int[] xPositions = {590, 385, 590, 304, 394, 505, 524};
	int[] yPositions = {130, 150, 222, 320, 494, 470, 640};
	Run game;
	Image archer, mage, barracks, artillery, bar;
	Image leftGate = new ImageIcon("images/left gate.jpg").getImage();
	Image rightGate = new ImageIcon("images/right gate.jpg").getImage();
	Image map = new ImageIcon("images/stages/stage1.jpg").getImage();
	double xdim = 200, ydim = 50, bubbleVel = .2;
	int x = 535, vel = -5, turn = 0, stop = 0;
	boolean open = true, close = false;

	public Stage1(Run game) {
		initializeCheckpoints(); //backend
		createWaves(); //backend
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		setVisible(true);
		compileImages();
		this.game = game;
	}
	public void createWaves() {
		waves = new ArrayList<Wave>();
		Enemy[] enemies = new Enemy[1];
		Point i = checkpoints.get(0).generatePoint();
		
		enemies[0] = new Goblin(i.x, i.y);
		for(Enemy enemy: enemies) {
			enemy.changeDestination(checkpoints.get(1).generatePoint());
		}
		System.out.println("X: "+enemies[0].getX());
		System.out.println("Y: "+enemies[0].getY());
		waves.add(new Wave(enemies));
	}
	public void initializeCheckpoints() {
		checkpoints = new ArrayList<Checkpoint>();
		int[] x = {1050,1050,1100,1100};
		int[] y = {278,351,351,278};
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 814; x[1] = 735; x[2] = 787; x[3] = 834;
		y[0] = 264; y[1] = 304; y[2] = 345; y[3] = 293;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 691; x[1] = 694; x[2] = 762; x[3] = 737;
		y[0] = 103; y[1] = 159; y[2] = 160; y[3] = 112;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 598; x[1] = 618; x[2] = 598; x[3] = 549;
		y[0] = 96; y[1] = 154; y[2] = 175; y[3] = 132;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 515; x[1] = 495; x[2] = 557; x[3] = 580;
		y[0] = 253; y[1] = 309; y[2] = 325; y[3] = 282;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 262; x[1] = 314; x[2] = 261; x[3] = 229;
		y[0] = 316; y[1] = 362; y[2] = 372; y[3] = 349;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 298; x[1] = 296; x[2] = 247; x[3] = 242;
		y[0] = 437; y[1] = 514; y[2] = 482; y[3] = 452;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 638; x[1] = 638; x[2] = 662; x[3] = 665;
		y[0] = 444; y[1] = 518; y[2] = 516; y[3] = 453;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 640; x[1] = 641; x[2] = 701; x[3] = 668;
		y[0] = 583; y[1] = 660; y[2] = 616; y[3] = 592;
		checkpoints.add(new Checkpoint(x, y, 4));
		x[0] = 516; x[1] = 515; x[2] = 434; x[3] = 467;
		y[0] = 622; y[1] = 695; y[2] = 679; y[3] = 647;
		checkpoints.add(new Checkpoint(x, y, 4)); 
		x[0] = 420; x[1] = 420; x[2] = 500; x[3] = 500;
		y[0] = 813; y[1] = 833; y[2] = 834; y[3] = 815;
		checkpoints.add(new Checkpoint(x, y, 4));
	}
	public void compileImages() {
		Image a = new ImageIcon("images/towers/archer.png").getImage();
		archer = a.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image b = new ImageIcon("images/towers/mage.png").getImage();
		mage = b.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image c = new ImageIcon("images/towers/barracks.png").getImage();
		barracks = c.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image d = new ImageIcon("images/towers/artillery.png").getImage();
		artillery = d.getScaledInstance(125, 125, Image.SCALE_AREA_AVERAGING);
		Image e = new ImageIcon("images/extra/bar.png").getImage();
		bar = e.getScaledInstance(300, 141, Image.SCALE_AREA_AVERAGING);
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		System.out.println(x+", "+ y);
		if(in(e, 128, 184, 651, 730)) {
			close = true;
			System.out.println(x + ", " +vel);
			x = 0;
			vel = 5;
			repaint();
		} else if (in(e, 0,100,0,100)) {
			
		}
	}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		/*if(in(e, 385, 650, 587, 700)) {
			repaint();
		} else {
			repaint();
		}*/
	}	
	public boolean in(MouseEvent e, int x1, int x2, int y1, int y2) {
		int x = e.getX();
		int y = e.getY();
		if(x>x1 && x<x2 && y>y1 && y<y2) {
			return true;
		}
		return false;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(map, 0, 0, this);
		g2.drawImage(bar, -15, -10, this);
		g2.setColor(Color.red);
		for(Checkpoint point: checkpoints) {
			g2.fillPolygon(point.getArea());
		}
		drawEnemies(g2);
		drawTowers(g2);
		if(open || close) {
			moveDoors(g2);
			timer.start();
		}
		troopTimer.start();
	}
	public void drawTowers(Graphics g) {
		g.drawImage(bar, -15, -10, this);
		for(int i = 0; i<7; i++) {
			g.drawImage(mage,xPositions[i],yPositions[i]+10,this);
		}
	}
	public void drawEnemies(Graphics g) {
		Enemy[] enemies = waves.get(0).getEnemies();
		for(Enemy enemy: enemies) {
			System.out.println("X: "+enemy.getX());
			System.out.println("Y: "+enemy.getY());
			enemy.move();
			if(enemy.reachedCheckpoint()) {
				System.out.println("reached");
				enemy.changeDestination(checkpoints.get(enemy.getCheckpointNum()+1).generatePoint());
			}
			g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
		}
	}
	
	public void moveDoors(Graphics g) {
		g.drawImage(leftGate, x-535, 0, this);
		g.drawImage(rightGate, 1060-x, 0, this);
	}
	public void openDoors() {
		if(open) {
			x+=vel;
		} 
		if(x<0) {
			open = false;
		}
	}
	public void closeDoors() {
		if(close) {
			if(x>534 && turn<3) {
				vel *= -1;
				turn++;
			}
			if(x==500 && vel<0 && (turn==1||turn==2)) {
				vel *= -1;
			}
			if(x>534 && turn==3 && stop==20) {
				close = false;
				timer.stop();
			} else if(x>534 && turn==3 && stop<20) {
				stop++;
			}
			if(stop==0) {
				x+=vel;
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		openDoors();
		closeDoors();
		
		if(xdim>205 || xdim <200) {
			bubbleVel *= -1;
		}
		xdim += bubbleVel;
		ydim += bubbleVel;
		repaint();
	}
}
