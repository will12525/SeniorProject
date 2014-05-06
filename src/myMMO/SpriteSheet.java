package myMMO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Finds and loads the sprite sheet
 *
 *
 */
public class SpriteSheet {
	public String path;
	public int width;
	public int height;
	public int[] pixels;
/**
 * finds and loads the sprite sheet for the game, 
 * @param path = path to desired sprite sheet
 */
	public SpriteSheet(String path) 
	{
		BufferedImage image = null;

		try {
			image=ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
//if there is no image image then end
		if(image==null)
		{
			System.out.println("No image");
			return;
		}
		this.path=path;
		this.width=image.getWidth();
		this.height=image.getHeight();
//gets the pixels in the image
		pixels=image.getRGB(0, 0, width, height, null, 0, width);
		

//puts all the pixels into an array
		for(int i=0;i<pixels.length;i++)
		{
			pixels[i]=(pixels[i]&0xff)/64;



		}
	}
}
