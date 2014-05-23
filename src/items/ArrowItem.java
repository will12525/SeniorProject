package items;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.PlayerEntity;

public class ArrowItem extends Item{
	private static int colours= Colours.get(-1,222,321,555);
	private static int id =2+19*32;
	public ArrowItem(String name) 
	{
		super(name, colours, id);
		
	}

	public void doAction(PlayerEntity player, Level level) 
	{
	
		
	}

}
