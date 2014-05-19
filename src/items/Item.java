package items;

import myMMO.Display;
import myMMO.Game;
import myMMO.entity.PlayerEntity;

public abstract class Item {
	protected int x, y, id;

	public Item(String name)
	{
	}

	public abstract void doAction(PlayerEntity player);

	public abstract void renderOnGround(Display display);

	public abstract void renderInInventory(Display display, int inventoryIndex);

	public boolean tryPickup(PlayerEntity player)
	{
		//int position=0;
		
		int itemX = x;
		int itemY = y;

		int playerX = player.getMobX()>>3;
		int playerY = player.getMobY()>>3;

		int differenceX = Math.abs(Math.abs(itemX) - Math.abs(playerX));
		int differenceY = Math.abs(Math.abs(itemY) - Math.abs(playerY));

		//System.out.println("ItemX: "+itemX+", ItemY: "+itemY+", PlayerX: "+playerX+", PlayerY: "+playerY+" DifferenceX: "+differenceX + ", DifferenceY: " + differenceY);

		if(differenceX <= 1 && differenceY <= 1)
		{
			for(int position=0;position<15;position++)
			{
				if(player.getItem(position) instanceof InvyItemBlank)
				{
					Game.getLevel().getItems().remove(this);
					player.changeItem(this, position);
					//player.getItems().add(this);
					return true;	
				}
			}
			
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

	public abstract void renderInMainInventory(Display display, int position);

}
