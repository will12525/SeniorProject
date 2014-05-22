package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.SolidTile;
import myMMO.tile.Tile;

public class VoidTile extends SolidTile {

	public VoidTile(int xcoord, int ycoord) {
		super(0, 0, 0, Colours.get(000, 500, -1, -1), 0xFF000000, xcoord, ycoord);
	}
	public Tile getDestroyedVarient(Item item){
		return new VoidTile(xcoord,ycoord);
	}

	public void drop(Level level) {
		
		
	}
}
