package myMMO.packet;

public abstract class Packet
{
	protected String info;
	protected static String[] args;
		
	public Packet(String info)
	{
		this.info = info;
		args = info.split(":");
		
	}
	
	public abstract void parse();
	
	public static int getID(String info)
	{
		return Integer.parseInt(args[0]);
	}
	
}
