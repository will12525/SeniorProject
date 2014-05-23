package myMMO.entity;

import items.ArrowItem;
import items.Item;

import java.awt.Rectangle;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Game;

public class Arrow extends Entity {
	private static int colours= Colours.get(-1,222,321,555);
	private int facingDirection=0;
	private int x,y;
	private int airTime=60;

	private boolean adjusted=false;
	private boolean dontRenderYet=true;

	public Arrow(int x,int y, int facingDirection) {
		super("Arrow", x, y, 1,"",2,19,colours);
		this.x=x;
		this.y=y;
		this.facingDirection=facingDirection;
	}

	public void tick()
	{
		if(!adjusted)
		{

			if(facingDirection==0)
			{
				y=y-8;
				x=x+5;
				adjusted=true;
			}
			if(facingDirection==1)
			{
				y=y+5;
				x=x-4;
				adjusted=true;
			}
			if(facingDirection==2)
			{
				y=y-3;
				x=x-7;
				adjusted=true;
			}
			if(facingDirection==3)
			{
				y=y-3;
				x=x+5;
				adjusted=true;
			}
			dontRenderYet=false;
		}

		if(facingDirection==0)
		{
			y--;
		}
		if(facingDirection==1)
		{

			y++;
		}
		if(facingDirection==2)
		{

			x--;
		}
		if(facingDirection==3)
		{

			x++;
		}
		airTime--;
		if(airTime<0)
		{
			die();

		}
	}
	protected void drops()
	{
		int r=random.nextInt(2);
		System.out.println(r);
		if(r==1)
		{
			Item arrowItem=new ArrowItem("arrow");
			arrowItem.setX(x>>3);
			arrowItem.setY(y>>3);
			arrowItem.setCoolDown(30);
			Game.level.addItem(arrowItem);
		}
	}
	public Rectangle getActionBounds()
	{
		return new Rectangle(x,y,0,0);
	}
	public Rectangle getBounds()
	{
		if(airTime>55)
		{
			return new Rectangle(x,y,0,0);
		}
		else
		{
			return new Rectangle(x,y,8,8);
		}
	}
	public void render(Display display)
	{
		if(dontRenderYet)
		{
			return;
		}
		int xTile=2;
		int yTile=19;
		if(facingDirection==0)
		{
			display.render(x, y, xTile+yTile*32, colours, 0, 0, 1);
		}
		if(facingDirection==1)
		{
			display.render(x, y, xTile+yTile*32, colours, 0, 1, 1);
		}
		if(facingDirection==2)
		{
			display.render(x, y, (xTile+1)+yTile*32, colours, 0, 0, 1);
		}
		if(facingDirection==3)
		{
			display.render(x, y, (xTile+1)+yTile*32, colours, 1, 0, 1);
		}
	}



	protected int getXTile() {

		return 2;
	}


	protected int getYTile() {

		return 19;
	}

}
