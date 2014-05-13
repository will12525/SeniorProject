package myMMO.tile;

import myMMO.Level;
import myMMO.entity.Entity;

public class AnimatedTile extends Tile {
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;

	public AnimatedTile(int id, boolean isSolid, boolean isEmitter, int[][] animationCoords, int tileColour, int levelColour, int animationSwitchDelay, boolean flipx, boolean flipy, int x, int y) {
		super(id, isSolid, isEmitter, tileColour, levelColour, x, y, flipx, flipy);
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
			this.id = (byte) (animationTileCoords[currentAnimationIndex][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		}
	}
}
