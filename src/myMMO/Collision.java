package myMMO;

import java.util.List;

import myMMO.entity.Arrow;
import myMMO.entity.Entity;
import myMMO.entity.Mob;
import myMMO.entity.PlayerEntity;

public class Collision {
	//private Level level;
	static long lastArrowHit=0;

	public static boolean entityCollision(List<Entity>entities)
	{
		if(entities.isEmpty())
		{
			return false;
		}
		for(int i=0;i<entities.size();i++)
		{
			for(int k=i+1;k<entities.size();k++)
			{
				Mob mob1 =(Mob) entities.get(i);
				//mob2 is usually player
				Mob mob2=(Mob) entities.get(k);
				/*
				 * handles collision of entities
				 */
				/*if((mob1 instanceof Arrow)||(mob2 instanceof Arrow))
				{
					return false;
				}*/



				if(mob1.getBounds().intersects(mob2.getBounds()))
				{
					if(System.currentTimeMillis()-lastArrowHit>10)
					{


						//arrow removal
						if(mob1 instanceof Arrow)
						{
							System.out.println("hi");
							mob1.die();
							//((Arrow) mob1).endArrowsLife(mob2);
							//mob1.die();
							mob2.health=mob2.health-1;

						}
						if(mob2 instanceof Arrow)
						{
							mob2.die();
							System.out.println("poo");

							//((Arrow) mob2).endArrowsLife(mob1);
							//mob2.die();
							mob1.health=mob1.health-1;;

						}
						lastArrowHit=System.currentTimeMillis();
					}


					//regular collision stuff
					if(mob1.getClass()!=PlayerEntity.class)
					{
						mob1.stopMoving(mob1);
					}
					if(mob2.getClass()!=PlayerEntity.class)
					{
						mob2.stopMoving(mob2);
					}
					if(mob2.getMobX()>mob1.getMobX())
					{

						mob2.x=mob2.x+1;
						mob1.x=mob1.x-1;
					}
					if(mob2.getMobX()<mob1.getMobX())
					{
						mob2.x=mob2.x-1;
						mob1.x=mob1.x+1;
					}
					if(mob2.getMobY()>mob1.getMobY())
					{
						mob2.y=mob2.y+1;
						mob1.y=mob1.y-1;
					}
					if(mob2.getMobY()<=mob1.getMobY())
					{
						mob2.y=mob2.y-1;
						mob1.y=mob1.y+1;
					}
					return true;
				}

			}
		}
		return false;
	}

	public static Mob getEntityActedWith(List<Entity>entities)
	{

		for(int i=0;i<entities.size();i++)
		{
			for(int k=i+1;k<entities.size();k++)
			{
				Mob mob1 =(Mob) entities.get(i);

				//mob2 is usually player
				Mob mob2=(Mob) entities.get(k);
				/*
				 * handles action of entities
				 */
				if(mob1.getClass()==PlayerEntity.class||mob2.getClass()==PlayerEntity.class)
				{

					if(mob1.getActionBounds().intersects(mob2.getActionBounds()))
					{
						mob1.stopMoving(mob1);
						mob2.stopMoving(mob2);
						if(mob1 instanceof PlayerEntity)
						{

							return mob2;
						}
						if(mob2 instanceof PlayerEntity)
						{
							return mob1;
						}
						return null;

					}
				}

			}
		}
		return null;
	}
}
