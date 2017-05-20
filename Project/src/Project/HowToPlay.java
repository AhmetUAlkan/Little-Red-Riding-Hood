package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HowToPlay extends JFrame{
	
	Icon w = new ImageIcon("res/images/w.png");
	Icon a = new ImageIcon("res/images/a.png");
	Icon s = new ImageIcon("res/images/s.png");
	Icon d = new ImageIcon("res/images/d.png");
	Icon space = new ImageIcon("res/images/space.png");
	Icon esc = new ImageIcon("res/images/esc.png");
	
	Icon icon_w = new ImageIcon("res/gifs/w.gif");
	Icon icon_a = new ImageIcon("res/gifs/a.gif");
	Icon icon_s = new ImageIcon("res/gifs/s.gif");
	Icon icon_d = new ImageIcon("res/gifs/d.gif");
	
	JButton backButton;
	
	static Color bloodyColor = new Color(255,51,52);
	static Font bloody = new Font("Bloody",1,32);
	
	
	public HowToPlay(){
		
		Letters panel = new Letters();
		panel.makePanel("HOW TO PLAY");
		panel.setBackground(Color.BLACK);
		add(panel);
		
		try {
	            Letters w = new Letters();
	            w.makePanel(this.w, icon_w, "Clicking 'W' will make your character go to up side.!");
	            add(w);
	            
	            Letters a = new Letters();
	            a.makePanel(this.a, this.icon_a, "Clicking 'A' will make your character go to left side.!");
	            add(a);
	            
	            Letters s = new Letters();
	            s.makePanel(this.s, icon_s, "Clicking 'S' will make your character go to down side.!");
	            add(s);
	            
	            Letters d = new Letters();
	            d.makePanel(this.d, icon_d, "Clicking 'D' will make your character go to right side.!");
	            add(d);
	            
	            Letters space = new Letters();
	            space.makePanel(this.space, null, "To take the logs click 'SPACEBAR'");
	            add(space);
	            
	            Letters esc = new Letters();
	            esc.makePanel(this.esc, null, "To exit the game, press 'ESC'");
	            add(esc);
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
		
		backButton = Panel.createSimpleButton("BACK");
		add(backButton);		
		
		backButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					Panel frame = new Panel();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dispose();
			}
		});
		
		setSize(800,600);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		getContentPane().setLayout(new GridLayout(0,1));
		this.getContentPane().setBackground( Color.BLACK );
		setVisible(true);
		
	}
	
	public static JLabel createLabel(String text){
		
		JLabel nLabel = new JLabel();
		nLabel.setText(text);
		nLabel.setForeground(bloodyColor);
		nLabel.setBackground(Color.BLACK);
		nLabel.setFont(bloody);
		return nLabel;
		
	}
	
public static JLabel createLabelWithSize(String text, int size){
		
		JLabel nLabel = new JLabel();
		nLabel.setText(text);
		nLabel.setForeground(bloodyColor);
		nLabel.setBackground(Color.BLACK);
		nLabel.setFont(new Font("Bloody",1,size));
		return nLabel;
		
	}
	
}
