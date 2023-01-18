/* CLASS COMMENT:
 * An abstract class that offered for objects which user can interact with.*/

package kitchen;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class BaseButton {
	protected double xPos;
	protected double yPos;
	protected double scale;
	protected BufferedImage img;
	
	public BaseButton(double x, double y,  double s) {
		xPos = x;
		yPos = y;
		scale = s;
	}
	
	public boolean clicked(double x, double y){
		boolean clicked = false;
		
		if(x > (xPos - ((double) img.getWidth())/2*scale) 
		&& x < (xPos + ((double) img.getWidth())/2*scale) 
		&& y > (yPos - ((double) img.getHeight())/2*scale) 
		&& y < (yPos + ((double) img.getHeight())/2*scale)) 
			clicked = true;
		
		return clicked;
	}
	
	public abstract void drawButton(Graphics2D g2);

	public void setStart(boolean b) {
		// TODO Auto-generated method stub	
	}

	public abstract void setButtonImg(int i);

	public abstract boolean hit(BaseButton pan);

	public abstract double getX();
	public abstract double getY();

	public abstract void setPos(double mouseX, double mouseY);

}
