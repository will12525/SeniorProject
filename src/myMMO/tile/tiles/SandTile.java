package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class SandTile extends BaseTile{

	public SandTile( int xcoord, int ycoord) {
		super(5,4,0,Colours.get(-1, 550, 550, 554),0xFFFFFF00,0,0, xcoord, ycoord);
	}
	public Tile getDestroyedVarient(Item item){
		return new DirtTile(xcoord,ycoord);
	}
}
