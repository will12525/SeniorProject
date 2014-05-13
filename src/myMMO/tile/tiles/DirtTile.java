package myMMO.tile.tiles;

import myMMO.Colours;
import myMMO.tile.BaseTile;

public class DirtTile extends BaseTile{

	public DirtTile(int xcoord, int ycoord) {
		super(6,4,0,Colours.get(-1, -1, 211, 322),0xFF412000, 0, 0, xcoord, ycoord);
	}

}
