/* CLASS COMMENT: 
 * A class that creates a TaiyakiApp object.
 * It is the default class that runs the application.*/

package main;

import javax.swing.JFrame;

public class TaiyakiApp extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TaiyakiApp(String title) {
		super(title);
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setLocation(0, 0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TaiyakiPanel bpnl = new TaiyakiPanel(this);
		this.add(bpnl); 
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public static void main (String[] args){
		new TaiyakiApp("TaiyakiApp");
		
	}
}