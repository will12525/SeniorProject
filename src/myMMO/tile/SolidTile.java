package myMMO.tile;

import items.Item;
import myMMO.Level;
import myMMO.entity.Entity;

public abstract class SolidTile extends BaseTile{

	public SolidTile(int id, int x, int y, int tileColour, int levelColour, int xcoord, int ycoord) {
		super(id, x, y, tileColour, levelColour,0,0, xcoord, ycoord);
		this.solid = true;
	}
	public boolean mayPass(Level level, int x,int y, Entity e)
	{
		return false;
	}
	public abstract Tile getDestroyedVarient(Item item);
	
	public abstract void drop(Level level);
	

}
