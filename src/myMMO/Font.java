package myMMO;

public class Font {
	private static String chars=""+"ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+"0123456789.,:;'\"!?$%()-=+/      " ;

	/**
	 * 
	 * @param msg = message to be displayed
	 * @param display = the display
	 * @param x = x position
	 * @param y = y position
	 * @param colour = Color of letters/numbers ex (-1,-1,-1,555) will make them all white
	 * @param scale = how big it should be
	 */
	public static void renderFont(String msg, Display display, int x, int y, int colour,int scale)
	{
		msg=msg.toUpperCase();
		for(int i=0;i<msg.length();i++)
		{
			int charIndex = chars.indexOf(msg.charAt(i));
			if(charIndex>=0)
			{
				display.render((x+(i*8)*scale), (y)*scale,charIndex+(30*32), colour, 0,0,scale);
			}
		}
	}
}



