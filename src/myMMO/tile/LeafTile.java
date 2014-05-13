package myMMO.tile;

import java.util.Random;

import old.BaseTile;
import myMMO.Level;
import myMMO.entity.Entity;

public class LeafTile extends Tile{
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;
	Random random=new Random();

	public LeafTile(int id, boolean isSolid, boolean isEmitter, int[][] animationCoords, int tileColour, int levelColour, int animationSwitchDelay,boolean flipX,boolean flipY, int x, int y) {
		super(id, isSolid, isEmitter, tileColour, levelColour, x, y, flipY, flipY);
		this.animationTileCoords = animationCoords;
		this.currentAnimationIndex = 0;
		this.solid=isSolid;
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
			int newAnimation=random.nextInt(4);
			this.id = (byte) (animationTileCoords[newAnimation][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		}
	}
}