package myMMO.entity;

import items.ArrowItem;
import items.BowItem;
import items.Item;

import java.awt.Rectangle;

import myMMO.Colours;
import myMMO.Game;
import myMMO.Level;

public class Skeleton extends Entity {
	@SuppressWarnings("unused")
	private int tickCount;
	private static int colour=Colours.get(-1, 555, 500, 000);
	private long lastMove;
	private static String message = "Ooh ooh ah ah!";
	private int randomWalkTime=0;
	private boolean stopMoving = false;
	private int tryToFire=2000;
	private long lastFire;
	Arrow arrow;
	
	//Rectangle monkeyBox=new Rectangle();
	public Skeleton(String name, int x, int y) {
		super("Skelly", x, y, 1,message,0,0,colour);

	}
	//skeletons movement is different
	public void tick() {
		int xa=0;
		int ya=0;

		if (Game.level.getPlayer() != null && randomWalkTime == 0) {
			int xd = Game.level.getPlayer().x - x;
			int yd = Game.level.getPlayer().y - y;
			if ((xd * xd + yd * yd <= 80 * 80)&&(xd * xd + yd * yd >= 30 * 30)) {
				xa = 0;
				ya = 0;
				if (xd < 0)
				{
					xa = -1;
				}
				if (xd > 0)
				{
					xa = +1;
				}
				if (yd < 0)
				{
					ya = -1;
				}
				if (yd > 0) 
				{
					ya = +1;
				}
			}
			if((xd * xd + yd * yd >0)&&(xd * xd + yd * yd <= 80 * 80))
			{
				if((System.currentTimeMillis()-lastFire)>=tryToFire)
				{
					lastFire=System.currentTimeMillis();
					//System.out.println("new arrow");
					arrow =new Arrow(xOffset, yOffset, movingDirection);
					Level.addEntity(arrow);
				}
			}
		}


		if (random.nextInt(200) == 0) {
			randomWalkTime = 60;
			xa = (random.nextInt(3) - 1) * random.nextInt(2);
			ya = (random.nextInt(3) - 1) * random.nextInt(2);
		}
		if (randomWalkTime > 0) randomWalkTime--;
		if ((System.currentTimeMillis() - lastMove) >= (20)) {
			lastMove = System.currentTimeMillis();
			if(!stopMoving)
			{
				move(xa,ya);
			}
			stopMoving=false;
		}

		tickCount++;
		if(health<=0)
		{
			die();
		}
	}

	public void stopMoving(Entity entity)
	{
		stopMoving=true;
	}


	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
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

	
	
	
	protected int getXTile() 
	{
		return 0;
	}

	protected int getYTile() 
	{
		return 21;
	}

	protected void drops() {
		int r= random.nextInt(5);
		/*for(int k =0;k<r;k++)
		{
			Item arrowItem=new ArrowItem("arrow");
			arrowItem.setX(x>>3);
			arrowItem.setY(y>>3);
			arrowItem.setCoolDown(30);
			Game.level.addItem(arrowItem);
		}*/
		if(r==2)
		{
			Item bow = new BowItem("Bow");
			bow.setX(x>>3);
			bow.setY(y>>3);
			bow.setCoolDown(30);
			Game.level.addItem(bow);
		}
		
	}
}