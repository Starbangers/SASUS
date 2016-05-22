package com.starbangers.sasus;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by complover116 on 25.05.2015 for QAR-1 Reloaded
 */
public class Resources {
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	
	public static String loadStep = "Loading...";
	public static byte loaded = 0;
	
	
	public static void loadVital() {
		textures.put("splashscreen", new Texture(Gdx.files.internal("img/Logo.png")));
		textures.put("ERROR", new Texture(Gdx.files.internal("img/ERROR.png")));
	}
	public static void load() {

		Gdx.app.log("Resources", "Loading image list...");
		String imglistRaw = Gdx.files.internal("ImageList").readString();

		String imglist[] = imglistRaw.split("\n");
		Gdx.app.log("Resources", "Found " + imglist.length + " image declarations");

		for (String imagename : imglist) {
			imagename = imagename.trim();
			loadStep = "Loading " + imagename;
			try {
				textures.put(imagename, new Texture(Gdx.files.internal("img/" + imagename + ".png")));
				Gdx.app.log("Resources", "Loaded " + imagename);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + imagename);
				e.printStackTrace();
				
				loaded = -1;

				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
				return;
			}
		}

		Gdx.app.log("Resources", "Loading sound list...");

		String soundlistRaw = Gdx.files.internal("SoundList").readString();

		String soundlist[] = soundlistRaw.split("\n");
		Gdx.app.log("Resources", "Found " + soundlist.length + " sound declarations");

		for (String soundname : soundlist) {
			soundname = soundname.trim();
			loadStep = "Loading " + soundname;
			try {
				sounds.put(soundname, Gdx.audio.newSound(Gdx.files.internal("sound/effects/" + soundname + ".wav")));
				Gdx.app.log("Resources", "Loaded " + soundname);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + soundname);
				loaded = -1;
				return;
			}
		}
		/*
		Gdx.app.log("Resources", "Loading music...");
		try {
			Music_DM = Gdx.audio.newMusic(Gdx.files.internal("sound/music/Q1R_DM.ogg"));
			Music_Offline = Gdx.audio.newMusic(Gdx.files.internal("sound/music/Q1R_Offline.ogg"));
			
			Music_Offline.setLooping(true);
			Music_DM.setLooping(true);
		} catch (Exception e) {
			Gdx.app.error("Resources", "Failed loading music!");
			loaded = -1;
			return;
		}
		*/
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {

		}
		loaded = 0;
	}

	public static Texture getImage(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		}
		Gdx.app.error("Resources", "");
		return null;
	}

	/*static void playSound(String name) {
		sounds.get(name).play(Settings.soundVolume / (float) 100);
	}*/
}