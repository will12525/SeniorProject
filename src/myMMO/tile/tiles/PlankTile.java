package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.tile.SolidTile;
import myMMO.tile.Tile;

public class PlankTile extends SolidTile{

	public PlankTile(int xcoord, int ycoord) {
		super(9,6,0,Colours.get(-1, 321, 432, -1),0xFF845510, xcoord, ycoord);
		// TODO Auto-generated constructor stub
	}
	public Tile getDestroyedVarient(Item item){
		return new DirtTile(xcoord,ycoord);
	}
}
