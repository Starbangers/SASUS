package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class MainMenuScreen implements Screen {
	
	private Array<GElement> buttons = new Array<GElement>();
	
	private GButton startButton, quitButton;
	
	private Sprite arrow;
	
	public MainMenuScreen()
	{
		startButton = new GButton(SASUS.viewport.getScreenWidth()/2 - 200/2, SASUS.viewport.getScreenHeight()/2- 50/2,
								200, 50, "interface/New_Game");
		buttons.add(startButton);
		
		quitButton = new GButton(SASUS.viewport.getScreenWidth()/2 - 200/2, SASUS.viewport.getScreenHeight()/2- 50/2 - 50/2 - 30,
								200, 50, "interface/Quit");
		buttons.add(quitButton);
		
		arrow = new Sprite(Resources.getImage("interface/Arrow"));
		arrow.setX(startButton.getX() - 25 - 20);
		arrow.setY(startButton.getY() + 25/2 + 5);
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
			if (current instanceof GButton)
			{
				if (((GButton) current).isPointedAt())
					arrow.setY(((GButton) current).getY() + 25/2 + 5);
			}
		}
		
		arrow.draw(SASUS.batch);
		SASUS.batch.end();
		
		if(startButton.isJustReleased())
		{			
			GameManager.beginGame();
		}
		else if (quitButton.isJustReleased())
		{
			
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
