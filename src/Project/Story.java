package Project;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Story extends JFrame implements Runnable,KeyListener{
	public static final int width = 1366;
	public static final int height = 768;
	KeyboardManager keyboardManager;
	String text = "";

	static Font bloody = new Font("Bloody",1,32);
	
	private Thread thread;
	PlayScreen playScreen;
	
	
	private boolean isRunning = true;

	private int shift = 2;
	
	private int x = 0;
	private int y = -700;
		
	JLabel label1;
	
	public Story(){
		keyboardManager = new KeyboardManager();
		addKeyListener(keyboardManager);
		try{
			Scanner story = new Scanner(Paths.get("res/texts/story.txt"));
			
			text += "<html>";
			while(story.hasNextLine()){
				text += story.nextLine() + "<br><br>";
			}
			text += "</html>";
		}catch(Exception e){}
		
		
		label1 = new JLabel(text);
		setLayout(new FlowLayout());
		setUndecorated(true);
		setSize(width, height);
		setResizable(false);
		setLocationRelativeTo(null);
		setBackground(Color.black);
		setVisible(true);
		
		add(label1);		
		
	}
	
	public void update(){
		
		y += shift;
	
		label1.setLocation(x, y);
		
	}

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		
		getContentPane().setBackground(Color.BLACK);
		label1.setForeground(Color.WHITE);
		label1.setFont(bloody);
		
		while(isRunning){
			update();
			label1.setLocation(x , y);
			try {
				thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(y > height){
				isRunning = false;
				playScreen = new PlayScreen();
				playScreen.start();
				setVisible(false);
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ESCAPE){
			int result = JOptionPane.showConfirmDialog(null,
			        "Are you sure you want to pass the story?",
			        "Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION){
				playScreen = new PlayScreen();
				playScreen.start();
				setVisible(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
