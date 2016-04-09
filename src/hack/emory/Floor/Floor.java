package hack.emory.Floor;

import java.awt.geom.Point2D;

public class Floor
{
	private Room[][] rooms;
	
	private Point2D start;
	private Point2D end;
	
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	public Floor()
	{
		rooms = new Room[HEIGHT][WIDTH];
		start = new Point2D.Double((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT));
		while(end == null || end.equals(start))
		{
			end = new Point2D.Double((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT));
		}
		
		generateRooms();
	}
	
	public Floor(Point2D start, Point2D end)
	{
		rooms = new Room[HEIGHT][WIDTH];
		this.start = start;
		this.end = end;
		
		generateRooms();
	}
	
	public Room getRoom(int i, int j)
	{
		return rooms[i][j];
	}
	
	public Point2D getStart()
	{
		return start;
	}
	
	public Point2D getEnd()
	{
		return end;
	}
	
	public void generateRooms()
	{
		int currentX = (int) start.getX();
		int currentY = (int) start.getY();
		rooms[currentX][currentY] = new Room();
		while((currentX != end.getX()) && (currentY != end.getY()))
		{
			Point2D[] neighbors = new Point2D[4];
			neighbors[0] = new Point2D.Double(currentX, currentY - 1);
			neighbors[1] = new Point2D.Double(currentX + 1, currentY);
			neighbors[2] = new Point2D.Double(currentX, currentY + 1);
			neighbors[3] = new Point2D.Double(currentX - 1, currentY);
			double[] distance = new double[4];
			distance[0] = Math.abs(neighbors[1].distance(end));
			distance[1] = Math.abs(neighbors[2].distance(end));
			distance[2] = Math.abs(neighbors[3].distance(end));
			distance[3] = Math.abs(neighbors[4].distance(end));
			int min = 0;
			for(int i = 1; i < 3; i ++)
			{
				if(distance[min] > distance[i])
				{
					min = i;
				}
			}
			rooms[(int)neighbors[min].getX()][(int)neighbors[min].getX()] = new Room();
			currentX = (int)neighbors[min].getX();
			currentY = (int)neighbors[min].getY();
		}
	}
}