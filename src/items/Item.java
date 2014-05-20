package items;

import myMMO.Display;
import myMMO.Game;
import myMMO.entity.PlayerEntity;

public abstract class Item {
	protected int x, y, id, colour;
	private int coolDown=0;

	public void tick()
	{
		if(coolDown>0)
		{
			coolDown--;
		}
	}
	public void setCoolDown(int coolDown)
	{
		this.coolDown=coolDown;
	}
	public Item(String name, int colour)
	{
		this.colour=colour;
	}

	public abstract void doAction(PlayerEntity player);

	public abstract void renderOnGround(Display display);

	public abstract void renderInInventory(Display display, int inventoryIndex);

	public void renderOnMouse(Display display, int mouseX,int mouseY)
	{
		this.x=mouseX;
		this.y=mouseY;
		display.render((x>>3), (y>>3)-10, 4+19*32,colour, 0, 0, 1);
	}

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
		if(coolDown<=0)
		{
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
