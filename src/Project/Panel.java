package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Panel extends JPanel{
	
	JButton playButton;
	JButton howToPlayButton;
	JButton exit;
	Image image;
	static Color bloodyColor = new Color(255,51,52);
	static Font bloody = new Font("Bloody",1,32);
	public Panel() throws IOException{
		setLayout(null);
		image = new ImageIcon("res/images/background.jpg").getImage();	
		playButton = createSimpleButton("Play");
		playButton.setBounds(458, 270, 400, 75);
		howToPlayButton = createSimpleButton("How To Play");
		howToPlayButton.setBounds(458, 370, 400, 75);
		exit = createSimpleButton("Exit");
		exit.setBounds(458, 470, 400, 75);
		
		playButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Story story = new Story();
				story.start();
			}
		});
		
		howToPlayButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				HowToPlay playScreen = new HowToPlay();
			}
		});
		
		exit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		add(playButton);
		add(howToPlayButton);
		add(exit);		
	}

	public void paintComponent(Graphics g) {
	        g.drawImage(image, 0, 0, this);
	  }
	 
	 public static JButton createSimpleButton(String text) {
		  JButton button = new JButton();
		  button.setText(text);
		  button.setForeground(bloodyColor);
		  button.setBackground(Color.BLACK);
		  button.setFont(bloody);
		  button.setBorderPainted(false);
		  button.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				button.setForeground(Color.red);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				button.setForeground(bloodyColor);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				button.setForeground(new Color(239,11,11));
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				button.setForeground(bloodyColor);
			}
			  
		  });
		  
		  button.setFocusPainted(false);
		  button.setContentAreaFilled(false);
		  Border line = new LineBorder(Color.BLACK);
		  Border margin = new EmptyBorder(5, 15, 5, 15);
		  Border compound = new CompoundBorder(line, margin);
		  button.setBorder(compound);
		  button.setOpaque(false);
		  return button;
	}
}	
