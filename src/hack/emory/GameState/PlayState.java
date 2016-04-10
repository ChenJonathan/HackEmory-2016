package hack.emory.GameState;

import hack.emory.Entity.Entity;
import hack.emory.Entity.Monster;
import hack.emory.Entity.Player;
import hack.emory.Floor.Floor;
import hack.emory.Floor.Room;
import hack.emory.Main.Game;
import hack.emory.Manager.Content;
import hack.emory.Manager.Data;
import hack.emory.Manager.GameStateManager;
import hack.emory.Manager.Input;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PlayState extends GameState
{
	private int kills;
	private int time;
	
	int roomX;
	int roomY;
	private Floor floor;
	
	private Player player;
	private ArrayList<Entity> entities;
	
	private boolean minimap;
	
	public static final int DOOR_LENGTH = 150;
	
	public static final int END_RADIUS = 25;
	
	public static final int ROOM_LENGTH = 80;
	public static final int CORRIDOR_LENGTH = 5;
	public static final int ROOM_PADDING = 10;
	public static final int ROOM_TOTAL_LENGTH = ROOM_LENGTH + ROOM_PADDING * 2;
	public static final int NUM_ROOMS = 5;

	public static final int MAP_X = Game.WIDTH / 2;
	public static final int MAP_Y = Game.HEIGHT / 2;
	public static final int MAP_LENGTH = ROOM_TOTAL_LENGTH *NUM_ROOMS;

	public static final double FRICTION = 0.5;

	public PlayState(GameStateManager gsm)
	{
		super(gsm);

		// Initialize stage
		floor = new Floor();
		roomX = (int) floor.getStart().getX();
		roomY = (int) floor.getStart().getY();
		player = new Player(this, Game.WIDTH / 2, Game.HEIGHT / 2, Player.BASE_HEALTH);
		entities = new ArrayList<Entity>();
		entities.add(player);
		minimap = Data.getMinimap();

		// Initialize timer
		kills = Data.getKills();
		time = Data.getTime();
	}

	@Override
	public void update()
	{
		// Increment timer
		time++;
		
		// Spawn entities
		if(time % 500 == 0)
		{
			Monster monster = new Monster(this, Math.random() * (Game.WIDTH - Monster.WIDTH) + Monster.WIDTH / 2,
					Math.random() * (Game.HEIGHT - Monster.HEIGHT) + Monster.HEIGHT / 2, Monster.BASE_HEALTH);
			entities.add(monster);
		}
		
		// Update entities
		for(int i = 0; i < entities.size(); i++)
		{
			Entity entity = entities.get(i);
			entity.update();
			
			if(entity.getHealth() > 0)
			{
				// Wall collisions
				entity.setX(Math.min(Math.max(entity.getX(), entity.getWidth() / 2), Game.WIDTH - entity.getWidth() / 2));
				entity.setY(Math.min(Math.max(entity.getY(), entity.getHeight() / 2), Game.HEIGHT - entity.getHeight() / 2));
			}
		}
		
		handleInput();
	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(Content.getImage(Content.LEVEL_BACKGROUND), 0, 0, null);
		
		// Render entities
		for(Entity entity : entities)
		{
			entity.render(g);
		}
		
		// HUD
		g.setFont(new Font("Garamond", Font.BOLD, 36));
		g.setColor(Color.BLACK);
		g.drawString("Level: " + Data.getLevel(), 22, 52);
		g.drawString("Kills: " + kills, 22, 102);
		g.drawString("Time: " + (time / 60), 22, 152);
		g.setColor(Color.WHITE);
		g.drawString("Level: " + Data.getLevel(), 20, 50);
		g.drawString("Kills: " + kills, 20, 100);
		g.drawString("Time: " + (time / 60), 20, 150);
		if(minimap)
		{
			g.drawRect(MAP_X - MAP_LENGTH / 2, MAP_Y - MAP_LENGTH / 2, MAP_LENGTH, MAP_LENGTH);
			for(int x = -2; x < 3; x++)
			{
				for(int y = -2; y < 3; y++)
				{
					if(roomX + x < 0 || roomX + x >= Floor.WIDTH || roomY + y < 0 || roomY + y >= Floor.HEIGHT)
					{
						continue;
					}
					
					Room room = floor.getRoom(roomX + x, roomY + y);
					if(room != null)
					{
						g.drawRect(MAP_X + x * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2,
								   MAP_Y + y * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2,
								   ROOM_LENGTH, ROOM_LENGTH);
						if(room.getDoor(Entity.Direction.UP))
						{
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2, 
									MAP_X + x * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2 - ROOM_PADDING);
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2, 
									MAP_X + x * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2 - ROOM_PADDING);
						}
						if(room.getDoor(Entity.Direction.DOWN))
						{
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2, 
									MAP_X + x * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2 + ROOM_PADDING);
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2, 
									MAP_X + x * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, MAP_Y + y * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2 + ROOM_PADDING);
						}
						if(room.getDoor(Entity.Direction.LEFT))
						{
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2, MAP_Y + y * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, 
									MAP_X + x * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2 - ROOM_PADDING, MAP_Y + y * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH);
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2, MAP_Y + y * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, 
									MAP_X + x * ROOM_TOTAL_LENGTH - ROOM_LENGTH / 2 - ROOM_PADDING, MAP_Y + y * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH);
						}
						if(room.getDoor(Entity.Direction.RIGHT))
						{
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2, MAP_Y + y * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH, 
									MAP_X + x * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2 + ROOM_PADDING, MAP_Y + y * ROOM_TOTAL_LENGTH - CORRIDOR_LENGTH);
							g.drawLine(MAP_X + x * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2, MAP_Y + y * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH, 
									MAP_X + x * ROOM_TOTAL_LENGTH + ROOM_LENGTH / 2 + ROOM_PADDING, MAP_Y + y * ROOM_TOTAL_LENGTH + CORRIDOR_LENGTH);
						}
						if(new Point2D.Double(roomX + x, roomY + y).equals(floor.getEnd()))
						{
					        g.fill(new Ellipse2D.Double(MAP_X + x * ROOM_TOTAL_LENGTH - END_RADIUS, MAP_Y + y * ROOM_TOTAL_LENGTH - END_RADIUS, 2 * END_RADIUS, 2 * END_RADIUS));
						}
					}
				}
			}
		}
	}

	public void handleInput()
	{
		// Movement
		if(Input.instance.keyDown(Input.W))
		{
			player.move(Entity.Direction.UP);
		}
		if(Input.instance.keyDown(Input.S))
		{
			player.move(Entity.Direction.DOWN);
		}
		if(Input.instance.keyDown(Input.A))
		{
			player.move(Entity.Direction.LEFT);
		}
		if(Input.instance.keyDown(Input.D))
		{
			player.move(Entity.Direction.RIGHT);
		}
		
		// Action
		if(Input.instance.keyPress(Input.SHIFT))
		{
			minimap = !minimap;
		}
		if(Input.instance.keyPress(Input.SPACE))
		{
			boolean attack = true;
			switch(player.getDirection())
			{
				case UP:
					if(floor.getRoom(roomX, roomY).getDoor(Entity.Direction.UP) 
							&& player.getX() > Game.WIDTH / 2 - DOOR_LENGTH / 2
							&& player.getX() < Game.WIDTH / 2 + DOOR_LENGTH / 2
							&& player.getY() == player.getHeight() / 2)
					{
						player.setY(Game.HEIGHT - player.getHeight() / 2);
						roomY--;
						attack = false;
					}
					break;
				case DOWN:
					if(floor.getRoom(roomX, roomY).getDoor(Entity.Direction.DOWN) 
							&& player.getX() > Game.WIDTH / 2 - DOOR_LENGTH / 2
							&& player.getX() < Game.WIDTH / 2 + DOOR_LENGTH / 2
							&& player.getY() == Game.HEIGHT - player.getHeight() / 2)
					{
						player.setY(player.getHeight() / 2);
						roomY++;
						attack = false;
					}
					break;
				case LEFT:
					if(floor.getRoom(roomX, roomY).getDoor(Entity.Direction.LEFT) 
							&& player.getY() > Game.HEIGHT / 2 - DOOR_LENGTH / 2
							&& player.getY() < Game.HEIGHT / 2 + DOOR_LENGTH / 2
							&& player.getX() == player.getWidth() / 2)
					{
						player.setX(Game.WIDTH - player.getWidth() / 2);
						roomX--;
						attack = false;
					}
					break;
				case RIGHT:
					if(floor.getRoom(roomX, roomY).getDoor(Entity.Direction.RIGHT) 
							&& player.getY() > Game.HEIGHT / 2 - DOOR_LENGTH / 2
							&& player.getY() < Game.HEIGHT / 2 + DOOR_LENGTH / 2
							&& player.getX() == Game.WIDTH - player.getWidth() / 2)
					{
						player.setX(player.getWidth() / 2);
						roomX++;
						attack = false;
					}
					break;
			}
			if(attack)
			{
				player.attack();
			}
			else
			{
				while(entities.size() > 1)
				{
					entities.remove(entities.size() - 1);
				}
				if(new Point2D.Double(roomX, roomY).equals(floor.getEnd()))
				{
					Data.setLevel(Data.getLevel() + 1);
					Data.setKills(kills);
					Data.setTime(time);
					Data.setMinimap(minimap);
					gsm.setState(GameStateManager.PLAY);
				}
			}
		}
		
		if(Input.instance.keyPress(Input.ESCAPE))
		{
			gsm.setPaused(true);
		}
	}
	
	public void incrementKills()
	{
		kills++;
	}
	
	public Player getPlayer()
	{
		return player;
	}
	
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
}