package items;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.PlayerEntity;

public class WheatItem extends Item{

	private static int colour=Colours.get(-1, 330, 400, 220);
	private static int id =3+20*32;
	
	public WheatItem(String name) {
		super(name, colour, id);
		
	}
	

	public void doAction(PlayerEntity player, Level level) {
		
		
	}

}
