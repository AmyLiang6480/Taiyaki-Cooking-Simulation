/* CLASS COMMENT:
 * A class that creates a panel.
 * Simulation states change at here, and GUI buttons are instantiated at here*/

/*ECOs:
 * sophisticated complex shapes that are well polished and sensible to the app: snowflakes(fractal), smoke(perlin noise)
 * day/night shifting through GUI-based interactions(window interface)
 * seasonal change involved through GUI-based interactions(window interface)
 * preferred decorator pattern features: window and paper bag
 * all images used are self-created with excellent quality
 * add multiple states to objects(for instance, drag one egg from egg basket, milk is poured when drag it)
 * state changes after multiple conditions achieved: drag 4 ingredients in state 1
 * offers more than one options for user(choose the fillings they like to add into the taiyaki)
 * change the following objects' forms based on decision(fillings choice)
*/

package main;

import static util.ImageLoader.loadImage;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.Timer;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import decorator.DayDecorator;
import decorator.FinalTaiyaki;
import decorator.KitchenWindow;
import decorator.NightDecorator;
import decorator.PaperBag;
import decorator.PatternDecorator;
import decorator.SnowDecorator;
import decorator.StickerDecorator;
import decorator.TextDecorator;
import decorator.Window;
import kitchen.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import util.ImageLoader;
import util.MinimHelper;

public class TaiyakiPanel extends JPanel implements ActionListener {
	public static int W_WIDTH = 1050;
	public static int W_HEIGHT = 750;

	// variables for holding mouse position
	private double mouseX;
	private double mouseY;

	// Fields for state and transitions
	private int state = 0;
	
	private Kitchen kitchen;

	private Smoke smoke;
	private Instruction message;
	//private RestartButton restartBtn;
	private ArrayList<Ingredients> foodList;
	private ArrayList<Fillings> fillingList;
	
	private boolean milkin = false;
	private boolean eggin = false;
	private boolean sugarin = false;
	private boolean flourin = false;
	
	private boolean redbean = false;
	private boolean matcha = false;
	private boolean chocolate = false;
	
	private boolean remove = false;

	private Timer timer;
	private JFrame frame;
	
	private int bakeTimer = 0;
	private boolean halfDone = false;
	
	private Minim minim;
	private AudioPlayer intro, click, bgm, egg, milk, flour, sugar, drag, open, close, cooking, wrapping;
	
	Window window;
	FinalTaiyaki tbag;
	private boolean bagCreated = false;
	private boolean windowCreated = false;
	
	JButton dayDecor, nightDecor, snowDecor;
	JButton patternDecor, textDecor, stickerDecor, allDecor;
	
	FoodFactory foodMaker;
	private BaseButton startBtn;
	private BaseButton restartBtn;
	private BaseButton bowl;
	private BaseButton pan;
	private BaseButton tiyk;

