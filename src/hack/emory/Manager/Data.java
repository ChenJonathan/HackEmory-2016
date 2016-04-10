package hack.emory.Manager;

/**
 * Stores general persisting information (player statistics, map statistics, etc).
 */
public class Data
{
	private static int level = 1;
	private static int kills = 0;
	private static int time = -1;
	private static boolean minimap = false;

	public static int getLevel()
	{
		return level;
	}

	public static void setLevel(int level)
	{
		Data.level = level;
	}

	public static int getKills()
	{
		return kills;
	}

	public static void setKills(int kills)
	{
		Data.kills = kills;
	}

	public static int getTime()
	{
		return time;
	}

	public static void setTime(int time)
	{
		Data.time = time;
	}
	
	public static boolean getMinimap()
	{
		return minimap;
	}
	
	public static void setMinimap(boolean minimap)
	{
		Data.minimap = minimap;
	}
}