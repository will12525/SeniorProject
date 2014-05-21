package items;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.StoneTile;

public class RockItem extends Item {

	
	private static int id=4+19*32;
	private static int colour=Colours.get(-1, 111, 444, 333);
	

	public RockItem(String name) {
		super("Rock",colour,id);
	}

	public void doAction(PlayerEntity player,Level level) 
	{

	}
	public Tile getTile(int newX,int newY)
	{
		return new StoneTile(newX,newY);
	}






	


	
}
