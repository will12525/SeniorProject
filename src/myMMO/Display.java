package myMMO;



public class Display {
	public static final int MAP_WIDTH=64;
	public static final int MAP_WIDTH_MASK=MAP_WIDTH-1;
	/**
	 * the array list of pixels
	 */
	public int[]pixels;
	public static final byte BIT_MIRROR_X=0x01;
	public static final byte BIT_MIRROR_Y=0x02;

	public int xOffset;
	public int yOffset;
	public int width;
	public int height;

	public SpriteSheet sheet;
	/**
	 * 
	 * @param width = width of frame provided by main class
	 * @param height = height of frame provided by main class
	 * @param sheet = path to the sprite sheet
	 */
	public Display(int width,int height,SpriteSheet sheet)
	{
		this.width=width;
		this.height=height;
		this.sheet=sheet;

		pixels=new int[width*height];
	}
	public void clear(int color)
	{
		for(int i=0;i<pixels.length;i++)
		{
			pixels[i]=color;
		}
	}
	
/**
 * 
 * @param xPos = x position on the game map
 * @param yPos = y position on the game map
 * @param tile = the tile id
 * @param colour = color wanted
 * @param flipX = if 1 flips on the x axis else remains normal
 * @param flipY = if 1 flips on the y axis else remains normal
 * @param scale = scale
 */
	public void render(int xPos,int yPos,int tile,int colour,int flipX,int flipY,int scale)
	{
		boolean mirrorX=false;
		boolean mirrorY=false;
		//keeps the display with the player, otherwise display would always be set at 0,0
		xPos-=xOffset;
		yPos-=yOffset;

		if(flipX==1)
		{
			mirrorX=true;
		}
		else
		{
			mirrorX=false;
		}
		if(flipY==1)
		{
			mirrorY=true;
		}
		else
		{
			mirrorY=false;
		}
		//boolean mirrorX=(mirrorDirection&BIT_MIRROR_X)>0;
		//boolean mirrorY=(mirrorDirection&BIT_MIRROR_Y)>0;

		int scaleMap=scale-1;
		//gets position of tile on a axis of sprite sheet
		int xTile=tile%32;
		//gets position of tile on y axis of sprite sheet
		int yTile=tile/32;
		//tile position on sprite sheet is xtile * 2^3 (or multiply by 8, moving over 1 per pixel, each tile has 8 pixels) + (yTile * 2^3)*   the width of the sheet
		int tileOffset=(xTile<<3)+(yTile<<3)*sheet.width;

		//each y
		for(int y=0;y<8;y++)
		{
			int ySheet=y;
			if(mirrorY)
			{
				ySheet=7-y;
			}
			//scales the y pixel
			int yPixel=y+yPos+(y*scaleMap)-((scaleMap<<3)/2);
			//each x
			for (int x = 0; x < 8; x++) 
			{
				int xSheet = x;
				if (mirrorX)
				{
					xSheet = 7 - x;
				}
				//scales the x pixel
				int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) / 2);

				//gets the color data for each tiles pixels
				int col = (colour >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255;
				//makes sure its not rendering the transparent color
				if (col < 255) {
					for (int yScale = 0; yScale < scale; yScale++) 
					{
						if (yPixel + yScale < 0 || yPixel + yScale >= height)
						{
							continue;
						}
						for (int xScale = 0; xScale < scale; xScale++) 
						{
							if (xPixel + xScale < 0 || xPixel + xScale >= width)
							{
								continue;
							}
							pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
						}
					}
				}

			}
		}
	}
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset=xOffset;
		this.yOffset=yOffset;

		
	}
}