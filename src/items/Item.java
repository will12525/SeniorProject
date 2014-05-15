package items;

import myMMO.Display;
import myMMO.Game;
import myMMO.entity.PlayerEntity;

public abstract class Item {
	protected int x, y;
	
	public Item(String name)
	{
		
	}

	public abstract void doAction(PlayerEntity player);
	
	public abstract void renderOnGround(Display display);
	
	public abstract void renderInInventory(Display display, int inventoryIndex);
	
	public boolean tryPickup(PlayerEntity player)
	{
		int itemX = x;
		int itemY = y;
		
		int playerX = player.getMobX();
		int playerY = player.getMobY();
		
		int differenceX = Math.abs(Math.abs(itemX) + Math.abs(playerX));
		int differenceY = Math.abs(Math.abs(itemY) + Math.abs(playerY));
		
		System.out.println(differenceX + " " + differenceY);
		
		if(differenceX <= 1 && differenceY <= 1)
		{
			//pickup
			Game.getLevel().getItems().remove(this);
			player.getItems().add(this);
			return true;
		}
		
		return false;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
}
