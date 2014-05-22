package myMMO.tile.tiles;

import items.Item;

import java.util.Random;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.Entity;
import myMMO.tile.AnimatedTile;
import myMMO.tile.Tile;

@SuppressWarnings("all")
public class LeafTile extends AnimatedTile{
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private long lastIterationTime;
	private int animationSwitchDelay;
	Random random=new Random();

	public LeafTile( int xcoord, int ycoord) {
		super(7,new int[][] { { 0, 4 }, { 1, 4 }, { 2, 4 }, { 1, 4 } },Colours.get(121, -1, 151, -1),0xFF9eff7d,1000,0,0, xcoord, ycoord);
	
		this.solid=true;
	
	}
	public boolean mayPass(Level level, int x, int y, Entity e) {
		return false;
	}
	/*public void tick() {
		if ((System.currentTimeMillis() - lastIterationTime) >= (animationSwitchDelay)) {
			lastIterationTime = System.currentTimeMillis();
			currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
			int newAnimation=random.nextInt(4);
			this.tileId = (animationTileCoords[newAnimation][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		}
	}*/
	public Tile getDestroyedVarient(Item item){
		return new GrassTile(xcoord,ycoord);
	}
}