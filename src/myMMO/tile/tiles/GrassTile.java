package myMMO.tile.tiles;

import items.HoeItem;
import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class GrassTile extends BaseTile{

	public GrassTile( int xcoord, int ycoord) {
		super(2,2,0,Colours.get(253, -1, 141, 250),0xFF00FF00,0,0, xcoord, ycoord);
	}
	
	public Tile getDestroyedVarient(Item item){
		if(item instanceof HoeItem)
		{
			return new PlowedDirt(xcoord,ycoord);
		}
		return new DirtTile(xcoord,ycoord);
	}


	public void drop(Level level) {
		
		
	}
}
