package myMMO;

import java.util.List;

import myMMO.entity.Arrow;
import myMMO.entity.Entity;
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

					if(entity1 instanceof Arrow || entity2 instanceof Arrow)
					{
						return false;
					}
					//regular collision stuff
					if(entity1.getClass()!=PlayerEntity.class)
					{
						entity1.stopMoving();
					}
					if(entity2.getClass()!=PlayerEntity.class)
					{
						entity2.stopMoving();
					}
					if(entity2.getX()>entity1.getX())
					{

						entity2.setX(entity2.getX()+1);
						entity1.setX(entity1.getX()-1);
					}
					if(entity2.getX()<entity1.getX())
					{
						entity2.setX(entity2.getX()-1);
						entity1.setX(entity1.getX()+1);
					}
					if(entity2.getY()>entity1.getY())
					{
						entity2.setY(entity2.getY()+1);
						entity1.setY(entity1.getY()-1);
					}
					if(entity2.getY()<=entity1.getY())
					{
						entity2.setY(entity2.getY()-1);
						entity1.setY(entity1.getY()+1);
					}
					return true;
				}

			}
		}
		return false;
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
