package com.starbangers.sasus;

public class Particle {
	enum Shape {
		SQUARE, ROUND
	}
	
	Shape shape;
	
	float r, g, b;
	float startR, startG, startB, endR, endG, endB;
	float x, y, velX, velY, size, decay;
	
	float lifeTime;
	
	boolean glow;
	boolean transColor;
	float gravity;
	
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
	public Particle(Shape shape) {
		this.shape = shape;
	}
	public Particle setGravity(float gravity) {
		this.gravity = gravity;
		return this;
	}
	public Particle setPos(float x, float y) {
		this.x = x;
		this.y = y;
		return this; //Method chaining
	}
	public Particle setVel(float x, float y) {
		this.velX = x;
		this.velY = y;
		return this; //Method chaining
	}
	public Particle setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.transColor = false;
		return this; //Method chaining
	}
	public Particle setFadingColors(float r, float g, float b, float r2, float g2, float b2) {
		this.startR = r;
		this.startG = g;
		this.startB = b;
		this.r = startR;
		this.g = startG;
		this.b = startB;
		this.endR = r2;
		this.endG = g2;
		this.endB = b2;
		this.transColor = true;
		return this; //Method chaining
	}
	public Particle setGlowing(boolean glow) {
		this.glow = glow;
		return this;
	}
	public Particle setSizeAndDecay(float size, float decay) {
		this.size = size;
		this.decay = decay;
		this.lifeTime = this.size/this.decay;
		return this;
	}
	public void spawn() {
		CurGame.particles.add(this);
	}
	
	public Particle(float _x, float _y, float _velX, float _velY, float _size, float _decay, float _r, float _g, float _b) {
		this(_x, _y, _velX, _velY, _size, _decay, _r, _g, _b, Shape.SQUARE, false);
	}
	
	public boolean tick(float deltaT) {
		this.velY -= this.gravity*deltaT;
		this.x += velX*deltaT;
		this.y += velY*deltaT;
		this.size -= this.decay*deltaT;
		if(true) {
			this.r += (this.endR - this.startR)/this.lifeTime*deltaT;
			this.g += (this.endG - this.startG)/this.lifeTime*deltaT;
			this.b += (this.endB - this.startB)/this.lifeTime*deltaT;
		}
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
