package myMMO;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import myMMO.menu.InventoryMenu;



public class MouseListeners implements MouseListener
{
	private boolean mouseHas=false;
	public void mouseClicked(MouseEvent e) 
	{
		if(Game.getMenu() instanceof InventoryMenu)
		{
			
			System.out.println("hi");
		}
	//System.out.println(e.getButton());
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
