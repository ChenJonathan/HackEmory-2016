package hack.emory.Manager;

import java.awt.geom.Point2D;

/**
 * Stores general persisting information (player statistics, map statistics, etc).
 */
public class Data
{
	private static boolean minimap = false;;
	
	public static boolean getMinimap()
	{
		return minimap;
	}
	
	public static void setMinimap(boolean set)
	{
		minimap = set;
	}
}