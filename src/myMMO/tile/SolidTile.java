package myMMO.tile;

import myMMO.Level;
import myMMO.entity.Entity;

public class SolidTile extends Tile {

	 public SolidTile(int id, boolean isEmitter, int tileColour, int levelColour, int x, int y, boolean flipx, boolean flipy) {
	        super(id, true, isEmitter, tileColour, levelColour, x, y, flipx, flipy);
	        this.solid = true;
	    }
	 public boolean mayPass(Level level, int x,int y, Entity e)
	 {
		 return false;
	 }

}
