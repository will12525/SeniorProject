package myMMO.entity;

import items.HoeItem;
import items.InvyItemBlank;
import items.Item;
import items.SeedItem;
import items.SwordItem;
import items.Tool;
import items.WheatItem;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import myMMO.Collision;
import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;
import myMMO.Game;
import myMMO.KeyInputHandler;
import myMMO.menu.ChatMenu;
import myMMO.menu.InventoryMenu;
import myMMO.packet.Packet01Move;
import myMMO.tile.Tile;

@SuppressWarnings("all")
public class PlayerEntity extends Entity {

	private KeyInputHandler input;
	private static int colour=Colours.get(-1, 125, 111, 543);
	private int scale=1;
	private int tickCount;
	private long lastMove=0;
	private int stall=0;
	private String username;
	protected int xOffset;
	protected int yOffset;

	private boolean talking = false;
	private int waitForNextAction = 200;
	private long lastAction;

	List<Item> items = new ArrayList<Item>();
	public Item holdItem=null;
	public int holdItemPosition=1;

	public long canDestroy=0;
	public int cantDestroyYet=1000;
	private static int xTile=0;
	private static int yTile=28;

	public PlayerEntity(int xOnMap, int yOnMap, KeyInputHandler input,String username,boolean isSwimming) {
		super("Player", xOnMap, yOnMap, 1,"",xTile,yTile,colour);
		//Level level, String name, int x, int y, int speed, String message,int xTile,int yTile, int colour
		this.input=input;
		this.username=username;
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
			else if(blankItems==3)
			{
				items.add(blankItems,new WheatItem("wheat"));
			}
			else
			{
				items.add(blankItems, new InvyItemBlank("empty"));
			}
		}

	}
	public void setHoldItem(Item item,int position)
	{
		holdItemPosition=position;
		holdItem=item;
	}
	public int getHoldItemPosition()
	{
		return holdItemPosition;
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
	
	//a delay time between actions
	public void setLastAction(long last)
	{
		this.lastAction=last;
	}

	public void tick() {
		int xa=0;
		int ya=0;
		Tile standingAt=Game.level.getTile(x, y);
		//System.out.println(standingAt);
		//System.out.println(standingAt+", X: "+x+", Y: "+y);
		/*Tile standingAt = level.getTile(((x+4)>>3), ((y+3)>>3)+1);
		if(standingAt==Tile.GRASS||standingAt==Tile.SAND)
		{
			//level.setTile(x>>3, (y>>3)+1, Tile.FENCE);
		}*/
		/*if(level.getTile(((x+4)>>3), ((y+3)>>3))==Tile.WATER)
		{
			stall=20;
		}
		else
		{
			stall=0;
		}*/

		if(input.up.down)
		{
			ya--;
		}
		
		if(input.down.down)
		{
			ya++;
		}
		
		if(input.left.down)
		{
			xa--;
		}
		
		if(input.right.down)
		{
			xa++;
		}
		
		if ((System.currentTimeMillis() - lastMove) >= stall) {
			lastMove = System.currentTimeMillis();
			if ((xa != 0 || ya != 0)&&!talking) {
				move(xa, ya);
				isMoving = true;

			} 
			else {
				isMoving = false;
			}
		}
		
		tickCount++;

		if((System.currentTimeMillis() - lastAction) >= (waitForNextAction)) 
		{

			if(input.inventory.down)
			{
				Game.setMenu(new InventoryMenu(this));
				lastAction=System.currentTimeMillis();
			}
			if(input.action.down)
			{
				Entity actedEntity = Collision.getEntityActedWith(Game.level.getEntities());
				if(actedEntity==null)
				{
					return;
				}
				else
				{
					Game.setMenu(new ChatMenu(this,actedEntity.getMessage()));
				}

			}
		}
		//System.out.println("X: "+getMobX()+", Y: "+getMobY());

		//update server of player's new position
		if(Game.multiplayer != null)
			new Packet01Move("1:" + getX() + ":" + getY()).send(Game.multiplayer.getOutput());
	}

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

		Font.renderFont((x>>3)+", "+(y>>3), display, xOffset, yOffset-20, Colours.get(-1, -1, -1, 555), 1);
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
	
	public void doAction() {
		if(!(holdItem instanceof Tool))
		{
			return;
		}
	
		holdItem.doAction(this,Game.level);
	
	}
}
