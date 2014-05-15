package items;

import myMMO.Colours;
import myMMO.Display;
import myMMO.entity.PlayerEntity;

public class RockItem extends Item {

	public RockItem(int x, int y, int spriteLocation) {
		super(x, y, spriteLocation);
	}


	public void render(Display display) 
	{
		display.render(20, 20, 2+19*32, Colours.get(-1, 555, 444, 333), 0, 0, 1);
	}

	
	public void doAction(PlayerEntity player) 
	{
		
		
	}

	
	public void doPickup(PlayerEntity player)
	{
		
		
	}

}
