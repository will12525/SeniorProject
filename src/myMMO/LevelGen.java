package myMMO;

import myMMO.tile.Tile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.WaterTile;

public class LevelGen {

	public static void createWorld(Level level,int xMax,int yMax,int xMin,int yMin)
	{

		for(int y = yMin;y<yMax;y++)
		{
			for(int x =xMin;x<xMax;x++)
			{

				//Tile t = new LeafTile(x<<3, y<<3);

				//Tile t= new DirtTile(x<<3, y<<3);
				Tile t = new GrassTile(x, y);
				level.addTile(t);


			}
		}
	}

	public static void addMorePosXTiles(int newXMax,int origXMax,int currentYMax,int currentYMin,Level level)
	{
		for(int x=origXMax;x<=newXMax;x++)
		{
			for(int y=currentYMin;y<=currentYMax;y++)
			{
				Tile t =new GrassTile(x,y);
				level.addTile(t);
				System.out.println(x+", "+y+" Tile: "+t);
				
			}
		}
		level.originalxMax=newXMax;
	}
	public static void addMoreNegXTiles(int newXMin,int origXMin,int currentYMax,int currentYMin,Level level)
	{
		for(int x=origXMin;x>=newXMin;x--)
		{
			
			for(int y=currentYMin;y<=currentYMax;y++)
			{
				Tile t =new GrassTile(x,y);
				level.addTile(t);
				System.out.println(x+", "+y+" Tile: "+t);
				
			}
		}
		level.originalxMin=newXMin;
	}
	
	
	
	public static void addMorePosYTiles(int newYMax,int origYMax,int currentXMax,int currentXMin,Level level)
	{
		for(int x=currentXMin;x<=currentXMax;x++)
		{
			for(int y=origYMax;y<=newYMax;y++)
			{
				Tile t =new GrassTile(x,y);
				level.addTile(t);
				System.out.println(x+", "+y+" Tile: "+t);
				
			}
		}
		level.originalyMax=newYMax;
	}
	

	public static void addMoreNegYTiles(int newYMin,int origYMin,int currentXMax,int currentXMin,Level level)
	{
		for(int x=currentXMin;x<=currentXMax;x++)
		{
			for(int y=origYMin;y>=newYMin;y--)
			{
				Tile t =new GrassTile(x,y);
				level.addTile(t);
				System.out.println(x+", "+y+" Tile: "+t);
				
			}
		}
		level.originalyMin=newYMin;
	}
	
}
