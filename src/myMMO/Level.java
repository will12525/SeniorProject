package myMMO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.LogTile;

public class Level
{
	private Game game;
	private String imagePath;

	private static List<Entity> entities = new ArrayList<Entity>();
	private static Entity player;

	private static List<Tile> tiles = new ArrayList<Tile>();

	private Random random = new Random();
	
	public int currentxMax=20;
	public int currentxMin=-20;
	public int currentyMax=20;
	public int currentyMin=-20;
	
	public int originalxMax= currentxMax;
	public int originalxMin=currentxMin;
	public int originalyMax=currentyMax;
	public int originalyMin=currentyMin;
	
	private int tickCount = 0;
	

	public Level(Game game, String imagePath)
	{
		this.game = game;
		this.imagePath = imagePath;

		LevelGen.createWorld(this,currentxMax,currentyMax,currentxMin,currentyMin);

	}

	public static void addEntity(Entity e)
	{
		entities.add(e);
	}

	public static List<Entity> getEntities()
	{
		return entities;
	}

	public void setPlayer(PlayerEntity player)
	{
		this.player = player;
	}
	
	public static Entity getPlayer()
	{
		return player;
	}

	public void addTile(Tile t)
	{
		tiles.add(t);
	}

	public void setTile(int x, int y, int id)
	{
		for(Tile t : tiles)
		{
			if(t.getX() == x && t.getY() == y)
			{
				t.setID(id);
			}
		}
	}

	public Tile getTile(int x, int y)
	{
		for(Tile t : tiles)
		{
			if(t.getX() == x && t.getY() == y)
			{
				return t;
			}
		}

		return new LogTile(x, y);
	}

	public static List<Tile> getTiles()
	{
		return tiles;
	}

	//keep everything running
	public void tick()
	{
		
		//System.out.println("tile list size: " + tiles.size());
		
		//tick tiles
		//tick entities

		//System.out.println(player.x);

		for(Tile t : tiles)
		{
			t.tick();
		}

		for(Entity e : entities)
		{
			e.tick();
		}
		
		
		
		//generates more tiles as needed based on the players location
		
		if((player.x>>3)>currentxMax-20)
		{
		//this
			currentxMax=currentxMax+8;
			LevelGen.addMorePosXTiles(currentxMax,originalxMax,currentyMax,currentyMin,this);
		}
		if((player.x>>3)<currentxMin+20)
		{
			//System.out.println(player.x+", "+currentxMin+ " :player x smaller");
			currentxMin=currentxMin-8;
			LevelGen.addMoreNegXTiles(currentxMin,originalxMin,currentyMax,currentyMin,this);
		}

		if((player.y>>3)>currentyMax-20)
		{
			
			currentyMax=currentyMax+8;
			LevelGen.addMorePosYTiles(currentyMax,originalyMax,currentxMax,currentxMin,this);
		}
		if((player.y>>3)<currentyMin+20)
		{
			System.out.println(player.y+", "+currentyMin+" :player y smaller");
			currentyMin=currentyMin-8;
			LevelGen.addMoreNegYTiles(currentyMin,originalyMin,currentxMax,currentxMin,this);
		}
		
		/*
		List<Tile> tilesToRemove = new ArrayList<Tile>();
		
		if(tickCount % 20 == 0)
		{
			for(Tile t : tiles)
			{
				int x1 = player.x >> 3;
				int x2 = t.getX() >> 3;
				
				int y1 = player.y >> 3;
				int y2 = t.getY() >> 3;
				
				//int distance = (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
				int deltax = x2-x1;
				int deltay = y2-y1;
				
				deltax = deltax*deltax;
				deltay = deltay*deltay;
				double distance = Math.sqrt(deltax + deltay);
				//System.out.println(distance);
				System.out.println(x1 + " " + y1 + "   " + x2 + " " + y2);
				
				if(distance > 25)
				{
					tilesToRemove.add(t);
				}
			}
			
			for(Tile t : tilesToRemove)
			{
				tiles.remove(t);
			}
			
			tilesToRemove.clear();
						
		}
		*/
		
		
		tickCount++;
	}

	public void renderTiles(Display display, int xoffset, int yoffset)
	{
		display.setOffset(xoffset, yoffset);
		for(Tile t : tiles)
		{
			//draws tiles only if there within a 20 block radius of player
			if((t.getX()<<3)<player.x+(12<<3)&&(t.getX()<<3)>player.x-(12<<3))
			{
				if((t.getY()<<3)<player.y+(12<<3)&&(t.getY()<<3)>player.y-(12<<3))
				{
					t.render(display, this, t.getX()<<3, t.getY()<<3);
				}
			}

		}
	}

	public void renderEntities(Display display, int xoffset, int yoffset)
	{
		display.setOffset(xoffset, yoffset);
		for(Entity e : entities)
		{
			//draws entities only if they're in a 20 block radius of player
			if((e.getMobX())<(player.x)+(20<<3)&&(e.getMobX())>(player.x)-(20<<3))

			{
				if((e.getMobY())<(player.y)+(20<<3)&&(e.getMobY())>(player.y)-(20<<3))
				{
					e.render(display);
				}
			}

		}
	}
	public void spawnHostiles()
	{
		int amount = random.nextInt(20)+10;
		for(int spawning=0;spawning<=amount;spawning++)
		{
			//addEntity(new Skeleton(this,"skeleton",hostileSpawnX(),hostileSpawnY(),0,false));

		}
	}

	public int hostileSpawnX()
	{
		int sX=random.nextInt(150)+40;
		int posNeg=random.nextInt(2);
		if(posNeg==0)
		{
			return player.x-sX;
		}
		else
		{
			return player.x+sX;
		}


	}
	public int hostileSpawnY()
	{
		int sY=random.nextInt(150);
		int posNeg=random.nextInt(2);
		if(posNeg==0)
		{
			return player.y-sY;
		}
		else
		{
			return player.y+sY;
		}
	}

}
