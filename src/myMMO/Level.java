package myMMO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class Level
{
	private Game game;
	private String imagePath;
	
	private List<Entity> entities = new ArrayList<Entity>();
	private Entity player;
	
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	private Random random = new Random();
	
	public Level(Game game, String imagePath)
	{
		this.game = game;
		this.imagePath = imagePath;
		
		LevelGen.createWorld(this,200, 200);
	}
	
	public void addEntity(Entity e)
	{
		entities.add(e);
	}
	
	public List<Entity> getEntities()
	{
		return entities;
	}
	
	public void setPlayer(PlayerEntity player)
	{
		this.player = player;
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
		
		return null;
	}
	
	public static List<Tile> getTiles()
	{
		return tiles;
	}
	
	//keep everything running
	public void tick()
	{
		//tick tiles
		//tick entities
		
		for(Tile t : tiles)
		{
			t.tick();
		}
		
		for(Entity e : entities)
		{
			e.tick();
		}
	}
	
	public void renderTiles(Display display, int xoffset, int yoffset)
	{
		for(Tile t : tiles)
		{
			t.render(display, this, t.getX(), t.getY());
		}
	}
	
	public void renderEntities(Display display, int xoffset, int yoffset)
	{
		for(Entity e : entities)
		{
			e.render(display);
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
