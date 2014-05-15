package myMMO.menu;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;
import myMMO.Level;
import myMMO.entity.PlayerEntity;

public class InventoryMenu extends Menu {
	private PlayerEntity player;
	int canClose=200;
	long lastAction=System.currentTimeMillis();
int colours =Colours.get(-1, -1, 000, 222);
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


	public void render(Display display,Level level) {
		//Font.renderFont("hi", display, 50, 50, Colours.get(-1, -1, -1, 5), 1);

		//top corners
		display.render(32, 72, (1+27*32), colours,0,0,1);
		display.render(32, 24, (1+27*32), colours,0,1,1);

		//bottom corners
		display.render(120, 72, (1+27*32), colours,1,0,1);
		display.render(120, 24, (1+27*32), colours,1,1,1);

		for(int xS=0;xS<=9;xS++)
		{
			//top and bottom
			display.render((xS*8)+40, 72, (2+27*32),colours,0,0,1);
			display.render((xS*8)+40, 24, (2+27*32),colours,0,1,1);
			for(int yS=0;yS<=4;yS++)
			{
				//sides
				display.render(32, (yS*8)+32, (3+27*32), colours,1,1,1);
				display.render(120, (yS*8)+32, (3+27*32),colours,0,1,1);
				//filler
				display.render((xS*8)+40, (yS*8)+32, (3+27*32), Colours.get(-1, -1, 222, 222),0,1,1);

			}
		}
		Font.renderFont("Inventory", display, 36, 28, Colours.get(-1, -1, -1, 000), 1);
		for(int xS=0;xS<=4;xS++)
		{
			//taskbar item holders
			display.render((xS*8)+40, 40 ,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);
		}
		for(int xS=0;xS<=4;xS++)
		{
			for(int yS=0;yS<=1;yS++)
			{
				//other holder
				display.render((xS*8)+40, (yS*8)+56 ,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);
			}
		}
		for(int yS=0;yS<3;yS++)
		{
			display.render(100, ((yS*12))+40 ,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);
		}


		//render items in player's inventory
		for(int i = 0; i < level.getPlayer().getItems().size(); i++)
		{
			level.getPlayer().getItems().get(i).renderInMainInventory(display,i);
		}

	}

}
