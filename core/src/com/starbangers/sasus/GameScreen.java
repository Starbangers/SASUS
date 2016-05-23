package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {
	public static boolean useWidescreenLayout = false;
	@Override
	public void show() {}

	@Override
	public void render(float deltaT) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SASUS.camera.update();
		SASUS.batch.setProjectionMatrix(SASUS.camera.combined);
		
		CurGame.player.update(deltaT);
		
		SASUS.batch.begin();
		CurGame.player.draw();
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
