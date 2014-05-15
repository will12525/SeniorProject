package packet;



public class Packet02AddEntity extends Packet {

	//public Packet02AddEntity(byte[] data, Client client) {
	//	super(data, 2, client);
	//}
	
	public Packet02AddEntity(byte[] data)
	{
		super(data, 2);
	}

	@Override
	public void parse() {
		//never need to parse
	}

}
