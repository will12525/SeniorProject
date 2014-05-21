package items;

import myMMO.Colours;

public class HoeItem extends Tool{
	
	private static int colour=Colours.get(-1, 211, 444, 333);
	private static int id =1+20*32;
	
	
	public HoeItem(String name,int hoeLevel) {
		super(name, colour, id);
		// TODO Auto-generated constructor stub
	}

}
