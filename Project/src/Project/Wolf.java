package Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.SecureRandom;
import javax.swing.JOptionPane;

public class Wolf {
	PlayScreen playScreen;
	private int x = 0;
	private int y = 0;
	private Map mapConstraint;
	private Character hunt;
	private SpriteSheet Wolf;
	private int stage = 0;
	private int move;
	private int sCount;
	private int tick;
	private int waitReach;
	private int walkReach;
	private BufferedImage currentSprite;
	private Rectangle wolfRectangle;
	
	/*
	 * 0 - bekle
	 * 1 - yürü
	 * 2 - avlan
	 */
	
	
	/* Move
	 *  sag 0 
	 *  asagi 1
	 *  yukari 2
	 *  sola 3
	 * */
	
	
	public Wolf(PlayScreen playScreen, int x, int y){
		this.x = x;
		this.y = y;
		this.playScreen = playScreen;
		sCount = 0;
		tick = 0;
		move = 0;
		waitReach = 60;
		walkReach = 30;
		stage = 0;
		Wolf = new SpriteSheet(ImageLoader.loadImage("res/images/wolf.png"));
		currentSprite = Wolf.crop(0, 0, 48, 48);
		wolfRectangle = new Rectangle(x, y, 48, 48);
	}
	
	public void setMapConstraint(Map map)
	{
		this.mapConstraint = map;
	}
	
	public void setHunt(Character c)
	{
		this.hunt = c;
	}
	
	private boolean notSolid(Rectangle newRectangle)
	{
		if(mapConstraint != null)
			return !mapConstraint.isSolidThere(newRectangle);
		else
			return true;
	}
	
	
	public Rectangle HuntRectangle()
	{
		return new Rectangle(x - 24, y - 24, 96, 96);
	}
	
	public void decideMove()
	{
		if(hunt != null && mapConstraint != null)
		{
			//check if player is close
			SecureRandom random = new SecureRandom();
			move = random.nextInt(4);
			sCount = 0;
			//go walk
			stage = 1;
		}
	}
	
	public void arrangeSprite()
	{
		if(move == 0)
		{
			currentSprite = Wolf.crop(sCount * 48, 96, 48, 48);
		}
		else if(move == 1)
		{
			currentSprite = Wolf.crop(sCount * 48, 0, 48, 48);
		}
		else if(move == 2)
		{
			currentSprite = Wolf.crop(sCount * 48, 144, 48, 48);
		}
		else if(move == 3)
		{
			currentSprite = Wolf.crop(sCount * 48, 48, 48, 48);
		}
	}
	
	public void update(){
		tick ++;
		if(stage == 0)
		{
			if(tick == waitReach)
			{
				decideMove();
				tick = 0;
			}
		}
		else if(stage == 1)
		{
			if(tick == walkReach)
			{
				tick = 0;
				stage = 0;
			}
			else
			{
				if(move == 0)
				{
					if(x + 51 < playScreen.getFrame().getWidth() && notSolid(wolfRectangle.move(+3,0)))
					{
						x += 3;
						if(tick % 10 == 0)
							sCount++;
						wolfRectangle = wolfRectangle.move(+3,0);
					}
					else
						stage = 0;
				}
				else if(move == 1)
				{
					if(y + 51 <  playScreen.getFrame().getHeight() && notSolid(wolfRectangle.move(0, +3)))
					{
						y += 3;
						if(tick % 10 == 0)
							sCount++;
						wolfRectangle = wolfRectangle.move(0,+3);
					}
					else 
						stage = 0;
				}
				else if(move == 2)
				{
					if(y > 3 &&  notSolid(wolfRectangle.move(0, -3)))
					{
						y -= 3;
						if(tick % 10 == 0)
							sCount++;
						wolfRectangle = wolfRectangle.move(0,-3);
					}
					else stage = 0;
				}
				else if(move == 3)
				{
					if(x > 3 && notSolid(wolfRectangle.move(-3, 0)))
					{
						x -= 3;
						if(tick % 10 == 0)
							sCount++;
						wolfRectangle = wolfRectangle.move(-3,0);
					}
					else stage = 0;
				}
			}
			
			sCount = sCount % 4;
			arrangeSprite();
		}
		
		
		
	}

	public void render(Graphics g){
		g.drawImage(currentSprite, x, y, null);
		g.setColor(Color.RED);
		g.drawRect(x - 24, y - 24, 96, 96);
		
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

}

