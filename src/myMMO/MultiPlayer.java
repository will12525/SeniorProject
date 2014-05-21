package myMMO;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import myMMO.entity.PlayerEntity;

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
			//we now have a string of data from the server to work with
			
			int id = Integer.parseInt(info.split(":")[0]);
			
			switch(id)
			{
			case 1:
				//the other player has moved
				int x = Integer.parseInt(info.split(":")[1]);
				int y = Integer.parseInt(info.split(":")[2]);
				
				otherPlayer.setX(x);
				otherPlayer.setY(y);
				break;
			case 2:
			}
		}
	}
	
	
}
