package hack.emory.Floor;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Floor
{
	private Room[][] rooms;
	
	private Point2D start;
	private Point2D end;
	
	public static final int WIDTH = 5;
	public static final int HEIGHT = 5;
	
	public Floor(boolean random)
	{
		if(random)
		{
			start = new Point2D.Double((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT));
			while(end == null || end.equals(start))
			{
				end = new Point2D.Double((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT));
			}
		}
		else
		{
			start = new Point2D.Double(2, 2);
			while(end == null || end.equals(start))
			{
				end = new Point2D.Double((int)(Math.random() * WIDTH), (int)(Math.random() * HEIGHT));
			}
		}
		rooms = new Room[HEIGHT][WIDTH];
		generateRooms();
	}
	
	//Start is set to the Point2D param
	public Floor(Point2D start)
	{
		this.start = start;
		rooms = new Room[HEIGHT][WIDTH];
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
	/*
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
			distance[0] = Math.abs(neighbors[0].distance(end));
			distance[1] = Math.abs(neighbors[1].distance(end));
			distance[2] = Math.abs(neighbors[2].distance(end));
			distance[3] = Math.abs(neighbors[3].distance(end));
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
	}*/
	
	private void generateRooms()
	{
		rooms[(int) start.getX()][(int) start.getY()] = new Room();
		ArrayList<Point2D> firstGen = new ArrayList<Point2D>();
		ArrayList<Point2D> secondGen = new ArrayList<Point2D>();
		ArrayList<Point2D> thirdGen = new ArrayList<Point2D>();
		ArrayList<Point2D> fourthGen = new ArrayList<Point2D>();
		ArrayList<Point2D> fifthGen = new ArrayList<Point2D>();
		ArrayList<Point2D> sixthGen = new ArrayList<Point2D>();
		if(start.getX() > 0 && start.getX() < 5 && start.getX() > 0 && start.getX() < 5){
		if(start.getX() > 0 && start.getX() < 5 && start.getX() > 0 && start.getX() < 5){
			firstGen.add(makeNorth(start));
		}
		if(start.getX() > 0 && start.getX() < 5 && start.getX() > 0 && start.getX() < 5){
			firstGen.add(makeEast(start));
		}
		if(start.getX() > 0 && start.getX() < 5 && start.getX() > 0 && start.getX() < 5){
			firstGen.add(makeSouth(start));
		}
		if(start.getX() > 0 && start.getX() < 5 && start.getX() > 0 && start.getX() < 5){
			firstGen.add(makeWest(start));
		}
		
		for(int i = 0; i < firstGen.size(); i++){
			int r1 = (int)(Math.random() * 4);
			switch(r1){
				case 0: 
					if(firstGen.get(i).getX() > 1 && firstGen.get(i).getX() < 4 && firstGen.get(i).getY() > 1 && firstGen.get(i).getY() < 4){
						secondGen.add(makeNorth(firstGen.get(i)));
					}
					break;
				case 1:
					if(firstGen.get(i).getX() > 1 && firstGen.get(i).getX() < 4 && firstGen.get(i).getY() > 1 && firstGen.get(i).getY() < 4){
						secondGen.add(makeEast(firstGen.get(i)));
					}
					break;
				case 2: 
					if(firstGen.get(i).getX() > 1 && firstGen.get(i).getX() < 4 && firstGen.get(i).getY() > 1 && firstGen.get(i).getY() < 4){
						secondGen.add(makeSouth(firstGen.get(i)));
					}
					break;
				case 3:
					if(firstGen.get(i).getX() > 1 && firstGen.get(i).getX() < 4 && firstGen.get(i).getY() > 1 && firstGen.get(i).getY() < 4){
						secondGen.add(makeWest(firstGen.get(i)));
					}
					break;
			}
			for(int j = 0; j < secondGen.size(); j++){
				int r2 = (int)(Math.random() * 4);
				switch(r2){
					case 0: 
						if(secondGen.get(j).getX() > 1 && secondGen.get(j).getX() < 4 && secondGen.get(j).getY() > 1 && secondGen.get(j).getY() < 4){
							thirdGen.add(makeNorth(secondGen.get(j)));
						}
						break;
					case 1: 
						if(secondGen.get(j).getX() > 1 && secondGen.get(j).getX() < 4 && secondGen.get(j).getY() > 1 && secondGen.get(j).getY() < 4){
							thirdGen.add(makeEast(secondGen.get(j)));
						}
						break;
					case 2: 
						if(secondGen.get(j).getX() > 1 && secondGen.get(j).getX() < 4 && secondGen.get(j).getY() > 1 && secondGen.get(j).getY() < 4){
							thirdGen.add(makeSouth(secondGen.get(j)));
						}
						break;
					case 3: 
						if(secondGen.get(j).getX() > 1 && secondGen.get(j).getX() < 4 && secondGen.get(j).getY() > 1 && secondGen.get(j).getY() < 4){
							thirdGen.add(makeWest(secondGen.get(j)));
						}
						break;
				}
				for(int k = 0; k < thirdGen.size(); k++){
					int r3 = (int)(Math.random() * 4);
					switch(r3){
						case 0: 
							if(thirdGen.get(k).getX() > 1 && thirdGen.get(k).getX() < 4 && thirdGen.get(k).getY() > 1 && thirdGen.get(k).getY() < 4){
								fourthGen.add(makeNorth(thirdGen.get(k)));
							}
							break;
						case 1: 
							if(thirdGen.get(k).getX() > 1 && thirdGen.get(k).getX() < 4 && thirdGen.get(k).getY() > 1 && thirdGen.get(k).getY() < 4){
								fourthGen.add(makeEast(thirdGen.get(k)));
							}
							break;
						case 2: 
							if(thirdGen.get(k).getX() > 1 && thirdGen.get(k).getX() < 4 && thirdGen.get(k).getY() > 1 && thirdGen.get(k).getY() < 4){
								fourthGen.add(makeSouth(thirdGen.get(k)));
							}
							break;
						case 3:
							if(thirdGen.get(k).getX() > 1 && thirdGen.get(k).getX() < 4 && thirdGen.get(k).getY() > 1 && thirdGen.get(k).getY() < 5){
								fourthGen.add(makeWest(thirdGen.get(k)));
							}
							break;
					}
				}
			}
		}
	}
}
	
	private Point2D makeNorth(Point2D curRoom)
	{
		int x = (int)curRoom.getX();
		int y = (int)curRoom.getY();
		boolean[] initDoors = new boolean[]{false, false, true, false};
		if(rooms[x][y-1] == null){
			rooms[x][y-1] = new Room(initDoors);
		}
		else
		{
			rooms[x][y-1].addDoors(initDoors);
		}
		rooms[x][y].addDoors(new boolean[]{true, false, false, false});
		return new Point2D.Double(x, y-1);
	}
	
	private Point2D makeEast(Point2D curRoom)
	{
		int x = (int)curRoom.getX();
		int y = (int)curRoom.getY();
		boolean[] initDoors = new boolean[]{false, false, false, true};
		if(rooms[x+1][y] == null)
		{
		rooms[x+1][y] = new Room(initDoors);
		}
		else
		{
			rooms[x+1][y].addDoors(initDoors);
		}
		rooms[x][y].addDoors(new boolean[]{false, true, false, false});
		return new Point2D.Double(x+1,y);
	}
	
	private Point2D makeSouth(Point2D curRoom)
	{
		int x = (int)curRoom.getX();
		int y = (int)curRoom.getY();
		boolean[] initDoors = new boolean[]{true, false, false, false};
		if(rooms[x][y+1] == null)
		{
			rooms[x][y+1] = new Room(initDoors);
		}
		else
		{
			rooms[x][y+1].addDoors(initDoors);
		}
		rooms[x][y].addDoors(new boolean[]{false, false, true, false});
		return new Point2D.Double(x, y+1);
	}
	
	private Point2D makeWest(Point2D curRoom)
	{
		int x = (int)curRoom.getX();
		int y = (int)curRoom.getY();
		boolean[] initDoors = new boolean[]{false, true, false, false};
		if(rooms[x-1][y] == null)
		{
			rooms[x-1][y] = new Room(initDoors);
		}
		else
		{
			rooms[x-1][y].addDoors(initDoors);
		}
		rooms[x][y].addDoors(new boolean[]{false, false, false, true});
		return new Point2D.Double(x-1,y);
	}
}