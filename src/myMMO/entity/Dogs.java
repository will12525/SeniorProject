package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;


public class Dogs extends Entity {

	private static int colour=Colours.get(-1, 321, 211, 000);

	public Dogs(String name, int x, int y) {
		super("Dog", x, y, 0,"Woof!",8,25,colour);

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
		return 25;
	}


	protected void drops() {
		
		
	}
	



}
