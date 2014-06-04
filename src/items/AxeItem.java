package items;

import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class AxeItem extends Tool{
	private static int colour=Colours.get(-1, 211, 444, 333);
	private static int id =6+20*32;
	public AxeItem(String name) {
		super(name, colour, id);
		// TODO Auto-generated constructor stub
	}
	public void doAction(PlayerEntity player,Level level)
	{
		Game.level.destroyTile();
	}
	
	public int[] getDestroyables()
	{
		
		return new int[] {Tile.LOG,Tile.FENCE,Tile.PLANK};
	}
}
