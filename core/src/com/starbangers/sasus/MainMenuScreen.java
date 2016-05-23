package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

public class MainMenuScreen implements Screen {
	
	private Array<GElement> buttons = new Array<GElement>();
	
	private GButton startButton;
	
	public MainMenuScreen()
	{
		startButton = new GButton(SASUS.viewport.getScreenWidth()/2 - 200/2, SASUS.viewport.getScreenHeight()/2- 50/2,
								200, 50, "interface/New_Game");
		buttons.add(startButton);
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
		
		if(startButton.isJustReleased()) {			
			GameManager.beginGame();
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
