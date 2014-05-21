package old;


public class Packet01Move extends Packet
{

	//public Packet01Move(byte[] data, Client client)
	//{
	//	super(data, 1, client);
	//}
	
	public int newX, newY;
	
	public Packet01Move(byte[] data)
	{
		super(data, 1);
		
		newX = Integer.parseInt(getInfo().split(":")[1]);
		newY = Integer.parseInt(getInfo().split(":")[2]);
	}
	
	public int getNewX()
	{
		return this.newX;
	}
	
	public int getNewY()
	{
		return this.newY;
	}

	//format: id:newX:newY
	//client is telling us their new x and y locations
	
	@Override
	public void parse() {
		/*
		//get newX and newY
		int newX = Integer.parseInt(getInfo().split(":")[1]);
		int newY = Integer.parseInt(getInfo().split(":")[2]);
		
		//update position in player entity object
		Server.players.get(getClient()).setX(newX);
		Server.players.get(getClient()).setY(newY);
		
		//finally tell all other clients that we have moved to another location
		for(Client client : Server.players.keySet())
		{
			if(client != getClient())
				client.sendPacket(new Packet01Move(new String(1 + ":" + newX + ":" + newY).getBytes()));
		}
		*/
	} 

}
