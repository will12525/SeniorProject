package items;

import myMMO.Collision;
import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;

public class ArrowItem extends Item{
	private static int colours= Colours.get(-1,222,321,555);
	private static int id =2+19*32;
	public ArrowItem(String name) 
	{
		super(name, colours, id);

	}

	public void doAction(PlayerEntity player, Level level) 
	{
		Entity entity=Collision.getEntityActedWith();
		System.out.println(entity);
		if(entity==null)
		{
			return;
		}

		entity.hurt(1);
		

	}

	@Override
	public Tile getTile(int newX, int newY) {

		return null;
	}

}
