package myMMO.menu;

import myMMO.Colours;
import myMMO.Display;
import myMMO.Font;

public class InventoryMenu extends Menu {

	@Override
	public void tick() {

	}

	@Override
	public void render(Display display) {
		//Font.renderFont("hi", display, 50, 50, Colours.get(-1, -1, -1, 5), 1);
		display.render(0, 7, 7, Colours.get(-1, -1, -1, 5), 0, 0, 1);

	}

}
