package myMMO.tile;

import myMMO.Display;
import myMMO.Level;
import myMMO.entity.Entity;

public class FenceTile extends Tile {
	private int[][] animationTileCoords;
	private int currentAnimationIndex;

	//	Level level;
	private boolean flipX;
	private boolean flipY;

	int renderThis;
	/**
	 * can change from 0 to 8 , anything else will crash, decides what the tile will be
	 */
	int renderId=0;
/**
 * the fence tile
 * @param id = Id of tile
 * @param animationCoords = coordinates on the sprite sheet for the tile
 * @param tileColour = color of tile in game
 * @param levelColour = color of tile on map sheet
 * @param flipX = 1 if the tile should be flipped on the x axis, 0 if not
 * @param flipY = 1 if the tile should be flipped on the y axis, 0 if not
 */
	public FenceTile(int id, boolean isSolid, boolean isEmitter, int[][] animationCoords, int tileColour, int levelColour, boolean flipX, boolean flipY, int x, int y) {
		super(id, isSolid, isEmitter, tileColour, levelColour, x, y, flipX,flipY);
		this.solid=true;
		this.animationTileCoords = animationCoords;
		this.currentAnimationIndex = 0;
		this.flipX=flipX;
		this.flipY=flipY;

	}
	public boolean mayPass(Level level, int x,int y, Entity e)
	 {
		 return false;
	 }
	public void render(Display display, Level level, int x, int y) {

		int x1 =(x>>3);
		int y1=(y>>3);
		flipX=false;
		flipY=false;
		
		
		if(level.getTile(x1, y1)==Tile.FENCE)
		{
			renderId=0;
			//one left
			if(level.getTile(x1+1, y1)==Tile.FENCE)
			{
				renderId=1;
				
			}
			//1 right
			if(level.getTile(x1-1, y1)==Tile.FENCE)
			{
				renderId=1;
				flipX=true;
			}
			//one up
			if(level.getTile(x1, y1-1)==Tile.FENCE)
			{
				renderId=2;
				
			}
			//1 down
			if(level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=2;
				flipY=true;
			}
			//2 on x
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1+1, y1)==Tile.FENCE)
			{
				renderId=3;
			}
			//2 on y
			if(level.getTile(x1, y1-1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=4;
			}
			//corner left down
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=5;
				flipX=true;
				flipY=true;
			}
			//corner left up
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1, y1-1)==Tile.FENCE)
			{
				renderId=5;
				flipX=true;
			}
			//corner right down
			if(level.getTile(x1+1, y1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=5;
				flipY=true;
			}
			//corner right up
			if(level.getTile(x1+1, y1)==Tile.FENCE&&level.getTile(x1, y1-1)==Tile.FENCE)
			{
				renderId=5;
			}
			//three down
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1+1, y1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=6;
				flipY=true;
			}
			//three up
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1+1, y1)==Tile.FENCE&&level.getTile(x1, y1-1)==Tile.FENCE)
			{
				
				renderId=6;
			}
			//three right
			if(level.getTile(x1+1, y1)==Tile.FENCE&&level.getTile(x1, y1-1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=7;
				
			}
			//three left
			if(level.getTile(x1-1, y1)==Tile.FENCE&&level.getTile(x1, y1-1)==Tile.FENCE&&level.getTile(x1, y1+1)==Tile.FENCE)
			{
				renderId=7;
				flipX=true;
				//four connector
				if(level.getTile(x1+1, y1)==Tile.FENCE)
				{
					renderId=8;
				}
			}
			
		}

		renderThis =(animationTileCoords[renderId][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		display.render(x, y, renderThis, tileColour, flipX, flipY, 1);
	}
}
