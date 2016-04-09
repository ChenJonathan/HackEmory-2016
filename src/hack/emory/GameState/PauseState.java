package hack.emory.GameState;

import hack.emory.Manager.Content;
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
		g.drawImage(Content.getImage(Content.PAUSED), 0, 0, null);
	}

	@Override
	public void handleInput()
	{
		if (Input.instance.mouseLeftRelease()) { // onclick
			if (Input.instance.mouseInRect(735, 517, 918 - 735, 577 - 517)) {
				gsm.setPaused(false);
			} else if (Input.instance.mouseInRect(1101, 516, 1206 - 1101, 570 - 516)) {
				gsm.setPaused(false);
				gsm.setState(GameStateManager.MENU);
			}
		}
	}
}