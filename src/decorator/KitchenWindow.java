/* CLASS COMMENT:
 * A class functions as a base(target component) where window decorators can apply upon.*/

package decorator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import util.ImageLoader;

import static main.TaiyakiPanel.*;

public class KitchenWindow implements Window{
	private BufferedImage img;
	private int xPos, yPos;
	private double scale;
	
	public KitchenWindow(int x, int y, double sca) {
		xPos= x;
		yPos = y;
		scale = sca;
		img = ImageLoader.loadImage("assets/window_frame.png");
	}

	@Override
	public void showWindow(Graphics2D g2){
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
	}

}