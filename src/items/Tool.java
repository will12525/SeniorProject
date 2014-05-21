package items;

import myMMO.Display;
import myMMO.Level;
import myMMO.entity.PlayerEntity;

public class Tool extends Item{

	public Tool(String name, int colour, int id) {
		super(name, colour, id);
	}


	public void doAction(PlayerEntity player,Level level) {
	
		
	}
	public void renderOnHand(Display display,Level level,PlayerEntity player) {
		int xModifier=0;
		int yModifier=0;
		int rotateX=0;
		int rotateY=0;
		if(player.getMovingDirection()==0)
		{
			xModifier=+8;
			yModifier=-3;
			
		}
		if(player.getMovingDirection()==1)
		{
			xModifier=-8;
			yModifier=-3;
			rotateX=1;
		}
		if(player.getMovingDirection()==2)
		{
			xModifier=-6;
			yModifier=-3;
			rotateX=1;
		}
		if(player.getMovingDirection()==3)
		{
			xModifier=6;
			yModifier=-3;
		}
		
		display.render(player.getX()+xModifier, player.getY()+yModifier, id, colour, rotateX, rotateY, 1);

	}



	
}
