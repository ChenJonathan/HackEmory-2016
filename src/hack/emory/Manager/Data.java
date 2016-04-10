package hack.emory.Manager;

import java.awt.geom.Point2D;

/**
 * Stores general persisting information (player statistics, map statistics, etc).
 */
public class Data
{
	private static Point2D start = null;
	
	public static Point2D getStart()
	{
		return start;
	}
	
	public static void setStart(Point2D end)
	{
		start = end;
	}
}