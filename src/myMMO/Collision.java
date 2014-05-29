package myMMO;

import java.util.List;

import myMMO.entity.Arrow;
import myMMO.entity.Entity;
import myMMO.entity.FakePlayerEntity;
import myMMO.entity.PlayerEntity;

public class Collision {
	//private Level level;
	static long lastArrowHit=0;

	public static boolean entityCollision()
	{
		List<Entity>entities=Game.level.getEntities();
		if(entities.isEmpty())
		{
			return false;
		}
		for(int i=0;i<entities.size();i++)
		{
			for(int k=i+1;k<entities.size();k++)
			{
				Entity entity1 = entities.get(i);
				//entity2 is usually player
				Entity entity2 = entities.get(k);
				
				if(entity1 instanceof FakePlayerEntity || entity2 instanceof FakePlayerEntity)
				{
					return false;
				}
				/*
				 * handles collision of entities
				 */
				/*if((entity1 instanceof Arrow)||(entity2 instanceof Arrow))
				{
					return false;
				}*/



				if(entity1.getBounds().intersects(entity2.getBounds()))
				{
					if(System.currentTimeMillis()-lastArrowHit>10)
					{


						//arrow removal
						if(entity1 instanceof Arrow)
						{

							entity1.die();
							entity2.health=entity2.health-1;

							lastArrowHit=System.currentTimeMillis();


						}
						if(entity2 instanceof Arrow)
						{
							entity2.die();
							entity1.health=entity1.health-1;
							lastArrowHit=System.currentTimeMillis();

						}

					}
					entity1.stopMoving();
					entity2.stopMoving();
					//regular collision stuff
					/*if(entity1.getClass()!=PlayerEntity.class)
					{
						entity1.stopMoving();
					}
					if(entity2.getClass()!=PlayerEntity.class)
					{
						entity2.stopMoving();
					}*/
					if(entity1 instanceof Arrow||entity2 instanceof Arrow)
					{
						pushBack(entity1,entity2,5);
					}
					else
					{
						pushBack(entity1,entity2,1);
					}
					
					return true;
				}

			}
		}
		return false;
	}
	private static void pushBack(Entity entity1,Entity entity2, int pushBack)
	{
		if(entity2.getX()>entity1.getX())
		{

			entity2.setX(entity2.getX()+pushBack);
			entity1.setX(entity1.getX()-pushBack);
			return;
		}
		if(entity2.getX()<entity1.getX())
		{
			entity2.setX(entity2.getX()-pushBack);
			entity1.setX(entity1.getX()+pushBack);
			return;
		}
		if(entity2.getY()>entity1.getY())
		{
			entity2.setY(entity2.getY()+pushBack);
			entity1.setY(entity1.getY()-pushBack);
			return;
		}
		if(entity2.getY()<=entity1.getY())
		{
			entity2.setY(entity2.getY()-pushBack);
			entity1.setY(entity1.getY()+pushBack);
			return;
		}
	}

	public static Entity getEntityActedWith()
	{
		List<Entity>entities = Game.level.getEntities();
		for(int i=0;i<entities.size();i++)
		{
			for(int k=i+1;k<entities.size();k++)
			{
				Entity entity1 = entities.get(i);

				//entity2 is usually player
				Entity entity2= entities.get(k);
				/*
				 * handles action of entities
				 */
				if(entity1 instanceof PlayerEntity || entity2 instanceof PlayerEntity)
				{

					if(entity1.getActionBounds().intersects(entity2.getActionBounds()))
					{
						//System.out.println(entity1+", "+entity2);
						entity1.stopMoving();
						entity2.stopMoving();

						if(entity1 instanceof PlayerEntity)
						{

							return entity2;
						}
						if(entity2 instanceof PlayerEntity)
						{
							return entity1;
						}
						return null;

					}
				}

			}
		}
		return null;
	}
}
