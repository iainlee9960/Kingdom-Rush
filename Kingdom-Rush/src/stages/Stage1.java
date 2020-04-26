package stages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
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

import main.*;

public class Stage1 extends Stage implements MouseListener, MouseMotionListener, ActionListener{
	Timer timer = new Timer(5, this);
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
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		setVisible(true);
		compileImages();
		this.game = game;
	}
	public void initializeCheckpoints() {
		
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
		}
	}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(in(e, 385, 650, 587, 700)) {
			repaint();
		} else {
			repaint();
		}
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
		for(int i = 0; i<7; i++) {
			g2.drawImage(artillery,xPositions[i],yPositions[i]+10,this);
		}
		if(open || close) {
			moveDoors(g2);
			timer.start();
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
