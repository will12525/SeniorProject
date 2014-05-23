package myMMO.entity;

import java.awt.Rectangle;
import java.util.Random;

import myMMO.Collision;
import myMMO.Colours;
import myMMO.Display;
import myMMO.Game;
import myMMO.Level;
import myMMO.tile.Tile;


/**
 * 
 * Main constructor for all entities in the game
 *
 */
@SuppressWarnings("all")
public abstract class Entity {

	private int xTile;
	private int yTile;;
	protected int x;
	protected int y;
	protected int xOffset,yOffset;
	public int xr=6;
	public int yr=6;
	public boolean removed;

	protected String name;
	private String message=null;
	protected int speed;
	protected int numSteps=0;
	protected boolean isMoving;
	protected int movingDirection =1;
	protected int scale=1;
	protected boolean isSwimming;
	public int maxHealth=10;
	public int health=maxHealth;
	public int colour;

	Random random = new Random();
	Monkey monkey;
	Turtle turtle;
	Collision collision;

	private boolean moveRight=false;
	private boolean moveLeft=false;
	private boolean moveUp=false;
	private boolean moveDown=false;
	private int timeToMove=0;
	private long lastMove;
	private int tickCount=0;

	public Entity(String name, int x, int y, int speed, String message,int xTile,int yTile, int colour)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.xTile=xTile;
		this.yTile=yTile;
		this.colour=colour;
		this.message=message;
	}

	public void tick() {

		if(!(this instanceof PlayerEntity)||!(this instanceof Skeleton))
		{
			//int tile =Tile.WATER.getId();
			int xa=0;
			int ya=0;
			byte standingAt = Game.level.getTile(x>>3, y>>3).getId();
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
		if(health<=0)
		{
			die();
		}
	}

	public void render(Display display)
	{
		this.xTile=getXTile();
		this.yTile=getYTile();
		
		
		
		int walkingSpeed =3;
		int flipTopX=(numSteps>>walkingSpeed)&1;
		int flipTopY=(numSteps>>walkingSpeed)&1;
		int flipBottomL=(numSteps>>walkingSpeed)&1;
		int flipBottomR=(numSteps>>walkingSpeed)&1;

		int modifier =8*scale;
		xOffset =x-modifier/2;
		yOffset =y-modifier/2-4;
		//Font.render((x>>3)+", "+(y>>3), display, x, y, Colours.get(-1, -1, -1, 555), 1);
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
		
		
		
		
		
		//I tried to move all rendering here but it caused a lot of bugs, I tried to fix them but they wouldnt stop O_O   So I switched it back
	}

protected abstract int getXTile();
protected abstract int getYTile();

	public boolean intersects(int x1,int y1,int x2,int y2)
	{
		return !(x+xr<x1||y+yr<y1||x-xr>x2||y-yr>y2);
	}

	public void die()
	{
		Game.level.removeEntity(this);
		//removed=true;
	}
	public boolean timeToDie()
	{
		return removed;
	}
	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}
	//name of mob
	public String getName()
	{
		return name;
	}
	//direction mob facing
	public int getMovingDirection()
	{
		return movingDirection;
	}
	//bounds for collison
	public Rectangle getBounds() 
	{
		return null;
	}
	//bounds for talking/actions
	public Rectangle getActionBounds()
	{
		return null;
	}
	//what the mob says
	public String getMessage()
	{
		return message;
	}
	//stop mobs from moving
	public void stopMoving()
	{
		moveRight=false;
		moveLeft=false;
		moveUp=false;
		moveDown=false;
	}
	public void hurt(int damage)
	{
		health=health-damage;
	}

	public void move(int xa,int ya)
	{
		if(xa!= 0&&ya!=0)
		{
			move(xa,0);
			move(0,ya);
			numSteps--;
			return;
		}
		numSteps++;
		if(!hasCollided(xa,ya))
		{
			if(!Collision.entityCollision())
			{
				if(ya<0)
				{//up

					movingDirection =0;
				}
				if(ya>0)
				{//down
					movingDirection =1;
				}
				if(xa<0)
				{//left
					movingDirection =2;
				}
				if(xa>0)
				{//right
					movingDirection =3;
				}

				x+=xa*speed;
				y+=ya*speed;
			}
		}
		if(Game.level.getTile(this.x>>3, this.y>>3).getId()==3)
		{
			isSwimming= true;
		}
		if(isSwimming&&Game.level.getTile(this.x>>3, this.y>>3).getId()!=3)
		{
			isSwimming=false;
		}
	}

	public boolean hasCollided(int xa,int ya) {
		int xMin = 0;
		int xMax = 7;
		int yMin = 3;
		int yMax = 7;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				stopMoving();
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				stopMoving();
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				stopMoving();
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				stopMoving();
				return true;
			}
		}
		return false;
	}
	protected boolean isSolidTile(int nextX,int nextY,int x,int y)
	{
		if(Game.level==null)
		{
			return false;
		}
		Tile lastTile = Game.level.getTile((this.x+x)>>3,(this.y+y)>>3);
		Tile newTile =Game.level.getTile((this.x+x+nextX)>>3,(this.y+y+nextY)>>3);
		//Tile currentTile = level.getTile(x, y)
		//System.out.println(this.x + "  " + x);
		if(!lastTile.equals(newTile)&&newTile.isSolid())
		{
			return true;
		}
		return false;
	}	
}