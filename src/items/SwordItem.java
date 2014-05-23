package items;

import myMMO.Collision;
import myMMO.Colours;
import myMMO.Display;
import myMMO.Level;
import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;

public class SwordItem extends Tool{

	private static int colour=Colours.get(-1, 211, 444, 333);
	private static int id =0+19*32;
	private int swordLevel;

	public SwordItem(String name,int swordLevel) {
		super(name, colour, id);
this.swordLevel=swordLevel;
	}

	public void doAction(PlayerEntity player,Level level) {
		Entity entity=Collision.getEntityActedWith();
		//System.out.println(entity);
		
		if(entity==null)
		{
			return;
		}
		
		entity.hurt(swordLevel);

	}
	public void renderOnHand(Display display,Level level,PlayerEntity player) {
		int xModifier=0;
		int yModifier=0;
		int rotateX=0;
		int rotateY=0;
		if(player.getMovingDirection()==0)
		{
			xModifier=+8;
			yModifier=-4;

		}
		if(player.getMovingDirection()==1)
		{
			xModifier=-8;
			yModifier=-4;
			rotateX=1;
		}
		if(player.getMovingDirection()==2)
		{
			xModifier=-6;
			yModifier=-4;
			rotateX=1;
		}
		if(player.getMovingDirection()==3)
		{
			xModifier=6;
			yModifier=-4;
		}

		display.render(player.getX()+xModifier, player.getY()+yModifier, id, colour, rotateX, rotateY, 1);

	}

}
