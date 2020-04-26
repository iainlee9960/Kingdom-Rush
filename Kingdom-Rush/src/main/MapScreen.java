package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import stages.*;
import enemies.*;

public class MapScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	Timer timer = new Timer(5, this);
	Run game;
	Image leftGate = new ImageIcon("images/left gate.jpg").getImage();
	Image rightGate = new ImageIcon("images/right gate.jpg").getImage();
	double xdim = 200, ydim = 50, bubbleVel = .2;
	int x = 535, vel = -5, turn = 0, stop = 0;
	boolean open = true, close = false;
	public MapScreen(Run game) {
		addMouseListener(this);
		addMouseMotionListener(this);
		setBackground(Color.black);
		setVisible(true);
		this.game = game;
	}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		//System.out.println(x+", "+ y);
		if(in(e, 128, 184, 651, 730)) {
			close = true;
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
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Image map = new ImageIcon("images/mapScreen.jpg").getImage();
		g2.drawImage(map, 0, 0, this);
		
		
		g2.setColor(new Color(255,245,195));
		RoundRectangle2D rect = new RoundRectangle2D.Double(58- (xdim-200)/2, 435- (ydim-200), xdim, ydim, 10,10);
		g2.fill(rect);
		g2.setColor(Color.black);
		g2.draw(rect);
		
		g2.setColor(Color.black);
		g2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN,(int)xdim/10));
		g2.drawString("START HERE", (int)(90-(xdim-200)/2), (int)(550-(ydim-200)/2));
		
		if(open || close) {
			moveDoors(g2);
		}
		timer.start();
	}
	public void moveDoors(Graphics g) {
		g.drawImage(leftGate, x-535, 0, this);
		g.drawImage(rightGate, 1060-x, 0, this);
	}
	
	public void newGame() {
		game.frame.getContentPane().removeAll();
		Stage1 g = new Stage1(game);
		game.frame.getContentPane().add(g);
		game.frame.revalidate();
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
			if(x>534 && turn==3 && stop==30) {
				close = false;
				timer.stop();
				newGame();
			} else if(x>534 && turn==3 && stop<30) {
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
