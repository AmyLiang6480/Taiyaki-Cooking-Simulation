/* CLASS COMMENT:
 * A decorator that creates the day view on the window, 
 * and an animation of a rotating sun.*/

package decorator;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class DayDecorator extends WindowDecorator{
	private BufferedImage sun;
	private float angle = 0;

	public DayDecorator(Window bw, int x, int y, double sca) {
		super(bw, x, y, sca);
		img = ImageLoader.loadImage("assets/day.png");
		sun = ImageLoader.loadImage("assets/sun.png");
	}
	
	public void showWindow(Graphics2D g2) {
		super.showWindow(g2);
		angle += 0.03;
		addDayView(g2);
	}
	
	private void addDayView(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
		
		AffineTransform at2 = g2.getTransform();
		g2.translate(xPos-20, yPos-25);
		g2.scale(0.45, 0.45);
		g2.rotate(angle);
		g2.drawImage(sun, -sun.getWidth()/2, -sun.getHeight()/2, null);
		g2.setTransform(at2);
	}

}
