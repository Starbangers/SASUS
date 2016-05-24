package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Player {
	public static final float BASE_SPEED = 250;
	
	
	private Sprite sprite;
	
	float x = 300;
	float goalX = 300;
	float y = 32;
	float rot = 0;
	float goalRot = 0;
	
	int health = 10;
	
	float particleTime = 0;
	
	public Player() {
		sprite = new Sprite(Resources.getImage("player/ref"));
		sprite.setOrigin(31.5f, 31.5f);
	}
	
	public void update(float deltaT) {
		particleTime += deltaT;
		
		rot += (goalRot - rot)*deltaT*8;
		goalRot = -(goalX-x)/200;
		
		x += (goalX - x)*deltaT*3;
		
		float speedX = Math.abs(goalX - x);
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && goalX < 736) {
			goalX += BASE_SPEED*deltaT;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && goalX > 0) {
			goalX -= BASE_SPEED*deltaT;
		}
		
		if(particleTime > 1/(speedX+10)) {
			particleTime = 0;
			CurGame.particles.add(new Particle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*-35, y+31.5f+(float)Math.sin(rot+Math.PI/2)*-35, (float)(Math.random()*80-40), -20 - speedX*1.5f, 8, 16, 1, 0.7f+(float)Math.random()*0.2f, 0));
		}
	}
	
	public void draw() {
		sprite.setPosition(x, y);
		sprite.setRotation((float) Math.toDegrees(rot));
		sprite.draw(SASUS.batch);
	}
	
	public void drawShapes() {
		SASUS.shapeRenderer.setColor(0, 0.5f, 1f, 0.1f+(float)(0.05f*Math.random()));
		
		for(int i = 0; i < 5; i++) {
			SASUS.shapeRenderer.circle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*8, y+31.5f+(float)Math.sin(rot+Math.PI/2)*8, i*10);
		}
		
		SASUS.shapeRenderer.setColor(1, 0.8f, 0, Math.abs(goalX - x)/1000+(float)(0.1f*Math.random()));
		
		for(int i = 0; i < 5; i++) {
			SASUS.shapeRenderer.circle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*-35, y+31.5f+(float)Math.sin(rot+Math.PI/2)*-35, i*10);
		}
	}
}