	TaiyakiPanel(JFrame frame) {
		this.frame = frame;
		this.setBackground(new Color(58, 77, 0));
		setPreferredSize(new Dimension(W_WIDTH, W_HEIGHT));
//		
		foodMaker = new FoodConcreteFactory();
		startBtn = foodMaker.createFood("startBtn");
		restartBtn = foodMaker.createFood("restartBtn");
		bowl = foodMaker.createFood("bowl");
		pan = foodMaker.createFood("pan");
		tiyk = foodMaker.createFood("tiyk");
		
		this.foodList = new ArrayList<>();
		this.fillingList = new ArrayList<>();

		kitchen = new Kitchen("assets/bgImage.png");
	    smoke = new Smoke(pan.getX() - 104, pan.getY() - 230, 210, 300);
	    message = new Instruction(155, 70);
	    
//        foodList.add(foodMaker.createFood("milk"));
//        foodList.add(foodMaker.createFood("sugar"));
//        foodList.add(foodMaker.createFood("egg"));
//        foodList.add(foodMaker.createFood("flour"));

	    foodList.add(new Milk(W_WIDTH / 2-100, W_HEIGHT / 2 - 180, 0.28));
	    foodList.add(new Flour(W_WIDTH / 2-300, W_HEIGHT / 2 - 160, 0.32));
	    foodList.add(new Sugar(W_WIDTH / 2-460, W_HEIGHT / 2 - 140, 0.15));
	    foodList.add(new Egg(W_WIDTH / 2+70, W_HEIGHT / 2 - 150, 0.28));
	    
	    fillingList.add(new Redbean(W_WIDTH / 2-120, W_HEIGHT / 2 - 150, 0.18));
	    fillingList.add(new Matcha(W_WIDTH / 2-300, W_HEIGHT / 2 - 150, 0.18));
	    fillingList.add(new Chocolate(W_WIDTH / 2+70, W_HEIGHT / 2 - 150, 0.18));
		
		minim = new Minim(new MinimHelper());

		intro = minim.loadFile("intro.mp3");
		click = minim.loadFile("tap.mp3");
		bgm = minim.loadFile("bgm.mp3");
		egg = minim.loadFile("egg.mp3");
		sugar = minim.loadFile("sugar.mp3");
		flour = minim.loadFile("flour.mp3");
		milk = minim.loadFile("milk.mp3");
		open = minim.loadFile("pan_open.mp3");
		close = minim.loadFile("pan_close.mp3");
		drag = minim.loadFile("drag.mp3");
		cooking = minim.loadFile("pan_cooking.mp3");
		wrapping = minim.loadFile("wrapping.mp3");
		
		MyMouseListener ml = new MyMouseListener();
		addMouseListener(ml);
		addMouseMotionListener(new MyMouseMotionListener());

		timer = new Timer(30, this);
		timer.start();
		intro.loop();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		//opening screen
		if (state == 0) {
			startBtn.setStart(false);
			kitchen.drawKitchen(g2);
			startBtn.drawButton(g2);
			message.setInstruction(0);
			message.drawInstruction(g2);
		} 
		
		//drag 4 ingredients into the bowl
		else if (state == 1) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(1);
			message.drawInstruction(g2);
			bowl.drawButton(g2);
			//for(Ingredients f : foodList) f.drawButton(g2);
			for(BaseButton f : foodList) f.drawButton(g2);
			//whisk.draw(g2, 140, 15);
		}
		
		//get a bowl of batter, pan appears, click the pan to open it
		else if (state == 2) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(2);
			message.drawInstruction(g2);
			bowl.drawButton(g2);
			pan.drawButton(g2);
			
