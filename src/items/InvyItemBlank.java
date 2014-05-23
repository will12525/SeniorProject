package items;

import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class InvyItemBlank extends Tool {

	private static int id =0;
	private static int colour=Colours.get(-1,-1, -1, -1);


	public InvyItemBlank(String name) {
		super(name,colour,id);
	}

	public void doAction(PlayerEntity player,Level level) 
	{
		Game.level.destroyTile();
	}
	public int[] getDestroyables()
	{
		
		return new int[] {Tile.DIRT,Tile.GRASS,Tile.WHEAT,Tile.PLOWED_DIRT,Tile.FLOWER_TILE,Tile.LEAVES,Tile.LOG,Tile.PLANK,Tile.SAND};
	}




}
