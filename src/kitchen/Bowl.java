/* CLASS COMMENT:
 * A subclass of BaseButton that creates a bowl
 * ingredients will be dragged and put into the bowl to make a bowl of batter,
 * then the bowl of batter  will be dragged to pour into the pan
 */

package kitchen;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;

public class Bowl extends BaseButton {
	// constructor
	public Bowl(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/bowl.png");
	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	public void setButtonImg(int bowlState) {

	    if (bowlState == 0)

	        img = ImageLoader.loadImage("assets/bowl.png");

	    else if (bowlState == 1)

	        img = ImageLoader.loadImage("assets/bowl_batter.png"); 

	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	


	@Override
	public boolean hit(BaseButton pan) {
		boolean hit = false;
		if(Math.abs(xPos - pan.getX())< 50 && Math.abs(yPos - (pan.getY()-10))< 30) {
			hit = true;
		}
		return hit;
	}


	@Override
	public void setPos(double mouseX, double mouseY) {
		xPos = mouseX;
		yPos = mouseY;
	}

	
//		boolean clicked = false;
//		
//		if (x > (xPos - ((double) img.getWidth()) / 2 * sca) && x < (xPos + ((double) img.getWidth())/2*sca) && y > (yPos - ((double) img.getHeight())/2*sca) && y < (yPos + ((double) img.getHeight())/2*sca)) 
//			clicked = true;
//		
//		return clicked;
//	}

}