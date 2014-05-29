package myMMO.tile;

import items.Item;
import myMMO.Display;
import myMMO.Level;
import myMMO.entity.Entity;

public abstract class BaseTile extends Tile {

	protected int tileId;
	protected int tileColour;
	private int flipX;
	private int flipY;
	

	public BaseTile(int id, int x, int y, int tileColour, int levelColour, int flipX, int flipY, int xcoord, int ycoord) {
		super(id, false, false, levelColour, xcoord, ycoord);
		this.tileId = x + y * 32;
		this.tileColour = tileColour;
		this.flipX=flipX;
		this.flipY=flipY;
	}
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}
	public void tick() {
	}

	public void render(Display display, Level level, int x, int y) {
		display.render(x, y, tileId, tileColour, flipX, flipY, 1);
	}
	public abstract void drop(Level level);
	
	public abstract Tile getDestroyedVarient(Item item);


}
