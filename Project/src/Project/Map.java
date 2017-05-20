package Project;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Map {

	private String mapName;
	private SpriteSheet tileSet;
	private String data;
	private MapObject[] mapData;
	public Map(String mapName) throws FileNotFoundException
	{
		this.mapName = mapName;
		data = "";
		tileSet = new SpriteSheet(ImageLoader.loadImage("res/maps/" + mapName + "/tileset.png"));
		File file = new File("res/maps/" + mapName + "/map.txt");
		Scanner sc = new Scanner(file);
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			data += line;
		}
		sc.close();
		createMap();
	}
	
	private void createMap()
	{
		String[] entities = data.split("\\|");
		mapData = new MapObject[entities.length];
		int ct = 0;
		for(String entity : entities)
		{
			entity = entity.substring(1, entity.length() - 1);
			String[] arr = entity.split("\\,");
			int xPos = Integer.parseInt(arr[0].trim());
			int yPos = Integer.parseInt(arr[1].trim());
			int xTilePos = Integer.parseInt(arr[2].trim());
			int yTilePos = Integer.parseInt(arr[3].trim());
			boolean solid = arr[4].trim().equals("True");
			MapObject mapObject = new MapObject(xPos * 32, yPos * 32, tileSet.crop(xTilePos * 32, yTilePos * 32, 32, 32), solid);
			mapObject.setGrassSprite(tileSet.crop(0, 0, 32, 32));
			mapObject.setxTile(xTilePos);
			mapObject.setyTile(yTilePos);
			mapData[ct] = mapObject;
			ct ++;
		}
	}
	
	public Tuple<Boolean, MapObject[]> isOnWood(Rectangle rect)
	{
		MapObject[] arr = new MapObject[2];
		for(MapObject instance : mapData)
		{
			if(!instance.isWood())
				continue;
			
			if(instance.getAbsoluteRectangle(32).collision(rect))
			{
				arr[0] = instance;
				if(instance.getxTile() == 14 && instance.getyTile() == 0)
				{
					for(MapObject waste : mapData)
					{
						if(waste.getxTile() == 14 && waste.getyTile() == 1 
								&& waste.getX() == instance.getX() && waste.getY() == instance.getY() + 32)
						{
							arr[1] = waste;
							break;
						}
					}
				}
				else if(instance.getxTile() == 14 && instance.getyTile() == 1)
				{
					for(MapObject waste : mapData)
					{
						if(waste.getxTile() == 14 && waste.getyTile() == 0 
								&& waste.getX() == instance.getX() && waste.getY() == instance.getY() - 32)
						{
							arr[1] = waste;
							break;
						}
					}
				}
				else if(instance.getxTile() == 11 && instance.getyTile() == 2)
				{
					for(MapObject waste : mapData)
					{
						if(waste.getxTile() == 12 && waste.getyTile() == 2 
								&& waste.getX()  == instance.getX() + 32 && waste.getY() == instance.getY())
						{
							arr[1] = waste;
							break;
						}
					}
				}
				else if(instance.getxTile() == 12 && instance.getyTile() == 2)
				{
					for(MapObject waste : mapData)
					{
						if(waste.getxTile() == 11 && waste.getyTile() == 2
								&& waste.getX()  == instance.getX() - 32 && waste.getY() == instance.getY())
						{
							arr[1] = waste;
							break;
						}
					}
				}
				return new Tuple<Boolean, MapObject[]>(true, arr);
			}
		}
		return new Tuple<Boolean, MapObject[]>(false, null);
	}
	
	
	public boolean isSolidThere(Rectangle moved)
	{
		for(MapObject instance : mapData)
		{
			if(!instance.isSolid())
				continue;
			
			Rectangle a = instance.getAbsoluteRectangle(32);
			if(a.collision(moved))
				return true;
		}
		return false;
	}
	
	public void render(Graphics g){
		for(MapObject object : mapData)
		{
			object.render(g);
		}
		
	}
	
}
