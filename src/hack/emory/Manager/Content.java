package hack.emory.Manager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

/**
 * This class is the content manager for Indigo. Call {@link Content#getImage(ImageData)},
 * {@link Content#getAnimation(AnimationData)}, or {@link Content#getSound(SoundData)} with the respective
 * static image or animation data to get and cache the content, if it hasn't already. <br>
 * <br>
 * Call {@link #dispose()} whenever the stage is changed. To prevent RAM hogging.<br>
 * <br>
 * Conventions should dictate that {@link #dispose()} can be called at any given time, so images and animations should
 * be reattained from the cache every frame.
 */
public class Content
{
	// Cursor
	public static ImageData CURSOR = new ImageData("/images/cursor/cursor.png", 32, 32);

	// HUD

	// Menus
	public static ImageData MENU_BACKGROUND = new ImageData("/images/menus/menu_background.png", 1920, 1080);
	
	// Sprites
	public static AnimationData PLAYER_MOVE_UP = new AnimationData("/images/sprites/player/move_up.png", 128, 128, 4);
	public static AnimationData PLAYER_MOVE_DOWN = new AnimationData("/images/sprites/player/move_down.png", 128, 128, 4);
	public static AnimationData PLAYER_MOVE_LEFT = new AnimationData("/images/sprites/player/move_left.png", 128, 128, 4);
	public static AnimationData PLAYER_MOVE_RIGHT = new AnimationData("/images/sprites/player/move_right.png", 128, 128, 4);

	// Storage
	private static HashMap<ImageData, BufferedImage> imageMap;
	private static HashMap<AnimationData, BufferedImage[]> animationMap;
	private static HashMap<SoundData, byte[]> soundMap;

	static
	{
		imageMap = new HashMap<>();
		animationMap = new HashMap<>();
		soundMap = new HashMap<>();
	}

	private static class ImageData
	{
		private String path;
		private int width, height;

		private ImageData(String path, int width, int height)
		{
			this.path = path;
			this.width = width;
			this.height = height;
		}
	}

	private static class AnimationData
	{
		private String path;
		private int width, height, frames;

		private AnimationData(String path, int width, int height, int frames)
		{
			this.path = path;
			this.width = width;
			this.height = height;
			this.frames = frames;
		}
	}

	static class SoundData
	{
		private String path;
		private boolean doesLoop;

		private SoundData(String path, boolean doesLoop)
		{
			this.path = path;
			this.doesLoop = doesLoop;
		}

		public String path()
		{
			return path;
		}

		public boolean doesLoop()
		{
			return doesLoop;
		}
	}

	/**
	 * @param ad The ImageData to retrieve the image from. Should be attained from {@link Content}.
	 * @return A BufferedImage composing the requested image.
	 */
	public static BufferedImage getImage(ImageData id)
	{
		BufferedImage img = imageMap.get(id);
		if(img != null)
		{
			return img;
		}
		img = load(id);
		imageMap.put(id, img);
		return img;
	}

	/**
	 * @param ad The AnimationData to retrieve the animation from. Should be attained from {@link Content}.
	 * @return A BufferedImage[] composing the requested animation.
	 */
	public static BufferedImage[] getAnimation(AnimationData ad)
	{
		BufferedImage[] ani = animationMap.get(ad);
		if(ani != null)
		{
			return ani;
		}
		ani = load(ad);
		animationMap.put(ad, ani);
		return ani;
	}

	/**
	 * @param sd The SoundData to retrieve the sound from. Should be attained from {@link Content}
	 * @return A byte array containing the requested sound data.
	 */
	public static byte[] getSound(SoundData sd)
	{
		byte[] snd = soundMap.get(sd);
		if(snd != null)
		{
			return snd;
		}
		snd = load(sd);
		soundMap.put(sd, snd);
		return snd;
	}

	private static BufferedImage load(ImageData id)
	{
		return load(id.path, id.width, id.height);
	}

	private static BufferedImage load(String path, int width, int height)
	{
		BufferedImage img;
		try
		{
			img = ImageIO.read(new File(new File("").getAbsolutePath() + "/resources" + path));
			img = img.getSubimage(0, 0, width, height);
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

	private static BufferedImage[] load(AnimationData ad)
	{
		BufferedImage[] img = new BufferedImage[ad.frames];
		BufferedImage sheet = load(ad.path, ad.width * ad.frames, ad.height);
		try
		{
			for(int i = 0; i < ad.frames; i++)
			{
				img[i] = sheet.getSubimage(i * ad.width, 0, ad.width, ad.height);
			}
			return img;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading graphics.");
			System.exit(0);
		}
		return null;
	}

	private static byte[] load(SoundData sd)
	{
		try
		{
			byte[] snd = new byte[0];
			String filePath = new File("").getAbsolutePath() + "/resources" + sd.path;
			File AudioFile = new File(filePath);
			AudioInputStream ais = AudioSystem.getAudioInputStream(AudioFile);
			AudioFormat Format = ais.getFormat();
			snd = new byte[(int)(ais.getFrameLength() * Format.getFrameSize())];
			ais.read(snd, 0, snd.length);
			ais.close();
			return snd;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Error loading sounds.");
			System.exit(0);
		}
		return null;
	}

	/**
	 * Clears the image, animation, and sound cache. To be called between stages, but should conventionally be able to
	 * be called at any time.
	 */
	public static void dispose()
	{
		imageMap.clear();
		animationMap.clear();
		soundMap.clear();
	}
}