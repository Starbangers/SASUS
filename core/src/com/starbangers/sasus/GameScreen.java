package com.starbangers.sasus;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen implements Screen {
	public static boolean useWidescreenLayout = false;
	
	public static Sprite leftBorder;
	public static Sprite rightBorder;
	
		
	private static Array<GButton> controls = new Array<GButton>();
	
	public static GButton left_button;
	public static GButton right_button;
	public static GButton fire_button;
	
	public GameScreen() {
		leftBorder = new Sprite(Resources.getImage("interface/ScreenBorders"));
		rightBorder = new Sprite(Resources.getImage("interface/ScreenBorders"));
		leftBorder.setPosition(-128, 0);
		rightBorder.setPosition(800, 0);
		left_button = new GButton(0, 0, 128, 256, "null");
		right_button = new GButton(0, 0, 128, 256, "null");
		fire_button = new GButton(0, 0, 128, 256, "null");
		controls.add(left_button);
		controls.add(right_button);
		controls.add(fire_button);
	}
	
	@Override
	public void show() {}

	@Override
	public void render(float deltaT) {
		float XTension = (SASUS.viewport.getWorldWidth() - 800) / 2;
		
		useWidescreenLayout = XTension >= 64;
		float HPpos = useWidescreenLayout ? -64 : -XTension;
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		SASUS.camera.update();
		SASUS.batch.setProjectionMatrix(SASUS.camera.combined);
		SASUS.shapeRenderer.setProjectionMatrix(SASUS.camera.combined);
		
		if(Gdx.app.getType() == ApplicationType.Android)
		for(GButton butt : controls) {
			butt.update();
		}
		
		GameMaster.tick(deltaT);
		
		CurGame.player.update(deltaT);
		CurGame.tick(deltaT);
		
		
		SASUS.shapeRenderer.setColor(Color.WHITE);
		SASUS.shapeRenderer.begin(ShapeType.Filled);
		
		for(Vector2 star : CurGame.stars) {
			SASUS.shapeRenderer.circle(star.x, star.y, (float) (2+Math.random()));
		}
		
		SASUS.shapeRenderer.end();
		
		SASUS.batch.begin();
		CurGame.player.draw();
		
		for(Entity ent : CurGame.entities) {
			ent.draw();
		}
		
		
		
		
		
		SASUS.batch.end();
		
		
		Gdx.gl.glEnable(GL20.GL_BLEND);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		SASUS.shapeRenderer.begin(ShapeType.Filled);
		
		CurGame.player.drawShapes();
		
		for(Particle part : CurGame.particles) {
			part.draw();
		}
		
		
		SASUS.shapeRenderer.end();
		Gdx.gl.glDisable(GL20.GL_BLEND);
		
		SASUS.batch.begin();
		
		for(int i = 0; i < 10; i ++) {
			SASUS.batch.draw(Resources.getImage("HUD/hpBar"), HPpos, 280+i*32);
		}
		
		if(GameMaster.waveTime > -5) {
			if(GameMaster.waveTime < -4) {
				SASUS.font.draw(SASUS.batch, "5", 400, 300);
			} else if(GameMaster.waveTime < -3) {
				SASUS.font.draw(SASUS.batch, "4", 400, 300); 
			} else if(GameMaster.waveTime < -2) {
				SASUS.font.draw(SASUS.batch, "3", 400, 300); 
			} else if(GameMaster.waveTime < -1) {
				SASUS.font.draw(SASUS.batch, "2", 400, 300); 
			} else if(GameMaster.waveTime < 0) {
				SASUS.font.draw(SASUS.batch, "1", 400, 300); 
			}
		} else {
			SASUS.font.draw(SASUS.batch, "Stage "+GameMaster.level, 200, 300); 
		}
		
		SASUS.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		SASUS.viewport.update(width, height);
		float XTension = (SASUS.viewport.getWorldWidth() - 800) / 2;
		left_button.setX(-XTension);
		right_button.setX(-XTension+128);
		fire_button.setX(672+XTension);

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
