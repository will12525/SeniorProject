package myMMO.tile.tiles;

import java.util.Random;

import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.BaseTile;
import myMMO.tile.Tile;

public class DirtTile extends BaseTile{

	public DirtTile(int xcoord, int ycoord) {
		super(6,4,0,Colours.get(-1, -1, 211, 322),0xFF412000, 0, 0, xcoord, ycoord);
	}
	public void tick()
	{
		Random random = new Random();
		
		int turnToGrass=random.nextInt(2000)+1;
		if(turnToGrass<=10)
		{
			for(int i = 0; i < Level.tiles.size(); i++)
			{
				Tile tile = Level.tiles.get(i);
				if(tile.getX() == xcoord && tile.getY() == ycoord)
				{
					Level.tiles.set(i, new GrassTile(xcoord, ycoord));
				}
			}
		/*	System.out.println(xcoord+", "+ycoord);
			Tile tile = new GrassTile(xcoord,ycoord);
			level.setTile(xcoord, ycoord, tile.id);
			
		//	level.addTile(tile);
			//level.setTile((xcoord<<3), (ycoord<<3), Tile.GRASS.getId());
			System.out.println(turnToGrass);*/
		}
		
	}

}
