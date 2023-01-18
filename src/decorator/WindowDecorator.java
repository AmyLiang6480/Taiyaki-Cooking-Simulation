/* CLASS COMMENT:
 * A decorator superclass that creates the baseWindow 
 * offered to day, night and snow decorators.*/

package decorator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WindowDecorator implements Window{
	protected Window baseWindow;
	protected BufferedImage img;
	protected double scale;
	protected int xPos, yPos;
	
	public WindowDecorator(Window bw, int x, int y, double sca) {
		baseWindow = bw;
		xPos= x;
		yPos = y;
		scale = sca;
	}

	@Override
	public void showWindow(Graphics2D g2) {
		baseWindow.showWindow(g2);
	}
	

}
