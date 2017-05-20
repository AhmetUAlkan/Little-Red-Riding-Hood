package Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JOptionPane;

public class Character{
	
	PlayScreen playScreen;
	
	private int x = 0;
	private int y = 0;
	private SpriteSheet sprite;
	private int move = 0;
	private int sCount = 0;
	private int tick = 0;
	private int reach = 20;// 60 / 3 = 20
	private BufferedImage currentSprite = null;
	private Map mapConstraint = null;
	private Rectangle characterRectangle;
	private int logCount = 0;
	
	/*
	 *  sag 0 
	 *  asagi 1
	 *  yukari 2
	 *  sola 3
	 * */
	
	 
	public Character(PlayScreen playScreen, int x, int y){
		this.x = x;
		this.y = y;
		this.playScreen = playScreen;
		sprite = new SpriteSheet(ImageLoader.loadImage("res/images/girl.png"));
		currentSprite = sprite.crop(0, 0, 32, 48);
		characterRectangle = new Rectangle(x, y, 32, 48);
	}
	
	public void setMap(Map map)
	{
		this.mapConstraint = map;
	}
	
	private boolean notSolid(Rectangle newRectangle)
	{
		if(mapConstraint != null)
			return !mapConstraint.isSolidThere(newRectangle);
		else
			return true;
	}
	
	public int LogCount()
	{
		return logCount;
	}
	
	
	public void update(){
		tick++;
		if(playScreen.getKeyboardManager().up){
			
			if(y > 0 && notSolid(characterRectangle.move(0,-3))){
				y -= 3;
				if(move != 2)
					sCount = 0;
				move = 2;
				characterRectangle = characterRectangle.move(0,-3);
			}
		}
		else if(playScreen.getKeyboardManager().down){
			if(y < playScreen.getFrame().getHeight() - Assets.player_down.getHeight() && notSolid(characterRectangle.move(0,+3))){
				y += 3;
				if(move != 1)
					sCount = 0;
				move = 1;
				characterRectangle = characterRectangle.move(0,+3);
			}
		}
		else if(playScreen.getKeyboardManager().right){
			if(x < playScreen.getFrame().getWidth() - Assets.player_right.getWidth() && notSolid(characterRectangle.move(+3,0))){
				x += 3;
				if(move != 0)
					sCount = 0;
				move = 0;
				characterRectangle = characterRectangle.move(+3,0);
			}
		}
		else if(playScreen.getKeyboardManager().left){
			if(x > 0 && notSolid(characterRectangle.move(-3,0))){
				x -= 3;
				if(move != 3)
					sCount = 0;
				move = 3;
				characterRectangle = characterRectangle.move(-3,0);
			}
		}
		else if(playScreen.getKeyboardManager().space)
		{
			move = -1;
			Tuple<Boolean, MapObject[]> tuple = mapConstraint.isOnWood(characterRectangle); 
			if(tuple.x)
			{
				tuple.y[0].changeToGrass();
				if(tuple.y[1] != null)
					tuple.y[1].changeToGrass();
				logCount++;
			}
		}
		else
			move = -1;
		
		arrangeSprite();
	}
	
	/*
	 *  sag 0 
	 *  asagi 1
	 *  yukari 2
	 *  sola 3
	 * */
	public void arrangeSprite()
	{
		int w = 32;
		int h = 48;
		tick++;
		if(tick == reach)
		{
			tick = 0;
			if(move == -1)
			{
				currentSprite = sprite.crop(0, 0, 32, 48);
			}
			else if(move == 0)
			{
				sCount++;
				sCount = sCount % 4;
				currentSprite = sprite.crop(sCount *  w, 2 * h, w, h);
			}
			else if(move == 1)
			{
				sCount++;
				sCount = sCount % 4;
				currentSprite = sprite.crop(sCount *  w, 0, w, h);
			}
			else if(move == 2)
			{
				sCount++;
				sCount = sCount % 4;
				currentSprite = sprite.crop(sCount *  w, 3 * h, w, h);
			}
			else if(move == 3)
			{
				sCount++;
				sCount = sCount % 4;
				currentSprite = sprite.crop(sCount *  w, h, w, h);
			}
		}
	}

	public void render(Graphics g){
		g.drawImage(currentSprite, x, y, null);
		if(Config.debug)
		{
			g.setColor(Color.red);
			g.drawRect(characterRectangle.getX(), characterRectangle.getY(), characterRectangle.getWidth(), characterRectangle.getHeight());
			g.drawString(logCount +"", 100 , 100);
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(x, y, 32, 48);
	}
	
}
