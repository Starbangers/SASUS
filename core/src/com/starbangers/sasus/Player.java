package com.starbangers.sasus;

import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
	private Sprite sprite;
	
	float x = 300;
	float y = 32;
	float rot = 3;
	float goalRot = 0;
	
	public Player() {
		sprite = new Sprite(Resources.getImage("player/ref"));
		sprite.setOrigin(31.5f, 31.5f);
	}
	
	public void update(float deltaT) {
		rot += (goalRot - rot)*deltaT;
	}
	
	public void draw() {
		sprite.setPosition(x, y);
		sprite.setRotation((float) Math.toDegrees(rot));
		sprite.draw(SASUS.batch);
	}
}
