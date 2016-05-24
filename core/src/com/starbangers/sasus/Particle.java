package com.starbangers.sasus;

public class Particle {
	enum Shape {
		SQUARE, ROUND
	}
	
	Shape shape;
	
	float r, g, b;
	
	float x, y, velX, velY, size, decay;
	
	boolean glow;
	
	public Particle(float _x, float _y, float _velX, float _velY, float _size, float _decay, float _r, float _g, float _b, Shape _shape, boolean _glow) {
		this.x = _x;
		this.y = _y;
		this.decay = _decay;
		this.velX = _velX;
		this.velY = _velY;
		this.shape = _shape;
		this.size = _size;
		this.glow = _glow;
		this.r = _r;
		this.g = _g;
		this.b = _b;
	}
	
	public Particle(float _x, float _y, float _velX, float _velY, float _size, float _decay, float _r, float _g, float _b) {
		this(_x, _y, _velX, _velY, _size, _decay, _r, _g, _b, Shape.SQUARE, false);
	}
	
	public boolean tick(float deltaT) {
		this.x += velX*deltaT;
		this.y += velY*deltaT;
		this.size -= this.decay*deltaT;
		if(size < 0)return false;
		return true;
	}
	public void draw() {
		
		if(glow)
			SASUS.shapeRenderer.setColor(r, g, b, 0.2f);
		else
			SASUS.shapeRenderer.setColor(r, g, b, 1);
		
		switch(shape) {
		case SQUARE:
			if(glow) {
				for(int i = 0; i < 5; i ++) {
					SASUS.shapeRenderer.rect(x-size/10*i, y-size/10*i, size/5*i, size/5*i);
				}
			} else
			SASUS.shapeRenderer.rect(x-size/2, y-size/2, size, size);
		break;
		case ROUND:
			if(glow) {
				for(int i = 0; i < 5; i ++) {
					SASUS.shapeRenderer.circle(x, y, size/5*i);
				}
			} else
			SASUS.shapeRenderer.circle(x, y, size);
		break;
		}
	}
}
