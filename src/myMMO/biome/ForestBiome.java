package myMMO.biome;



public class ForestBiome extends Biome{

	public ForestBiome(int type, int xStart, int xEnd, int yStart, int yEnd) {
		super(type, xStart, xEnd, yStart, yEnd);
		
	}
	public int[] tileTypes()
	{
		//2,4,6   grass, log, dirt
		int[] tileIds={2,4,6};
		
		return tileIds;
	}

}
