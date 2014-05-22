package myMMO.tile.tiles;

import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.SolidTile;
import myMMO.tile.Tile;

public class LogTile extends SolidTile{

	public LogTile(int xcoord, int ycoord) {
		super(4,3,0,Colours.get(100, 433, 322, 211),0xFFaa5500, xcoord, ycoord);
		// TODO Auto-generated constructor stub
	}
	public Tile getDestroyedVarient(Item item){
		return new DirtTile(xcoord,ycoord);
	}
	
	public void drop(Level level) {
		
		
	}

}
