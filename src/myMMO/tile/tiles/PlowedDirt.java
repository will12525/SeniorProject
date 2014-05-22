package myMMO.tile.tiles;

import items.HoeItem;
import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class PlowedDirt extends BaseTile{

	public PlowedDirt(int xcoord, int ycoord) {
		super(11, 4, 0,Colours.get(-1, 100, 211, 322), 0xFF422000, 0, 0, xcoord, ycoord);
		// TODO Auto-generated constructor stub
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
