package myMMO.packet;

import myMMO.tile.Tile;

public class Packet03TileUpdate extends Packet {

	private Tile t;

	public Packet03TileUpdate(String info) {
		super(info);
	}

	@Override
	public void parse() {
		int x = Integer.parseInt(args[1]);
		int y = Integer.parseInt(args[2]);
		int tileID = Integer.parseInt(args[3]);

		Tile t = Tile.createTile(x, y, tileID);

		this.t = t;
	}
	
	public Tile getTile()
	{
		return this.t;
	}
}