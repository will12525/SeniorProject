package myMMO.tile.tiles;

import java.util.Random;

import items.Item;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.AnimatedTile;
import myMMO.tile.Tile;

public class WheatTile extends AnimatedTile{
	private int growth=4000;
	Random random=new Random();
	private int[][] animationTileCoords;
	private int currentAnimationIndex;

	private static int colours=Colours.get(-1, 004, 115, -1);
	public WheatTile(int xcoord, int ycoord) {
		super(12, new int[][] { { 3, 4 }, { 4, 4 }, { 5, 4 }}, colours, 0xFF84ff7d,2000,0, 0, xcoord, ycoord);

	}

	public void tick()
	{
		
		if(growth>0)
		{
			growth--;
		}
		//currentAnimationIndex = (currentAnimationIndex + 1) % animationTileCoords.length;
		int newAnimation=random.nextInt(4);
		this.tileId=3+4*32;
		//this.tileId = (animationTileCoords[newAnimation][0] + (animationTileCoords[currentAnimationIndex][1] * 32));
		WheatTile.colours=Colours.get(000, 000, 000, 000);
	}





	public Tile getDestroyedVarient(Item item) {

		return null;
	}


	public void drop(Level level) {


	}

}
