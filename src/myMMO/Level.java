package myMMO;

import items.InvyItemBlank;
import items.Item;
import items.Tool;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import sun.awt.image.ToolkitImage;
import myMMO.biome.Biome;
import myMMO.entity.Entity;
import myMMO.entity.FakePlayerEntity;
import myMMO.entity.PlayerEntity;
import myMMO.packet.Packet03TileUpdate;
import myMMO.tile.Tile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.VoidTile;

@SuppressWarnings("all")
public class Level
{
	private Game game;
	private String imagePath;

	private static List<Entity> entities = new ArrayList<Entity>();
	private static List<Entity> entitiesToRemove = new ArrayList<Entity>();
	private static List<Entity> entitiesToAdd = new ArrayList<Entity>();
	private static PlayerEntity player;

	public static List<Tile> tiles = new ArrayList<Tile>();
	private static List<Biome>biomes=new ArrayList<Biome>();
	private static List<Item> items=new ArrayList<Item>();
	private static List<Item> mouseItem= new ArrayList<Item>();
	private static Item playerHoldItem=null;

	private Random random = new Random();

	public static int currentxMax=20;
	public static int currentxMin=-20;
	public static int currentyMax=20;
	public static int currentyMin=-20;

	public static int originalxMax= currentxMax;
	public static int originalxMin=currentxMin;
	public static int originalyMax=currentyMax;
	public static int originalyMin=currentyMin;

	public static int tickCount = 0;


	public int width;
	public int height;
	public BufferedImage image;
	public BufferedImage imageNegX;
	public BufferedImage imageNegY;
	public BufferedImage imageNegBoth;
	int type = BufferedImage.TYPE_INT_RGB;
	public BufferedImage bigImage=new BufferedImage(Math.abs(currentxMin)+currentxMax, Math.abs(currentyMin)+currentyMax,type);


	int xOffset=0;
	int yOffset=0;
	int topxOff=0;
	int topyOff=0;

	List<Tile> tilesToSave= new ArrayList<Tile>();

