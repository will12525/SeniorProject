package myMMO.menu;

import myMMO.Colours;
import myMMO.Display;
import myMMO.entity.PlayerEntity;

public class InventoryMenu extends Menu {
	private PlayerEntity player;
	int canClose=200;
	long lastAction=System.currentTimeMillis();

	public InventoryMenu(PlayerEntity player)
	{
		this.player=player;

	}


	public void tick() 
	{
		if(game.menu instanceof InventoryMenu)
		{
			if(System.currentTimeMillis()-lastAction>=canClose)
			{
				if(input.inventory.down)
				{
					player.setLastAction(System.currentTimeMillis());
					game.setMenu(null);
					lastAction=System.currentTimeMillis();
				}
			}
		}
	}


	public void render(Display display) {
		//Font.renderFont("hi", display, 50, 50, Colours.get(-1, -1, -1, 5), 1);
		//display.render(160-8-1, 108,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);
		
		for(int i = 0; i != 5; i++)
		{
			
		}
	}

}
