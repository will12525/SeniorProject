package items;


import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;


public class Tool extends Item{
	
	public Tool(String name, int colour, int id) {
		super(name, colour, id);
	}


	public void doAction(PlayerEntity player,Level level) {


	}

	public int[] getDestroyables()
	{
		return null;
	}


	
	public Tile getTile(int newX, int newY) {
		
		return null;
	}



}
