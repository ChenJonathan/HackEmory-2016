package hack.emory.Floor;

import java.awt.geom.Point2D;

public class Room {
	private int id;
	private boolean[] doors;
	
	private Point2D[] friendlies;
	private Point2D[] enemies;
	
	private boolean discovered;
	
	public static int idCounter = 0;
	
	public Room()
	{
		id = idCounter;
		idCounter++;
		discovered = false;
		doors = new boolean[4];
	}
	
	public void addDoors(boolean[] newDoors)
	{
		for(int i = 0; i < 4; i ++)
		{
			doors[i] = doors[i] || newDoors[i];
		}
	}
	
	public int getID()
	{
		return id;
	}
}