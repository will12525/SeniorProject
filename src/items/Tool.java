package items;


import myMMO.Level;
import myMMO.entity.PlayerEntity;


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



}
