package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class LoadingScreen implements Screen {

	@Override
	public void show() {}

	@Override
	public void render(float deltaT) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SASUS.camera.update();
		SASUS.batch.setProjectionMatrix(SASUS.camera.combined);
		
		SASUS.batch.begin();
		SASUS.batch.draw(Resources.getImage("splashscreen"), 0, 0);
		SASUS.font.draw(SASUS.batch, ">"+Resources.loadStep, 0, 70);
		SASUS.batch.end();
		
		if(Resources.loaded == 0) {
			Resources.load();
		}
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
