package myMMO.sound;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
	
	public static final Sound pickUP = new Sound("/sounds/pickUp.wav");
	public static final Sound sand = new Sound("/sounds/sand.wav");
	public static final Sound enterWater = new Sound("/sounds/enterWater.wav");

	private AudioClip clip;

	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}