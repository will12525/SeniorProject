package myMMO;

import myMMO.tile.Tile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.PlankTile;

@SuppressWarnings("all")
public class LevelGen {

	public static void createWorld(Level level,int xMax,int yMax,int xMin,int yMin)
	{

		for(int y = yMin;y<yMax;y++)
		{
			for(int x =xMin;x<xMax;x++)
			{
				if(x == 0 && y == 0) 
				{
					Tile t = new PlankTile(0, 0);
					level.addTile(t);
				}
				else
				{
					Tile t = new GrassTile(x, y);
					level.addTile(t);
				}
							
				//Tile t = new LeafTile(x<<3, y<<3);

				//Tile t= new DirtTile(x<<3, y<<3);
				


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
				//System.out.println(x+", "+y+" Tile: "+t);
				
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
				//System.out.println(x+", "+y+" Tile: "+t);
				
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
				//System.out.println(x+", "+y+" Tile: "+t);
				
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
				//System.out.println(x+", "+y+" Tile: "+t);
				//maybe
			}
		}
		level.originalyMin=newYMin;
	}
	public static void getTileBiome(Level level,int x,int y)
	{
		
		level.getTile(x,y);
		
	}
	
	
}
