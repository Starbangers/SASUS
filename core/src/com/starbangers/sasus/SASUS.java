package com.starbangers.sasus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SASUS extends Game {
	public static SpriteBatch batch;
	public static Viewport viewport;
	public static OrthographicCamera camera;
	
	public static BitmapFont font;
	@Override
	public void create () {
		font = new BitmapFont();
		font.setColor(Color.GREEN);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		viewport = new FitViewport(800, 600, camera);
		batch = new SpriteBatch();
		font.getData().setScale(2);
		Resources.loadVital();
		
		this.setScreen(new LoadingScreen());
		//Resources.startLoadThread();
	}

	@Override
	public void render () {
		super.render();
	}
}
