package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class MainMenuScreen implements Screen {
	
	private Array<GElement> buttons = new Array<GElement>();
	private final int buttonWidth = 100, buttonHeight = 100; 
	
	public MainMenuScreen()
	{
		buttons.add(new GButton(SASUS.viewport.getScreenWidth()/2 - buttonWidth/2, SASUS.viewport.getScreenHeight()/2- buttonHeight/2,
								buttonWidth, buttonHeight, "butt"));
	}
	
	@Override
	public void show()
	{
		
	}

	@Override
	public void render(float deltaT)
	{		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SASUS.camera.update();
		SASUS.batch.setProjectionMatrix(SASUS.camera.combined);
		SASUS.batch.begin();
		for (GElement current : buttons)
		{
			current.update();
			current.draw();
		}
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
