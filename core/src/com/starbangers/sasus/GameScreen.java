package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class GameScreen implements Screen {
	public static boolean useWidescreenLayout = false;
	
	public static Sprite leftBorder;
	public static Sprite rightBorder;
	
	public GameScreen() {
		leftBorder = new Sprite(Resources.getImage("interface/ScreenBorders"));
		rightBorder = new Sprite(Resources.getImage("interface/ScreenBorders"));
		leftBorder.setPosition(-64, 0);
		rightBorder.setPosition(800, 0);
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float deltaT) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SASUS.camera.update();
		SASUS.batch.setProjectionMatrix(SASUS.camera.combined);
		SASUS.shapeRenderer.setProjectionMatrix(SASUS.camera.combined);
		
		CurGame.player.update(deltaT);
		
		SASUS.shapeRenderer.setColor(Color.WHITE);
		SASUS.shapeRenderer.begin(ShapeType.Filled);
		
		for(Vector2 star : CurGame.stars) {
			SASUS.shapeRenderer.circle(star.x, star.y, (float) (Math.random()*3));
		}
		
		SASUS.shapeRenderer.end();
		
		SASUS.batch.begin();
		CurGame.player.draw();
		
		leftBorder.draw(SASUS.batch);
		rightBorder.draw(SASUS.batch);
		SASUS.batch.end();
		
		
	}

	@Override
	public void resize(int width, int height) {
		SASUS.viewport.update(width, height);
		
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {}

}
