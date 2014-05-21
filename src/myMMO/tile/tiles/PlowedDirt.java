package myMMO.tile.tiles;

import myMMO.Colours;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class PlowedDirt extends BaseTile{

	public PlowedDirt(int xcoord, int ycoord) {
		super(11, 4, 0,Colours.get(-1, 100, 211, 322), 0xFF422000, 0, 0, xcoord, ycoord);
		// TODO Auto-generated constructor stub
	}
	public Tile getDestroyedVarient(){
		return new DirtTile(xcoord,ycoord);
	}
}
