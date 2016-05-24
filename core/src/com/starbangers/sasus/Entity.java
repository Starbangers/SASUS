package com.starbangers.sasus;

public abstract class Entity {
	float x, y;
	public abstract boolean tick(float deltaT);
	public abstract void draw();
	public abstract void drawShapes();
}
