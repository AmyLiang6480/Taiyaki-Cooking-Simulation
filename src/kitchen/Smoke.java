/* CLASS COMMENT:
 * A class that creates smoke using Perlin noise, 
 * added below the pan to make the cooking animation effect
 */

package kitchen;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import processing.core.PApplet;
import util.Util;

public class Smoke {
	private double xPos, yPos;
	private int width, height;

	private float xstart;
	private float xnoise;
	private float ynoise;
	private PApplet pa;

	public Smoke(double x , double y, int w, int h) {
		xPos = x;
		yPos = y;
		width = w;
		height = h;
		xstart = Util.random(10);
		xnoise = xstart;
		ynoise = Util.random(10);
		pa = new PApplet();
	}
	
	public void drawCloud(Graphics2D g2) {
		float noiseFactor;
		AffineTransform at = g2.getTransform();
		g2.translate(xPos, yPos);

		for(int y=0; y <=height; y += 3) {
			ynoise += 0.1;
			xnoise = xstart;
			for(int x= 0; x<=width; x += 3) {
				xnoise+= 0.1;
				noiseFactor = pa.noise(xnoise,ynoise);

				AffineTransform at1 = g2.getTransform();
				g2.translate(x, y);
				g2.rotate(noiseFactor*Util.radians(450));
				float edgeSize = noiseFactor * 35;
				int red = (int) (110 + (noiseFactor*100));
				int green = (int) (110 + (noiseFactor*100));
				int grey = (int) (105 + (noiseFactor*100));
				int alph = (int) (30 +(noiseFactor*105));
				g2.setColor(new Color(red,green,grey,alph));
				g2.fill(new Ellipse2D.Float(-edgeSize/2, -edgeSize/2, edgeSize, edgeSize*noiseFactor));
				g2.setTransform(at1);
			}

		}
		g2.setTransform(at);
	}

	public void setWidth(int i) {
		width = i;
		
	}

	public void setHeight(int i) {
		height = i;	
	}
}