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
					
					System.out.println("position 1");
				}
				//box 2
				if(mX>338&&mX<392)
				{
					Game.mouseItemPosition=1;
					Game.mouseHas=true;
					
					System.out.println("position 2");
				}
				//box 3
				if(mX>392&&mX<450)
				{
					position=3;
					System.out.println("position 3");
				}
				//box 4
				if(mX>450&&mX<500)
				{
					position=4;
					System.out.println("position 4");
				}
				//box 5
				if(mX>500&&mX<563)
				{
					position=5;
					System.out.println("position 5");
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
					System.out.println("position 6");
				}
				//box 7
				if(mX>338&&mX<392)
				{
					System.out.println("position 7");
				}
				//box 8
				if(mX>392&&mX<450)
				{
					System.out.println("position 8");
				}
				//box 9
				if(mX>450&&mX<500)
				{
					System.out.println("position 9");
				}
				//box 10
				if(mX>500&&mX<563)
				{
					System.out.println("position 10");
				}
			}
			if(mY>450&&mY<506)
			{
				//box 11
				if(mX>286&&mX<338)
				{
					System.out.println("position 11");
				}
				//box 12
				if(mX>338&&mX<392)
				{
					System.out.println("position 12");
				}
				//box 13
				if(mX>392&&mX<450)
				{
					System.out.println("position 13");
				}
				//box 14
				if(mX>450&&mX<500)
				{
					System.out.println("position 14");
				}
				//box 15
				if(mX>500&&mX<563)
				{
					System.out.println("position 15");
				}
				//boots
				if(mX>710&&mX<760)
				{
					System.out.println("boots");
				}
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
