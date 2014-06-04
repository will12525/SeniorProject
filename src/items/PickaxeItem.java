package items;

import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class PickaxeItem extends Tool{
	private static int colour=Colours.get(-1, 211, 444, 333);
	private static int id =5+20*32;
	public PickaxeItem(String name) {
		super(name, colour, id);
		
	}
	public void doAction(PlayerEntity player,Level level)
	{
		Game.level.destroyTile();
	}
	
	public int[] getDestroyables()
	{
		
		return new int[] {Tile.STONE};
	}
}
