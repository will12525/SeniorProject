package myMMO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MouseListener extends MouseAdapter {
	public void MousePressed(MouseEvent e)
	{
		int mouseX=e.getX();
		System.out.println(mouseX);
		System.out.println("hi");
	}
	public void MouseReleased(MouseEvent e)
	{

	}
}
