package myMMO.tile;

import myMMO.Level;
import myMMO.entity.Entity;

public class SolidTile extends BaseTile{

	 public SolidTile(int id, int x, int y, int tileColour, int levelColour, int xcoord, int ycoord) {
	        super(id, x, y, tileColour, levelColour,0,0, xcoord, ycoord);
	        this.solid = true;
	    }
	 public boolean mayPass(Level level, int x,int y, Entity e)
	 {
		 return false;
	 }

}
