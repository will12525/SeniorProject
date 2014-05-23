package myMMO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import myMMO.entity.Entity;
import myMMO.entity.EntityType;
import myMMO.entity.Monkey;
import myMMO.packet.Packet;
import myMMO.packet.Packet01Move;
import myMMO.packet.Packet03TileUpdate;
import myMMO.packet.Packet04AddEntity;
import myMMO.packet.Packet05RemoveEntity;
import myMMO.tile.Tile;

@SuppressWarnings("all")
public class MultiPlayer extends Thread
{
	private String IP;
	private Socket socket;
	private InputStreamReader in;
	private OutputStreamWriter out;
	
	private Entity otherPlayer;
	
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
			
			int id = Packet.getID(info);
			
			switch(id)
			{
			case 0:
				break;
				
			case 1:
				Packet01Move p01 = new Packet01Move(info);
				otherPlayer.setX(p01.getNewX());
				otherPlayer.setY(p01.getNewY());
				break;
			
			case 2:
				break;
				
			case 3:
				//getting a tile
				Packet03TileUpdate p03 = new Packet03TileUpdate(info);
				Tile t = p03.getTile();
				Game.level.setTile(t.getX(), t.getY(), t.getId());
				break;
			
			case 4: 
				//adding an entity
				Packet04AddEntity p04 = new Packet04AddEntity(info);
				Entity e = p04.getEntity();
				Game.level.addEntity(e);
				//temp //TODO: fix
				otherPlayer = e;
				break;
			
			case 5:
				//removing an entity
				Packet05RemoveEntity p05 = new Packet05RemoveEntity(info);
				EntityType toRemove = p05.getToRemove();
				
				switch(toRemove)
				{
				case MONKEY:
					//Game.level.addEntity(new Monkey("Name", 0, 0));
					Game.level.removeEntity(otherPlayer);
					break;
				
				case PLAYER:
					//Game.level.addEntity(new Monkey("Name", 0, 0));
					Game.level.removeEntity(otherPlayer);
				}
			}
			/*
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
					Entity e = new Turtle("turtle", 0, 0);
					Game.level.addEntity(e);
					otherPlayer = e;
				}
				else if(type.equalsIgnoreCase("player"))
				{
					Entity e = new Monkey("monkey", 0, 0);
					Game.level.addEntity(e);
					otherPlayer = e;
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
			*/
		}
	}	
	
	public OutputStreamWriter getOutput()
	{
		return this.out;
	}
}
