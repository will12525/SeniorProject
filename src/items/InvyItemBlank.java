package items;

import myMMO.Colours;
import myMMO.Display;
import myMMO.entity.PlayerEntity;

public class InvyItemBlank extends Item {


	private int colour=Colours.get(-1,-1, -1, -1);


	public InvyItemBlank(String name) {
		super("Rock");
	}


	public void renderOnGround(Display display) 
	{
		
	}


	public void doAction(PlayerEntity player) 
	{

	}



	public void renderInInventory(Display display, int inventoryIndex) {
		display.render(151-8*inventoryIndex, 108, 4+19*32, colour, 0, 0, 1);
	}



	public void renderInMainInventory(Display display, int position) {
		if(position<5)
		{
			display.render((position*8)+40, 40, 4+19*32, colour, 0, 0, 1);
		}
		if(position<10&&position>4)
		{
			display.render(((position-5)*8)+40, 56, 4+19*32, colour, 0, 0, 1);
		}
		if(position<15&&position>9)
		{
			display.render(((position-10)*8)+40, 64, 4+19*32, colour, 0, 0, 1);
		}

		//display.render((xS*8)+24, 40 ,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);

	}

}
