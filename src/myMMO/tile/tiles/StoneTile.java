package myMMO.tile.tiles;

import items.Item;
import items.RockItem;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.SolidTile;

public class StoneTile extends SolidTile {

	public StoneTile(int xcoord, int ycoord) {
		super(1,1,0,Colours.get(332, 333, 333, 343),0xFF555555, xcoord, ycoord);
	}
	public void drop(Level level) {
		Item item=new RockItem("rock");
		item.setX(xcoord);
		item.setY(ycoord);
		item.setCoolDown(100);
		level.addItem(item);

	}

}