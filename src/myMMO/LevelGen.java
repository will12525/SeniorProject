package myMMO;

import items.Item;
import items.RockItem;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import myMMO.biome.Biome;
import myMMO.biome.ForestBiome;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.FlowerTile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.LeafTile;
import myMMO.tile.tiles.LogTile;
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
					//t = new PlankTile(0, 0);
					t = new GrassTile(0, 0);
				}
				else if(x==1&&y<5&&y>0)
				{
					//t= new PlowedDirt(x,y);
					t = new GrassTile(x, y);
				}

				else if(x>1&&x<4&&y>0&&y<5)
				{
					//t=new SandTile(x,y);
					t = new GrassTile(x, y);
				}

				else
				{
					if(r<=1)
					{
						t=new FlowerTile(x,y);
					}
					else
					{

						t= new GrassTile(x, y);

					}
				}
				level.addTile(t);

			}
		}



		/*for(int rocky=0;rocky<20;rocky++)
		{
			Item rock = new RockItem("Rock");
			rock.setX((rocky)+8);
			rock.setY(6);
			level.addItem(rock);
		}*/

		System.out.println("Done generating level");
		Game.instance.doneGenerating = true;
	}
	public static void addWater(Level level, List<Tile> tilesToAdd, int x,int y)
	{
		int pondSize=r.nextInt(2);
		if(pondSize==0)
		{
			for(int k=0;k<2;k++)
			{

				addStuff(level,tilesToAdd,new WaterTile(x+k,y),x+k,y);

			}


			addStuff(level,tilesToAdd,new WaterTile(x-1,y+1),x-1,y+1);
			for(int k=0;k<3;k++)
			{

				addStuff(level,tilesToAdd,new WaterTile(x+k,y+1),x+k,y+1);

			}
			addStuff(level,tilesToAdd,new WaterTile(x,y+2),x,y+2);
			addStuff(level,tilesToAdd,new WaterTile(x+1,y+2),x+1,y+2);


		}

		if(pondSize==1)
		{
			for(int k=0;k<4;k++)
			{


				addStuff(level,tilesToAdd,new WaterTile(x+k,y),x+k,y);
			}

			addStuff(level,tilesToAdd,new WaterTile(x-1,y+1),x-1,y+1);

			for(int k=0;k<5;k++)
			{

				addStuff(level,tilesToAdd,new WaterTile(x+k,y+1),x+k,y+1);
			}


			addStuff(level,tilesToAdd,new WaterTile(x-1,y+2),x-1,y+2);


			addStuff(level,tilesToAdd,new WaterTile(x-2,y+2),x-2,y+2);


			addStuff(level,tilesToAdd,new WaterTile(x-1,y+3),x-1,y+3);


			addStuff(level,tilesToAdd,new WaterTile(x-2,y+3),x-2,y+3);

			for(int k=0;k<6;k++)
			{			
				addStuff(level,tilesToAdd,new WaterTile(x+k,y+3),x+k,y+3);
				addStuff(level,tilesToAdd,new WaterTile(x+k,y+2),x+k,y+2);
			}

			addStuff(level,tilesToAdd,new WaterTile(x-1,y+4),x-1,y+4);

			for(int k=0;k<5;k++)
			{
				addStuff(level,tilesToAdd,new WaterTile(x+k,y+4),x+k,y+4);
			}

			for(int k=0;k<4;k++)
			{
				addStuff(level,tilesToAdd,new WaterTile(x+k,y+5),x+k,y+5);
			}

		}

	}
	public static void addStone(Level level, List<Tile> tilesToAdd,int x, int y)
	{
		int blockSize=r.nextInt(2);
		if(blockSize==0)
		{
			for(int k=0;k<2;k++)
			{

				addStuff(level,tilesToAdd,new StoneTile(x+k,y),x+k,y);

			}


			addStuff(level,tilesToAdd,new StoneTile(x-1,y+1),x-1,y+1);
			for(int k=0;k<3;k++)
			{

				addStuff(level,tilesToAdd,new StoneTile(x+k,y+1),x+k,y+1);

			}
			addStuff(level,tilesToAdd,new StoneTile(x,y+2),x,y+2);
			addStuff(level,tilesToAdd,new StoneTile(x+1,y+2),x+1,y+2);


		}

		if(blockSize==1)
		{
			for(int k=0;k<4;k++)
			{


				addStuff(level,tilesToAdd,new StoneTile(x+k,y),x+k,y);
			}

			addStuff(level,tilesToAdd,new StoneTile(x-1,y+1),x-1,y+1);

			for(int k=0;k<5;k++)
			{

				addStuff(level,tilesToAdd,new StoneTile(x+k,y+1),x+k,y+1);
			}


			addStuff(level,tilesToAdd,new StoneTile(x-1,y+2),x-1,y+2);


			addStuff(level,tilesToAdd,new StoneTile(x-2,y+2),x-2,y+2);


			addStuff(level,tilesToAdd,new StoneTile(x-1,y+3),x-1,y+3);


			addStuff(level,tilesToAdd,new StoneTile(x-2,y+3),x-2,y+3);

			for(int k=0;k<6;k++)
			{			
				addStuff(level,tilesToAdd,new StoneTile(x+k,y+3),x+k,y+3);
				addStuff(level,tilesToAdd,new WaterTile(x+k,y+2),x+k,y+2);
			}

			addStuff(level,tilesToAdd,new StoneTile(x-1,y+4),x-1,y+4);

			for(int k=0;k<5;k++)
			{
				addStuff(level,tilesToAdd,new StoneTile(x+k,y+4),x+k,y+4);
			}

			for(int k=0;k<4;k++)
			{
				addStuff(level,tilesToAdd,new StoneTile(x+k,y+5),x+k,y+5);
			}

		}
	}
	public static void addSand(Level level,List<Tile> tilesToAdd,int x,int y)
	{
		Tile xPlus=level.getTile(x+1, y);
		Tile yPlus=level.getTile(x, y+1);
		Tile xMin=level.getTile(x-1, y);
		Tile yMin=level.getTile(x, y-1);

		if(!(xPlus instanceof WaterTile))
		{
			addStuff(level,tilesToAdd,new SandTile(x+1,y),x+1,y);
		}
		if(!(yPlus instanceof WaterTile))
		{
			addStuff(level,tilesToAdd,new SandTile(x,y+1),x,y+1);
		}
		if(!(xMin instanceof WaterTile))
		{
			addStuff(level,tilesToAdd,new SandTile(x-1,y),x-1,y);
		}
		if(!(yMin instanceof WaterTile))
		{
			addStuff(level,tilesToAdd,new SandTile(x,y-1),x,y-1);
		}

	}
	public static void addStuff(Level level,List<Tile> tilesToAdd,Tile t,int x,int y)
	{
		level.setTile(x, y, t);
		tilesToAdd.add(t);
	}

	public static void trees(Level level,List<Tile> tilesToAdd,int x,int y)
	{
		addStuff(level,tilesToAdd,new LogTile(x,y),x,y);
		addStuff(level,tilesToAdd,new LogTile(x,y-1),x,y-1);
		addStuff(level,tilesToAdd,new LogTile(x,y-2),x,y-2);		
		addStuff(level,tilesToAdd,new LeafTile(x,y-3),x,y-3);
		addStuff(level,tilesToAdd,new LeafTile(x-1,y-2),x-1,y-2);
		addStuff(level,tilesToAdd,new LeafTile(x+1,y-2),x+1,y-2);
	}




	public static void getTileBiome(Level level,int x,int y)
	{

		level.getTile(x,y);

	}




	public static void generateChunks(PlayerEntity player)
	{

	}
	public static void unloadChunks(PlayerEntity player,Level level)
	{

	}


}
