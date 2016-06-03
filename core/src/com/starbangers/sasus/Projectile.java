package com.starbangers.sasus;

public class Projectile extends Entity {

	float r, g, b;
	float velX, velY;
	boolean isFriendly;
	float size;
	int damage;
	float time = 0;
	
	
	@Override
	public boolean tick(float deltaT) {
		this.x += velX*deltaT;
		this.y += velY*deltaT;
		new Particle(Particle.Shape.SQUARE)
		.setPos(x, y)
		.setColor(r, g, b)
		.setSizeAndDecay(8, 32)
		.setVel((float)Math.random()*10-5, (float)Math.random()*10-5).spawn();
		
		for(int i = 0; i < CurGame.entities.size; i ++) {
			if(CurGame.entities.get(i) instanceof Enemy) {
				Enemy enemy = (Enemy)CurGame.entities.get(i);
				if(enemy.sprite.getBoundingRectangle().contains(x, y)) {
					enemy.getHit(damage, velX, velY);
					for(int j = 0; j < 10; j ++) 
						new Particle(Particle.Shape.SQUARE).setPos(x, y).setColor(r, g, b)
						.setVel((float)Math.random()*200-100, (float) (Math.random()*200-100))
						.setSizeAndDecay(8, 16).spawn();
						
					return true;
				}
			}
		}
		
		if(this.x > 800+size) return true;
		if(this.x < -size) return true;
		if(this.y < -size) return true;
		if(this.y > 600+size) return true;
		return false;
	}

	@Override
	public void draw() {}

	@Override
	public void drawShapes(){
		SASUS.shapeRenderer.setColor(r, g, b, 255);
		SASUS.shapeRenderer.circle(x, y, size);
	}
	
	public Projectile(float x, float y, float velX, float velY, float r, float g, float b, float size, boolean friendly, int damage) {
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.r = r;
		this.g = g;
		this.b = b;
		this.size = size;
		this.isFriendly = friendly;
		this.damage = damage;
	}
}
