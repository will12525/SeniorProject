package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.AnimatedTile;
import myMMO.tile.Tile;

public class WheatTile extends AnimatedTile{
	private static int colours=Colours.get(-1, 004, 115, -1);
	public WheatTile(int xcoord, int ycoord) {
		super(12, new int[][] { { 0, 4 }, { 1, 4 }, { 2, 4 }, { 1, 4 } }, colours, 0xFF84ff7d,2000,0, 0, xcoord, ycoord);

	}


	public Tile getDestroyedVarient(Item item) {

		return null;
	}


	public void drop(Level level) {


	}

}
