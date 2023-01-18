/* CLASS COMMENT:
 * A decorator that creates the day view on the window.*/

package decorator;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class NightDecorator extends WindowDecorator{

	public NightDecorator(Window bw, int x, int y, double sca) {
		super(bw, x, y, sca);
		img = ImageLoader.loadImage("assets/night.png");
	}
	
	public void showWindow(Graphics2D g2) {
		super.showWindow(g2);
		addNightView(g2);
	}
	
	private void addNightView(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}

}
