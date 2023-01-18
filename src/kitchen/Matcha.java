/* CLASS COMMENT:
 * A subclass of Fillings that creates matcha filling object.*/

package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Matcha extends Fillings{

	public Matcha(double x, double y, double sca) {
		super(x, y, sca);
		img = ImageLoader.loadImage("assets/matcha.PNG");
	}
	
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
}