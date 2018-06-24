package Project;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferStrategy;
import java.awt.image.ImageObserver;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PlayScreen implements Runnable, ImageObserver{
	public static final int width = 1366;
	public static final int height = 768;
	
	int x = 0; int y = 0;

	private Canvas canvas;
	private JFrame frame;
	
	public boolean isRunning = false;
	private Thread thread;
	private boolean won = false;
	
	
	private BufferStrategy bs;
	private Graphics g;
	
	private Map m;
	
	KeyboardManager keyboardManager;
	Character character;
	Wolf wolf1;
	Wolf wolf2;
	Wolf wolf3;
	
	public PlayScreen(){
		createDisplay();	
		keyboardManager = new KeyboardManager();
		try {
			 m = new Map("map1");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		character = new Character(this, 300, 300);
		character.setMap(m);
		wolf1 = new Wolf(this, 100, 100);
		wolf2 = new Wolf(this, 600, 620);
		wolf3 = new Wolf(this, 1000, 100);
		
		
		wolf1.setHunt(character);
		wolf1.setMapConstraint(m);
		
		wolf2.setHunt(character);
		wolf2.setMapConstraint(m);
		
		wolf3.setHunt(character);
		wolf3.setMapConstraint(m);
	}
		
	public synchronized void start(){
		if(isRunning){
			return;
		}

		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!isRunning){
			return;
		}
		
		isRunning = false;
		thread.stop();
	}
	
	public void init(){
		
		getFrame().addKeyListener(keyboardManager);
		
		Assets.init();
		
	}
	
	public void update(){
		if(!won)
		{
			character.update();
			wolf1.update();
			wolf2.update();
			wolf3.update();
		}
		if(wolf1.HuntRectangle().collision(character.getRectangle()) ||
				wolf2.HuntRectangle().collision(character.getRectangle()) ||
				wolf3.HuntRectangle().collision(character.getRectangle()))
		{
			   JOptionPane.showMessageDialog(null, "Game over...", "You've lost" , JOptionPane.INFORMATION_MESSAGE);
			   frame.setVisible(false);
			   frame.dispose();
			   stop();	   
		}
		
		if(character.LogCount() == 4 && !won)
		{
			won = true;
			JOptionPane.showMessageDialog(null, "You won, you and your grandma have survived", "Congratz" , JOptionPane.INFORMATION_MESSAGE);
			try {
				m = new Map("Map2");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		keyboardManager.update();
	}
	
	public void render(){
		bs = canvas.getBufferStrategy();
		if(bs == null){
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.setColor(new Color(47,129,54));
		g.fillRect(0, 0, width, height);
		//Draw Here!
		m.render(g);
		if(!won)
		{
		character.render(g);
		wolf1.render(g);
		wolf2.render(g);
		wolf3.render(g);
		}
		//End Drawing!
		bs.show();
		g.dispose();
	}
	
	public void run() {
		
		init();
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;
		
		while(isRunning){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				updates++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
	}	
	
	
	
	private void createDisplay(){
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		
		frame.add(canvas);
		frame.pack();
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public KeyboardManager getKeyboardManager(){
		return keyboardManager;
	}

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		return false;
	}

}
