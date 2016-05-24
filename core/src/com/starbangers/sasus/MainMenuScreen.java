package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class MainMenuScreen implements Screen {
	
	private Array<GElement> elements = new Array<GElement>();
	
	private GButton startButton, quitButton, optionsButton, creditsButton, backButton;
	private GSlider musicSlider, soundSlider;
	private GImage creditsText, musicText, sfxText;
	
	private Sprite arrow;
	//private float prevArrowPos = 0;
	
	public MainMenuScreen()
	{
		startButton = new GButton(800/2 - 200/2, 600/2 - 50/2,
								200, 50, "interface/New_Game");		
		
		optionsButton = new GButton((int)startButton.getX(), (int)startButton.getY() - 50/2 - 30,
								200, 50, "interface/Options");
		
		creditsButton = new GButton((int)startButton.getX(), (int)optionsButton.getY() - 50/2 - 30,
								200, 50, "interface/Credits");
		
		quitButton = new GButton((int)startButton.getX(), (int)creditsButton.getY() - 50/2 - 30,
				200, 50, "interface/Quit");
		
		backButton = new GButton(800/2 - 200/2, 600/2 - 50/2 - 200,
				200, 50, "interface/Back");
		
		musicSlider = new GSlider(322, 600/2 + 50/2, (int)Settings.musicVolume);
		soundSlider = new GSlider(322, 600/2 + 50/2 - 70, (int)Settings.soundVolume);
		
		creditsText = new GImage(800/2 - 600/2, 600/2 - 180/2 + 100, "interface/CreditsText");
		musicText = new GImage(177, musicSlider.getY() + 25/2 + 5, "interface/Music");
		sfxText = new GImage(177, soundSlider.getY() + 25/2 + 2, "interface/SFX");
		
		arrow = new Sprite(Resources.getImage("interface/Arrow"));
		arrow.setX(startButton.getX() - 25 - 20);
		arrow.setY(startButton.getY() + 25/2 + 5);
		
		setState(State.MainState);
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
		
		/*
		if(prevArrowPos != arrow.getY())
		{
			prevArrowPos = arrow.getY();
			Resources.playSound("ui/select");
		}
		*/
		
		SASUS.batch.begin();
		for (GElement current : elements)
		{
			if(!(current instanceof GImage))
				current.update();
			
			Settings.musicVolume = musicSlider.getValue();
			Resources.updateMusicVolume();
			
			Settings.soundVolume = soundSlider.getValue();
			
			current.draw();
			if (current instanceof GButton)
			{
				if (((GButton) current).isPointedAt())
					arrow.setY(((GButton) current).getY() + 25/2 + 5);
				if(((GButton) current).isJustReleased()) {
					Resources.playSound("ui/confirm");
				}
			}
		}
		
		arrow.draw(SASUS.batch);
		SASUS.batch.end();
		
		if (startButton.isJustReleased())
		{			
			GameManager.beginGame();
		}
		else if (optionsButton.isJustReleased())
		{
			setState(State.OptionsState);
		}
		else if (creditsButton.isJustReleased())
		{
			setState(State.CreditsState);
		}
		else if (quitButton.isJustReleased())
		{
			Gdx.app.exit();
		}
		else if (backButton.isJustReleased())
		{
			setState(State.MainState);
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
	
	enum State
	{
		MainState, CreditsState, OptionsState
	}
	
	void setState(State _state)
	{
		for (GElement element : elements)
		{
			element.update(); //do a final update
		}
		
		elements.clear();
		switch (_state)
		{
		case CreditsState:
			elements.add(creditsText);
			elements.add(backButton);
			break;
		case OptionsState:
			elements.add(musicSlider);
			elements.add(musicText);
			elements.add(soundSlider);
			elements.add(sfxText);
			elements.add(backButton);
			break;
		case MainState: //fall through to default
		default:
			elements.add(startButton);
			elements.add(optionsButton);
			elements.add(creditsButton);
			elements.add(quitButton);
			break;
		}
		
		for (GElement element : elements)
		{
			if (element instanceof GButton)
			{
				arrow.setY(((GButton)element).getY() + 25/2 + 5);
				break;
			}
		}
	}

}
