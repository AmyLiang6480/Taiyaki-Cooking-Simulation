/* CLASS COMMENT:
 * A class creates a tree using fractals done by recursive functions
*/
package decorator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import kitchen.BaseButton;
import kitchen.Bowl;
import util.ImageLoader;

public class Tree extends BaseButton {

	public Tree(double x, double y, double s) {
		super(x, y, s);
	}
	
	public void draw(Graphics2D g2, float len, int branchWidth) {
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos); 
		g2.scale(scale, scale);
		drawTree(g2, len, branchWidth);
		g2.setTransform(at);
	}

	public void drawTree(Graphics2D g2, float len, int branchWidth) {
		g2.setStroke(new BasicStroke(branchWidth));
		g2.setColor(Color.white); 
		g2.draw(new Line2D.Float(0, 0, 0, -len));

		g2.translate(0, -len);

		len *= 0.75; 		
		branchWidth *= 0.75;
		
		if (len > 10) { 
			AffineTransform at = g2.getTransform();
			g2.rotate(Math.PI / 7); 
			drawTree(g2, len, branchWidth); 
			g2.setTransform(at);

			at = g2.getTransform();
			g2.rotate(-Math.PI / 7);
			drawTree(g2, len, branchWidth);
			g2.setTransform(at);
		}
	}
	
	
	public double getX() {
		return xPos;
	}
	
	public double getY() {
		return yPos;
	}
	
	public void setXPos(double x){
		xPos = x;
	}
	
	public void setYPos(double y){
		yPos = y;
	}
	
	public boolean hit(Bowl bowl) {
		boolean hit = false;
		if(Math.abs(xPos - bowl.getX())< 50 && Math.abs(yPos - bowl.getY())< 30) {
			hit = true;
		}
		return hit;
	}

	@Override
	public void drawButton(Graphics2D g2) {
		// TODO Auto-generated method stub
	}


	public boolean hit(BaseButton pan) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setButtonImg(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setPos(double mouseX, double mouseY) {
		// TODO Auto-generated method stub
		
	}

}
