package myMMO.packet;

import java.util.ArrayList;
import java.util.List;

import myMMO.tile.Tile;

public class Packet03Map extends Packet {
	private List<Tile> tiles = new ArrayList<Tile>();

	public Packet03Map(String info) {
		super(info);
	}

	@Override
	public void parse() {
		for(int i = 1; i < tiles.size(); i += 3)
		{
			int x = Integer.parseInt(args[i]);
			int y = Integer.parseInt(args[i + 1]);
			int tileID = Integer.parseInt(args[i + 2]);
			
			Tile t = Tile.getTile(tileID);
			t.setX(x);
			t.setY(y);
			
			tiles.add(t);
		}
	}

}