	public Level(Game game, String imagePath)
	{
		this.game = game;
		this.imagePath = imagePath;
		if(imagePath!=null)
		{
			this.imagePath=imagePath;
			loadLevelFromFile();
		}
		else
		{
			LevelGen.createWorld(this,currentxMax,currentyMax,currentxMin,currentyMin);
			File original = new File(Level.class.getResource("/levels/blank.png").getFile());
			File destination = new File(Level.class.getResource("/levels/imgTest.png").getFile());
			try {
				Files.copy(original.toPath(),destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//LevelGen.spawnPond(this);
		}
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


		for(int x=0;x<image.getWidth();x++)
		{
			for(int y=0;y<image.getHeight();y++)
			{
				int color =image.getRGB(x, y);

				Tile t = Tile.getTileFromColor(color,x,y);
				tiles.add(t);
			}
		}

		/*		//loads each pixel from the imagePath and gets the color code into an array of ints
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
		}*/
	}
	public static void addEntity(Entity e)
	{
		entitiesToAdd.add(e);
	}

	public List<Entity> getEntities()
	{
		return entities;
	}
	public void removeEntity(Entity e) {
		this.entitiesToRemove.add(e);
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
		for(int i = 0; i < tiles.size(); i++)
		{
			Tile t = tiles.get(i);

			if(t.getX() == x && t.getY() == y)
			{
				Tile tile = Tile.createTile(x, y, id);

				tiles.set(i, tile);
				return;
			}
		}

		//it is a new tile, add it to the list
		Tile tile = Tile.createTile(x,  y, id);
		tiles.add(tile);
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

	public void addMouseItem(Item i)
	{
		mouseItem.add(i);
	}
	public List<Item> getMouseItem()
	{
		return mouseItem;
	}

	public void addItem(Item i)
	{
		items.add(i);
	}
	public synchronized List<Item> getItems()
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

		int itemPosition = game.selectedBox-1;

		Item item=player.holdItem;
		if(player.holdItem==null||item instanceof Tool)
		{
			return;
		}


		//player.getItems().remove(0);

		int x = (player.getX() >> 3);
		int y = (player.getY() >> 3);

		switch(player.getMovingDirection())
		{
		case 0: y -= 2; break; //up
		case 1: y += 2; break; //down
		case 2: x -= 2; break; //left
		case 3: x += 2; break; //right
		default: return;
		}

		Tile t = null;
		t=item.getTile(x,y);

		if(t==null)
		{
			return;
		}

		for(int i = 0; i < tiles.size(); i++)
		{
			if(tiles.get(i).getX() == x && tiles.get(i).getY() == y && tiles.get(i).getId() != t.getId())
			{
				//send update packet
				if(Game.multyPlayer)
				{
					System.out.println("sending tile packet");
					Packet03TileUpdate p03 = new Packet03TileUpdate("3:" + t.getX() + ":" + t.getY() + ":" + t.getId());
					p03.send(Game.instance.multiplayer.getOutput());
				}
				tiles.set(i, t);
				player.changeItem(new InvyItemBlank("empty"), itemPosition);
				player.setHoldItem(new InvyItemBlank("empty"),i);

			}
		}
	}
	public void setTile(int x,int y,Tile tile)
	{
		setTile(x, y, tile.getId());	
	}

	public void destroyTile()
	{
		int x = player.getX() >> 3;
		int y = player.getY() >> 3;

		switch(player.getMovingDirection())
		{
		case 0: y -= 2; break; //up
		case 1: y += 2; break; //down
		case 2: x -= 2; break; //left
		case 3: x += 2; break; //right
		default: return;
		}

		for(int i = 0; i < tiles.size(); i++)
		{
			Tile tile =tiles.get(i);
			Item playersItem=player.getHoldItem();
			if(tile.getX() == x && tile.getY() == y)
			{
				for(int k=0;k<playersItem.getDestroyables().length;k++)
				{
					if(playersItem.getDestroyables()[k]==tile.getId())
					{
						Tile newTile =tile.getDestroyedVarient(playersItem);
						if(newTile==null)
						{
							return;
						}
						tile.drop(this);
						tiles.set(i, newTile);
						if(playersItem.shouldDelete())
						{
							player.changeItem(new InvyItemBlank("empty"), player.getHoldItemPosition());
						}
					}

				}

				/*Tile newTile =tile.getDestroyedVarient(playersItem);

				tile.drop(this);
				tiles.set(i, newTile);*/
			}
		}
	}

	//keep everything running
	public void tick()
	{
		//tickCount++;

		for(Tile t : tiles)
		{
			t.tick();

		}

		for(Entity e : getEntities())
		{
			e.tick();

			if(e instanceof FakePlayerEntity)
			{
				//System.out.println(e.getX() + " " + e.getY());
			}
		}
		entities.removeAll(entitiesToRemove);
		entitiesToRemove.clear();
		entities.addAll(entitiesToAdd);
		entitiesToAdd.clear();

		for(Item item : items)
		{
			item.tick();
			if(item.tryPickup(player))
			{
				break;
			}
		}


		//	LevelGen.unloadChunks(player, this);
		//LevelGen.generateChunks(player);

		//generates more tiles as needed based on the players location


		int whatToSave=0;
		//adds pos x tiles
		if((player.getX()>>3)>currentxMax-20)
		{
			//this
			currentxMax=currentxMax+8;
		

			//LevelGen.addMorePosXTiles(currentxMax,originalxMax,currentyMax,currentyMin,this);
		}
		//adds neg x tiles
		if((player.getX()>>3)<currentxMin+20)
		{

			//System.out.println(player.x+", "+currentxMin+ " :player x smaller");
			currentxMin=currentxMin-8;
		
			//LevelGen.addMoreNegXTiles(currentxMin,originalxMin,currentyMax,currentyMin,this);
		}

		if((player.getY()>>3)>currentyMax-20)
		{

			currentyMax=currentyMax+8;
			//whatToSave=2;
			//LevelGen.addMorePosYTiles(currentyMax,originalyMax,currentxMax,currentxMin,this);
		}
		if((player.getY()>>3)<currentyMin+20)
		{
			//System.out.println(player.y+", "+currentyMin+" :player y smaller");
			currentyMin=currentyMin-8;
			//whatToSave=3;
			//LevelGen.addMoreNegYTiles(currentyMin,originalyMin,currentxMax,currentxMin,this);
		}



		//File f2= new File(Level.class.getResource("/levels/imgTestBig.png").getFile());
		//System.out.println(f2);

		List<Tile> tilesToRemove = new ArrayList<Tile>();
		List<Tile> tilesToAdd = new ArrayList<Tile>();
		//System.out.println(tickCount%20);
		if((tickCount) % 20 == 0)
		{

			BufferedImage resizedBigImage=new BufferedImage(Math.abs(currentxMin)+currentxMax+1000, Math.abs(currentyMin)+currentyMax+1000,type);
			try {
				bigImage=ImageIO.read(Level.class.getResource("/levels/imgTest.png"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//	boolean newTiles=true;

			if((resizedBigImage.getHeight()>bigImage.getHeight())||(resizedBigImage.getWidth()>bigImage.getWidth()))
			{

				if(tilesToSave.size()>3000)
				{

					File f1= new File(Level.class.getResource("/levels/imgTestBig.png").getFile());
					try {
						ImageIO.write(resizedBigImage, "png", f1);
						//System.out.println("wrote file");
					} catch (IOException e) {

						e.printStackTrace();
					}

					System.out.println("saving game");
					//findTopLeftTile();
					//	int[] levelColours=bigImage.getRGB(0, 0, bigImage.getWidth(), bigImage.getHeight(), null, 0, bigImage.getWidth());
					//this only transfers pixels from the original image to the new image
					//System.out.println(xOffset+", "+yOffset);

					File f3= new File(Level.class.getResource("/levels/bigImage.png").getFile());
					try {
						ImageIO.write(bigImage, "png", f3);
						//System.out.println("wrote file");
					} catch (IOException e) {

						e.printStackTrace();
					}

					System.out.println(resizedBigImage.getWidth()+", "+resizedBigImage.getHeight());
					for(int x=0;x<bigImage.getWidth();x++)
					{
						for(int y=0;y<bigImage.getHeight();y++)
						{
							int OIC=bigImage.getRGB(x, y);
							//System.out.println(OIC+", X: "+x+", Y: "+y);
							
						
							
							//black pixel
							if(OIC!=-16777216)
							{
								///System.out.println(OIC);
								//-16777216
								//System.out.println(x+", "+y);
								//if(x+Math.abs(currentxMin)<bigImage.getWidth()&&y+Math.abs(currentyMin)<bigImage.getHeight())
								{
									System.out.println(Math.abs(currentxMin));
								//	System.out.println((x+Math.abs(currentxMin))+", "+(y+Math.abs(currentyMin)));
									resizedBigImage.setRGB(x+Math.abs(currentxMin), y+Math.abs(currentyMin), OIC);
									//	resizedBigImage.setRGB(x+40, y, OIC);
								}

							}


						}
					}
					originalxMin=currentxMin;
					//System.exit(0);

					for(Tile t:tilesToSave)
					{
						//System.out.println(xOffset+", "+yOffset);
						//System.out.println(t.getY());
						//System.out.println((t.getX()+t.getX())+", "+(t.getY()+t.getY()));
						//System.out.println(t.getX());
						resizedBigImage.setRGB(t.getX()+Math.abs(currentxMin), t.getY()+Math.abs(currentyMin), t.getLevelColour());
					}


					tilesToSave.clear();
					bigImage=resizedBigImage;
					File f= new File(Level.class.getResource("/levels/imgTest.png").getFile());
					try {
						
						ImageIO.write(bigImage, "png", f);
						//System.out.println("wrote file");
					} catch (IOException e) {

						e.printStackTrace();
					}
				}

				if(tilesToSave.size()>2500)
				{
				System.out.println(tilesToSave.size());
				}







			}

			for(Tile t : tiles)
			{
				int x1 = (player.getX()>>3);
				int x2 = (t.getX());

				int y1 = (player.getY()>>3);
				int y2 = (t.getY());

				int deltax = x2-x1;
				int deltay = y2-y1;

				deltax = deltax*deltax;
				deltay = deltay*deltay;
				double distance = Math.sqrt(deltax + deltay);

				//System.out.println(x1 + " " + y1 + "   " + x2 + " " + y2+" "+t);

				if(distance > 30&&!(t instanceof VoidTile))
				{


					tilesToRemove.add(t);
				}
			}
			tiles.removeAll(tilesToRemove);
			tilesToSave.addAll(tilesToRemove);
			tilesToRemove.clear();


			for(int pX=(player.getX()>>3)-15;pX<(player.getX()>>3)+15;pX++)
			{
				for(int pY=(player.getY()>>3)-15;pY<(player.getY()>>3)+15;pY++)
				{
					//	System.out.println((player.getX()>>3)+", "+(player.getY()>>3)+", "+pX+", "+pY);

					if(getTile(pX,pY)==null)
					{

						tilesToAdd.add(new GrassTile(pX,pY));

					}

				}

			}
			tiles.addAll(tilesToAdd);
			tilesToAdd.clear();


		}
		tickCount++;

	}


	public void renderTiles(Display display, int xoffset, int yoffset)
	{
		display.setOffset(xoffset, yoffset);
		for(Tile t : tiles)
		{
			//draws tiles only if there within a 20 block radius of player
			if((t.getX()<<3)<player.getX()+(12<<3)&&(t.getX()<<3)>player.getX()-(12<<3))
			{
				if((t.getY()<<3)<player.getY()+(12<<3)&&(t.getY()<<3)>player.getY()-(12<<3))
				{
					t.render(display, this, t.getX()<<3, t.getY()<<3);
				}
			}

		}
	}

	public void renderItems(Display display)
	{
		for(Item i : getItems())
		{
			i.renderOnGround(display);
		}

	}
	public void renderMouseItem(Display display,int frameX,int frameY)
	{
		PointerInfo info = MouseInfo.getPointerInfo();
		Point p =info.getLocation();
		for(Item i : mouseItem)
		{
			i.renderOnMouse(display,( (int)p.getX()-frameX)+45,((int)p.getY()-frameY)+80);
		}
	}
	public void renderHoldItem(Display display)
	{
		if(player.getHoldItem()!=null)
		{
			player.getHoldItem().renderOnHand(display,this,player);
		}

	}
	public void renderEntities(Display display, int xoffset, int yoffset)
	{
		display.setOffset(xoffset, yoffset);
		for(Entity e : entities)
		{
			//draws entities only if they're in a 20 block radius of player
			if((e.getX())<(player.getX())+(20<<3)&&(e.getX())>(player.getX())-(20<<3))

			{
				if((e.getY())<(player.getY())+(20<<3)&&(e.getY())>(player.getY())-(20<<3))
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
			return player.getX()-sX;
		}
		else
		{
			return player.getX()+sX;
		}


	}
	public int hostileSpawnY()
	{
		int sY=random.nextInt(150);
		int posNeg=random.nextInt(2);
		if(posNeg==0)
		{
			return player.getY()-sY;
		}
		else
		{
			return player.getX()+sY;
		}
	}



}
