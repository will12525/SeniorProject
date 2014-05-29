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

public class FakePlayerEntity extends Entity
{

	private static int colour = Colours.get(-1, 050, 111, 543);
	@SuppressWarnings("unused")
	private String userName;
	protected int xOffset;
	protected int yOffset;

	List<Item> items = new ArrayList<Item>();
	public Item holdItem=null;

	public long canDestroy=0;
	public int cantDestroyYet=1000;
	private static int xTile=0;
	private static int yTile=28;

	public FakePlayerEntity(String userName, int x, int y) {
		super("Player", x, y, 1,"",xTile,yTile,colour);
		//Level level, String name, int x, int y, int speed, String message,int xTile,int yTile, int colour
		this.userName=userName;
		
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

	protected int getXTile() {
		return 0;
	}

	protected int getYTile() {
	
		return 28;
	}

	protected void drops() {
		
	}

	
}
