package Project;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyboardManager implements KeyListener{

	private boolean[] keys;
	public boolean up, down, right, left, space;
	
	public KeyboardManager(){
		keys = new boolean[256];
	}
	
	public void update(){
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		space = keys[KeyEvent.VK_SPACE];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		
		if(e.getKeyCode() == e.VK_ESCAPE){
			int result = JOptionPane.showConfirmDialog(null,
			        "Are you sure you want to quit?",
			        "Confirm Quit", JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == JOptionPane.YES_OPTION){
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
