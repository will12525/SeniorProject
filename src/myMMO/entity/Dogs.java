package myMMO.entity;

import java.awt.Rectangle;

import myMMO.Colours;
import myMMO.Display;

public class Dogs extends Entity {
	private int tickCount;
	private static int colour=Colours.get(-1, 321, 211, 000);

	protected int xOffset;
	protected int yOffset;

	//Rectangle monkeyBox=new Rectangle();
	public Dogs(String name, int x, int y) {
		super("Dog", x, y, 0,"Woof!",8,25,colour);

	}
	public void render(Display display) {
		int xTile=8;
		int yTile=25;

		int walkingSpeed =3;
		int flipTopX=(numSteps>>walkingSpeed)&1;
		int flipTopY=(numSteps>>walkingSpeed)&1;
		int flipBottomL=(numSteps>>walkingSpeed)&1;
		int flipBottomR=(numSteps>>walkingSpeed)&1;

		//int fixer=;
		int modifier =8*scale;
		xOffset =x-modifier/2;
		yOffset =y-modifier/2-4;
		//Font.render((x>>3)+", "+(y>>3), display, x, y, Colours.get(-1, -1, -1, 555), 1);
		if(movingDirection==1)
		{
			xTile+=2;
		}
		else if(movingDirection>1)
		{
			xTile+=4+((numSteps>>walkingSpeed)&1)*2;
			flipTopX=(movingDirection-1)%2;
		}

		if(isSwimming)
		{
			int waterColour = 0;
			yOffset+=4;
			if(tickCount%60<15)
			{
				yOffset-=1;
				waterColour=Colours.get(-1, -1, 225, -1);

			}
			else if(15<=tickCount%60&&tickCount%60<30)
			{
				waterColour=Colours.get(-1, 225, 115, -1);
			}
			else if(30<=tickCount%60&&tickCount%60<45)
			{
				yOffset-=1;
				waterColour=Colours.get(-1, 115, -1, 225);
			}
			else
			{
				waterColour=Colours.get(-1, 225, 115, -1);
			}
			display.render(xOffset, yOffset+3, 0+27*32, waterColour, flipTopX-1,flipTopY-1, 1);
			display.render(xOffset+8, yOffset+4, 0+27*32, waterColour, 1,1, 1);
		}



		display.render(xOffset+(modifier*flipTopX), yOffset, (xTile+yTile*32), colour,flipTopX,flipTopY-1,scale);
		display.render(xOffset+modifier-(modifier*flipTopX), yOffset, (xTile+1)+yTile*32, colour,flipTopX,flipTopY-1,scale);


		if(!isSwimming)
		{
			display.render(xOffset+(modifier*flipBottomL), yOffset+modifier, xTile+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
			display.render(xOffset+modifier-(modifier*flipBottomL), yOffset+modifier, (xTile+1)+(yTile+1)*32, colour,flipBottomL,flipBottomR-1,scale);
		}
	}
	
	public Rectangle getActionBounds()
	{
		return new Rectangle(x-2,y-2,12,12);
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x,y,8,8);
	}
	



}
