package myMMO.tile;

import items.Item;
import myMMO.Level;
import myMMO.entity.Entity;

public abstract class AnimatedTile extends BaseTile{
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;

	public AnimatedTile(int id, int[][] animationCoords, int tileColour, int levelColour, int animationSwitchDelay,int flipX,int flipY, int xcoord, int ycoord) {
		super(id, animationCoords[0][0], animationCoords[0][1], tileColour, levelColour, flipY, flipY, xcoord, ycoord);
		this.animationTileCoords = animationCoords;
		this.currentAnimationIndex = 0;
		this.lastIterationTime = System.currentTimeMillis();
		this.animationSwitchDelay = animationSwitchDelay;

	}
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return true;
	}
	public void tick() {
		if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
			lastIterationTime = System.currentTimeMillis();
			currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
			this.tileId = (animationTileCoords[currentAnimationIndex][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		}
	}
	public abstract Tile getDestroyedVarient(Item item);
	
	public abstract void drop(Level level);
}
