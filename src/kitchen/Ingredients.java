/* CLASS COMMENT:
 * A subclass of BaseButton so user can interact with,
 * also as superclass of 4 ingredient subclasses -- 
 * sugar, egg, flour, milk*/

package kitchen;

import static util.ImageLoader.loadImage;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ingredients extends BaseButton{
	
	public Ingredients(double x, double y, double sca) {
		super(x, y, sca);
	}

	public void drawButton(Graphics2D g2) {
	}
	
	public void setPos(double mouseX, double mouseY) {
		xPos = mouseX;
		yPos = mouseY;
	}
	
	public void setButtonImg(int state) {}
	
	public boolean hit(BaseButton bowl) {
		boolean hit = false;
		if(Math.abs(xPos - bowl.getX())< 50 && Math.abs(yPos - (bowl.getY()-10))< 30) {
			hit = true;
		}
		return hit;
	}


	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}
}
	
	
