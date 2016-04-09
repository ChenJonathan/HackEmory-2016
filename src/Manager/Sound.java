package Manager;

import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

/**
 * Handles the sound for the application.
 */
public class Sound
{
	// List of currently playing sounds
	private static ArrayList<Clip> playingSounds = new ArrayList<>();
	private static ArrayList<Content.SoundData> playingData = new ArrayList<>();

	// Current Sound Volume (Between -50f and 0f)
	private static float currVolume;

	/**
	 * Retrieves the requested sound from the ContentManager and plays the sound.
	 * 
	 * @param snd The SoundData of the sound to be played
	 */
	public static void play(Content.SoundData snd)
	{
		try
		{
			byte[] SoundBytes = Content.getSound(snd);
			Clip AudioClip = AudioSystem.getClip();
			AudioFormat Format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
			AudioClip.open(Format, SoundBytes, 0, SoundBytes.length);
			AudioClip.stop();
			if(snd.doesLoop())
			{
				AudioClip.setLoopPoints(0, -1);
				AudioClip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			FloatControl ClipVolume = (FloatControl)AudioClip.getControl(FloatControl.Type.MASTER_GAIN);
			ClipVolume.setValue(currVolume);
			AudioClip.addLineListener(new LineListener()
			{
				@Override
				public void update(LineEvent arg0)
				{
					if(arg0.getType() == LineEvent.Type.STOP)
						synchronized(playingSounds)
						{
							synchronized(playingData)
							{
								for(int i = 0; i < playingSounds.size(); i++)
								{
									if(!playingSounds.get(i).isRunning())
									{
										playingSounds.get(i).stop();
										playingSounds.get(i).close();
										playingSounds.remove(i);
										playingData.remove(i);
										break;
									}
								}
							}
						}
				}
			});
			playingSounds.add(AudioClip);
			playingData.add(snd);
			AudioClip.start();
		}
		catch(Exception Ex)
		{
			Ex.printStackTrace();
			System.out.println("Error playing sound.");
			System.exit(0);
		}
	}
	
	/**
	 * Removes the specified SoundData from the currently playing sounds
	 * 
	 * @param snd The SoundData to remove
	 */
	public static void removeSound(Content.SoundData snd)
	{
		synchronized(playingSounds)
		{
			synchronized(playingData)
			{
				while(playingData.lastIndexOf(snd) != -1)
				{
					int currIndex = playingData.lastIndexOf(snd);
					playingSounds.get(currIndex).stop();
					playingSounds.get(currIndex).close();
					playingSounds.remove(currIndex);
					playingData.remove(currIndex);
				}
			}
		}
	}

	/**
	 * Pauses every currently playing sound
	 */
	public static void stopAll()
	{
		synchronized(playingSounds)
		{
			for(Clip C : playingSounds)
			{
				C.stop();
			}
		}
	}

	/**
	 * Changes the Sound Volume of all the playing clips.
	 * 
	 * @param newVolume The new Volume of the Sound Clips
	 */
	public static void changeVolume(float newVolume)
	{
		currVolume = newVolume;
		synchronized(playingSounds)
		{
			for(Clip C : playingSounds)
			{
				FloatControl ClipVolume = (FloatControl)C.getControl(FloatControl.Type.MASTER_GAIN);
				ClipVolume.setValue(newVolume);
			}
		}
	}
}