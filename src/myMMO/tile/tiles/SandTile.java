package myMMO.tile.tiles;

import items.Item;
import items.RockItem;
import items.SandItem;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class SandTile extends BaseTile{

	public SandTile( int xcoord, int ycoord) {
		super(5,4,0,Colours.get(-1, 550, 550, 554),0xFFFFFF00,0,0, xcoord, ycoord);
	}
	public Tile getDestroyedVarient(Item item){
		return new DirtTile(xcoord,ycoord);
	}
	
	public void drop(Level level) {
		Item item=new SandItem("sand");
		item.setX(xcoord);
		item.setY(ycoord);
		item.setCoolDown(30);
		level.addItem(item);
	}
	
}
