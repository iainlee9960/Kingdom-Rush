package stages;
import java.awt.*;

public class Checkpoint {
	Polygon area;
	public Checkpoint(int[] x, int [] y, int numPoints) {
		area = new Polygon(x, y, numPoints);
	}
	public boolean in(int x, int y) {
		return false;
	}
	public Point generatePoint(Shape region){
	    Rectangle r = region.getBounds();
	    int x, y;
	    do {
	        x = (int)(r.getX() + r.getWidth() * Math.random());
	        y = (int)(r.getY() + r.getHeight() * Math.random());
	    } while(!region.contains(x,y));
	    return new Point(x,y);
	}
}