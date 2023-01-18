/* CLASS COMMENT:
 * A class that creates cooked taiyaki(designated goal of user).*/

package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Taiyaki extends BaseButton{

	public Taiyaki(double x, double y, double s) {
		super(x, y, s);
	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
	
	public void setButtonImg(int tState) {

	    if (tState == 0)

	        img = ImageLoader.loadImage("assets/taiyaki_redbean.png");
	    
	    else if (tState == 1)

	        img = ImageLoader.loadImage("assets/taiyaki_matcha.png"); 

	    else if (tState == 2)

	        img = ImageLoader.loadImage("assets/taiyaki_chocolate.png"); 
	}


	public boolean hit(BaseButton pan) {
		// TODO Auto-generated method stub
		return false;
	}

	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setPos(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		
	}

}
