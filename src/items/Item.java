package items;

import myMMO.Display;
import myMMO.entity.PlayerEntity;

public abstract class Item {
	protected int x, y;
	protected int spriteLocation;
	
	public Item(int x, int y, int spriteLocation)
	{
		this.x = x;
		this.y = y;
		this.spriteLocation = spriteLocation;
	}

	public abstract void doAction(PlayerEntity player);
	
	public abstract void render(Display display);
	
	public abstract void doPickup(PlayerEntity player);
}
