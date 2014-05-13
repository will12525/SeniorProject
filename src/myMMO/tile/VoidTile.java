package myMMO.tile;

import myMMO.Colours;

public class VoidTile extends SolidTile {

	public VoidTile(int xcoord, int ycoord) {
		super(0, 0, 0, Colours.get(000, 500, -1, -1), 0xFF000000, xcoord, ycoord);
	}

}
