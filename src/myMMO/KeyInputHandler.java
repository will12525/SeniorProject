package myMMO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;



public class KeyInputHandler implements KeyListener {

	Game game;
	public KeyInputHandler(Game game)
	{
		this.game=game;
		game.addKeyListener(this);
	}

	public class Key{
		private int numTimesPressed=0;
		//private boolean pressed = false;
		public boolean down;

		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}



		public void toggle(boolean isPressed)
		{

			if(isPressed!=down)
			{
				down=isPressed;
			}
			if(isPressed)numTimesPressed++;
		}
	}


	public List<Key>keys=new ArrayList<Key>();
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	public Key action = new Key();
	public Key enter = new Key();
	public Key inventory = new Key();
	public Key placeTile = new Key();
	public Key destroyTile = new Key();
	public Key escape = new Key();

	public void keyPressed(KeyEvent pressed) {
		if(pressed.getKeyChar()==KeyEvent.VK_ESCAPE)
		{
			if(Game.menu!=null)
			{
				Game.setMenu(null);
			}
			else
			{
				System.exit(0);
			}
		}
		if(pressed.getKeyChar()==KeyEvent.VK_SPACE)
		{
			Game.level.placeTile();
		}


		toggleKey(pressed.getKeyCode(),true);

	}


	public void keyReleased(KeyEvent released) {
		toggleKey(released.getKeyCode(),false);

	}


	public void keyTyped(KeyEvent typed) {


	}

	public void releaseAll()
	{
		for(int i=0;i<keys.size();i++)
		{
			keys.get(i).down=false;
		}
	}
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if(isPressed)
		{
			if(keyCode==KeyEvent.VK_1)
			{
				Game.selectedBox=1;
			}
			if(keyCode==KeyEvent.VK_2)
			{
				Game.selectedBox=2;
			}
			if(keyCode==KeyEvent.VK_3)
			{
				Game.selectedBox=3;
			}
			if(keyCode==KeyEvent.VK_4)
			{
				Game.selectedBox=4;
			}
			if(keyCode==KeyEvent.VK_5)
			{
				Game.selectedBox=5;
			}


			if(keyCode==KeyEvent.VK_F)
			{
				Game.level.destroyTile();
				destroyTile.toggle(isPressed);
			}

			if(keyCode==KeyEvent.VK_ENTER)
			{
				enter.toggle(isPressed);
			}
			if(keyCode==KeyEvent.VK_SPACE)
			{
				action.toggle(isPressed);
			}
		}
		if(keyCode==KeyEvent.VK_W||keyCode==KeyEvent.VK_UP)
		{
			up.toggle(isPressed);

		}
		if(keyCode==KeyEvent.VK_A||keyCode==KeyEvent.VK_LEFT)
		{
			left.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_S||keyCode==KeyEvent.VK_DOWN)
		{
			down.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_D||keyCode==KeyEvent.VK_RIGHT)
		{
			right.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_E)
		{
			inventory.toggle(isPressed);
		}
	}

}
