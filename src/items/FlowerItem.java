package items;

import myMMO.Colours;
import myMMO.entity.PlayerEntity;

public class FlowerItem extends Item
{
	static int id =0+18*32;
	static int colour =Colours.get(-1, 500, -1, 131);
	
	public FlowerItem(String name) 
	{
		super(name, colour,id);
		
	}


	public void doAction(PlayerEntity player) {
		

	}

}
