/* CLASS COMMENT:
 * A decorator superclass that creates the base bag(taiyaki) 
 * offered to pattern, sticker, and text decorators.*/

package decorator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class BagDecorator implements FinalTaiyaki{
	protected FinalTaiyaki taiyaki;
	protected BufferedImage img;
	protected double scale;
	protected int xPos, yPos;
	
	public BagDecorator(FinalTaiyaki ft, int x, int y, double sca) {
		taiyaki = ft;
		xPos= x;
		yPos = y;
		scale = sca;
	}

	@Override
	public void decorBag(Graphics2D g2) {
		taiyaki.decorBag(g2);
	}
}
