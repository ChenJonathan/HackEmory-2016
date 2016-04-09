package Manager;

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
	public static ImageData HUD_ICE = new ImageData("/images/hud/hud_ice.png", 1920, 135);
	public static ImageData HEALTH_BAR = new ImageData("/images/hud/health_bar.png", 110, 20);
	public static ImageData HUD_WATER = new ImageData("/images/hud/hud_water.png", 1920, 135);
	public static ImageData OVERLAY_CAST = new ImageData("/images/hud/overlay_cast.png", 125, 125);
	public static ImageData OVERLAY_SELECT = new ImageData("/images/hud/overlay_select.png", 125, 125);
	public static ImageData SKILL_GEYSER = new ImageData("/images/hud/skill_geyser.png", 125, 125);
	public static ImageData SKILL_ICE_ARMOR = new ImageData("/images/hud/skill_ice_armor.png", 125, 125);
	public static ImageData SKILL_ICE_CHAINS = new ImageData("/images/hud/skill_ice_chains.png", 125, 125);
	public static ImageData SKILL_LOCKED = new ImageData("/images/hud/skill_locked.png", 125, 125);
	public static ImageData SKILL_MIST = new ImageData("/images/hud/skill_mist.png", 125, 125);
	public static ImageData SKILL_PULSE = new ImageData("/images/hud/skill_pulse.png", 125, 125);
	public static ImageData SKILL_WHIRLWIND = new ImageData("/images/hud/skill_whirlwind.png", 125, 125);
	public static ImageData SPRING = new ImageData("/images/hud/spring.png", 101, 89);
	public static ImageData SPRING_TOP_ICE = new ImageData("/images/hud/spring_top_ice.png", 101, 10);
	public static ImageData SPRING_TOP_WATER = new ImageData("/images/hud/spring_top_water.png", 101, 10);

	// Menus
	public static ImageData ARROW_BIG_LEFT = new ImageData("/images/menus/arrow_big_left.png", 200, 300);
	public static ImageData ARROW_BIG_RIGHT = new ImageData("/images/menus/arrow_big_right.png", 200, 300);
	public static ImageData ARROW_LEFT = new ImageData("/images/menus/arrow_left.png", 40, 60);
	public static ImageData ARROW_RIGHT = new ImageData("/images/menus/arrow_right.png", 40, 60);
	public static ImageData ARROW_LEFT_ACTIVE = new ImageData("/images/menus/arrow_left_active.png", 60, 75);
	public static ImageData ARROW_LEFT_INACTIVE = new ImageData("/images/menus/arrow_left_inactive.png", 60, 75);
	public static ImageData ARROW_RIGHT_ACTIVE = new ImageData("/images/menus/arrow_right_active.png", 60, 75);
	public static ImageData ARROW_RIGHT_INACTIVE = new ImageData("/images/menus/arrow_right_inactive.png", 60, 75);
	public static ImageData BOOK = new ImageData("/images/menus/book.png", 900, 600);
	public static ImageData BUTTON_BACK = new ImageData("/images/menus/button_back.png", 358, 106);
	public static ImageData BUTTON_BLANK = new ImageData("/images/menus/button_blank.png", 400, 106);
	public static ImageData BUTTON_LEVEL = new ImageData("/images/menus/button_level.png", 106, 106);
	public static ImageData BUTTON_CLEAR = new ImageData("/images/menus/button_clear.png", 106, 106);
	public static ImageData BUTTON_CREDITS = new ImageData("/images/menus/button_credits.png", 358, 106);
	public static ImageData BUTTON_EXIT = new ImageData("/images/menus/button_exit.png", 106, 106);
	public static ImageData BUTTON_INSTRUCTIONS = new ImageData("/images/menus/button_instructions.png", 358, 106);
	public static ImageData BUTTON_LEVEL_EDITOR = new ImageData("/images/menus/button_level_editor.png", 358, 106);
	public static ImageData BUTTON_LOAD = new ImageData("/images/menus/button_load.png", 358, 106);
	public static ImageData BUTTON_NEXT = new ImageData("/images/menus/button_next.png", 358, 106);
	public static ImageData BUTTON_OPTIONS = new ImageData("/images/menus/button_options.png", 358, 106);
	public static ImageData BUTTON_PLAY = new ImageData("/images/menus/button_play.png", 358, 106);
	public static ImageData BUTTON_PREVIOUS = new ImageData("/images/menus/button_previous.png", 358, 106);
	public static ImageData BUTTON_QUIT = new ImageData("/images/menus/button_quit.png", 358, 106);
	public static ImageData BUTTON_RESUME = new ImageData("/images/menus/button_resume.png", 358, 106);
	public static ImageData BUTTON_SAVE = new ImageData("/images/menus/button_save.png", 358, 106);
	public static ImageData BUTTON_SAVE_LOAD = new ImageData("/images/menus/button_save_load.png", 358, 106);
	public static ImageData CONFIRM_BUTTON = new ImageData("/images/menus/confirm_button.png", 175, 50);
	public static ImageData CREDITS = new ImageData("/images/menus/credits.png", 1920, 1080);
	public static ImageData DESCRIPTION_BOX = new ImageData("/images/menus/description_box.png", 300, 440);
	public static ImageData GLOW_CIRCLE_CLICK = new ImageData("/images/menus/glow_circle_click.png", 166, 166);
	public static ImageData GLOW_CIRCLE_HOVER = new ImageData("/images/menus/glow_circle_hover.png", 166, 166);
	public static ImageData GLOW_RECTANGLE_CLICK = new ImageData("/images/menus/glow_rectangle_click.png", 418, 166);
	public static ImageData GLOW_RECTANGLE_HOVER = new ImageData("/images/menus/glow_rectangle_hover.png", 418, 166);
	public static ImageData INSTRUCTIONS_CONTROLS = new ImageData("/images/menus/instructions_controls.png", 1720, 880);
	public static ImageData INSTRUCTIONS_OBJECTIVES = new ImageData("/images/menus/instructions_objectives.png", 1720, 880);
	public static ImageData INSTRUCTIONS_SKILLS = new ImageData("/images/menus/instructions_skills.png", 1720, 880);
	public static ImageData MENU_BACKGROUND = new ImageData("/images/menus/menu_background.png", 1920, 1080);
	public static ImageData OPTION_BAR = new ImageData("/images/menus/option_bar.png", 1720, 158);
	public static ImageData PAUSE = new ImageData("/images/menus/pause.png", 570, 200);
	public static ImageData SAVE_LOAD_BAR = new ImageData("/images/menus/save_load_bar.png", 1720, 158);
	public static ImageData SELECTION_BOX = new ImageData("/images/menus/selection_box.png", 300, 75);
	public static ImageData TITLE_BACKGROUND = new ImageData("/images/menus/title_background.png", 1920, 1080);
	public static ImageData TOOLBAR = new ImageData("/images/menus/toolbar.png", 302, 402);

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