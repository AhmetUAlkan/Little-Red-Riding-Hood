package Project;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Letters extends JPanel{
	
	JLabel letter;
	JLabel gif;
	JLabel description;
	
	
	public void makePanel(Icon letter, Icon giff, String desc){
	
		this.letter = new JLabel(letter);
		this.add(this.letter);
		this.description = HowToPlay.createLabel(desc);
		this.add(this.description);
		this.setBackground(Color.BLACK);
		this.gif = new JLabel(giff);
		this.add(this.gif);	
	}
	
	public void makePanel(String writing){
		this.description = HowToPlay.createLabelWithSize("HOW TO PLAY", 64);
		this.setLayout(new BorderLayout());
		description.setHorizontalAlignment(JLabel.CENTER);
		add(this.description);
	}

}
