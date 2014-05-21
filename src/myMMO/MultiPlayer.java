package myMMO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import myMMO.entity.Entity;
import myMMO.entity.Monkey;
import myMMO.entity.PlayerEntity;
import myMMO.entity.Turtle;
import myMMO.tile.Tile;

@SuppressWarnings("all")
public class MultiPlayer extends Thread
{
	private String IP;
	private Socket socket;
	private InputStreamReader in;
	private OutputStreamWriter out;
	
	private PlayerEntity otherPlayer;
	
	public MultiPlayer(String IP)
	{
		if(IP.isEmpty())
		{
			return;
		}
		else
		{
			this.IP = IP;
			
			try
			{
				this.socket = new Socket(IP, 4567);
			}
			catch (UnknownHostException e)
			{
				e.printStackTrace();
				return;
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
			
			try
			{
				this.in = new InputStreamReader(this.socket.getInputStream());
			}
			catch(IOException e)
			{
				e.printStackTrace();
				return;
			}
			
			try
			{
				this.out = new OutputStreamWriter(this.socket.getOutputStream());
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return;
			}
		}
		
		this.start();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			char[] cData = new char[1024];
			
			try
			{
				this.in.read(cData);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				continue;
			}
			
			byte[] data = new byte[1024];
			
			for(int i = 0; i != 1024; i++)
				data[i] = (byte)cData[i];
			
			String info = new String(data);
			info = info.trim();
			
			//we now have a string of data from the server to work with
			String[] split = info.split(":");

			int id = Integer.parseInt(split[0]);
			
			String type;
			
			switch(id)
			{
			case 1:
				//the other player has moved
				int x = Integer.parseInt(info.split(":")[1]);
				int y = Integer.parseInt(info.split(":")[2]);
				
				otherPlayer.setX(x);
				otherPlayer.setY(y);
				break;
			case 3:
				//recieving the map
				
				for(int i = 1; i < split.length; i += 3)
				{
					int tileX = Integer.parseInt(split[i]);
					int tileY = Integer.parseInt(split[i + 1]);
					int tileID = Integer.parseInt(split[i + 2]);
					
					Tile t = Tile.getTile(tileID);
					t.setX(tileX);
					t.setY(tileY);
					
					Game.level.addTile(t);
				}
				
				break;
			case 4:
				//add an entity
				type = split[1];
				
				if(type.equalsIgnoreCase("turtle"))
				{
					Game.level.addEntity(new Turtle(Game.level, "turtle", 0, 0));
				}
				else if(type.equalsIgnoreCase("player"))
				{
					//Game.level.addEntity(new PlayerEntity(Game.instance, Game.level, 0, 0, null, "player", false));	
					Game.level.addEntity(new Monkey(Game.level, "monkey", 0, 0));
				}
				break;
			case 5:
				//remove an entity
				type = split[1];
				
				if(type.equalsIgnoreCase("turtle"))
				{
					for(Entity e : Game.level.getEntities())
					{
						if(e instanceof Turtle)
						{
							Game.level.removeEntity(e);
							break;
						}
					}
				}
				else if(type.equalsIgnoreCase("player"))
				{
					for(Entity e : Game.level.getEntities())
					{
						if(e instanceof Monkey && e != Game.level.getPlayer())
						{
							Game.level.removeEntity(e);
							break;
						}
					}	
				}
				break;
			}
		}
	}	
	
	public void send(String info) 
	{
		try
		{
			this.out.flush();
			this.out.write(info);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
