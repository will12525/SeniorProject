package myMMO;

public class MultiPlayer extends Thread
{
	String IP;
	
	public MultiPlayer(String IP)
	{//
		if(IP.isEmpty())
		{
			return;
		}
		else
		{
			this.IP = IP;
		}
		
		this.start();
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			
		}
	}
}
