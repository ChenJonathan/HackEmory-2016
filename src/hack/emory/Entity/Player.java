package hack.emory.Entity;

import java.awt.Graphics2D;

import hack.emory.GameState.PlayState;
import hack.emory.Manager.Content;

public class Player extends Entity
{
	// Animations
	private final int ATTACK_UP = 0;
	private final int ATTACK_DOWN = 1;
	private final int ATTACK_LEFT = 2;
	private final int ATTACK_RIGHT = 3;
	private final int IDLE_UP = 4;
	private final int IDLE_DOWN = 5;
	private final int IDLE_LEFT = 6;
	private final int IDLE_RIGHT = 7;
	private final int MOVE_UP = 8;
	private final int MOVE_DOWN = 9;
	private final int MOVE_LEFT = 10;
	private final int MOVE_RIGHT = 11;

	public static final int WIDTH = 128;
	public static final int HEIGHT = 128;
	
	public static final double MAX_SPEED = 4;
	public static final double ACCELERATION = 2;
	
	public static final int BASE_HEALTH = 100;
	
	public Player(PlayState ps, double x, double y, int health)
	{
		super(ps, x, y, WIDTH, HEIGHT, health);
		
		direction = Direction.RIGHT;
		setAnimation(MOVE_RIGHT, Content.getAnimation(Content.PLAYER_MOVE_RIGHT), 10);
	}
	
	public void update()
	{
		super.update();
		if (velY == 0 && velX ==0) {
			switch(direction)
			{
				case UP:
					setAnimation(IDLE_UP, Content.getAnimation(Content.PLAYER_IDLE_UP), 10);
					break;
				case DOWN:
					setAnimation(IDLE_DOWN, Content.getAnimation(Content.PLAYER_IDLE_DOWN), 10);
					break;
				case LEFT:
					setAnimation(IDLE_LEFT, Content.getAnimation(Content.PLAYER_IDLE_LEFT), 10);
					break;
				case RIGHT:
					setAnimation(IDLE_RIGHT, Content.getAnimation(Content.PLAYER_IDLE_RIGHT), 10);
					break;
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(animation.getImage(), (int) getX() - getWidth() / 2, (int) getY() - getHeight() / 2, null);
	}
	
	public void attack()
	{
		switch(direction)
		{
			case UP:
				setAnimation(MOVE_UP, Content.getAnimation(Content.PLAYER_MOVE_UP), 10);
				break;
			case DOWN:
				velY = Math.min(velY + ACCELERATION, MAX_SPEED);
				if(velX == 0)
				{
					setAnimation(MOVE_DOWN, Content.getAnimation(Content.PLAYER_MOVE_DOWN), 10);
				}
				break;
			case LEFT:
				velX = Math.max(velX - ACCELERATION, -MAX_SPEED);
				setAnimation(MOVE_LEFT, Content.getAnimation(Content.PLAYER_MOVE_LEFT), 10);
				break;
			case RIGHT:
				velX = Math.min(velX + ACCELERATION, MAX_SPEED);
				setAnimation(MOVE_RIGHT, Content.getAnimation(Content.PLAYER_MOVE_RIGHT), 10);
				break;
		}
	}
	
	public void move(Direction direction)
	{
		this.direction = direction;
		switch(direction)
		{
			case UP:
				velY = Math.max(velY - ACCELERATION, -MAX_SPEED);
				if(velX == 0)
				{
					setAnimation(MOVE_UP, Content.getAnimation(Content.PLAYER_MOVE_UP), 10);
				}
				break;
			case DOWN:
				velY = Math.min(velY + ACCELERATION, MAX_SPEED);
				if(velX == 0)
				{
					setAnimation(MOVE_DOWN, Content.getAnimation(Content.PLAYER_MOVE_DOWN), 10);
				}
				break;
			case LEFT:
				velX = Math.max(velX - ACCELERATION, -MAX_SPEED);
				setAnimation(MOVE_LEFT, Content.getAnimation(Content.PLAYER_MOVE_LEFT), 10);
				break;
			case RIGHT:
				velX = Math.min(velX + ACCELERATION, MAX_SPEED);
				setAnimation(MOVE_RIGHT, Content.getAnimation(Content.PLAYER_MOVE_RIGHT), 10);
				break;
		}
	}
}