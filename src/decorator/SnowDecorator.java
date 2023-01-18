/* CLASS COMMENT:
 * A decorator that creates the snow view on the window, 
 * fractal shapes of snowflake and trees are decorated in this class,
 * and present an animation of snow fall.*/

package decorator;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;
import util.Util;

public class SnowDecorator extends WindowDecorator{
	
	private Tree tree;
	private Snowflake[] snowflakes = new Snowflake[5];

	public SnowDecorator(Window bw, int x, int y, double sca) {
		super(bw, x, y, sca);
		img = ImageLoader.loadImage("assets/snow.png");
		tree = new Tree(910, 235, 0.5);
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i] = new Snowflake(Util.random(750, 930), Util.random(20, 170), Util.random(0.1, 0.18));
		}
	}
	
	public void showWindow(Graphics2D g2) {
		super.showWindow(g2);
		addSnowView(g2);
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i].move();
		}
	}
	
	private void addSnowView(Graphics2D g2) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);
		g2.scale(scale, scale);
		g2.drawImage(img, -img.getWidth()/2, -img.getHeight()/2, null);
		g2.setTransform(at);
		tree.draw(g2, 70, 10);
		for (int i = 0; i < snowflakes.length; i++) {
			snowflakes[i].drawSnowflake(g2);
		}
	}

}