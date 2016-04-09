package hack.emory.GameState;

import hack.emory.Entity.Entity;
import hack.emory.Entity.Player;
import hack.emory.Main.Game;
import hack.emory.Manager.Content;
import hack.emory.Manager.GameStateManager;
import hack.emory.Manager.Input;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * The state where the player is actively playing the game. Handles Manager.inputs (skill use) and sends the info to the
 * HUD, Stage, and Phase objects.
 */
public class PlayState extends GameState
{
	private int time;
	
	private Player player;
	private ArrayList<Entity> entities;
	
	public static final double FRICTION = 1;

	/**
	 * Sets up the play state and initializes stage, stage objects, phases, display, and timer.
	 * 
	 * @param gsm The game state manager.
	 */
	public PlayState(GameStateManager gsm)
	{
		super(gsm);

		// Initialize stage TODO
		player = new Player(this, Game.WIDTH / 2, Game.HEIGHT / 2, Player.BASE_HEALTH);
		entities = new ArrayList<Entity>();
		entities.add(player);

		// Initialize timer
		time = -1;
	}

	@Override
	public void update()
	{
		handleInput();

		// Increment timer
		time++;
		
		// Update entities
		for(Entity entity : entities)
		{
			entity.update();
			
			// Wall collisions
			entity.setX(Math.min(Math.max(entity.getX(), entity.getWidth() / 2), Game.WIDTH - entity.getWidth() / 2));
			entity.setY(Math.min(Math.max(entity.getY(), entity.getWidth() / 2), Game.HEIGHT - entity.getWidth() / 2));
		}
	}

	@Override
	public void render(Graphics2D g)
	{
		g.drawImage(Content.getImage(Content.MENU_BACKGROUND), 0, 0, null);
		
		// Render entities
		for(Entity entity : entities)
		{
			entity.render(g);
		}
	}

	/**
	 * Handles all of the player Manager.input during play. Relays skillcasting information to both stage and HUD. Also
	 * checks for pause, class switch, etc.
	 */
	public void handleInput()
	{
		// Movement
		if(Input.instance.keyDown(Input.A))
		{
			player.move(Entity.Direction.LEFT);
		}
		if(Input.instance.keyDown(Input.D))
		{
			player.move(Entity.Direction.RIGHT);
		}
		if(Input.instance.keyDown(Input.W))
		{
			player.move(Entity.Direction.UP);
		}
		if(Input.instance.keyDown(Input.S))
		{
			player.move(Entity.Direction.DOWN);
		}
	}

	/**
	 * @return The current game time.
	 */
	public int getTime()
	{
		return time;
	}
}