package myMMO;

import myMMO.tile.FenceTile;
import myMMO.tile.Tile;
import myMMO.tile.tiles.DirtTile;
import myMMO.tile.tiles.LeafTile;
import myMMO.tile.tiles.VoidTile;
import myMMO.tile.tiles.WaterTile;

public class LevelGen {

	public static void createWorld(Level level,int width,int height)
	{

		for(int y = 0;y<height;y++)
		{
			for(int x =0;x<width;x++)
			{

				//Tile t = new LeafTile(x<<3, y<<3);
				if(x % 3 == 0 || y % 3 == 0)
				{
					Tile t= new DirtTile(x<<3, y<<3);
					level.addTile(t);
				}else{
					Tile t= new LeafTile(x<<3, y<<3);
					level.addTile(t);
				}
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
