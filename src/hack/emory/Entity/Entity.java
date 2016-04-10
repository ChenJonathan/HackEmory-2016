package hack.emory.Entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;

import hack.emory.Manager.Animation;
import hack.emory.GameState.PlayState;

public abstract class Entity
{
	protected PlayState ps;
	
	protected double x, y;
	protected double prevX, prevY;
	protected double velX, velY;
	
	protected int width, height;

	protected int health, maxHealth;
	
	public enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction direction;
	
	protected Animation animation;
	protected int currentAnimation;
	
	public Entity(PlayState ps, double x, double y, int width, int height, int health)
	{
		this.ps = ps;
		
		this.x = prevX = x;
		this.y = prevY = y;
		velX = velY = 0;
		
		this.width = width;
		this.height = height;

		maxHealth = this.health = health;
		
		animation = new Animation();
	}
	
	// Standard position updating stuff: gravity, friction, etc
	public void update()
	{
		prevX = x;
		prevY = y;

		x += velX;
		y += velY;

		// Friction
		if(velX < 0)
		{
			velX = Math.min(velX + PlayState.FRICTION, 0);
		}
		else if(velX > 0)
		{
			velX = Math.max(velX - PlayState.FRICTION, 0);
		}
		if(velY < 0)
		{
			velY = Math.min(velY + PlayState.FRICTION, 0);
		}
		else if(velY > 0)
		{
			velY = Math.max(velY - PlayState.FRICTION, 0);
		}
		
		animation.update();
	}
	
	public abstract void render(Graphics2D g);
	
	public abstract Shape getHitbox();
	
	// Method used to change animation
	protected void setAnimation(int count, BufferedImage[] images, int delay)
	{
		if(currentAnimation != count)
		{
			currentAnimation = count;
			animation.setFrames(images);
			animation.setDelay(delay);
		}
	}
	
	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int health)
	{
		this.health = Math.min(Math.max(health, 0), maxHealth);
		if(health == 0)
		{
			ps.getEntities().remove(this);
		}
	}
	
	public Direction getDirection()
	{
		return direction;
	}
}