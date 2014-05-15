package items;

import myMMO.Display;
import myMMO.entity.PlayerEntity;

public abstract class Item {

	public abstract void doAction(PlayerEntity player);
	
	public abstract void render(Display display);
	
	public abstract void doPickup(PlayerEntity player);
}
