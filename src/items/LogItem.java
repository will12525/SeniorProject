package items;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.PlankTile;

public class LogItem extends Item{
private static int colours= Colours.get(-1, 433, 322, 211);
private static int id= 5+19*32;
	public LogItem(String name) {
		super(name, colours, id);
		
	}


	public void doAction(PlayerEntity player, Level level) {
	
		
	}


	public Tile getTile(int newX, int newY) {
		
		return new PlankTile(newX,newY);
	}

}
