package myMMO.packet;

public abstract class Packet
{
	protected String info;
	protected String[] args;
	
	protected int id;
	
	public Packet(String info)
	{
		this.info = info;
		this.args = info.split(":");
		
		this.id = Integer.parseInt(args[0]);
	}
		
	public int getID()
	{
		return this.id;
	}
	
	public abstract void parse();
	
}
