package myMMO.tile.tiles;

import myMMO.Colours;
import myMMO.tile.BaseTile;

public class GrassTile extends BaseTile{

	public GrassTile(int id, int x, int y, int tileColour, int levelColour, int flipX, int flipY, int xcoord, int ycoord) {
		super(2,2,0,Colours.get(253, -1, 141, 250),0xFF00FF00,0,0, xcoord, ycoord);
	}

}
