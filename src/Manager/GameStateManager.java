package Manager;

import java.awt.Graphics2D;

import GameState.GameState;
import GameState.MenuState;
import GameState.PauseState;
import GameState.PlayState;

/**
 * Manages the state of the game. Game state determines what is currently being updated and rendered.
 */
public class GameStateManager
{
	private Data data;

	private boolean paused;
	private PauseState pauseState;

	private GameState currentState;

	public static final int MENU = 0;
	public static final int PLAY = 1;

	/**
	 * Sets up the GameManager with the current game instance.
	 * 
	 * @param game The current game session.
	 */
	public GameStateManager()
	{
		data = new Data();

		paused = false;
		pauseState = new PauseState(this);

		setState(MENU);
	}

	/**
	 * Changes the game state and removes the previous state.
	 * 
	 * @param state The new game state.
	 */
	public void setState(int state)
	{
		currentState = null;
		Content.dispose();
		if(state == MENU)
		{
			currentState = new MenuState(this);
		}
		else if(state == PLAY)
		{
			currentState = new PlayState(this);
		}
	}

	/**
	 * Pauses and unpauses the game.
	 * 
	 * @param paused Whether the game is paused.
	 */
	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}

	/**
	 * Delegates game updates to current game state. Priority is given to "temporary" game states.
	 */
	public void update()
	{
		if(paused)
		{
			pauseState.update();
		}
		else if(currentState != null)
		{
			currentState.update();
		}
	}

	/**
	 * Renders the current game state. Priority is given to "temporary" game states.
	 * 
	 * @param g The graphics to be rendered.
	 */
	public void render(Graphics2D g)
	{
		if(paused)
		{
			pauseState.render(g);
		}
		else if(currentState != null)
		{
			currentState.render(g);
		}
	}

	/**
	 * Relays Data object to GameState objects.
	 * 
	 * @return The data to be relayed.
	 */
	public Data getData()
	{
		return data;
	}
}