package hack.emory.GameState;

import hack.emory.Manager.GameStateManager;
import hack.emory.Manager.Input;

import java.awt.Graphics2D;

/**
 * The state where the game is paused. Accessible during play.
 */
public class PauseState extends GameState
{
	/**
	 * Sets up the paused game state.
	 * 
	 * @param gsm The game state manager.
	 */
	public PauseState(GameStateManager gsm)
	{
		super(gsm);
	}

	@Override
	public void update()
	{
		handleInput();
	}

	@Override
	public void render(Graphics2D g)
	{
		
	}

	@Override
	public void handleInput()
	{
		if(Input.instance.keyPress(Input.ESCAPE))
		{
			gsm.setPaused(false);
		}
	}
}