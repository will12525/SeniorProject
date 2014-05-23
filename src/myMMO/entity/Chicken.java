package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;


public class Chicken  extends Entity {
	private static int colour=Colours.get(-1, 000, 555, 500);
	
	public Chicken(String name, int x, int y) {
		super("Chicken", x, y, 0,"Cluck!",8,23,colour);

	}

	
	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,8,8);
	}

	protected int getXTile() 
	{
		return 8;
	}

	protected int getYTile() 
	{
		return 23;
	}


	
	protected void drops() {
		
		
	}
}