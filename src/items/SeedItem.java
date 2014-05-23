package items;

import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class SeedItem extends Tool{

	private static int colour=Colours.get(-1, 040, 330, -1);
	private static int id =2+20*32;

	public SeedItem(String name) {
		super(name, colour, id);
		deleteAfterUse=true;
		// TODO Auto-generated constructor stub
	}


	public void doAction(PlayerEntity player, Level level) {
		Game.level.destroyTile();
	}
	public int[] getDestroyables()
	{
		return new int[] {Tile.PLOWED_DIRT};
	}

}
