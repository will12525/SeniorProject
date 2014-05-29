package myMMO.biome;


public class Biome {

	public int type;
	public int xStart,yStart,xEnd,yEnd;
	public Biome(int type, int xStart,int xEnd,int yStart,int yEnd)
	{
		this.type=type;
		this.xStart=xStart;
		this.xEnd=xEnd;
		this.yStart=yStart;
		this.yEnd=yEnd;
	}
	/**
	 * 
	 * @return
	 */
	public int[] tileTypes()
	{
		return null;
	}
	public int getType()
	{
		return type;
	}
	public int getXStart()
	{
		return xStart;
	}
	public int getYStart()
	{
		return yStart;
	}
	public int getXEnd()
	{
		return xEnd;
	}
	public int getYEnd()
	{
		return yEnd;
	}

}
