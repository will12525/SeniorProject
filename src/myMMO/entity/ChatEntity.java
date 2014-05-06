package myMMO.entity;

import myMMO.Display;
import myMMO.Level;

public class ChatEntity extends Entity {
	public boolean chatting=false;

	public ChatEntity(Level level, String name, int x, int y, int speed,boolean isSwimming) {
		super(level, "chat", x, y, 0, false);
		// TODO Auto-generated constructor stub
	}



	public void render(Display display)
	{

	}

	public void Chatting(Entity entity)
	{
		new ChatEntity(level,"turtle",10,10,0,false);
		System.out.println(entity.getMessage());


	}

}
