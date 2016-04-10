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
			start = new Point2D.Double(2,2);
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
	
	public void generateRooms(){
		rooms[(int) start.getX()][(int) start.getY()] = new Room();
		ArrayList<Point2D> firstGen = new ArrayList<Point2D>();
		ArrayList<Point2D> secondGen = new ArrayList<Point2D>();
		ArrayList<Point2D> thirdGen = new ArrayList<Point2D>();
		ArrayList<Point2D> fourthGen = new ArrayList<Point2D>();
		ArrayList<Point2D> fifthGen = new ArrayList<Point2D>();
		ArrayList<Point2D> sixthGen = new ArrayList<Point2D>();
		try{
			firstGen.add(makeNorth(start));
		}
		catch(Exception e){}
		try{
			firstGen.add(makeEast(start));
		}
		catch(Exception e){}
		try{
			firstGen.add(makeSouth(start));
		}
		catch(Exception e){}
		try{
			firstGen.add(makeWest(start));
		}
		catch(Exception e){}
		
		for(int i = 0; i < firstGen.size(); i++){
			int r1 = (int)(Math.random() * 4);
			switch(r1){
				case 0: 
					try{
						secondGen.add(makeNorth(firstGen.get(i)));
					}
					catch(Exception e){}
					break;
				case 1:
					try{
						secondGen.add(makeEast(firstGen.get(i)));
					}
					catch(Exception e){}
					break;
				case 2: 
					try{
						secondGen.add(makeSouth(firstGen.get(i)));
					}
					catch(Exception e){}
					break;
				case 3:
					try{
						secondGen.add(makeWest(firstGen.get(i)));
					}
					catch(Exception e){}
					break;
			}
			for(int j = 0; j < secondGen.size(); j++){
				int r2 = (int)(Math.random() * 4);
				switch(r2){
					case 0: 
						try{
							thirdGen.add(makeNorth(secondGen.get(j)));
						}
						catch(Exception e){}
						break;
					case 1: 
						try{
							thirdGen.add(makeEast(secondGen.get(j)));
						}
						catch(Exception e){}
						break;
					case 2: 
						try{
							thirdGen.add(makeSouth(secondGen.get(j)));
						}
						catch(Exception e){}
						break;
					case 3: 
						try{
							thirdGen.add(makeWest(secondGen.get(j)));
						}
						catch(Exception e){}
						break;
				}
				for(int k = 0; k < thirdGen.size(); k++){
					int r3 = (int)(Math.random() * 4);
					switch(r3){
						case 0: 
							try{
								fourthGen.add(makeNorth(thirdGen.get(k)));
							}
							catch(Exception e){}
							break;
						case 1: 
							try{
								fourthGen.add(makeEast(thirdGen.get(k)));
							}
							catch(Exception e){}
							break;
						case 2: 
							try{
								fourthGen.add(makeSouth(thirdGen.get(k)));
							}
							catch(Exception e){}
							break;
						case 3:
							try{
								fourthGen.add(makeWest(thirdGen.get(k)));
							}
							catch(Exception e){}
							break;
					}
					for(int l = 0; l < fourthGen.size(); l++){
						for(int m = 0; m < 2; m++){
							int r4 = (int)(Math.random() * 4);
							switch(r4){
								case 0: 
									try{
										fifthGen.add(makeNorth(fourthGen.get(l+m)));
									}
									catch(Exception e){}
									break;
								case 1:
									try{
										fifthGen.add(makeEast(fourthGen.get(l+m)));
									}
									catch(Exception e){}
									break;
								case 2:
									try{
										fifthGen.add(makeSouth(fourthGen.get(l+m)));
									}
									catch(Exception e){}
									break;
								case 3:
									try{
										fifthGen.add(makeWest(fourthGen.get(l+m)));
									}
									catch(Exception e){}
									break;
							}
							for(int n = 0; n < fifthGen.size(); n++){
								for(int o = 0; o < 2; o++){
									int r5 = (int)(Math.random() * 4);
									switch(r5){
										case 0:
											try{
												sixthGen.add(makeNorth(fifthGen.get(n+o)));
											}
											catch(Exception e){}
											break;
										case 1:
											try{
												sixthGen.add(makeEast(fifthGen.get(n+o)));
											}
											catch(Exception e){}
											break;
										case 2:
											try{
												sixthGen.add(makeSouth(fifthGen.get(n+o)));
											}
											catch(Exception e){}
											break;
										case 3:
											try{
												sixthGen.add(makeWest(fifthGen.get(n+o)));
											}
											catch(Exception e){}
											break;
									}
									int endRoom = (int)(Math.random() * (sixthGen.size()));
									end = sixthGen.get(endRoom);
//									for(int p = 0; p < sixthGen.size(); p++){
//										int r6 = (int)(Math.random() * 4);
//										switch(r6){
//											case 0:
//												try{
//													makeNorth(sixthGen.get(p));
//												}
//												catch(Exception e){}
//												break;
//											case 1:
//												try{
//													makeEast(sixthGen.get(p));
//												}
//												catch(Exception e){}
//												break;
//											case 2:
//												try{
//													makeSouth(sixthGen.get(p));
//												}
//												catch(Exception e){}
//												break;
//											case 3:
//												try{
//													makeWest(sixthGen.get(p));
//												}
//												catch(Exception e){}
//												break;
//										}
//									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public Point2D makeNorth(Point2D curRoom)
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
	
	public Point2D makeEast(Point2D curRoom)
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
	
	public Point2D makeSouth(Point2D curRoom)
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
	
	public Point2D makeWest(Point2D curRoom)
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