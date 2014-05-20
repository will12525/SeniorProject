package items;

import myMMO.Colours;
import myMMO.entity.PlayerEntity;

public class RockItem extends Item {

	
	private static int id=4+19*32;
	private static int colour=Colours.get(-1, 111, 444, 333);
	

	public RockItem(String name) {
		super("Rock",colour,id);
	}

	public void doAction(PlayerEntity player) 
	{

	}







	


	
}
