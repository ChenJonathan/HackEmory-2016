package hack.emory.GameState;

import hack.emory.Manager.Content;
import hack.emory.Manager.GameStateManager;
import hack.emory.Manager.Input;

import java.awt.Graphics2D;

/**
 * The state where the main menu is displayed. Present at game startup.
 */
public class MenuState extends GameState {
	private boolean credits;

	/**
	 * Sets up the menu and initializes the button states.
	 * 
	 * @param gsm
	 *            The game state manager.
	 */
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void update() {
		handleInput();
	}

	/**
	 * Displays the background and buttons. Also handles the visual response to
	 * button events.
	 * 
	 * @param g
	 *            The graphics to be rendered.
	 */
	@Override
	public void render(Graphics2D g) {
		if (credits) {
			// Draw credits
			g.drawImage(Content.getImage(Content.CREDITS), 0, 0, null);
			// if(Input.instance.mouseInRect(100, 874, 358, 106))
			// {
			// g.drawImage(ContentManager.getImage(Manager.input.mouseLeftDown()?
			// ContentManager.GLOW_RECTANGLE_CLICK
			// : ContentManager.GLOW_RECTANGLE_HOVER), 70, 844, null);
			// }
			// g.drawImage(ContentManager.getImage(ContentManager.BUTTON_BACK),
			// 100, 874, null);
		} else {
			// Draw main menu
			g.drawImage(Content.getImage(Content.MENU_BACKGROUND), 0, 0, null);
		}
	}

	/**
	 * Handles mouse interactions with the menu buttons.
	 */
	@Override
	public void handleInput() {
		if (credits) {
			// do credits
		} else { // in the menu
			if (Input.instance.mouseLeftRelease()) { // onclick
				if (Input.instance.mouseInRect(355, 766, 552 - 355, 840 - 766)) {
					gsm.setState(GameStateManager.PLAY);
				} else if (Input.instance.mouseInRect(861, 765, 1119 - 861, 837 - 765)) {
					credits = true;
				} else if (Input.instance.mouseInRect(1440, 766, 1575 - 1440, 841 - 766)) {
					System.exit(0);
				}
			}
		}

	}
}