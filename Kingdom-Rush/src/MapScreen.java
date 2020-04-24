import java.awt.Color;
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

public class MapScreen extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	private Timer tm = new Timer(5, this);
	Run game;
	double xdim = 200;
	double ydim = 50;
	double vel = .2;
	public MapScreen(Run game) {
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
		int x = e.getX();
		int y = e.getY();
		System.out.println(x+", "+ y);
		if(in(e, 385, 650, 587, 700)) {
			System.out.println("start");
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
		Image intro = new ImageIcon("images/mapScreen.jpg").getImage();
		g2.drawImage(intro, 0, 0, this);
		
		g2.setColor(new Color(200,200,200));
		RoundRectangle2D rect = new RoundRectangle2D.Double(300- (xdim-200)/2, 300- (ydim-200), xdim, ydim, 10,10);
		g2.fill(rect);
		tm.start();
	}
	public void actionPerformed(ActionEvent e) {
		if(xdim>205 || xdim <200) {
			vel *= -1;
		}
		xdim += vel;
		ydim += vel;
		repaint();
	}
}
