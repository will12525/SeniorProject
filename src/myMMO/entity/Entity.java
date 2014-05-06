package myMMO.entity;

import myMMO.Display;
import myMMO.Level;
import myMMO.tile.Tile;


/**
 * 
 * Main constructor for all entities in the game
 *
 */
public class Entity {

	public int x;
	public int y;
	public int xr=6;
	public int yr=6;
	public boolean removed;

	public Level level;

	public Entity(Level level)
	{
		init(level);
	}
	public final void init(Level level)
	{
		this.level =level;
	}


	public boolean intersects(int x1,int y1,int x2,int y2)
	{
		return !(x+xr<x1||y+yr<y1||x-xr>x2||y-yr>y2);
	}


	public boolean blocks(Entity e)
	{
		return false;
	}

	public void hurt(Mob mob,int dmg, int direction)
	{	
	}
	public void hurt(Tile tile, int x, int y, int dmg)
	{

	}

	public boolean isBlockableBy(Mob mob)
	{
		return true;
	}


	public void remove()
	{
		removed=true;
	}


	public void tick() {
	}

	/**
	 *
	 * @param display = the display
	 */
	public void render(Display display) {
	}


	protected void touchedBy(Entity entity)
	{

	}
	public int getMobX()
	{
		return 0;
	}
	public int getMobY()
	{
		return 0;
	}

	public int getLightRadius()
	{
		return 0;
	}
	//protected void touchedBy(Entity entity)




}
