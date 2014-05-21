package items;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Level;
import myMMO.entity.PlayerEntity;
import myMMO.tile.Tile;
import myMMO.tile.tiles.FlowerTile;

public class FlowerItem extends Item
{
	static int id =0+18*32;
	static int colour =Colours.get(-1, 500, -1, 131);
	
	public FlowerItem(String name) 
	{
		super(name, colour,id);
		
	}


	public void doAction(PlayerEntity player,Level level) {
		

	}
	public void renderOnHand(Display display,Level level,PlayerEntity player) {
		int xModifier=0;
		int yModifier=-3;
		if(player.getMovingDirection()==0)
		{
			//yModifier=-3;
			xModifier=7;
		}
		if(player.getMovingDirection()==1)
		{
			//yModifier=-3;
			xModifier=-7;
		}
		if(player.getMovingDirection()==2)
		{
			xModifier=-5;
		}
		if(player.getMovingDirection()==3)
		{
			xModifier=5;
		}
		
		display.render(player.getX()+xModifier, player.getY()+yModifier, id, colour, 0, 0, 1);

	}
	public Tile getTile(int newX,int newY)
	{
		return new FlowerTile(newX,newY);
	}
}
