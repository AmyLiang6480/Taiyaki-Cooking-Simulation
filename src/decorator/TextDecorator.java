/* CLASS COMMENT:
 * A decorator that creates text on the paper bag.*/

package decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class TextDecorator extends BagDecorator{

	public TextDecorator(FinalTaiyaki ft, int x, int y, double sca) {
		super(ft, x, y, sca);
		img = ImageLoader.loadImage("assets/text.png");
	}
	
	public void decorBag(Graphics2D g2) {
		super.decorBag(g2);
		addPattern(g2);
	}
	
	private void addPattern(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}

}