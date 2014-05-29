package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;

public class Turtle extends Entity {

	private static int colour=Colours.get(-1, 232, 040, 000);
	

	public Turtle(String name, int x, int y) {
		super("turtle", x, y, 1,"Yaawwnn...",0,23,colour);
		health=100;
	}
		
	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
	}

	public Rectangle getBounds()
	{
		return new Rectangle(x-2,y-3,12,8);
	}

	protected int getXTile() {
		
		return 0;
	}
	protected int getYTile() {
		
		return 23;
	}


	protected void drops() {
		
	}
}
