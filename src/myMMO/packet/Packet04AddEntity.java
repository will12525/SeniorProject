package myMMO.packet;

import myMMO.entity.Entity;
import myMMO.entity.FakePlayerEntity;

public class Packet04AddEntity extends Packet {
	
	private Entity e;

	public Packet04AddEntity(String info) {
		super(info);
	}

	@Override
	public void parse() {
		//temp
		//TODO: add actual functionality
		e = new FakePlayerEntity("Player", 0, 0);
	}
	
	public Entity getEntity()
	{
		return this.e;
	}

}
