package myMMO.tile.tiles;

import myMMO.Colours;
import myMMO.tile.SolidTile;

public class StoneTile extends SolidTile {

	public StoneTile(int xcoord, int ycoord) {
		super(1,1,0,Colours.get(332, 333, 333, 343),0xFF555555, xcoord, ycoord);
	}

}