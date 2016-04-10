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
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayState extends GameState
{
	private int time;
	
	int roomX;
	int roomY;
	private Floor floor;
	
	private Player player;
	private ArrayList<Entity> entities;
	
	public static final int DOOR_LENGTH = 150;
	
	public static final int ROOM_LENGTH = 80;
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

		// Initialize timer
		time = -1;
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
		for(Entity entity : entities)
		{
			entity.update();
			
			// Wall collisions
			entity.setX(Math.min(Math.max(entity.getX(), entity.getWidth() / 2), Game.WIDTH - entity.getWidth() / 2));
			entity.setY(Math.min(Math.max(entity.getY(), entity.getHeight() / 2), Game.HEIGHT - entity.getHeight() / 2));
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
		g.setColor(Color.WHITE);
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
						// g.drawRect(Game.WIDTH / 2, Game.HEIGHT / 2, 10, MAP_LENGTH);
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
			}
		}
		
		if(Input.instance.keyPress(Input.ESCAPE))
		{
			gsm.setPaused(true);
		}
	}

	public int getTime()
	{
		return time;
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