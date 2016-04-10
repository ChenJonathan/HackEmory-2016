package hack.emory.Entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

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
	public static final int BASE_DAMAGE = 100;
	
	public Player(PlayState ps, double x, double y, int health)
	{
		super(ps, x, y, WIDTH, HEIGHT, health);
		
		direction = Direction.RIGHT;
		setAnimation(MOVE_RIGHT, Content.getAnimation(Content.PLAYER_MOVE_RIGHT), 10);
	}
	
	public void update()
	{
		super.update();
		
		if(attacking())
		{
			if(animation.hasPlayedOnce())
			{
				resetAnimation();
				
				Rectangle2D weaponHitbox = null;
				switch(direction)
				{
					case UP:
						weaponHitbox = new Rectangle2D.Double(x - width / 2, y - height, width, height);
						break;
					case DOWN:
						weaponHitbox = new Rectangle2D.Double(x - width / 2, y, width, height);
						break;
					case LEFT:
						weaponHitbox = new Rectangle2D.Double(x - width, y - height / 2, width, height);
						break;
					case RIGHT:
						weaponHitbox = new Rectangle2D.Double(x, y - height / 2, width, height);
						break;
				}
				
				for(int i = 1; i < ps.getEntities().size(); i++)
				{
					Entity enemy = ps.getEntities().get(i);
					Area areaA = new Area(enemy.getHitbox());
					areaA.intersect(new Area(weaponHitbox));
					if(!areaA.isEmpty())
					{
						enemy.setHealth(enemy.getHealth() - BASE_DAMAGE);
					}
				}
			}
		}
		else if(velX == 0 && velY == 0)
		{
			resetAnimation();
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
	
	public boolean attacking()
	{
		return currentAnimation >= ATTACK_UP && currentAnimation <= ATTACK_RIGHT;
	}
	
	public void attack()
	{
		if(attacking())
		{
			return;
		}
		
		switch(direction)
		{
			case UP:
				setAnimation(ATTACK_UP, Content.getAnimation(Content.PLAYER_ATTACK_UP), 10);
				break;
			case DOWN:
				setAnimation(ATTACK_DOWN, Content.getAnimation(Content.PLAYER_ATTACK_DOWN), 10);
				break;
			case LEFT:
				setAnimation(ATTACK_LEFT, Content.getAnimation(Content.PLAYER_ATTACK_LEFT), 10);
				break;
			case RIGHT:
				setAnimation(ATTACK_RIGHT, Content.getAnimation(Content.PLAYER_ATTACK_RIGHT), 10);
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
				if(!attacking())
				{
					if(velX == 0)
					{
						setAnimation(MOVE_UP, Content.getAnimation(Content.PLAYER_MOVE_UP), 10);
					}
				}
				else if(currentAnimation != ATTACK_UP)
				{
					int frame = animation.getFrame();
					int count = animation.getCount();
					setAnimation(ATTACK_UP, Content.getAnimation(Content.PLAYER_ATTACK_UP), 10);
					animation.setFrame(frame);
					animation.setCount(count);
				}
				break;
			case DOWN:
				velY = Math.min(velY + ACCELERATION, MAX_SPEED);
				if(!attacking())
				{
					if(velX == 0)
					{
						setAnimation(MOVE_DOWN, Content.getAnimation(Content.PLAYER_MOVE_DOWN), 10);
					}
				}
				else if(currentAnimation != ATTACK_DOWN)
				{
					int frame = animation.getFrame();
					int count = animation.getCount();
					setAnimation(ATTACK_DOWN, Content.getAnimation(Content.PLAYER_ATTACK_DOWN), 10);
					animation.setFrame(frame);
					animation.setCount(count);
				}
				break;
			case LEFT:
				velX = Math.max(velX - ACCELERATION, -MAX_SPEED);
				if(!attacking())
				{
					setAnimation(MOVE_LEFT, Content.getAnimation(Content.PLAYER_MOVE_LEFT), 10);
				}
				else if(currentAnimation != ATTACK_LEFT)
				{
					int frame = animation.getFrame();
					int count = animation.getCount();
					setAnimation(ATTACK_LEFT, Content.getAnimation(Content.PLAYER_ATTACK_LEFT), 10);
					animation.setFrame(frame);
					animation.setCount(count);
				}
				break;
			case RIGHT:
				velX = Math.min(velX + ACCELERATION, MAX_SPEED);
				if(!attacking())
				{
					setAnimation(MOVE_RIGHT, Content.getAnimation(Content.PLAYER_MOVE_RIGHT), 10);
				}
				else if(currentAnimation != ATTACK_RIGHT)
				{
					int frame = animation.getFrame();
					int count = animation.getCount();
					setAnimation(ATTACK_RIGHT, Content.getAnimation(Content.PLAYER_ATTACK_RIGHT), 10);
					animation.setFrame(frame);
					animation.setCount(count);
				}
				break;
		}
	}
	
	private void resetAnimation()
	{
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