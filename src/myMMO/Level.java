package myMMO;

import items.Item;
import items.RockItem;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import myMMO.biome.Biome;
import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.LogTile;
import myMMO.tile.tiles.StoneTile;

@SuppressWarnings("all")
public class Level
{
	private Game game;
	private String imagePath;

	private static List<Entity> entities = new ArrayList<Entity>();
	private static PlayerEntity player;

	private static List<Tile> tiles = new ArrayList<Tile>();
	private static List<Biome>biomes=new ArrayList<Biome>();
	private static List<Item> items=new ArrayList<Item>();

	private Random random = new Random();

	public static int currentxMax=20;
	public static int currentxMin=-20;
	public static int currentyMax=20;
	public static int currentyMin=-20;

	public static int originalxMax= currentxMax;
	public static int originalxMin=currentxMin;
	public static int originalyMax=currentyMax;
	public static int originalyMin=currentyMin;

	private int tickCount = 0;

	
	public int width;
	public int height;
	private BufferedImage image;

	public Level(Game game, String imagePath)
	{
		this.game = game;
		this.imagePath = imagePath;
		//if(imagePath!=null)
		//{
		//	this.imagePath=imagePath;
		//	loadLevelFromFile();
		//}
		//else
		//{
			LevelGen.createWorld(this,currentxMax,currentyMax,currentxMin,currentyMin);
		//}
	}
	public Image getImage()
	{
		return image;
	}
	private void loadLevelFromFile()
	{
		try
		{
			image=ImageIO.read(Level.class.getResource(this.imagePath));
			width=image.getWidth();
			height=image.getHeight();
			loadTiles();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//levelColour is how each tile identifies with the tile on the jPeg
	private void loadTiles()
	{
		//loads each pixel from the imagePath and gets the color code into an array of ints
		int[] tileColours =this.image.getRGB(0, 0, width, height, null, 0, width);
		
		//width and height of the image
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				
				tileCheck: for(Tile t: Tile.tiles)
				{
					//checks for t to not equal null and makes sure it levelColour equals
					if(t!=null&&t.getLevelColour()==tileColours[x+y*width])
					{
						
						t.xcoord=x;
						t.ycoord=y;
						tiles.add(t);
						break tileCheck;
					}
				}
			}
		}
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

	public PlayerEntity getPlayer()
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

	public void addItem(Item i)
	{
		items.add(i);
	}
	public List<Item> getItems()
	{
		return items;
	}


	//infinite world/biome stuff
	public void addBiome(Biome b)
	{
		biomes.add(b);
	}
	public Biome getBiome(int x,int y)
	{
		for(Biome b : biomes)
		{
			for(int x1 =b.getXStart();x1<=b.getXEnd();x1++)
			{
				for(int y1 =b.getYStart();y1<=b.getYEnd();y1++)
				{
					if(x==x1&&y==y1)
					{

						return b;
					}
				}
			}
		}
		return null;//new OceanBiome(0,0,0,0);
	}
	
	public void placeTile()
	{
		if(player.getItems().size() == 0) return;
		
		Item item = player.getItems().get(0);
		player.getItems().remove(0);
		
		int x = player.getMobX() >> 3;
		int y = (player.getMobY() >> 3) - 2; //test -2
		
		Tile t;
		
		if(item instanceof RockItem)
		{
			t = new StoneTile(x, y);
		}
		
		for(int i = 0; i < tiles.size(); i++)
		{
			if(tiles.get(i).getX() == x && tiles.get(i).getY() == y)
			{
				tiles.set(i, new StoneTile(x, y));
			}
		}
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
		
		for(Item item : items)
		{
			if(item.tryPickup(player))
			{
				break;
			}
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
			//System.out.println(player.y+", "+currentyMin+" :player y smaller");
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

	public void renderItems(Display display)
	{
		for(Item i : items)
		{
			i.renderOnGround(display);
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
