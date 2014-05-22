package myMMO.entity;

import items.HoeItem;
import items.InvyItemBlank;
import items.Item;
import items.SeedItem;
import items.SwordItem;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;

public class FakePlayerEntity extends Entity
{

	private static int colour = Colours.get(-1, 050, 111, 543);
	private int scale=1;
	private int tickCount;
	private String username;
	protected int xOffset;
	protected int yOffset;

	List<Item> items = new ArrayList<Item>();
	public Item holdItem=null;

	public long canDestroy=0;
	public int cantDestroyYet=1000;
	private static int xTile=0;
	private static int yTile=28;

	public FakePlayerEntity(String name, int x, int y) {
		super("Player", x, y, 1,"",xTile,yTile,colour);
		//Level level, String name, int x, int y, int speed, String message,int xTile,int yTile, int colour
		this.username=name;
		
		for(int blankItems=0;blankItems<15;blankItems++)
		{
			if(blankItems==0)
			{
				items.add(blankItems,new SwordItem("sword",1));
			}
			else if(blankItems==1)
			{
				items.add(blankItems,new HoeItem("hoe",1));
			}
			else if(blankItems==2)
			{
				items.add(blankItems,new SeedItem("seed"));
			}
			else
			{
				items.add(blankItems, new InvyItemBlank("empty"));
			}
		}

	}
	public void setHoldItem(Item item)
	{
		holdItem=item;
	}

	public Item getHoldItem()
	{
		return holdItem;
	}

	public void changeItem(Item item,int position)
	{
		items.remove(position);
		items.add(position,item);
	}

	public List<Item> getItems()
	{
		return this.items;
	}

	public Item getItem(int position)
	{
		return items.get(position);
	}



	public void tick() {}


	public void render(Display display) {
		int xTile=0;
		int yTile=28;
		int walkingSpeed=3;

		int flipTopX=(numSteps>>walkingSpeed)&1;
		int flipTopY=(numSteps>>walkingSpeed)&1;
		int flipBottomL=(numSteps>>walkingSpeed)&1;
		int flipBottomR=(numSteps>>walkingSpeed)&1;

		if(movingDirection==1)
		{
			xTile+=2;
		}
		else if(movingDirection>1)
		{
			xTile+=4+((numSteps>>walkingSpeed)&1)*2;
			flipTopX=(movingDirection-1)%2;
		}

		int modifier =8*scale;
		xOffset = x-modifier/2;
		yOffset = y-modifier/2-4;
		if(isSwimming)
		{
			int waterColour = 0;
			yOffset+=4;
			if(tickCount%60<15)
			{
				yOffset-=1;
				waterColour=Colours.get(-1, -1, 225, -1);

			}
			else if(15<=tickCount%60&&tickCount%60<30)
			{
				waterColour=Colours.get(-1, 225, 115, -1);
			}
			else if(30<=tickCount%60&&tickCount%60<45)
			{
				yOffset-=1;
				waterColour=Colours.get(-1, 115, -1, 225);
			}
			else
			{
				waterColour=Colours.get(-1, 225, 115, -1);
			}

			//waves around head
			display.render(xOffset, yOffset+3, 0+27*32, waterColour, flipTopX-1,flipTopY-1, 1);
			display.render(xOffset+8, yOffset+4, 0+27*32, waterColour, 1,1, 1);
		}


		//face
		display.render(xOffset+(modifier*flipTopX), yOffset, (xTile+yTile*32), colour,flipTopX,flipTopY-1,scale);
		display.render(xOffset+modifier-(modifier*flipTopX), yOffset, (xTile+1)+yTile*32, colour,flipTopX,flipTopY-1,scale);


		if(!isSwimming)
		{
			//body and feet
			display.render(xOffset+(modifier*flipBottomL), yOffset+modifier, xTile+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
			display.render(xOffset+modifier-(modifier*flipBottomL), yOffset+modifier, (xTile+1)+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
		}

		if(username!=null)
		{
			Font.renderFont(username, display, xOffset-(username.length()-1), yOffset-10, Colours.get(-1, -1, -1, 555), 1);
		}

		//coordinates
		//Font.renderFont((x>>3)+", "+(y>>3), display, xOffset, yOffset-20, Colours.get(-1, -1, -1, 555), 1);
	}

	//interaction bounds such as talking or using a sword
	public Rectangle getActionBounds()
	{

		int xChange=0;
		int yChange=0;
		//max is 3
		if(movingDirection==0)
		{//up
			yChange=-3;
		}
		if(movingDirection == 1)
		{//down
			yChange=3;
		}
		if(movingDirection == 2)
		{//left
			xChange=-3;
		}
		if(movingDirection == 3)
		{//right
			xChange=3;
		}
		return new Rectangle(x+xChange,y+yChange,8,8);
	}

	//intersection bounds for collision
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,8,8);
	}

	
}
