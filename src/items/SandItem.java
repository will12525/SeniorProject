package items;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.SandTile;

public class SandItem extends Item{
	private static int colours =Colours.get(-1, 550, 554, -1);
	private static int id =6+19*32;
	
	public SandItem(String name) {
		super(name, colours, id);
		
	}
	
	public void doAction(PlayerEntity player, Level level) {
		
		
	}

	
	public Tile getTile(int newX, int newY) {
		
		return new SandTile(newX,newY);
	}

}
