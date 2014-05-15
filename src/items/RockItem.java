package items;

import myMMO.Colours;
import myMMO.Display;
import myMMO.entity.PlayerEntity;

public class RockItem extends Item {
private int colour=Colours.get(-1, 555, 444, 333);
	public RockItem(String name) {
		super("Rock");
		
	}


	public void renderOnGround(Display display) 
	{
		display.render(x<<3, y<<3, 4+19*32,colour, 0, 0, 1);
	}

	
	public void doAction(PlayerEntity player) 
	{
		
	}

	
	@Override
	public void renderInInventory(Display display, int inventoryIndex) {
		display.render(inventoryIndex*8, 108, 4+19*32, colour, 0, 0, 1);
	}
}
