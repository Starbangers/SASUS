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
	
	float gunCD = 0;
	
	long engineSoundID;
	
	int health = 10;
	
	float particleTime = 0;
	
	public Player() {
		sprite = new Sprite(Resources.getImage("player/ref"));
		sprite.setOrigin(31.5f, 31.5f);
		engineSoundID = Resources.sounds.get("ship/engine_active_loop").play();
		Resources.sounds.get("ship/engine_active_loop").setLooping(engineSoundID, true);
	}
	
	public void update(float deltaT) {
		gunCD -= deltaT;
		particleTime += deltaT;
		
		rot += (goalRot - rot)*deltaT*8;
		goalRot = -(goalX-x)/200;
		
		x += (goalX - x)*deltaT*3;
		
		float speedX = Math.abs(goalX - x);
		
		Resources.sounds.get("ship/engine_active_loop").setVolume(engineSoundID, speedX/160+0.2f);
		//Resources.sounds.get("ship/engine_active_loop").setPitch(engineSoundID, speedX/10);
		
		if((GameScreen.right_button.isPressed() || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) && goalX < 736) {
			goalX += BASE_SPEED*deltaT;
		}
		if((GameScreen.left_button.isPressed() || Gdx.input.isKeyPressed(Input.Keys.LEFT)) && goalX > 0) {
			goalX -= BASE_SPEED*deltaT;
		}
		if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT) && gunCD < 0) {
			gunCD = 1;
			Resources.playSound("weapon/laser"+(int)(Math.random()*3+1));
			for(int i = 0; i < 5; i ++)
			CurGame.particles.add(new Particle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*30, y+31.5f+(float)Math.sin(rot+Math.PI/2)*30, (float)Math.random()*80-40, (float)Math.random()*80-40, 4, 4, 0, 0.6f, 1));
			CurGame.entities.add(new Projectile(x+31.5f+(float)Math.cos(rot+Math.PI/2)*25, y+31.5f+(float)Math.sin(rot+Math.PI/2)*25, (float)Math.cos(rot+Math.PI/2)*600+speedX*3, (float)Math.sin(rot+Math.PI/2)*600, 0, 0.6f, 1, 8, true, 1));
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
		SASUS.shapeRenderer.setColor(0, 0.5f, 1f, gunCD>0 ? gunCD/10 + (float)(0.05f*Math.random()) : (float)(0.05f*Math.random()));
		
		for(int i = 0; i < 5; i++) {
			SASUS.shapeRenderer.circle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*8, y+31.5f+(float)Math.sin(rot+Math.PI/2)*8, i*10);
		}
		
		SASUS.shapeRenderer.setColor(1, 0.8f, 0, Math.abs(goalX - x)/1000+(float)(0.1f*Math.random()));
		
		for(int i = 0; i < 5; i++) {
			SASUS.shapeRenderer.circle(x+31.5f+(float)Math.cos(rot+Math.PI/2)*-35, y+31.5f+(float)Math.sin(rot+Math.PI/2)*-35, i*10);
		}
	}
}
