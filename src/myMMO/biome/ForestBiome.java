package myMMO.biome;



public class ForestBiome extends Biome{

	public ForestBiome(int xStart, int xEnd, int yStart, int yEnd) {
		super(0, xStart<<3, xEnd<<3, yStart<<3, yEnd<<3);
		
	}
	/**
	 * 
	 */
	public int[] tileTypes()
	{
		//2,4,6   grass, log, dirt
		int[] tileIds={2,4,6};
		
		return tileIds;
	}

}
