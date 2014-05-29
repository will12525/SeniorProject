package items;


import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class HoeItem extends Tool{
	
	private static int colour=Colours.get(-1, 211, 444, 333);
	private static int id =1+20*32;
	
	//private static int[] allowedTiles = {Tile.DIRT.getId(),Tile.GRASS.getId()};
	
	public HoeItem(String name,int hoeLevel) {
		super(name, colour, id);
		// TODO Auto-generated constructor stub
	}
	public void doAction(PlayerEntity player,Level level)
	{
		Game.level.destroyTile();
	}
	
	public int[] getDestroyables()
	{
		
		return new int[] {Tile.DIRT,Tile.GRASS,Tile.WHEAT};
	}

}
