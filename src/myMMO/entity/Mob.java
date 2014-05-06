package myMMO.entity;

import java.awt.Rectangle;
import java.util.Random;

import myMMO.Collision;
import myMMO.Display;
import myMMO.Level;
import myMMO.tile.Tile;

public  class Mob extends Entity{


	protected String name;
	protected int speed;
	protected int numSteps=0;
	protected boolean isMoving;
	protected int movingDirection =1;
	protected int scale=1;
	protected boolean isSwimming;
	public int maxHealth=10;
	public int health=maxHealth;

Random random = new Random();
	Monkey monkey;
	Turtle turtle;
	PlayerEntity player;
	Collision collision;


	public Mob(Level level,String name,int x,int y,int speed,boolean swimming) {
		super(level);
		this.name=name;
		this.x=x;
		this.y=y;
		this.speed=speed;
		this.isSwimming=swimming;
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
			if(!Collision.entityCollision(level.entities))
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
		if(level.getTile(this.x>>3, this.y>>3).getId()==3)
		{
			isSwimming= true;
		}
		if(isSwimming&&level.getTile(this.x>>3, this.y>>3).getId()!=3)
		{
			isSwimming=false;
		}
	


	}

	public boolean blocks(Entity e)
	{
		return e.isBlockableBy(this);
	}

	public void die()
	{
		remove();
	}

	//public abstract Rectangle getBounds();

	public boolean hasCollided(int xa,int ya) {
		return false;
	}


	//public abstract void stopMoving();
	/**
	 * 
	 * @param xa
	 * @param ya
	 * @param x
	 * @param y
	 * @return
	 */
	protected boolean isSolidTile(int xa,int ya,int x,int y)
	{
		if(level==null)
		{
			return false;
		}
		Tile lastTile = level.getTile((this.x+x)>>3,(this.y+y)>>3);
		Tile newTile =level.getTile((this.x+x+xa)>>3,(this.y+y+ya)>>3);
		if(!lastTile.equals(newTile)&&newTile.isSolid())
		{
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @return the name of the entity
	 */
	public String getName()
	{
		return name;
	}


	public void tick() {
		if(health<=0)
		{
			die();
		}
	}


	public void render(Display display) {

	}
	/**
	 * stops mob from moving
	 * @param mob
	 */
	public void stopMoving(Mob mob) {

	}
	/**
	 * returns the mobs x position
	 * @return
	 */
	public int getMobX()
	{
		return 0;
	}
	public int getMobY()
	{
		return 0;
	}

	public boolean checkMobs(Mob mob1, Mob mob2) {
		if(mob1.getBounds().intersects(mob2.getBounds()))
		{
			return true;
		}

		return false;
	}
	public boolean checkMobsAction(Mob mob1, Mob mob2) {
		if(mob1.getActionBounds().intersects(mob2.getActionBounds()))
		{
			return true;
		}

		return false;
	}
	/**
	 * the bounds of the entity
	 */
	public Rectangle getBounds() 
	{
		return null;

	}
	public Rectangle getActionBounds()
	{
		return null;
	}
	public String getMessage()
	{
		return "";
	}





}
