package myMMO.tile.tiles;

import items.FlowerItem;
import items.HoeItem;
import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class FlowerTile extends BaseTile{

	public FlowerTile(int xcoord, int ycoord) {
		super(10, 5, 0, Colours.get(131, 500, 141, 253),0xFFf40a10, 0, 0, xcoord, ycoord);
		// TODO Auto-generated constructor stub
	}
	public void drop(Level level) {
		Item item=new FlowerItem("Flower");
		item.setX(xcoord);
		item.setY(ycoord);
		item.setCoolDown(30);
		level.addItem(item);

	}
	public Tile getDestroyedVarient(Item item){
		if(item instanceof HoeItem)
		{
			return new PlowedDirt(xcoord,ycoord);
		}
		return new GrassTile(xcoord,ycoord);
	}
}
