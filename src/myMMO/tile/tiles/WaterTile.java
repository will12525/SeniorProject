package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.AnimatedTile;
import myMMO.tile.Tile;

public class WaterTile extends AnimatedTile{

	public WaterTile(int xcoord, int ycoord) {
		super(3,new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },Colours.get(-1, 004, 115, -1),0xFF0000FF,500, 0, 0, xcoord, ycoord);
		
	}
	public Tile getDestroyedVarient(Item item){
		return new SandTile(xcoord,ycoord);
	}
	
	public void drop(Level level) {
		
		
	}
}
