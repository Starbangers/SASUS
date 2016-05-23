package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
	public static final float BASE_SPEED = 250;
	
	
	private Sprite sprite;
	
	float x = 0;
	float goalX = 300;
	float y = 32;
	float rot = 3;
	float goalRot = 0;
	
	public Player() {
		sprite = new Sprite(Resources.getImage("player/ref"));
		sprite.setOrigin(31.5f, 31.5f);
	}
	
	public void update(float deltaT) {
		rot += (goalRot - rot)*deltaT*8;
		goalRot = -(goalX-x)/200;
		
		x += (goalX - x)*deltaT*3;
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && goalX < 736) {
			goalX += BASE_SPEED*deltaT;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && goalX > 0) {
			goalX -= BASE_SPEED*deltaT;
		}
	}
	
	public void draw() {
		sprite.setPosition(x, y);
		sprite.setRotation((float) Math.toDegrees(rot));
		sprite.draw(SASUS.batch);
	}
}
