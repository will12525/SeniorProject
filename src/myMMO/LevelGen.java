package myMMO;

import items.Item;
import items.RockItem;

import java.util.Random;

import myMMO.biome.Biome;
import myMMO.biome.ForestBiome;
import myMMO.tile.Tile;
import myMMO.tile.tiles.FlowerTile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.PlankTile;
import myMMO.tile.tiles.PlowedDirt;
import myMMO.tile.tiles.SandTile;
import myMMO.tile.tiles.StoneTile;
import myMMO.tile.tiles.WaterTile;

@SuppressWarnings("all")
public class LevelGen {
	static Random r = new Random();

	public static void createWorld(Level level,int xMax,int yMax,int xMin,int yMin)
	{
		Random rand=new Random();
		Biome b = new ForestBiome(xMin, xMax, yMin, yMax);
		//System.out.println(b+", "+xMax+", "+xMin+", "+yMax+", "+yMin);
		level.addBiome(b);
		int[] allowedTiles=b.tileTypes();
		for(int y = yMin;y<yMax;y++)
		{
			for(int x =xMin;x<xMax;x++)
			{
				int r=rand.nextInt(200)+1;
				/*
				int chosenTile=r.nextInt(allowedTiles.length);
				int id = allowedTiles[chosenTile];
				Tile t= Tile.getTile(id);

				//Tile t = Tile.tiles[id];
				t.xcoord = x;
				t.ycoord = y;
				level.addTile(t);
				System.out.println(t);
				 */


				Tile t=null;
				if(x == 0 && y == 0) 
				{
					t = new PlankTile(0, 0);
				}
				else if(x==1&&y<5&&y>0)
				{
					t= new PlowedDirt(x,y);
				}
				else if(x>1&&x<4&&y>0&&y<5)
				{
					t=new SandTile(x,y);
				}
				else
				{
					if(r<=1)
					{


						t=new FlowerTile(x,y);

					}
					else
					{
						if(level.getTile(x, y).getId()!=Tile.WATER)
						{
							t= new GrassTile(x, y);
						}
					}
				}
				level.addTile(t);



				//Tile t = new LeafTile(x<<3, y<<3);

				//Tile t= new DirtTile(x<<3, y<<3);



			}
		}



		for(int rocky=0;rocky<20;rocky++)
		{
			Item rock = new RockItem("Rock");
			rock.setX((rocky)+8);
			rock.setY(6);
			level.addItem(rock);
		}
	}


	public static void spawnPond(Level level)
	{
		int r1= r.nextInt(level.getTiles().size());
		int tileX= level.getTiles().get(r1).getX();
		int tileY=level.getTiles().get(r1).getY();
		System.out.println(tileX+", "+tileY);

		Tile tile =new WaterTile(tileX,tileY);

		level.levelGenPlace(tile,tileX, tileY);


//I dont even get how this is working
		for(int k = 0;k<level.getTiles().size();k++)
		{
			if(level.getTiles().get(k).getId()==Tile.WATER)
			{
				tileX=level.getTiles().get(k).getX()+1;
				for(int gaahhh=0;gaahhh<6;gaahhh++)
				{
					
				}
				tileY=level.getTiles().get(k).getY();
				level.levelGenPlace(new WaterTile(tileX,tileY), tileX, tileY);
System.out.println(k);

			}
		}





	}

	public static void addMorePosXTiles(int newXMax,int origXMax,int currentYMax,int currentYMin,Level level)
	{
		for(int x=origXMax;x<=newXMax;x++)
		{
			for(int y=currentYMin;y<=currentYMax;y++)
			{
				Tile t=null;
				if(newXMax>100)
				{
					t=new StoneTile(x,y);
				}
				else
				{
					t =new GrassTile(x,y);
				}
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

				Tile t=null;
				if(newXMin<-100)
				{
					t=new StoneTile(x,y);
				}
				else
				{
					t =new GrassTile(x,y);
				}


				//Tile t =new GrassTile(x,y);
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

				Tile t=null;
				if(newYMax>100)
				{
					t=new StoneTile(x,y);
				}
				else
				{
					t =new GrassTile(x,y);
				}

				//	Tile t =new GrassTile(x,y);
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
				Tile t=null;
				if(newYMin<-100)
				{
					t=new StoneTile(x,y);
				}
				else
				{
					t =new GrassTile(x,y);
				}
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
