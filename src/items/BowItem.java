package items;

import java.util.ArrayList;
import java.util.List;

import myMMO.Colours;
import myMMO.Level;
import myMMO.entity.Arrow;
import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;

public class BowItem extends Tool{

	private static int colours= Colours.get(-1,321,555,-1);
	private static int id =1+19*32;
	private  List<Item> ArrowToRemove = new ArrayList<Item>();
	public BowItem(String name) {
		super(name, colours, id);

	}
	public void doAction(PlayerEntity player,Level level) {
		Arrow arrow = null;
		int position=0;
		for(int k=0;k<player.getItems().size();k++)
		{
			if(player.getItem(k) instanceof ArrowItem)
			{
				arrow =new Arrow(player.getX(),player.getY(), player.getMovingDirection());
				Level.addEntity(arrow);
				position=k;
				ArrowToRemove.add(player.getItem(k));
				break;
			}
		}
		if(arrow!=null)
		{
			player.getItems().set(position,new InvyItemBlank("empty"));
		}
	//	player.getItems().removeAll(ArrowToRemove);

	

	}
}
