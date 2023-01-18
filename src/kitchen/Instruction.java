/* CLASS COMMENT:
 * A stand-alone class that creates instructions for actions or procedures for proceed.*/

package kitchen;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import util.ImageLoader;

public class Instruction {
	private double xPos;
	private double yPos;
	private String str, str2, str3;
	
	public Instruction(double x, double y) {
		xPos = x;
		yPos = y;
	}
	
	public void setInstruction(int instructionState) {
		if (instructionState == 0) {
			str = "Welcome to Taiyaki Simulation!";
			str2 = "Let's use mouse click and drag to interact";
			str3 = "with the simulation system :)";
		}
		else if (instructionState == 1) {
			str = "Step 1: "; 
			str2 = "Drag ingredients into the bowl"; 
			str3 = "to make batter!"; 
		}
		else if (instructionState == 2) {
			str = "Step 2:"; 
			str2 = "Now you get a bowl of batter."; 
			str3 = "Click the pan to open it!"; 
		}
		else if (instructionState == 3) {
			str = "Step 3:"; 
			str2 = "Pour the bowl of batter"; 
			str3 = "into the pan"; 
		}
		else if (instructionState == 4) {
			str = "Step 4:"; 
			str2 = "Choose a filling: Red bean,"; 
			str3 = "matcha, or chocolate";
		}
		else if (instructionState == 5) {
			str = "Step 5:"; 
			str2 = "Click to close the pan"; 
			str3 = "for cooking it"; 
		}
		else if (instructionState == 6) {
			str = "Step 6:"; 
			str2 = "Wait 5-8 seconds"; 
			str3 = "to cook it"; 
		}
		else if (instructionState == 7) {
			str = "Step 7:"; 
			str2 = "Decorate your paper bag!"; 
			str3 = "And click the bag to take it"; 
		}
		else if (instructionState == 8) {
			str = "Step 8:"; 
			str2 = "You made this! Y(OvO)Y. Click the"; 
			str3 = "'make another' button to play again"; 
		}
	}
	
	public void drawInstruction(Graphics2D g) {
		AffineTransform at = g.getTransform();
		g.translate(xPos, yPos);

		String st1 = str;
		String st2 = str2;
		String st3 = str3;
		Font f = new Font("Courier", Font.PLAIN, 12);
		FontMetrics metrics = g.getFontMetrics(f);

		float textWidth = metrics.stringWidth(st2);
		float textHeight = metrics.getHeight();
		float margin = 12;

		g.setColor(new Color(0, 0, 0, 80));
		g.fillRect((int) (-textWidth / 2 - margin),(int) (- textHeight /2 - margin * 2f),
				(int) (textWidth + margin * 2f), (int) (textHeight * 5f + margin * 2f));

		g.setColor(new Color(255, 222, 204));
		g.drawString(st1, -textWidth / 2,  textHeight/2*2f);
		g.drawString(st2, -textWidth / 2,  textHeight/2*2f + margin);
		g.drawString(st3, -textWidth / 2,  textHeight/2*4f + margin);
		
		g.setTransform(at);
	}

}

