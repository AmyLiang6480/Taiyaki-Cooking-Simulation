/* CLASS COMMENT:
 * A class creates a snowflake using fractals done by recursive functions. 
 * Source: https://discover.hubpages.com/technology/Java-Source-Code-Recursive-Snow-Flakes
*/

package decorator;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import kitchen.BaseButton;
import util.Util;
  
 
public class Snowflake extends BaseButton{
	private int level = 3;
	private int x1 = 20, y1 = 280;
	private int x2 = 160, y2 = 20;
	private int x3 = 300, y3 = 280;
	
	private double xPos, yPos;
	private double scale;
	
	public Snowflake(double x, double y, double s){
		super(x, y, s);
		xPos = x;
		yPos = y;
		scale = s;
	}

  
	public void drawSnowflake(Graphics2D g){
		AffineTransform transform = g.getTransform();
		g.translate(xPos, yPos);
		g.scale(scale, scale);
		g.setColor(Color.white);
	 
		drawFlake(level, x1, y1, x3, y3, g); 
		drawFlake(level, x2, y2, x1, y1, g); 
		drawFlake(level, x3, y3, x2, y2, g);
		//g.fill(null);
		g.setTransform(transform);
	}
 
	public void drawFlake(int order, double x1, double y1, double x5, double y5, Graphics2D g){
		double deltaX, deltaY, x2, y2, x3, y3, x4, y4;
		
	 
		if (order == 1) {
			g.setStroke(new BasicStroke(5));
			g.drawLine((int)x1, (int)y1, (int)x5, (int)y5); 
		}
		else{
			deltaX = x5 - x1; 
			deltaY = y5 - y1;
			 
			x2 = x1 + deltaX / 3;
			y2 = y1 + deltaY / 3;
			 
			x3 = ((x1 + x5) / 2 + (Math.sqrt(3.0) / 6) * (y1 - y5));
			y3 = ((y1 + y5) / 2 + (Math.sqrt(3.0) / 6) * (x5 - x1));
			 
			x4 = x1 + deltaX * 2 / 3;
			y4 = y1 + deltaY * 2 / 3;
			
			drawFlake(order - 1, x1, y1, x2, y2, g);
			drawFlake(order - 1, x2, y2, x3, y3, g);
			drawFlake(order - 1, x3, y3, x4, y4, g);
			drawFlake(order - 1, x4, y4, x5, y5, g);
		}
	}
	
	public void move() {
		yPos += 0.95;
		if (yPos>180) {
			xPos= Util.random(750, 930);
			yPos=10;
			scale = Util.random(0.1, 0.18);
		}
	}
	

	@Override
	public void drawButton(Graphics2D g2) {
		// TODO Auto-generated method stub
		
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
