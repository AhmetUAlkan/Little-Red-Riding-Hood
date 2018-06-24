package Project;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 48;
	
	public static BufferedImage grass;
	public static BufferedImage player_down, player_up, player_left, player_right;
	public static BufferedImage wolf_down, wolf_up, wolf_left, wolf_right;

	public static void init(){
		
		SpriteSheet sheetForGirl = new SpriteSheet(ImageLoader.loadImage("res/images/girl.png"));
		SpriteSheet sheetForWolf = new SpriteSheet(ImageLoader.loadImage("res/images/wolf.png"));
		SpriteSheet grassImage = new SpriteSheet(ImageLoader.loadImage("res/images/grass.png"));
		
		grass = grassImage.crop(0, 0, 1366, 768);
		player_down = sheetForGirl.crop(0, 0, width, height);
		player_up = sheetForGirl.crop(0, height, width, height);
		player_right = sheetForGirl.crop(width, height * 2, width, height);
		player_left = sheetForGirl.crop(width, height * 3, width, height);
		
		wolf_down = sheetForWolf.crop(width * 2, 0, width, height);
		wolf_up = sheetForWolf.crop(0, 0, width, height);
		wolf_right = sheetForWolf.crop(width, 0, width, height);
		wolf_left = sheetForWolf.crop(width * 3, height, width, height);
	
	}

}
