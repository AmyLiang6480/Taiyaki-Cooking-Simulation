/* CLASS COMMENT:
 * A subclass of Ingredients that creates flour object.*/

package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Flour extends Ingredients{

	public Flour(double x, double y, double sca) {
		super(x, y, sca);
		img = ImageLoader.loadImage("assets/flour.PNG");
	}
	
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public void setButtonImg(int flourState) {

	    if (flourState == 0)

	        img = ImageLoader.loadImage("assets/flour.png");
	    
	    else if (flourState == 1)

	        img = ImageLoader.loadImage("assets/flour_plate.png"); 
	}
	
}
