package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainMenu extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	private Timer timer = new Timer(5, this);
	Run game;
	boolean hover = false, changeScreen = false;
	int x = 0, vel = 5, turn = 0, stop = 0;
	Image intro2 = new ImageIcon("images/introGlow.jpg").getImage();
	Image intro1 = new ImageIcon("images/intro.jpg").getImage();
	Image leftGate = new ImageIcon("images/left gate.jpg").getImage();
	Image rightGate = new ImageIcon("images/right gate.jpg").getImage();
	MainMenu(Run game) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		setVisible(true);
		this.game = game;
	}
	public void mouseReleased(MouseEvent arg0) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		/*int x = e.getX();
		int y = e.getY();
		System.out.println(x+", "+ y);*/
		if(in(e, 385, 650, 587, 700)) {
			//System.out.println("start");
			changeScreen = true;
			repaint();
		}
	}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {
		if(in(e, 385, 650, 587, 700)) {
			hover = true;
			repaint();
		} else {
			hover = false;
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
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(hover) {
			g2.drawImage(intro2, 0, 0, this);
		} else {
			g2.drawImage(intro1, 0, 0, this);
		}
		if(changeScreen) {
			moveDoors(g2);
			timer.start();
		}
	}
	public void moveDoors(Graphics g) {
		g.drawImage(leftGate, x-535, 0, this);
		g.drawImage(rightGate, 1060-x, 0, this);
	}
	public void newMapScreen() {
		game.frame.getContentPane().removeAll();
		MapScreen map = new MapScreen(game);
		game.frame.getContentPane().add(map);
		game.frame.revalidate();
	}
	public void closeDoors() {
		if(x>534 && turn<3) {
			vel *= -1;
			turn++;
		}
		if(x==500 && vel<0 && (turn==1||turn==2)) {
			vel *= -1;
		}
		if(x>534 && turn==3 && stop==30) {
			changeScreen = false;
			timer.stop();
			newMapScreen();
		} else if(x>534 && turn==3 && stop<30) {
			stop++;
		}
		if(stop==0) {
			x+=vel;
		}
	}
	public void actionPerformed(ActionEvent e) {
		closeDoors();
		repaint();
	}
}





