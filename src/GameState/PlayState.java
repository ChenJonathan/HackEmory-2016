package GameState;

import java.awt.Graphics2D;

import Manager.GameStateManager;

/**
 * The state where the player is actively playing the game. Handles Manager.inputs (skill use) and sends the info to the
 * HUD, Stage, and Phase objects.
 */
public class PlayState extends GameState
{
	private int time;

	/**
	 * Sets up the play state and initializes stage, stage objects, phases, display, and timer.
	 * 
	 * @param gsm The game state manager.
	 */
	public PlayState(GameStateManager gsm)
	{
		super(gsm);

		// Initialize stage TODO

		// Initialize timer
		time = -1;
	}

	@Override
	public void update()
	{
		handleInput();

		time++;
	}

	@Override
	public void render(Graphics2D g)
	{
		
	}

	/**
	 * Handles all of the player Manager.input during play. Relays skillcasting information to both stage and HUD. Also
	 * checks for pause, class switch, etc.
	 */
	public void handleInput()
	{
		
	}

	/**
	 * @return The current game time.
	 */
	public int getTime()
	{
		return time;
	}
}