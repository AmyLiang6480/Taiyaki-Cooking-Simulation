/* CLASS COMMENT:
 * A subclass of Ingredients that creates egg object.*/

package kitchen;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Egg extends Ingredients{
	
	private boolean eggCracked;
	//private boolean eggIn = false;

	public Egg(double x, double y, double sca) {
		super(x, y, sca);
		img = ImageLoader.loadImage("assets/egg.PNG");
	}
	
	public void drawButton(Graphics2D g2) {
		AffineTransform transform = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);

		g2.drawImage(img, -img.getWidth() / 2, -img.getHeight() / 2, null);

		g2.setTransform(transform);
	}
	
//	@Override
//	public boolean hit(Bowl bowl) {
//		eggIn = true;
//		return super.hit(bowl);
//	}
	
	
	public void setButtonImg(int eggState) {

	    if (eggState == 0)

	        img = ImageLoader.loadImage("assets/egg.png");
	    
	    else if (eggState == 1)

	        img = ImageLoader.loadImage("assets/egg_one.png"); 

	    else if (eggState == 2)

	        img = ImageLoader.loadImage("assets/egg_crack.png"); 
	}

}
