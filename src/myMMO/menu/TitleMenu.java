package myMMO.menu;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;
import myMMO.Game;
import myMMO.Level;

public class TitleMenu extends Menu{
	
	public TitleMenu()
	{
		
	}
	
	public void tick()
	{
		if(input.enter.down)
		{
			Game.setMenu(null);
		}
	}
	public void render(Display display,Level level)
	{
		display.clear(0);
		//display.setOffset(0, 0);
		Font.renderFont("The Game", display, 18, 10, Colours.get(-1, -1, -1, 005), 2);
		Font.renderFont("W/Up    - move up", display, 0, 50, Colours.get(-1, -1, -1, 005), 1);
		Font.renderFont("S/Down  - move down", display, 0, 60, Colours.get(-1, -1, -1, 005), 1);
		Font.renderFont("A/Left  - move left", display, 0, 70, Colours.get(-1, -1, -1, 005), 1);
		Font.renderFont("D/Right - move right", display, 0, 80, Colours.get(-1, -1, -1, 005), 1);
		Font.renderFont("Space   - action", display, 0, 90, Colours.get(-1, -1, -1, 005), 1);
		Font.renderFont("Press Enter", display, 35, 100, Colours.get(-1, -1, -1, 005), 1);
	}

}
