package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;


public class Monkey extends Entity {

	private static int colour=Colours.get(-1, 321, 322, 545);

	private static String message = "Ooh ooh ah ah!";

	//Rectangle monkeyBox=new Rectangle();
	public Monkey(String name, int x, int y) {
		super("Monkey", x, y, 1,message,1,1,colour);

	}

	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
	}
	public String getMessage()
	{
		return message;
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x,y,8,8);
	}

	protected int getXTile() 
	{
		return 0;
	}

	protected int getYTile() 
	{
		return 25;
	}

	
	protected void drops() {
		
		
	}
	
}
