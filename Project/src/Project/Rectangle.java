package Project;

public class Rectangle {
	private int x, y, width, height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean equals(Object a)
	{
		if(a instanceof Rectangle)
		{
			Rectangle b = (Rectangle)a;
			return b.getX() == getX() && b.getY() == getY() &&
				   b.getWidth() == getWidth() && b.getHeight() == getHeight();
		}
		else
			return false;
	}
	
	public int hashCode()
	{
		return x * 1 + y * 2 + width * 3 + height * 4;
	}
	
	
	public boolean collision(Rectangle r)
	{
		return (r.getX() < x + width &&
			   r.getX() + r.getWidth() > x &&
			   r.getY() < y + height &&
			   r.getHeight() + r.getY() > y);
	}
	
	public Rectangle move(int xDis, int yDis)
	{
		return new Rectangle(x + xDis, y + yDis, width, height);
	}
	
	

}
