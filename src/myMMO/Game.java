package myMMO;

import items.InvyItemBlank;
import items.Item;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import myMMO.entity.Chicken;
import myMMO.entity.Dogs;
import myMMO.entity.Entity;
import myMMO.entity.Monkey;
import myMMO.entity.PlayerEntity;
import myMMO.entity.Skeleton;
import myMMO.entity.Turtle;
import myMMO.menu.InventoryMenu;
import myMMO.menu.Menu;
import myMMO.menu.TitleMenu;
import myMMO.packet.Packet02Disconnect;


/**
 * The game!
 */
public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;

	//the frame the game is in
	private JFrame frame;
	//to generate randoms when needed
	Random rand = new Random();
	//player, monkey, turtle objects
	public PlayerEntity Player;
	public Monkey monkey;
	public Turtle turtle;
	public Skeleton skelly;
	public Dogs dogs;
	public Chicken chicken;
	//more important objects
	private Display display;

	//private int daysSinceBegin=0;
	double cycle=0;
	int holdingTime=0;
	private boolean dayTime = true;
	private boolean nightTime=false;
	private boolean holdTime=true;
	private boolean spawnHostiles=true;
	//private Display dayNightDisplay;
	public static KeyInputHandler input;
	public static Level level;
	public boolean currentChat=false;
	public static Menu menu;

	public static boolean mouseHas=false;
	public static boolean mouseHolding=false;
	public static int mouseItemPosition=0;

	public long lastItemCheck=0;
	public static int selectedBox=1;

	//	InventoryMenu inventory = new InventoryMenu();



	ArrayList<Entity>boxs = new ArrayList<Entity>();
	//the players random x and y starting position
	//private int playerNewX=0;
	//private int playerNewY=0;
	//if an entity is swimming on start this will be true for them
	private boolean swimming = false;
	//public List<Entity>entities;

	//the main thing to keep the game going
	public boolean running = false;
	//cycles gone by
	public int tickCount= 0;
	//jframe width and height
	int WIDTH= 160;
	int HEIGHT=WIDTH/12*9;
	//scale to easily change size while keeping ratio
	int SCALE = 7;
	
	//instance of the class
	public static Game instance;
	
	//Multiplayer object for sending data from other classes
	public static MultiPlayer multiplayer;

	/**
	 * the image that contains everything
	 */
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	/**
	 * all the pixels on the image
	 */
	private int[] pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	/**
	 * colors for pixels, 6 different shades per color, max is 255
	 */
	private int[] colours = new int[6*6*6];

	public static void setMenu(Menu menu)
	{
		//System.out.println(menu);
		Game.menu=menu;
		
		if(menu!=null)
		{
			menu.init(instance, input);
		}
	}

	public static Menu getMenu()
	{
		return menu;
	}

	/**
	 * Creates the jframe that holds the game
	 * 
	 */
	public Game()
	{
		frame = new JFrame("Need A Name");


		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		frame.setLayout(new BorderLayout());
		//System.out.println(WIDTH*SCALE + ", "+HEIGHT*SCALE);
		frame.add(this,BorderLayout.CENTER);
		setIgnoreRepaint(true);

		frame.pack();
		frame.setResizable(false);
		//centers frame
		frame.setLocationRelativeTo(null);
		//frame.setLocation(-1500, 100);
		frame.setVisible(true);
		//checks the red close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//allows keys
		requestFocus();
		this.addMouseListener(new MouseListeners());
	}
	/**
	 * calls run, sets the boolean running = true which controls the while loop for the game
	 */
	public void start()
	{
		running = true;
		new Thread(this).start();
	}

	/**
	 * the game, contains everything/calls needed things
	 */
	@SuppressWarnings("unused")
	public void run()
	{
		long lastLoopTime = System.nanoTime();
		double nanoTick=1000000000/60;

		int ticks=0;
		int frames = 0;

		long lastTimer=System.currentTimeMillis();
		double delta=0;
		//calls the method that initializes entites, tiles, everything
		init();

		while(running)
		{
			long now=System.nanoTime();
			delta+=(now-lastLoopTime)/nanoTick;
			lastLoopTime=now;
			boolean shouldRender=true;
			while(delta>=1)
			{
				ticks++;
				tick();
				delta-=1;
				shouldRender=true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(shouldRender)
			{
				frames++;
				render();
			}
			if(System.currentTimeMillis()-lastTimer>=1000)
			{
				lastTimer+=1000;
			//	System.out.println("Ticks: "+ticks+", frames: "+frames);
				frames=0;
				ticks=0;
			}

		}
	}
	/**
	 * the initiator! Creates the list of colors, a new display with a sprite sheet path, gets input, gives the level image path, creates a player and any entity wanted in the game.
	 */
	@SuppressWarnings("static-access")
	public void init()
	{
		int index=0;
		//cycles every possible color, red
		for(int r =0;r<6;r++)
		{
			//green
			for(int g=0;g<6;g++)
			{
				//blue
				for(int b=0;b<6;b++)
				{
					//255 is the transparent color
					//shades of colors
					int rr=(r*255/5);
					int gg=(g*255/5);
					int bb=(b*255/5);
					//colours at index = r*2^8,g*2^8,b*2^8
					//can call for a color at index, will return desired color
					colours[index++]=rr<<16|gg<<8|bb;
				}
			}
		}

		//calls the display class which reads every pixel from the path and generates 8x8 tiles (width of frame, height of frame, path to entitys)
		display=new Display(WIDTH,HEIGHT,new SpriteSheet("/sprites/spriteSheet.png"));

		//sets up the inputhandler class
		input = new KeyInputHandler(this);

		//
		level=new Level(this,"/levels/levelStart.png");

		//calls the method that generates a random x and y for the player to spawn on
		/*if(playerNewX==0)
		{
			getPlayerXY();
		}*/

		//creates the player
		Player = new PlayerEntity(20,20,input,null/*JOptionPane.showInputDialog(this,"Please enter username")*/,swimming);
		level.addEntity(Player);
		level.setPlayer(Player);

		//generates turtles, random amount between 3 and 5
		/*for(int t=0;t<rand.nextInt(20)+10;t++)
		{
			turtle=new Turtle(level,"turtle",rand.nextInt(400)+40,rand.nextInt(400)+40,0,false);
			//add each turtle
			level.addEntity(turtle);
		}

		/*for(int t=0;t<rand.nextInt(50)+40;t++)
		{
			skelly=new Skeleton(level,"turtle",rand.nextInt(1300)+40,rand.nextInt(1300)+40,0,false);
			//add each turtle
			level.addEntity(skelly);

		}*/
		turtle=new Turtle("turtle",10,10);
		//add each turtle
		level.addEntity(turtle);

		//generates monkeys, random amount between 3 and 5
		/*for(int monk=0;monk<rand.nextInt(20)+10;monk++)
		{
			monkey = new Monkey(level,"monkey", rand.nextInt(400)+40,rand.nextInt(400)+40, 0,false);

			//adds each monkey
			level.addEntity(monkey);
		}*/

		setMenu(new TitleMenu());


		/*dogs=new Dogs(level,"dog",rand.nextInt((level.width*8)), rand.nextInt(level.height*5),0,false);
		level.addEntity(dogs);
		chicken=new Chicken(level,"chicken",rand.nextInt((level.width*8)), rand.nextInt(level.height*8),0,false);
		level.addEntity(chicken);
		 */
	}

	public static Level getLevel()
	{
		return level;
	}

	/**
	 * Gets a random x and y number for the player to spawn at, it also checks to make sure the spawn tile is grass or water.
	 */
	public void getPlayerXY()
	{//players x
	/*	playerNewX=rand.nextInt((100)*8);
		//players y
		playerNewY=rand.nextInt((100)*8);
		//if the tile hes going to spawn on is water, this makes sure the swimming animation is playing
		//if(level.getTile(playerNewX>>3, playerNewY>>3)!=Tile.GRASS||level.getTile(playerNewX>>3, playerNewY>>3)!=Tile.DIRT||level.getTile(playerNewX>>3, playerNewY>>3)!=Tile.SAND)
		//		{
		if(level.getTile(playerNewX>>3, playerNewY>>3)==Tile.WATER)
		{
			swimming=true;
		}
		if(level.getTile(playerNewX>>3, playerNewY>>3).isSolid())
		{

			swimming=false;
			playerNewX=0;
			playerNewY=0;
			getPlayerXY();

		}*/
	}








	/**
	 * called every cycle, calls level.tick()
	 */
	public void tick()
	{

		tickCount++;
		if(!hasFocus())
		{
			input.releaseAll();
		}
		else
		{
			if(menu!=null)
			{

				menu.tick();
			}
			else
			{

				level.tick();
				//timeCycle();



			}
		}
	}

	public void timeCycle()
	{
		if(nightTime&&!holdTime)
		{
			cycle=cycle+.1;
			if(cycle>=210)
			{

				holdTime=true;
			}
		}
		if(dayTime&&!holdTime)
		{
			cycle=cycle-.1;
			if(cycle<=1)
			{
				holdTime=true;
			}
		}
		if(holdTime)
		{
			if(nightTime)
			{

				if(spawnHostiles)
				{
					System.out.println("spawning mobs now");
					level.spawnHostiles();

					spawnHostiles =false;
				}
				holdingTime++;
				if(holdingTime>=900)
				{
					nightTime=false;
					dayTime=true;
					holdTime=false;
					holdingTime=0;
					spawnHostiles=true;
					return;
				}
			}
			if(dayTime)
			{
				holdingTime++;
				if(holdingTime>=1200)
				{
					nightTime=true;
					dayTime=false;
					holdTime=false;
					holdingTime=0;
					return;
				}
			}
		}
	}


	/**
	 * also called every cycle, creates a buffer strategy, calls the rendering of everything that needs to render, draws needed stuff
	 */
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}

		int xOffset=Player.getX()-(display.width/2);
		int yOffset=Player.getY()-(display.height/2);

		level.renderTiles(display, xOffset, yOffset);

		level.renderItems(display);


		level.renderEntities(display,xOffset,yOffset);
		level.renderHoldItem(display);


		renderGui();
		level.renderMouseItem(display,frame.getLocation().x,frame.getLocation().y);
		//String msg="Hello World!";
		//Font.render(msg,display,0,0,Colours.get(-1, -1, -1, 000));


		for(int y = 0;y<display.height;y++)
		{
			for(int x =0;x<display.width;x++)
			{
				int colourCode=display.pixels[x+y*display.width];
				if(colourCode<255)
				{
					pixels[x+y*WIDTH]=colours[colourCode];
				}
			}
		}




		Graphics g = bs.getDrawGraphics();
		g.fillRect(0,0,getWidth(),getHeight());


		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);



		g.setColor(new Color(0,0,0,(int) cycle));

		g.fillRect(0, 0, getWidth(), getHeight());

		//boxs.addAll((Collection<? extends Entity>) Arrays.asList(level.entitiesInTiles));
		/*for(int i=0;i<level.getEntities(x1, y1, x2, y2);i++)
		{Mob moby =(Mob) boxs.get(i);
			if(moby==monkey)
			{
				System.out.println("hi");
			}
		}*/




		//collision
		//g.drawRect(turtle.x, turtle.y, 12, 12);

		//g.drawRect((monkey.x), (monkey.y), 8, 8);
		//g.drawRect((Player.x), (Player.y), 8, 8);
		//action
		//g.drawRect((monkey.x-2), (monkey.y-2), 12, 12);
		//g.drawRect((Player.x)-3, (Player.y)-3, 14, 14);

		g.dispose();
		bs.show();
	}

	private void renderGui()
	{
		//render hearts
		display.setOffset(0, 0);
		for(int heart=0;heart<10;heart++)
		{
			if(heart<Player.health)
			{
				display.render(heart*8, -1, 0+20*32, Colours.get(-1, 000, 500, -1), 0, 0, 1);
			}
		}

		//render inventory bar
		//display.render(160-8-1, 108,(4+27*32), Colours.get(000, -1, -1, -1), 0, 0, 1);
		int boxColour=0;
		for(int i = 0; i != 5; i++)
		{
			if(i==selectedBox-1)
			{
				boxColour=Colours.get(005, -1, -1, -1);
			}
			else
			{
				boxColour= Colours.get(000, -1, -1, -1);
			}
			display.render((8*i)+119, 108 ,(4+27*32), boxColour, 0, 0, 1);
		}

		//render items in player's inventory
		for(int i = 0; i != 5 && i < level.getPlayer().getItems().size(); i++)
		{
			Item item=level.getPlayer().getItems().get(i);
			if((i==selectedBox-1))
			{
				if(!(item instanceof InvyItemBlank))
				{
					Player.setHoldItem(item,i);
				}
				else
				{
					Player.setHoldItem(new InvyItemBlank("empty"),i);
				}
			}
			
			
			item.renderInInventory(display,i);
		}

		//render  menu
		if(menu!=null)
		{
			menu.render(display,level);
		}
		if(menu instanceof InventoryMenu)
		{
			checkMouse();
		}
		if(mouseHolding)
		{
			if(menu==null)
			{
				Item item = level.getMouseItem().get(0);
				level.getMouseItem().remove(0);
				level.addItem(item);
				item.setX(Player.getX()>>3);
				item.setY(Player.getY()>>3);
				item.setCoolDown(300);
				mouseHolding=false;
			}
		}
	}

	public void checkMouse()
	{
		boolean allowCheck=true;
		if(level.getMouseItem().size()>0)
		{
			if(mouseHas)
			{
				Item item = level.getPlayer().getItem(mouseItemPosition);
				Item mouseItem= level.getMouseItem().get(0);
				if(item instanceof InvyItemBlank)
				{
					level.getPlayer().changeItem(mouseItem, mouseItemPosition);
					level.getMouseItem().remove(0);
					mouseHas=false;
					mouseHolding=false;
					mouseItemPosition=0;
					lastItemCheck=System.currentTimeMillis();
					allowCheck=false;
				}
				if(!(item instanceof InvyItemBlank))
				{
					level.getPlayer().changeItem(mouseItem, mouseItemPosition);
					level.getMouseItem().remove(0);
					level.getMouseItem().add(item);
					mouseHas=false;
				}
			}
		}
		
		
		if(mouseHas&&!mouseHolding&&allowCheck)
		{

			Item item = level.getPlayer().getItem(mouseItemPosition);
			if(item instanceof InvyItemBlank)
			{
				mouseHas=false;
				mouseItemPosition=0;
				return;
			}
			level.getPlayer().changeItem(new InvyItemBlank("empty"), mouseItemPosition);
			level.addMouseItem(item);

			mouseHas=false;
			mouseHolding=true;
		}
		
		
		//drops item if clicked outside inventory
		if(mouseItemPosition==18&&level.getMouseItem().size()>0)
		{
			Item item = level.getMouseItem().get(0);
			level.getMouseItem().remove(0);
			level.addItem(item);
			item.setX(Player.getX()>>3);
			item.setY(Player.getY()>>3);
			item.setCoolDown(300);
			mouseHolding=false;
		}
	}

	public void stop()
	{
		running = false;
	}

	/**
	 * calls game and start
	 * @param args
	 */
	public static void main(String[] args) {
		//multiplayer
		String s = JOptionPane.showInputDialog("Please enter IP for multiplayer (or blank for no multiplayer)");
		if(!s.isEmpty())
		{
			multiplayer = new MultiPlayer(s);
		}
		

		Game g = new Game();
		instance = g;
		g.start();
		
		//when the program exits, tell the server we are disconnecting
		Runtime.getRuntime().addShutdownHook(new Thread(){
			
			@Override
			public void run()
			{
				new Packet02Disconnect("2").send(Game.multiplayer.getOutput());
			}
		});
			
		

	}


}
