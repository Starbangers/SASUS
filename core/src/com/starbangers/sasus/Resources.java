package com.starbangers.sasus;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by complover116 on 25.05.2015 for QAR-1 Reloaded
 */
public class Resources {
	public static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	public static HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	public static HashMap<String, Music> music = new HashMap<String, Music>();
	
	public static volatile String loadStep = "Loading...";
	public static volatile byte loaded = 0;
	
	static byte status = 0;
	static String imglist[];
	static String soundlist[];
	static int i = 0;
	
	public static void loadVital() {
		textures.put("splashscreen", new Texture(Gdx.files.internal("img/Logo.png")));
		//textures.put("ERROR", new Texture(Gdx.files.internal("img/ERROR.png")));
	}
	public static void reset() {
		textures.clear();
		sounds.clear();
		music.clear();
		status = 0;
		i = 0;
	}
	public static void updateMusicVolume() {
		for(String sik : music.keySet()) {
			music.get(sik).setVolume(Settings.musicVolume/(float)100);
		}
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
				status = 4;
				//loadStep = "Finalizing...";
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
		
		if(status == 4){
			Gdx.app.log("Resources", "Loading music list...");

			String soundlistRaw = Gdx.files.internal("MusicList").readString();

			soundlist = soundlistRaw.split("\n");
			Gdx.app.log("Resources", "Found " + soundlist.length + " music declarations");
			status = 5;
			return;
		}
		if(status == 5){
			if(i >= soundlist.length) {
				i = 0;
				status = 109;
				loadStep = "Finalizing...";
				return;
			}
			String soundname = soundlist[i];
			soundname = soundname.trim();
			loadStep = "Loading " + soundname;
			try {
				music.put(soundname, Gdx.audio.newMusic(Gdx.files.internal("sound/music/" + soundname + ".ogg")));
				Gdx.app.log("Resources", "Loaded " + soundname);
			} catch (Exception e) {
				Gdx.app.error("Resources", "Failed loading " + soundname);
			}
			i++;
			return;
		}
		
		if(status == 109) {
			status = 110;
			return;
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {

		}
		status = 111;
		sounds.get("vox/initComplete").play();
		playMusic("sanch-escape");
	}
	
	
	public static void playMusic(String name) {
		for(String musid : music.keySet()) {
			if(musid.equals(name)) {
				music.get(musid).setLooping(true);
				music.get(musid).play();
			} else {
				music.get(musid).stop();
			}
		}
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