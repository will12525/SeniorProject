package myMMO.tile.tiles;

import java.util.Random;

import items.FlowerItem;
import items.HoeItem;
import items.Item;
import items.SeedItem;
import items.WheatItem;
import myMMO.Colours;
import myMMO.Level;
import myMMO.tile.AnimatedTile;
import myMMO.tile.Tile;

public class WheatTile extends AnimatedTile{
	private int growth=3000;
	Random random=new Random();
	private int[][] animationTileCoords;
	private int currentAnimationIndex;
	private int spriteX=3;
	//private static int colours=Colours.get(151, 100, 211, 230);
	public WheatTile(int xcoord, int ycoord) {
		super(12, new int[][] { { 3, 4 }, { 4, 4 }, { 5, 4 }}, Colours.get(230, 100, 211, 151), 0xFF84ff7d,2000,0, 0, xcoord, ycoord);

	}

	public void tick()
	{

		if(growth>0)
		{
			//	System.out.println(growth);
			growth--;
		}
		if(growth>=2000)
		{
			spriteX=3;
		}
		if(growth<2000)
		{
			spriteX=4;
			this.tileColour=Colours.get(151, 100, 211, 230);
		}
		if(growth<1000)
		{
			spriteX=5;
			this.tileColour=Colours.get(141, 131, 211, 230);
		}
		if(growth==0)
		{
			this.tileColour=Colours.get(000, 440, 211, 330);
			spriteX=6;
		}
		this.tileId=spriteX+4*32;

	}

	public Tile getDestroyedVarient(Item item) {


		return new PlowedDirt(xcoord,ycoord);

	}


	public void drop(Level level) {
		if(growth!=0){
			Item item=new SeedItem("Seed");
			item.setX(xcoord);
			item.setY(ycoord);
			item.setCoolDown(30);
			level.addItem(item);
		}
		if(growth==0)
		{
			Item item=null;
			int r=random.nextInt(3)+1;
			

			for(int k=0;k<r;k++)
			{
				item=new WheatItem("Seed");
				item.setX(xcoord);
				item.setY(ycoord);
				item.setCoolDown(30);
				level.addItem(item);
			}
			item=new SeedItem("Seed");
			item.setX(xcoord+6);
			item.setY(ycoord);
			item.setCoolDown(30);
			level.addItem(item);

		}

	}

}
