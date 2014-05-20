package myMMO;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import myMMO.menu.InventoryMenu;



public class MouseListeners implements MouseListener
{
	
	public boolean mouseHas=false;
	public int position=0;

	
	public void mouseClicked(MouseEvent e) 
	{
		
		System.out.println(e.getX()+", "+e.getY());
	
		
		if(Game.getMenu() instanceof InventoryMenu)
		{
			int mX=e.getX();
			int mY=e.getY();
			//taskbar row
			if(mY>287&&mY<336)
			{
				//box 1
				if(mX>286&&mX<338)
				{
					Game.mouseItemPosition=0;
					Game.mouseHas=true;

				}
				//box 2
				if(mX>338&&mX<392)
				{
					Game.mouseItemPosition=1;
					Game.mouseHas=true;
				}
				//box 3
				if(mX>392&&mX<450)
				{
					Game.mouseItemPosition=2;
					Game.mouseHas=true;
				}
				//box 4
				if(mX>450&&mX<500)
				{
					Game.mouseItemPosition=3;
					Game.mouseHas=true;
				}
				//box 5
				if(mX>500&&mX<563)
				{
					Game.mouseItemPosition=4;
					Game.mouseHas=true;
				}
				//helmet
				if(mX>710&&mX<760)
				{
					System.out.println("helmet");
				}
			}
			//chestplate
			if(mY>370&&mY<420)
			{
				if(mX>710&&mX<760)
				{
					System.out.println("ChestPlate");
				}
			}
			if(mY>400&&mY<450)
			{
				//box 6
				if(mX>286&&mX<338)
				{
					Game.mouseItemPosition=5;
					Game.mouseHas=true;
				}
				//box 7
				if(mX>338&&mX<392)
				{
					Game.mouseItemPosition=6;
					Game.mouseHas=true;
				}
				//box 8
				if(mX>392&&mX<450)
				{
					Game.mouseItemPosition=7;
					Game.mouseHas=true;
				}
				//box 9
				if(mX>450&&mX<500)
				{
					Game.mouseItemPosition=8;
					Game.mouseHas=true;
				}
				//box 10
				if(mX>500&&mX<563)
				{
					Game.mouseItemPosition=9;
					Game.mouseHas=true;
				}
			}
			if(mY>450&&mY<506)
			{
				//box 11
				if(mX>286&&mX<338)
				{
					Game.mouseItemPosition=10;
					Game.mouseHas=true;
				}
				//box 12
				if(mX>338&&mX<392)
				{
					Game.mouseItemPosition=11;
					Game.mouseHas=true;
				}
				//box 13
				if(mX>392&&mX<450)
				{
					Game.mouseItemPosition=12;
					Game.mouseHas=true;
				}
				//box 14
				if(mX>450&&mX<500)
				{
					Game.mouseItemPosition=13;
					Game.mouseHas=true;
				}
				//box 15
				if(mX>500&&mX<563)
				{
					Game.mouseItemPosition=14;
					Game.mouseHas=true;
				}
				//boots
				if(mX>710&&mX<760)
				{
					System.out.println("boots");
				}
			}
			if((mX>907||mX<224)||(mY>570||mY<167))
			{
				System.out.println("drop it");
				Game.mouseItemPosition=18;
				
			}
			

		}
	}

	public void mouseEntered(MouseEvent e) 
	{


	}

	public void mouseExited(MouseEvent e) 
	{


	}

	public void mousePressed(MouseEvent e) 
	{


	}

	public void mouseReleased(MouseEvent e) 
	{


	}
}
