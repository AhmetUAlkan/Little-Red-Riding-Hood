package Project;

import java.awt.image.BufferedImage;
import java.util.HashMap;



public class SpriteSheet {

	private BufferedImage sheet;
	HashMap<Rectangle, BufferedImage> cache;
	
	public SpriteSheet(BufferedImage sheet){
		this.sheet = sheet;
		cache = new HashMap<Rectangle, BufferedImage>();
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		if(cache.containsKey(new Rectangle(x, y, width, height)))
		{
			return cache.get(new Rectangle(x, y, width, height));
		}
		BufferedImage subImage = sheet.getSubimage(x, y, width, height);
		cache.put(new Rectangle(x, y, width, height), subImage);
		return subImage;
	}
	
}