			//for(Ingredients f : foodList) f.drawButton(g2);
		}
		
		//drag the bowl of batter, pour into the pan
		else if (state == 3) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(3);
			message.drawInstruction(g2);
			pan.drawButton(g2);
			bowl.drawButton(g2);
		}
		
		//choose a filling to add
		else if (state == 4) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(4);
			message.drawInstruction(g2);
			pan.drawButton(g2);
			for(Fillings f : fillingList) f.drawButton(g2);
		}
		
		//click the pan to close it to cook
		else if (state == 5) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(5);
			message.drawInstruction(g2);
			if (redbean) pan.setButtonImg(3);
			if (matcha) pan.setButtonImg(4);
			if (chocolate) pan.setButtonImg(5);
			pan.drawButton(g2);
		}
		
		//cooking
		else if (state == 6) {
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			message.setInstruction(6);
			message.drawInstruction(g2);
			smoke.drawCloud(g2);
			pan.drawButton(g2);
		}
		
		//finish
		else if (state == 7) {
			cooking.pause();
			startBtn.setStart(true);
			kitchen = new Kitchen("assets/kitchen_BG.png");
			kitchen.drawKitchen(g2);
			//pan.drawButton(g2);
			message.setInstruction(7);
			message.drawInstruction(g2);
			if (redbean) tiyk.setButtonImg(0);
			if (matcha) tiyk.setButtonImg(1);
			if (chocolate) tiyk.setButtonImg(2);
			tiyk.drawButton(g2);
			if (tbag != null)
				tbag.decorBag(g2);
		}
		
		//ending screen, restart button
		else if (state == 8) {
			window = null;
			bgm.pause();
			restartBtn.setStart(false);
			if(redbean) {
				kitchen = new Kitchen("assets/redbean_ending.png");
			}
			if(matcha) {
				kitchen = new Kitchen("assets/matcha_ending.png");
			}
			if(chocolate) {
				kitchen = new Kitchen("assets/chocolate_ending.png");
			}
			kitchen.drawKitchen(g2);
			restartBtn.drawButton(g2);
			message.setInstruction(8);
			message.drawInstruction(g2);
		}
		
		if (state!=0 && window != null && state!=8)
			window.showWindow(g2);
	}
	
	public void kitchenWindow() {
		window = new KitchenWindow(W_WIDTH / 2+330, W_HEIGHT / 2 - 250, 0.4);
		setLayout(null);
		
		dayDecor= new JButton("Day view");
		dayDecor.setBackground(new Color(200, 200, 0));
		dayDecor.setBounds(W_WIDTH / 2+150, 250, 100, 40);
		add(dayDecor);
		dayDecor.addActionListener(this);
		
		nightDecor = new JButton("Night view");
		nightDecor.setBackground(new Color(100, 0, 200));
		nightDecor.setBounds(W_WIDTH / 2+280, 250, 100, 40);
		add(nightDecor);
		nightDecor.addActionListener(this);
		
		snowDecor = new JButton("Snow view");
		snowDecor.setBackground(new Color(100, 0, 200));
		snowDecor.setBounds(W_WIDTH / 2+420, 250, 100, 40);
		add(snowDecor);
		snowDecor.addActionListener(this);
	}
	
	public void taiyakiBag() {
		tbag = new PaperBag(W_WIDTH / 2, W_HEIGHT / 2+60, 0.7);
		setLayout(null);
		
		patternDecor = new JButton("Add pattern");
		if(remove) patternDecor.setVisible(false);
		patternDecor.setBackground(new Color(200, 200, 0));
		patternDecor.setBounds(150, 620, 150, 40);
		add(patternDecor);
		patternDecor.addActionListener(this);
		
		textDecor = new JButton("Add text");
		textDecor.setBackground(new Color(100, 0, 200));
		textDecor.setBounds(350, 620, 150, 40);
		add(textDecor);
		textDecor.addActionListener(this);
		
		stickerDecor = new JButton("Add sticker");
		stickerDecor.setBackground(new Color(100, 0, 200));
		stickerDecor.setBounds(550, 620, 150, 40);
		add(stickerDecor);
		stickerDecor.addActionListener(this);
		
		allDecor = new JButton("All decoration");
		allDecor.setBackground(new Color(200, 200, 0));
		allDecor.setBounds(750, 620, 150, 40);
		add(allDecor);
		allDecor.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(state!=0 && !windowCreated) {
			kitchenWindow(); 
			windowCreated = true;
		}
		
		if(state == 7 && !bagCreated) {
			if(!wrapping.isPlaying()) wrapping.play(0);
			taiyakiBag();
			bagCreated = true;
		}
		
		if (state ==8) {
			dayDecor.setVisible(false);
			nightDecor.setVisible(false);
			snowDecor.setVisible(false);
			patternDecor.setVisible(false);
			textDecor.setVisible(false);
			stickerDecor.setVisible(false);
			allDecor.setVisible(false);
		}
		
		
		if (state == 6) {
			//smoke.setWidth(bakeTimer / 2);
			//smoke.setHeight(bakeTimer / 8);
			if(!cooking.isPlaying())cooking.play(0);
			if (!halfDone) {
				bakeTimer++;
				if (bakeTimer >= 10) {
					bakeTimer = 10;
					halfDone = true;
				}
			} else {
				bakeTimer--;
				if (bakeTimer <= 0) {
					bakeTimer = 0;
					state = 7;
				}
			}
		}
		

			if (e.getActionCommand() == "Day view") {
				//Window baseWindow = new KitchenWindow(W_WIDTH / 2+260, W_HEIGHT / 2 - 250, 0.38);
				window = new DayDecorator(window, W_WIDTH / 2+330, W_HEIGHT / 2 - 250, 0.395);
			}
			
			if (e.getActionCommand() == "Night view") {
				//Window baseWindow = new KitchenWindow(W_WIDTH / 2+260, W_HEIGHT / 2 - 250, 0.38);
				window = new NightDecorator(window ,W_WIDTH / 2+330, W_HEIGHT / 2 - 250, 0.395);
			}
			
			if (e.getActionCommand() == "Snow view") {
				//Window baseWindow = new KitchenWindow(W_WIDTH / 2+260, W_HEIGHT / 2 - 250, 0.38);
				window = new SnowDecorator(window,W_WIDTH / 2+330, W_HEIGHT / 2 - 250, 0.395);
			}
			
			
			if (e.getActionCommand() == "Add sticker") {
				tbag = new StickerDecorator(tbag, W_WIDTH / 2+40, W_HEIGHT / 2+50, 0.9);
			}
			
			if (e.getActionCommand() == "Add text") {
				tbag = new TextDecorator(tbag, W_WIDTH / 2+40, W_HEIGHT / 2+180, 0.8);
			}
			
			if (e.getActionCommand() == "Add pattern") {
				tbag = new PatternDecorator(tbag, W_WIDTH / 2+30, W_HEIGHT / 2+66, 0.72);
			}
			
			if (e.getActionCommand() == "All decoration") {
				tbag = new PatternDecorator(tbag, W_WIDTH / 2+30, W_HEIGHT / 2+66, 0.72);
				tbag = new StickerDecorator(tbag, W_WIDTH / 2+40, W_HEIGHT / 2+50, 0.9);
				tbag = new TextDecorator(tbag, W_WIDTH / 2+40, W_HEIGHT / 2+180, 0.8);
			}

		repaint();
	}

	public class MyMouseListener extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (state == 0 && startBtn.clicked(mouseX, mouseY)) {
				kitchen.setBGImg(2);
				click.play(0);
				state = 1;
				intro.pause();
				bgm.loop();
			}
		
			if(state == 2 && pan.clicked(mouseX, mouseY)) {
				open.play(0);
				pan.setButtonImg(1);
				state = 3;
			}
			
			if(state == 5 && pan.clicked(mouseX, mouseY)) {
				close.play(0);
				pan.setButtonImg(0);
				state = 6;
			}
			
			if(state == 7 && tiyk.clicked(mouseX, mouseY)) {
				state = 8;
			}else if (state == 8 && restartBtn.clicked(mouseX, mouseY)) {
				restart();
			}
			
		}
	}
	
	public void restart() {
		frame.dispose();
		frame = new TaiyakiApp("My Taiyaki App");
	}
	
	public class MyMouseMotionListener extends MouseMotionAdapter{
		@Override
		public void mouseDragged(MouseEvent e) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			for(BaseButton f : foodList) {
			//for(Ingredients f : foodList) {
				if(state==1 && f.clicked(mouseX, mouseY)) {
					f.setPos(mouseX, mouseY);
					if (f instanceof Egg) f.setButtonImg(1);
					if (f instanceof Sugar) f.setButtonImg(1);
					if (f instanceof Milk) f.setButtonImg(1);
					if (f instanceof Flour) f.setButtonImg(1);
					
					
					if(f.hit(bowl)) {
						
						if (f instanceof Egg) {
							f.setButtonImg(2);
							egg.play(0);
							eggin= true;
							if (eggin) f.setButtonImg(0);
							f.setPos(W_WIDTH / 2+70, W_HEIGHT / 2 - 150);
						}

						if (f instanceof Flour) {
							flour.play(0);
							flourin= true;
							if (flourin) f.setButtonImg(0);
							f.setPos(W_WIDTH / 2-300, W_HEIGHT / 2 - 160);
						}
						if (f instanceof Sugar) {
							sugar.play(0);
							sugarin= true;
							if (sugarin) f.setButtonImg(0);
							f.setPos(W_WIDTH / 2-460, W_HEIGHT / 2 - 140);
						}
						if (f instanceof Milk) {
							milk.play(0);
							milkin= true;
							if (milkin) f.setButtonImg(0);
							f.setPos(W_WIDTH / 2-100, W_HEIGHT / 2 - 180);
						}
						
						if (eggin && flourin && sugarin && milkin) {
						state=2;
						bowl.setButtonImg(1);
						}
					}

				}
			}
			
			
			if (state == 3) {
				bowl.setPos(mouseX, mouseY);
				if (bowl.hit(pan)) {
					milk.play(0);
					pan.setButtonImg(2);
					state = 4;
				}
			}
			
			for(Fillings f : fillingList) {
				if(state==4 && f.clicked(mouseX, mouseY)) {
					f.setPos(mouseX, mouseY);
					if(!drag.isPlaying())
						drag.play(0);
					
					if(f.hit(pan)) {
						
						if (f instanceof Redbean) {
							pan.setButtonImg(3);
							f.setPos(W_WIDTH / 2+70, W_HEIGHT / 2 - 150);
							redbean = true;
						}

						if (f instanceof Matcha) {
							pan.setButtonImg(4);
							f.setPos(W_WIDTH / 2-300, W_HEIGHT / 2 - 160);
							matcha = true;
						}
						
						if (f instanceof Chocolate) {
							pan.setButtonImg(5);
							f.setPos(W_WIDTH / 2-460, W_HEIGHT / 2 - 140);
							chocolate = true;
						}
						
						if (redbean||matcha||chocolate) {
							state=5;
						}
					}

				}
			}
			
			
		}
	}

	
}
