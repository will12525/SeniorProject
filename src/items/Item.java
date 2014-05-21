package items;

import myMMO.Display;
import myMMO.Game;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

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
	public Item(String name, int colour, int id)
	{
		this.id=id;
		this.colour=colour;
	}

	public abstract void doAction(PlayerEntity player);

	public void renderOnGround(Display display)
	{
		display.render(x<<3, y<<3, id,colour, 0, 0, 1);
	}

	public void renderInInventory(Display display, int inventoryIndex)
	{
		display.render((8*inventoryIndex)+119, 108, id, colour, 0, 0, 1);
	}

	public void renderOnMouse(Display display, int mouseX,int mouseY)
	{
		this.x=mouseX;
		this.y=mouseY;
		display.render((x>>3), (y>>3)-10, id,colour, 0, 0, 1);
	}
	public void renderInMainInventory(Display display, int position)
	{
		if(position<5)
		{
			display.render((position*8)+40, 40, id, colour, 0, 0, 1);
		}
		if(position<10&&position>4)
		{
			display.render(((position-5)*8)+40, 56, id, colour, 0, 0, 1);
		}
		if(position<15&&position>9)
		{
			display.render(((position-10)*8)+40, 64, id, colour, 0, 0, 1);
		}
	}
	public void renderOnHand(Display display,Level level,PlayerEntity player) {
		int xModifier=0;
		int yModifier=0;
		if(player.getMovingDirection()==0)
		{
			xModifier=8;
		}
		if(player.getMovingDirection()==1)
		{
			xModifier=-8;
		}
		if(player.getMovingDirection()==2)
		{
			xModifier=-4;
		}
		if(player.getMovingDirection()==3)
		{
			xModifier=4;
		}
		
		display.render(player.getMobX()+xModifier, player.getMobY()+yModifier, id, colour, 0, 0, 1);

	}


	public boolean tryPickup(PlayerEntity player)
	{
		//int position=0;

		int itemX = x;
		int itemY = y;

		int playerX = player.getX()>>3;
		int playerY = player.getY()>>3;

		int differenceX = Math.abs(itemX - playerX);
		int differenceY = Math.abs(itemY - playerY);

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
	public Tile getTile(int newX,int newY)
	{
		return null;
	}



}
