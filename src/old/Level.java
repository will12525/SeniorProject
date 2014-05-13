/*
package old;

import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import myMMO.entity.Skeleton;
import myMMO.tile.Tile;

public class Level {

	public int width;
	public int height;
	public int lastWidth;
	public int lastHeight;
	public boolean mapFixed=false;
	Game game;

	
	public static Map<Point, Tile> map = new HashMap<Point, Tile>();
	
	
	public ArrayList<Entity>entities = new ArrayList<Entity>();
	
	//public ArrayList<Entity>removeList=new ArrayList<Entity>();
	//	public int lastTile =0;
	private String imagePath;
	private BufferedImage image;
	private Random random = new Random();
	public Skeleton skelleton;
	//public Entity entity;
	public PlayerEntity player;
	public List<Entity>rowEntities=new ArrayList<Entity>();

	private Comparator<Entity> spriteSorter = new Comparator<Entity>() {
		public int compare(Entity e1, Entity e2) {
			if (e2.y < e1.y) return +1;
			if (e2.y > e1.y) return -1;
			return 0;
		}

	};

	@SuppressWarnings("unchecked")
	public Level(Game game,String imagePath)
	{
		this.game=game;
		if(imagePath!=null)
		{
			this.imagePath=imagePath;
			loadLevelFromFile();
		}
		else{

			width=200;
			height=200;
			//tiles1=new int[width*height];
			//tiles=new byte[width*height];
			
			
		
			LevelGen.createWorld(width,height);
			//this.generateLevel();
		}
		
		lastWidth=width;
		lastHeight=height;
	}

	public Image getImage()
	{
		return image;
	}

	private void loadLevelFromFile()
	{
		try{
			image=ImageIO.read(Level.class.getResource(this.imagePath));
			width=image.getWidth();
			height=image.getHeight();
		//	tiles=new byte [width*height];
			loadTiles();



		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	//width is width of image
	//tileColours is the pixel colour for each tile on the image
	private void loadTiles()
	{
		int[] tileColours =this.image.getRGB(0, 0, width, height, null, 0, width);
		for(int y=0;y<height;y++)
		{
			for(int x=0;x<width;x++)
			{
				tileCheck: 	for(Tile t: Tile.tiles)
				{
					if(t!=null&&t.getLevelColour()==tileColours[x+y*width])
					{

						//this.tiles[x+y*width]=t.getId();

						break tileCheck;
					}
				}
			}
		}
		//fixMap();

	}
	
	private void fixMap()
	{
		//random patches
		for(int y=0;y<height*8;y++)
		{
			if(random.nextInt(2000)<=1)
			{
				y++;
			}
			for(int x=0;x<width*8;x++)
			{
				if(random.nextInt(2000)<=1)
				{
					x++;
				}
				if(random.nextInt(7000)<=1)
				{
					Tile tileId=Tile.VOID;
					if(random.nextInt(10)>=5)
					{
						tileId=Tile.DIRT;
					}
					else
					{
						tileId=Tile.SAND;
					}
					makePatches(x,y,tileId);

				}

			}
		}
		for(int y=0;y<height*8;y++)
		{

			for(int x=0;x<width*8;x++)
			{
				//add sand
				/*if(getTile((x>>3),(y>>3)).id==3)
					System.out.println("hi"+x+", "+y);
				 
				if(getTile((x>>3), (y>>3))==Tile.WATER)
				{
					//System.out.println("Water!");
					if(getTile((x>>3)-1,(y>>3))==Tile.GRASS)
					{
						setTile((x>>3)-1,y>>3,Tile.SAND);
					}
					if(getTile((x>>3)+1,y>>3)==Tile.GRASS)
					{
						setTile((x>>3)+1,y>>3,Tile.SAND);
					}

					if(getTile(x>>3,(y>>3)-1)==Tile.GRASS)
					{
						setTile(x>>3,(y>>3)-1,Tile.SAND);
					}
					if(getTile((x>>3),(y>>3)+1)==Tile.GRASS)
					{
						setTile(x>>3,(y>>3)+1,Tile.SAND);
					}
				}

				//some random trees
				if(random.nextInt(1000)<=1)
				{
					if(getTile(x>>3,y>>3)==Tile.GRASS&&getTile((x>>3)-1,y>>3)==Tile.GRASS&&getTile((x>>3)+1,y>>3)==Tile.GRASS
							&&getTile(x>>3,(y>>3)-1)==Tile.GRASS&&getTile(x>>3,(y>>3)-2)==Tile.GRASS)
					{
						setTile(x>>3,y>>3,Tile.LOG);
						setTile(x>>3,(y>>3)-1,Tile.LOG);
						setTile(x>>3,(y>>3)-2,Tile.LEAVES);
						setTile((x>>3)-1,(y>>3)-1,Tile.LEAVES);
						setTile((x>>3)+1,(y>>3)-1,Tile.LEAVES);
					}
				}


			}
		}


	}
	
	public void makePatches(int xPos,int yPos,Tile tile)
	{

		if(getTile((xPos>>3),(yPos>>3))!=Tile.WATER&&getTile((xPos>>3),(yPos>>3))!=Tile.STONE)
		{
			setTile((xPos>>3),(yPos>>3),tile);
			setTile((xPos>>3)+1,(yPos>>3),tile);

			setTile((xPos>>3)-1,(yPos>>3)+1,tile);
			setTile((xPos>>3),(yPos>>3)+1,tile);
			setTile((xPos>>3)+1,(yPos>>3)+1,tile);
			setTile((xPos>>3)+2,(yPos>>3)+1,tile);

			setTile((xPos>>3),(yPos>>3)+2,tile);
			setTile((xPos>>3)+1,(yPos>>3)+2,tile);
		}
	}



	
	public void renderTiles(Display display, int xOffset,int yOffset)
	{
		if(!mapFixed)
		{
			//fixMap();
			mapFixed=true;
		}
	
		display.setOffset(xOffset,yOffset);

		for (int y = (yOffset >> 3); y < (yOffset + display.height >> 3) +1; y++) {
			for (int x = (xOffset >> 3); x < (xOffset + display.width >> 3) + 1; x++) {
				getTile(x, y).render(display, this, x << 3, y << 3);
			}
		}
	}


	public void renderTheEntities(Display display,int xOffset,int yOffset)
	{

		for(Entity e : entities) e.render(display);
	
		for(int y=(yOffset>>3)-3;y<=((yOffset)+display.height>>3)+3;y++)
		{
			for(int x=(xOffset>>3)-3;x<=(xOffset+display.width>>3)+3;x++)
			{
			
				
				rowEntities.addAll(entitiesInTiles[x+y*width]);
			}
			if(rowEntities.size()>0)
			{
				renderEntities(display,rowEntities);
			}
			rowEntities.clear();
		}
		
		display.setOffset(0, 0); 
	}





	
	public Tile getTile(int x,int y)
	{
		//System.out.println(x + "   " + y);
		
		if(x < 0 || x >= width || y < 0 || y >= height)
		{
			return Tile.DIRT;
		}
		else
		{
			return map.get(new Point(x, y));
	
		}
		//return Tile.tiles[tiles1[x+y*width]];
		//return Tile.tiles[tiles[x+y*width]];
	}

	public void setTile(int x,int y,Tile t)
	{
		if(0>x||x>=width||0>y||y>=height)
		{
			return;
		}
		
	//	tiles1[x+y*width]= t.id;
	}
	public int searchForTile(int x,int y,Tile t)
	{
		return y&x;

	}

	
	public void renderEntities(Display display,List<Entity>list)
	{
		Collections.sort(list,spriteSorter);
		for(int i=0;i<list.size();i++)
		{
			list.get(i).render(display);
		}
	}
	
	public void addEntity(Entity entity)
	{
		if(entity instanceof PlayerEntity)
		{
			player =(PlayerEntity) entity;
		}
		entity.removed=false;
		entities.add(entity);

		//insertEntity((entity.x>>3),(entity.y>>3),entity);
	}


	public void remove(Entity e)
	{
		entities.remove(e);
		int x1=(e.x>>3);
		int y1=(e.y>>3);
		removeEntity(x1,y1,e);
	}


	private void insertEntity(int x, int y, Entity e) {
		e.x = x;
		e.y = y;
		entities.add(e);
	}


	public void removeEntity(int x,int y,Entity entity)
	{
		entities.remove(entity);
	}
	
	// * First cycles through each tile and calls the tiles tick, then cycles through all the entities and calls there tick. If the entities remove boolean is true then the method to remove the entity is called
	 
	public void tick()
	{
		
		
		
		
		for(Tile t: Tile.tiles)
		{
			if(t==null)
			{
				break;
			}
			t.tick();
		}
		for(int i=0;i<entities.size();i++)
		{
			Entity e=entities.get(i);
			int x1=(e.x>>3);
			int y1=(e.y>>3);

			e.tick();
			if(e.removed)
			{
				entities.remove(i--);
				removeEntity(x1,y1,e);

			}
			else
			{

				int x2=(e.x>>3);
				int y2=(e.y>>3);
				if(x1!=x2||y1!=y2)
				{
					removeEntity(x1,y1,e);
					//insertEntity(x2,y2,e);
				}
			}
		}
		if((player.x>>3)>=lastWidth-30)
		{
			lastWidth=lastWidth+8;
			System.out.println(lastWidth);
		}
		if((player.y>>3)>=height-5)
		{
			height=height+8;
		}
		if((lastWidth>width)||(height<lastHeight))
		{
			System.out.println("more tiles?");
			LevelGen.addMoreTiles(width,height,lastWidth,lastHeight,this);
		}
	}
	public int getMapWidth()
	{
		return (width>>3);
	}
	public int getMapHeight()
	{
		return (height>>3);
	}

	public List<Entity> getEntities(int x1,int y1,int x2,int y2)
	{
		List<Entity>result=new ArrayList<Entity>();
		int xt1=(x1>>3)-1;
		int yt1=(y1>>3)-1;
		int xt2=(x2>>3)-1;
		int yt2=(y2>>3)-1;
		for(int y=yt1;y<yt2;y++)
		{
			for(int x=xt1;x<=xt2;x++)
			{
				if(x<0||x>=width||y<0||y>=height)
				{
					List<Entity>entities=entitiesInTiles[x+y*width];
					for(int i=0;i<entities.size();i++)
					{
						Entity e = entities.get(i);
						if(e.intersects(x1, y1, x2, y2))
						{

							result.add(e);
						}
					}
				}
			}
		}
		return result;
	} 

	public void spawnHostiles(Entity entity)
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

}*/
