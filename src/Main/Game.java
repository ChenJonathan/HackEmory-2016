package Main;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.*;

import Manager.Content;
import Manager.GameStateManager;
import Manager.Manager;

/**
 * Sets up the application as a game. Mostly does scary swing stuff.
 */
public class Game extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;

	// Logical width and height
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	
	// Display width and height - Change this
	public static int resolutionWidth = 1366;
	public static int resolutionHeight = 768;

	public static final int FPS = 60;

	private final int TARGET_TIME = 1000 / FPS;
	private Thread thread;
	private boolean running = false;

	private BufferedImage image;
	private Graphics2D g;
	private AffineTransform defaultForm;

	private GameStateManager gsm;

	/**
	 * Constructs game panel.
	 */
	public Game()
	{
		setPreferredSize(new Dimension(resolutionWidth, resolutionHeight));
		setFocusable(true);
		requestFocus();

		running = true;
		image = new BufferedImage(resolutionWidth, resolutionHeight, 1);
		g = (Graphics2D)image.getGraphics();
		g.scale((double)resolutionWidth / WIDTH, (double)resolutionHeight / HEIGHT);
		g.clipRect(0, 0, Game.WIDTH, Game.HEIGHT);
		defaultForm = g.getTransform();
		gsm = new GameStateManager();

		setCursor(Content.getImage(Content.CURSOR));
	}

	/**
	 * Called when Game object is added to a container.
	 */
	@Override
	public void addNotify()
	{
		super.addNotify();
		if(thread == null)
		{
			addKeyListener(Manager.input);
			addMouseListener(Manager.input);
			addMouseMotionListener(Manager.input);
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Main game loop that aims for 30 FPS.
	 */
	@Override
	public void run()
	{
		long ticks = 0; // For counter
		long skips = 0; // For counter

		long start;
		long elapsed;
		long wait;

		while(running)
		{
			start = System.currentTimeMillis();

			update();
			render();
			draw();

			elapsed = System.currentTimeMillis() - start;

			wait = TARGET_TIME - elapsed;
			while(wait < 0)
			{
				wait += TARGET_TIME;
				skips++;
				if((ticks + skips) == FPS)
				{
					System.out.println("FPS: " + ticks);
					ticks = skips = 0;
				}
			}

			try
			{
				Thread.sleep(wait);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			ticks++;
			if((ticks + skips) == FPS)
			{
				System.out.println("FPS: " + ticks);
				ticks = skips = 0;
			}
		}
	}

	/**
	 * Updates based on current game state and checks for inputs.
	 */
	private void update()
	{
		gsm.update();
		Manager.input.update();
	}

	/**
	 * Draws based on current game state.
	 */
	private void render()
	{
		gsm.render(g);
		g.setTransform(defaultForm);
	}

	/**
	 * Buffer strategy.
	 */
	private void draw()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, resolutionWidth, resolutionHeight, null);
		g2.dispose();
	}

	/**
	 * Creates the custom cursor to be used.
	 * 
	 * @param image The image for the cursor.
	 */
	public void setCursor(BufferedImage image)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "Cursor");
		setCursor(c);
	}
}