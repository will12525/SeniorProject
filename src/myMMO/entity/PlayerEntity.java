package myMMO.entity;

import items.InvyItemBlank;
import items.Item;
import items.RockItem;
import items.SwordItem;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import myMMO.Collision;
import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;
import myMMO.Game;
import myMMO.KeyInputHandler;
import myMMO.Level;
import myMMO.menu.ChatMenu;
import myMMO.menu.InventoryMenu;
import myMMO.tile.Tile;

@SuppressWarnings("all")
public class PlayerEntity extends Entity {

	private KeyInputHandler input;
	private int colour=Colours.get(-1, 125, 111, 543);
	private int scale=1;
	private int tickCount;
	private long lastMove=0;
	private int stall=0;
	private String username;
	protected int xOffset;
	protected int yOffset;
	Entity entityY;

	Game game;

	private boolean talking = false;
	private int waitForNextAction = 200;
	private long lastAction;
	//private int  stringLimit=18;
	//private int lastStringLine=0;
	String mobyTalks="";
	String nextLine="";
	//private boolean moreText;
	//	private int r = 0;

	List<Item> items = new ArrayList<Item>();
	public Item holdItem=null;



	public PlayerEntity(Game game,Level level, int x, int y, KeyInputHandler input,String username,boolean isSwimming) {
		super(level, "Player", x, y, 1,isSwimming);
		this.game=game;
		this.input=input;
		this.username=username;
		for(int blankItems=0;blankItems<15;blankItems++)
		{
			if(blankItems==0)
			{
				items.add(blankItems,new SwordItem("sword",1));
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

	public String getMessage()
	{
		return "I'm human";
	}

	public void setLastAction(long last)
	{
		this.lastAction=last;
	}

	public void tick() {
		super.tick();
		int xa=0;
		int ya=0;
		Tile standingAt=level.getTile(x, y);
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
				game.setMenu(new InventoryMenu(this));
				lastAction=System.currentTimeMillis();
			}
			if(input.action.down)
			{
				entityY = Collision.getEntityActedWith(level.getEntities());
				if(entityY==null)
				{
					return;
				}
				else
				{
					mobyTalks=entityY.getMessage();
					game.setMenu(new ChatMenu(this,mobyTalks));

				}

			}
		}
		//System.out.println("X: "+getMobX()+", Y: "+getMobY());

		//update server of player's new position
		Game.multiplayer.send("1:" + getX() + ":" + getY());

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

	public Rectangle getBounds()
	{
		return new Rectangle(x,y,8,8);
	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

	protected void touchedBy(Entity entity)
	{
		if(!(entity instanceof PlayerEntity))
		{

			entity.touchedBy(this);
		}
	}

	public void die()
	{
		super.die();
	}

	public void doAction() {
		

	}
}
