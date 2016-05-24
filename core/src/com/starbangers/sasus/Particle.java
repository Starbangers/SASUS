package com.starbangers.sasus;

import com.badlogic.gdx.graphics.Color;

public class Particle {
	enum Shape {
		SQUARE, ROUND
	}
	
	Shape shape;
	
	Color color;
	
	float x, y, velX, velY, size, decay;
	
	boolean glow;
	
	public Particle(int _x, int _y, int _velX, int _velY, int _size, int _decay, Color _color, Shape _shape, boolean _glow) {
		this.x = _x;
		this.y = _y;
		this.decay = _decay;
		this.velX = _velX;
		this.velY = _velY;
		this.shape = _shape;
		this.size = _size;
		this.glow = _glow;
		this.color = _color;
	}
	
	public Particle(int _x, int _y, int _velX, int _velY, int _size, int _decay, Color _color) {
		this(_x, _y, _velX, _velY, _size, _decay, _color, Shape.SQUARE, false);
	}
	
	public boolean tick() {
		this.x += velX;
		this.y += velY;
		this.size -= this.decay;
		if(size < 0)return false;
		return true;
	}
	public void draw() {
		
	}
}
