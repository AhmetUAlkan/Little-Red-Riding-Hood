package Project;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFrame;

public class Main {
	
	public static final int width = 1366;
	public static final int height = 768;

	public static void main(String[] args) throws IOException {
		
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/BLOODY.ttf")));
		} catch (IOException|FontFormatException e) {
		     System.out.println("An exception has happened");
		     e.printStackTrace();
		}
				
		JFrame app = new JFrame();
		Panel frame = new Panel();
		app.setUndecorated(true);
		app.setVisible(true);
		app.setResizable(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(width,height);
		
		app.add(frame);
	}

}
