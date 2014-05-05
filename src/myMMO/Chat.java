package myMMO;

public class Chat extends Mob{
	public boolean chatting=false;

	public Chat(Level level, String name, int x, int y, int speed,boolean isSwimming) {
		super(level, "chat", x, y, 0, false);
		// TODO Auto-generated constructor stub
	}



	public void render(Display display)
	{

	}

	public void Chatting(Mob mob)
	{
		new Chat(level,"turtle",10,10,0,false);
		System.out.println(mob.getMessage());


	}

}
