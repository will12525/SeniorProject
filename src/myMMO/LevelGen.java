package myMMO;

import java.awt.Point;

import myMMO.tile.Tile;

public class LevelGen {
	public static int newTiles[];
	public static void createWorld(int width,int height)
	{
		
		for(int y = 0;y<height;y++)
		{
			for(int x =-0;x<width;x++)
			{
				/*if(x*y%10<7)
				{
					tiles[x+y*width]=Tile.GRASS.getId();
				}
				else
				{
					tiles[x+y*width]=Tile.STONE.getId();
				}*/
				//tiles1[x+y*width]=Tile.GRASS.getId();
				Level.map.put(new Point(x, y),  Tile.SAND);
			}
		}
	}
	/*
	public static void addMoreTiles(int width, int height,int lastWidth,int lastHeight,Level level) {
		for(int y=height;y<=lastHeight;y++)
		{
			for(int x=width;x<=lastWidth;x++)
			{
				System.out.println("setting tiles");
				level.setTile(x, y, Tile.GRASS);
				
				//tiles[x+y*width]=Tile.GRASS.getId();
			}
		}
		newTiles=new int[(lastHeight*lastWidth)-level.tiles1.length];
		level.tiles1=new int[level.tiles1.length+newTiles.length];
		
		level.width=lastWidth;
		level.height=lastHeight;
		
	}*/

}
