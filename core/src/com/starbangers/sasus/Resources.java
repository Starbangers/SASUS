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
	
	public static volatile String loadStep = "Loading...";
	public static volatile byte loaded = 0;
	
	static byte status = 0;
	static String imglist[];
	static String soundlist[];
	static int i = 0;
	
	public static void startLoadThread() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				load();
			}
			
		}, "Resource load thread");
		thread.start();
	}
	public static void loadVital() {
		textures.put("splashscreen", new Texture(Gdx.files.internal("img/Logo.png")));
		//textures.put("ERROR", new Texture(Gdx.files.internal("img/ERROR.png")));
	}
	public static void load() {
		if(status == 0){
			Gdx.app.log("Resources", "Loading image list...");
			String imglistRaw = Gdx.files.internal("ImageList").readString();
			
			imglist = imglistRaw.split("\n");
			Gdx.app.log("Resources", "Found " + imglist.length + " image declarations");
			status = 1;
			return;
		}
		if(status == 1){
			if(i >= imglist.length){
				status = 2;
				i = 0;
				return;
			}
			String imagename = imglist[i];
			imagename = imagename.trim();
			loadStep = "Loading " + imagename;
			try {
				textures.put(imagename, new Texture(Gdx.files.internal("img/" + imagename + ".png")));
				Gdx.app.log("Resources", "Loaded " + imagename);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + imagename);
				//e.printStackTrace();

				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {

				}
			}
			i++;
			return;
		}
		if(status == 2){
		Gdx.app.log("Resources", "Loading sound list...");

		String soundlistRaw = Gdx.files.internal("SoundList").readString();

		soundlist = soundlistRaw.split("\n");
		Gdx.app.log("Resources", "Found " + soundlist.length + " sound declarations");
		status = 3;
		return;
		}
		if(status == 3){
			if(i >= soundlist.length) {
				i = 0;
				status = 5;
				loadStep = "Finalizing...";
				return;
			}
			String soundname = soundlist[i];
			soundname = soundname.trim();
			loadStep = "Loading " + soundname;
			try {
				sounds.put(soundname, Gdx.audio.newSound(Gdx.files.internal("sound/effects/" + soundname + ".ogg")));
				Gdx.app.log("Resources", "Loaded " + soundname);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + soundname);
			}
			i++;
			return;
		}
		if(status == 5) {
			status = 6;
			return;
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
			Thread.sleep(500);
		} catch (InterruptedException e1) {

		}
		status = 111;
		sounds.get("vox/initComplete").play();
		status = 4;
	}

	public static Texture getImage(String name) {
		if (textures.containsKey(name)) {
			return textures.get(name);
		}
		Gdx.app.error("Resources", name+" was requested, but is not loaded!");
		return textures.get("null");
	}

	static void playSound(String name) {
		sounds.get(name).play(Settings.soundVolume / (float) 100);
	}
}