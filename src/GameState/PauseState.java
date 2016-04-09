package GameState;

import java.awt.Graphics2D;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Input;
import Manager.Manager;

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
		// Draw pause menu and buttons
		g.drawImage(Content.getImage(Content.MENU_BACKGROUND), 0, 0, null);
		g.drawImage(Content.getImage(Content.PAUSE), 675, 150, null);
		if(Manager.input.mouseInRect(373, 500, 358, 106))
		{
			g.drawImage(Content.getImage(Manager.input.mouseLeftDown()? Content.GLOW_RECTANGLE_CLICK
					: Content.GLOW_RECTANGLE_HOVER), 343, 470, null);
		}
		else if(Manager.input.mouseInRect(781, 500, 358, 106))
		{
			g.drawImage(Content.getImage(Manager.input.mouseLeftDown()? Content.GLOW_RECTANGLE_CLICK
					: Content.GLOW_RECTANGLE_HOVER), 751, 470, null);
		}
		else if(Manager.input.mouseInRect(1189, 500, 358, 106))
		{
			g.drawImage(Content.getImage(Manager.input.mouseLeftDown()? Content.GLOW_RECTANGLE_CLICK
					: Content.GLOW_RECTANGLE_HOVER), 1159, 470, null);
		}
		g.drawImage(Content.getImage(Content.BUTTON_RESUME), 373, 500, null);
		g.drawImage(Content.getImage(Content.BUTTON_OPTIONS), 781, 500, null);
		g.drawImage(Content.getImage(Content.BUTTON_QUIT), 1189, 500, null);
	}

	@Override
	public void handleInput()
	{
		if(Manager.input.mouseLeftRelease())
		{
			if(Manager.input.mouseInRect(373, 500, 358, 106))
			{
				gsm.setPaused(false);
			}
			else if(Manager.input.mouseInRect(1189, 500, 358, 106))
			{
				gsm.setState(GameStateManager.MENU);
				gsm.setPaused(false);
			}
		}
		if(Manager.input.keyPress(Input.ESCAPE))
		{
			gsm.setPaused(false);
		}
	}
}