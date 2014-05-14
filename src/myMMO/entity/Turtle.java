package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Level;
import myMMO.tile.Tile;

public class Turtle extends Entity {

	private int tickCount;
	private int colour=Colours.get(-1, 232, 040, 000);
	private int timeToMove=0;
	private long lastMove;
	private boolean moveRight=false;
	private boolean moveLeft=false;
	private boolean moveUp=false;
	private boolean moveDown=false;
	protected int xOffset;
	protected int yOffset;
	private String message ="Yaawwnn...";


	public Turtle(Level level, String name, int x, int y, int speed,boolean isSwimming) {
		super(level, "turtle", x, y, 1, isSwimming);
	}



	public void tick() {
	
		super.tick();
		//int tile =Tile.WATER.getId();
		int xa=0;
		int ya=0;
		Tile standingAt = level.getTile(x>>3, y>>3);
		if(standingAt==Tile.LOG||standingAt==Tile.LEAVES)
		{
			//	level.setTile(x>>3, (y>>3), Tile.GRASS);
		}
		if(standingAt!=Tile.WATER||standingAt!=Tile.SAND)
		{
			//level.searchForTile(x>>3,y>>3,Tile.WATER);
		}

		if((x>>3)<=0)
		{
			xa++;
		}

		if((x>>3)>=200)
		{
			xa--;
		}
		if((y>>3)>200)
		{
			ya--;
		}
		if((y>>3)<0)
		{
			ya++;
		}
		if ((System.currentTimeMillis() - lastMove) >= (timeToMove)) {
			lastMove = System.currentTimeMillis();
			int nextMove = (int) (Math.random()*5);
			if(nextMove==0)
			{
				moveRight=false;
				moveLeft=false;
				moveUp=true;
				moveDown=false;
				ya++;
			}
			if(nextMove==1)
			{
				moveRight=false;
				moveLeft=false;
				moveUp=false;
				moveDown=true;
				ya--;
			}
			if(nextMove==2)
			{
				moveRight=false;
				moveLeft=true;
				moveUp=false;
				moveDown=false;
				xa--;
			}
			if(nextMove==3)
			{
				moveRight =true;
				moveLeft=false;
				moveUp=false;
				moveDown=false;
				xa++;
			}
			if(nextMove>=4)
			{
				moveRight=false;
				moveLeft=false;
				moveUp=false;
				moveDown=false;
				ya++;
			}
			timeToMove=3000+(int)(Math.random()*((8000-3000)+1));
		}
		
		
		switch((int)lastMove)
		{
		case 1: 
			System.out.println(1);
			break;
		case 2:
			System.out.println(2);
			break;
		}

		if(moveDown)
		{
			ya--;
		}
		if(moveUp)
		{
			ya++;
		}
		if(moveLeft)
		{
			xa--;
		}
		if(moveRight)
		{
			xa++;
		}
		if(xa!=0||ya!=0)
		{
			move(xa,ya);
			isMoving=true;
		}
		else
		{
			isMoving =false;
		}
		tickCount++;

	}


	public void render(Display display) {
		int xTile=0;
		int yTile=23;

		int walkingSpeed =3;
		int flipTopX=(numSteps>>walkingSpeed)&1;
		int flipTopY=(numSteps>>walkingSpeed)&1;
		int flipBottomL=(numSteps>>walkingSpeed)&1;
		int flipBottomR=(numSteps>>walkingSpeed)&1;

		int modifier =8*scale;
		xOffset =x-modifier/2;
		yOffset =y-modifier/2-4;

		if(movingDirection==1)
		{
			xTile+=2;
		}
		else if(movingDirection>1)
		{
			xTile+=4+((numSteps>>walkingSpeed)&1)*2;
			flipTopX=(movingDirection-1)%2;
		}

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
			display.render(xOffset, yOffset+3, 0+27*32, waterColour, flipTopX-1,flipTopY-1, 1);
			display.render(xOffset+8, yOffset+4, 0+27*32, waterColour, 1,1, 1);
		}



		display.render(xOffset+(modifier*flipTopX), yOffset, (xTile+yTile*32), colour,flipTopX,flipTopY-1,scale);
		display.render(xOffset+modifier-(modifier*flipTopX), yOffset, (xTile+1)+yTile*32, colour,flipTopX,flipTopY-1,scale);


		if(!isSwimming)
		{
			display.render(xOffset+(modifier*flipBottomL), yOffset+modifier, xTile+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
			display.render(xOffset+modifier-(modifier*flipBottomL), yOffset+modifier, (xTile+1)+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
		}
	}
	public void stopMoving(Entity entity)
	{
		moveRight=false;
		moveLeft=false;
		moveUp=false;
		moveDown=false;
	}
	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
	}
	public String getMessage()
	{
		return message;
	}

	public int getMobX()
	{
		return x;
	}
	public int getMobY()
	{
		return y;
	}
	public Rectangle getBounds()
	{
		return new Rectangle(x-2,y-3,12,8);
	}
	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				stopMoving(this);
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				stopMoving(this);
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				stopMoving(this);
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				stopMoving(this);
				return true;
			}
		}
		return false;
	}



}
