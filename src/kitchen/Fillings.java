/* CLASS COMMENT:
 * A subclass of BaseButton so user can interact with,
 * also as superclass of 3 filling subclasses -- 
 * redbean filling, matcha filling, chocolate filling*/

package kitchen;

import static util.ImageLoader.loadImage;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Fillings extends BaseButton{
	
	public Fillings(double x, double y, double sca) {
		super(x, y, sca);
	}

	public void drawButton(Graphics2D g2) {
	}
	
	public void setPos(double mouseX, double mouseY) {
		xPos = mouseX;
		yPos = mouseY;
	}
	
	public boolean hit(BaseButton pan) {
		boolean hit = false;
		if(Math.abs(xPos - (pan.getX()+15))< 50 && Math.abs(yPos - (pan.getY()-10))< 30) {
			hit = true;
		}
		return hit;
	}



	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}


	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}


	public void setButtonImg(int i) {
		// TODO Auto-generated method stub
		
	}

}
	
	
