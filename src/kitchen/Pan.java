/* CLASS COMMENT:
 * A subclass of BaseButton that creates a pan.
 * The pan could be opened and closed by clicking, filled with batter.
 * And run into cooking.
 */

package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Pan extends BaseButton{

	public Pan(double x, double y, double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/pan_closed.PNG");
	}

	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		g2.setTransform(transform);
	}
	
	public void setButtonImg(int panState) {

	    if (panState == 0)

	        img = ImageLoader.loadImage("assets/pan_closed.PNG");
	    
	    else if (panState == 1)

	        img = ImageLoader.loadImage("assets/pan_empty.png"); 

	    else if (panState == 2)

	        img = ImageLoader.loadImage("assets/pan_batter.png"); 
	    
	    else if (panState == 3)

	        img = ImageLoader.loadImage("assets/pan_redbean.png"); 
	    
	    else if (panState == 4)

	        img = ImageLoader.loadImage("assets/pan_matcha.png"); 
	    
	    else if (panState == 5)

	        img = ImageLoader.loadImage("assets/pan_chocolate.png"); 

	}
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}


	@Override
	public boolean hit(BaseButton pan) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setPos(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		
	}


}
