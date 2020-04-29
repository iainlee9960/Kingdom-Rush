package stages;
import java.awt.*;

public class Checkpoint {
	Polygon area;
	public Checkpoint(int[] x, int [] y, int numPoints) {
		int[] x1 = new int[x.length];
		int[] y1 = new int[y.length];
		for(int i =0; i<x.length;i++) {
			x1[i] = x[i]-10;
			y1[i] = y[i]-10;
		}
		area = new Polygon(x1, y1, numPoints);
	}
	public boolean in(int x, int y) {
		return false;
	}
	public Polygon getArea() {
		return area;
	}
	public Point generatePoint(){
	    Rectangle r = area.getBounds();
	    int x, y;
	    do {
	        x = (int)(r.getX() + r.getWidth() * Math.random());
	        y = (int)(r.getY() + r.getHeight() * Math.random());
	    } while(!area.contains(x,y));
	    return new Point(x,y);
	}
}
