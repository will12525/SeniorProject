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

		//spawnPond(level);

		for(int rocky=0;rocky<20;rocky++)
		{
			Item rock = new RockItem("Rock");
			rock.setX((rocky)+8);
			rock.setY(6);
			level.addItem(rock);
		}
	}


	private static void spawnPond(Level level)
	{
		int k= r.nextInt(level.getTiles().size());
		int tileX= level.getTiles().get(k).getX();
		int tileY=level.getTiles().get(k).getY();
		System.out.println(tileX+", "+tileY);
		
		
		for(int i = 0; i < Level.tiles.size(); i++)
		{
			Tile tile = Level.tiles.get(i);
			
			if(tile.getX() == tileX && tile.getY() == tileY)
			{
				//System.out.println(tile);
				Level.tiles.set(i, new WaterTile(tileX, tileY));
				//Level.tiles.set(i+1, new WaterTile(tileX+1, tileY));
				//Level.tiles.set(i+2, new WaterTile(tileX+1, tileY+1));
				//Level.tiles.set(i+3, new WaterTile(tileX+1, tileY+1));
				//Level.tiles.set(i+4, new WaterTile(tileX+1, tileY+2));
				//Level.tiles.set(i+5, new WaterTile(tileX+1, tileY+3));
				//Level.tiles.set(i+6, new WaterTile(tileX+1, tileY+4));
				//System.out.println(Level.getTiles().get(i));
			}
		}
		
		//level.addTile(new WaterTile(tileX,tileY));
		//level.setTile(tileX, tileY, Tile.WATER);
	
		/*level.addTile(new WaterTile(tileX,tileY));
		level.addTile(new WaterTile(tileX+1,tileY));
		level.addTile(new WaterTile(tileX,tileY+1));
		level.addTile(new WaterTile(tileX+1,tileY+1));
		/*Tile t=null;
		System.out.println(x);
		for(int x2=x;x<13;x++)
		{
			t=new WaterTile(x2,y);
		}

		level.addTile(t);*/
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
