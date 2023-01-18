/* CLASS COMMENT:
 * A class that creates the kitchen background environment.*/

package kitchen;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import main.TaiyakiPanel;
import util.ImageLoader;

public class Kitchen {
	private BufferedImage img;

	public Kitchen(String file) {
		img = ImageLoader.loadImage(file);
	}

	public void drawKitchen(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(0, 0);
		g2.scale(1, 1);
		g2.drawImage(img, 0, 0, TaiyakiPanel.W_WIDTH, TaiyakiPanel.W_HEIGHT, null);
		
		g2.setTransform(at);
	}
	
	public void setBGImg(int bgState) {
	    if (bgState == 0)

	        img = ImageLoader.loadImage("assets/bgImage.png"); 

	    else if (bgState == 1)

	        img = ImageLoader.loadImage("assets/kitchen_BG.png"); 
	}

}
