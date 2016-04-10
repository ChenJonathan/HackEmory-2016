package hack.emory.Entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import hack.emory.GameState.PlayState;
import hack.emory.Manager.Content;

public class Monster extends Entity
{
	// Animations
	private final int MOVE_UP = 0;
	private final int MOVE_DOWN = 1;
	private final int MOVE_LEFT = 2;
	private final int MOVE_RIGHT = 3;

	public static final int WIDTH = 128;
	public static final int HEIGHT = 128;
	
	public static final double MAX_SPEED = 3;
	public static final double ACCELERATION = 2;
	
	public static final int BASE_HEALTH = 100;
	
	public Monster(PlayState ps, double x, double y, int health)
	{
		super(ps, x, y, WIDTH, HEIGHT, health);
		
		direction = Direction.RIGHT;
		setAnimation(MOVE_RIGHT, Content.getAnimation(Content.MONSTER_MOVE_RIGHT), 10);
	}
	
	public void update()
	{
		super.update();
		
		double deltaX = ps.getPlayer().getX() - x;
		double deltaY = ps.getPlayer().getY() - y;
		if(Math.abs(deltaX) >= Math.abs(deltaY))
		{
			if(deltaX > 0)
			{
				move(Direction.RIGHT);
			}
			else if(deltaX < 0)
			{
				move(Direction.LEFT);
			}
		}
		else
		{
			if(deltaY > 0)
			{
				move(Direction.DOWN);
			}
			else if(deltaY < 0)
			{
				move(Direction.UP);
			}
		}
	}
	
	public void render(Graphics2D g)
	{
		g.drawImage(animation.getImage(), (int) x - width / 2, (int) y - height / 2, null);
	}
	
	public Shape getHitbox()
	{
		return new Ellipse2D.Double((int) x - width / 2, (int) y - height / 2, width, height);
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
					setAnimation(MOVE_UP, Content.getAnimation(Content.MONSTER_MOVE_UP), 10);
				}
				break;
			case DOWN:
				velY = Math.min(velY + ACCELERATION, MAX_SPEED);
				if(velX == 0)
				{
					setAnimation(MOVE_DOWN, Content.getAnimation(Content.MONSTER_MOVE_DOWN), 10);
				}
				break;
			case LEFT:
				velX = Math.max(velX - ACCELERATION, -MAX_SPEED);
				setAnimation(MOVE_LEFT, Content.getAnimation(Content.MONSTER_MOVE_LEFT), 10);
				break;
			case RIGHT:
				velX = Math.min(velX + ACCELERATION, MAX_SPEED);
				setAnimation(MOVE_RIGHT, Content.getAnimation(Content.MONSTER_MOVE_RIGHT), 10);
				break;
		}
	}
}