package myMMO.tile;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Level;
import myMMO.entity.Entity;
import myMMO.tile.tiles.DirtTile;
import myMMO.tile.tiles.GrassTile;
import myMMO.tile.tiles.LeafTile;
import myMMO.tile.tiles.LogTile;
import myMMO.tile.tiles.PlankTile;
import myMMO.tile.tiles.SandTile;
import myMMO.tile.tiles.StoneTile;
import myMMO.tile.tiles.VoidTile;
import myMMO.tile.tiles.WaterTile;

public class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new SolidTile(0,0,0,Colours.get(000, 500, -1, -1),0xFF000000, 0, 0);
	public static final Tile STONE = new SolidTile(1,1,0,Colours.get(332, 333, 333, 343),0xFF555555, 0, 0);
	public static final Tile GRASS = new BaseTile(2,2,0,Colours.get(253, -1, 141, 250),0xFF00FF00,0,0, 0, 0);
	public static final Tile WATER =new AnimatedTile(3,new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },Colours.get(-1, 004, 115, -1),0xFF0000FF,500, 0, 0, 0, 0);
	public static final Tile LOG = new SolidTile(4,3,0,Colours.get(100, 433, 322, 211),0xFFaa5500, 0, 0);
	public static final Tile SAND = new BaseTile(5,4,0,Colours.get(-1, -1, 550, 554),0xFFFFFF00,0,0, 0, 0);
	public static final Tile DIRT = new BaseTile(6,4,0,Colours.get(-1, -1, 211, 322),0xFF412000,0,0, 0, 0);
	public static final Tile LEAVES = new AnimatedTile(7,new int[][] { { 0, 4 }, { 1, 4 }, { 2, 4 }, { 1, 4 } },Colours.get(121, -1, 151, -1),0xFF9eff7d,500,0,0, 0, 0);
	public static final Tile FENCE = new FenceTile(8,new int[][] {{3,5},{4,5},{5,5},{6,5},{7,5},{8,5},{9,5},{10,5},{11,5}},Colours.get(253, 200,321,141),0xFFFF7d01,0,0, 0, 0);
	public static final Tile PLANK = new SolidTile(9,6,0,Colours.get(-1, 321, 432, -1),0xFF845510, 0, 0);
	public static final Tile FlowerTile = new BaseTile(10, 5, 0, Colours.get(131, 500, 141, 253),0xFFf40a10, 0, 0, 0, 0);
	
	
	//public static final Tile[] tiles = {};


	//public static final Tile CYCLE = new CycleTile(5,0,0,Colours.get(000, 000, 000, 000),0xFF000000);


	public byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColour;
	public int xcoord, ycoord;
/**
 * 
 * @param id = int Id of tile
 * @param isSolid = boolean true if entity cant pass through tile
 * @param isEmitter = boolean true if can produce light
 * @param levelColour = int color associated with tile on map sheet
 */
	public Tile(int id, boolean isSolid,boolean isEmitter,int levelColour, int xcoord, int ycoord)
	{

		this.id=(byte)id;
		if(tiles[id]!=null)
		{
			//throw new RuntimeException("Duplicate tile id on: "+id);
		}
		this.solid=isSolid;
		this.emitter=isEmitter;
		this.levelColour=levelColour;
		tiles[id]=this;
		
		this.xcoord = xcoord;
		this.ycoord = ycoord;
	}
	

	public static Tile createTile(int x, int y, int id)
	{
		switch(id)
		{
		case 0: return new VoidTile(x, y);
		case 1: return new StoneTile(x, y);
		case 2: return new GrassTile(x, y);
		case 3: return new WaterTile(x, y);
		case 4: return new LogTile(x, y);
		case 5: return new SandTile(x, y);
		case 6: return new DirtTile(x, y);
		case 7: return new LeafTile(x, y);
		case 8: return new FenceTile(x, y);
		case 9: return new PlankTile(x, y);
			
		default: return new DirtTile(x, y);
		}
	}
	/*
	 * public static Tile getTile(int id)
	{
		switch(id)
		{
		case 0: return VOID;
		case 1: return STONE;
		case 2: return GRASS;
		case 3: return WATER;
		case 4: return LOG;
		case 5: return SAND;
		case 6: return DIRT;
		case 7: return LEAVES;
		case 8: return FENCE;
		case 9: return PLANK;
			
		default: return DIRT;
		}
	}
	 */
	
	/**
	 * @return int, the id of the block
	 */
	public byte getId()
	{
		return id;
	}
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}
	/**
	 * @return boolean, the block is solid
	 */
	public boolean isSolid()
	{
		return solid;
	}
	/**
	 * @return boolean, the block can produce light
	 */

	/**
	 * @return int, the color associated with the tile on the map sheet 
	 */
	public int getLevelColour()
	{
		return levelColour;
	}
	public void bumpedInto(Level level,int x1,int y1,Entity entity)
	{
		
	}
	public void steppedOn(Level level, int x1,int y1, Entity entity)
	{
		
	}
	public void hurt(Level level, int x, int y, Entity entity, int damage, int direction)
	{
		
	}
	/**
	 * represents each tiles tick function
	 */
	public void tick()
	{
		
	}
	/**
	 * @param display = display
	 * @param level = level
	 * @param x = int x position in game
	 * @param y = int y position in game
	 */
	public void render(Display display, Level level, int x, int y)
	{
		
	}
	
	public int getX()
	{
		return xcoord;
	}
	
	public void setX(int xcoord)
	{
		this.xcoord = xcoord;
	}
	
	public int getY()
	{
		return ycoord;
	}
	
	public void setY(int ycoord)
	{
		this.ycoord = ycoord;
	}
	
	public void setID(int id)
	{
		this.id = (byte)id;
	}


	public void drop(Level level) {
		
		
	}
	public Tile getDestroyedVarient() {
		
		return null;
	}

	

}
