package myMMO.tile.tiles;

import myMMO.Colours;
import myMMO.tile.BaseTile;

public class SandTile extends BaseTile{

	public SandTile( int xcoord, int ycoord) {
		super(5,4,0,Colours.get(-1, -1, 550, 554),0xFFFFFF00,0,0, xcoord, ycoord);
	}

}