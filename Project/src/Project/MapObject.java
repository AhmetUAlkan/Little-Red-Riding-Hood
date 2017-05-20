package Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MapObject {
	private int x;
	private int y;
	private BufferedImage sprite;
	private BufferedImage grassSprite;
	private boolean solid;
	private int xTile;
	
	public int getxTile() {
		return xTile;
	}

	public void setxTile(int xTile) {
		this.xTile = xTile;
	}

	public int getyTile() {
		return yTile;
	}

	public void setyTile(int yTile) {
		this.yTile = yTile;
	}

	private int yTile;
	
	
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

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean solid) {
		this.solid = solid;
	}
	
	public void setGrassSprite(BufferedImage gSprite)
	{
		this.grassSprite = gSprite;
	}
	
	public void changeToGrass()
	{
		this.sprite = grassSprite;
		this.xTile = 0;
		this.yTile = 0;
	}
	
	public MapObject(int x, int y, BufferedImage sprite, boolean solid)
	{
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.solid = solid;
		xTile = -1;
		yTile = -1;
	}
	
	
	public boolean isWood()
	{
		return (xTile == 11 && yTile == 2) ||
				 (xTile == 12 && yTile == 2) ||
				 (xTile == 14 && yTile == 0) ||
				 (xTile == 14 && yTile == 1); 
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x, y, null);
		if(solid && Config.debug)
		{
			g.setColor(Color.red);
			int tileSize = 32;
			g.drawRect(x, y, tileSize, tileSize);
		}
	}
	
	public Rectangle getAbsoluteRectangle(int tileSize)
	{
		return new Rectangle(x, y, tileSize, tileSize);
	}
}
