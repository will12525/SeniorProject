package myMMO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import myMMO.entity.Entity;
import myMMO.entity.PlayerEntity;
import packet.Packet;
import packet.Packet01Move;

@SuppressWarnings("all")
public class MultiPlayer extends Thread
{
	private String IP;
	private Socket socket;
	private InputStreamReader in;
	private OutputStreamWriter out;
	
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
			
			int id = Integer.parseInt(String.valueOf(data[0]) + String.valueOf(data[1]));
			
			switch(id)
			{
			case 0: break; //never going to recieve a login packet
			case 1: 
				//find the other player in the world and move them to the new spot
				for(Entity e : Level.getEntities())
				{
					if((e instanceof PlayerEntity) && e != Level.getPlayer())
					{
						//this is the other player
						Packet01Move p = new Packet01Move(data);
						e.x = p.getNewX();
						e.y = p.getNewY();
						break;
					}
				}
				break;
			case 2:
				Level.addEntity(new PlayerEntity(null, null, 2, 2, new KeyInputHandler(null), "Player", false));
			}
			
			
		}
	}
	
	public void sendPacket(Packet p)
	{
		try
		{
			this.out.write(p.getInfo());
			this.out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
