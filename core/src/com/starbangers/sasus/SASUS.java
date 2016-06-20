package com.starbangers.sasus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class SASUS extends Game {
	public static SpriteBatch batch;
	public static ShapeRenderer shapeRenderer;
	public static Viewport viewport;
	public static OrthographicCamera camera;
	public static SASUS gmae;
	
	public static BitmapFont font;
	
	@Override
	public void create () {
		font = new BitmapFont(Gdx.files.internal("fonts/P2P.fnt"));
		font.setColor(Color.GREEN);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 600);
		viewport = new ExtendViewport(800, 600, camera);
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font.getData().setScale(0.3f);
		Resources.reset();
		Resources.loadVital();
		
		gmae = this;
		this.setScreen(new LoadingScreen());
		//Resources.startLoadThread();
	}

	@Override
	public void render () {
		super.render();
	}
}
