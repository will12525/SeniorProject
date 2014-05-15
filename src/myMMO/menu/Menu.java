package myMMO.menu;

import myMMO.Display;
import myMMO.Game;
import myMMO.KeyInputHandler;
import myMMO.Level;

public abstract class Menu {
	protected Game game;
	protected KeyInputHandler input;
	
	public void init(Game game, KeyInputHandler input)
	{
		this.input=input;
		this.game=game;
	}
	
	public abstract void tick();
	/**
	 * render stuff for the menu
	 * @param display
	 */
	public abstract void render(Display display,Level level);
	

}
