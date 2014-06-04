package myMMO.tile.tiles;

import items.Item;
import items.LogItem;
import items.RockItem;
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
		Item item=new LogItem("log");
		item.setX(xcoord);
		item.setY(ycoord);
		item.setCoolDown(30);
		level.addItem(item);
		
	}

}
