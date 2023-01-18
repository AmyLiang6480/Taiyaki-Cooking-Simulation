/* CLASS COMMENT:
 * A class that creates restart button for user to click 
 * to restart the simulation and play again.*/

package kitchen;

import static util.ImageLoader.loadImage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import util.ImageLoader;


public class RestartButton extends BaseButton {
	private boolean restart = false;

	// constructor
	public RestartButton(double x, double y,  double s) {
		super(x, y, s);
		img = ImageLoader.loadImage("assets/restart.PNG");
	}
 
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform(); // save(x~y)
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		
		g2.setTransform(transform);
	}
	
	public void setStart(boolean on){
		restart = on;
	}


	@Override
	public boolean hit(BaseButton pan) {
		// TODO Auto-generated method stub
		return false;
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

	public void setButtonImg(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPos(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
	}
}