package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CurGame {
	public static Player player;
	
	public static Array<Vector2> stars = new Array<Vector2>();
	public static Array<Particle> particles = new Array<Particle>();
	public static Array<Entity> entities = new Array<Entity>();
	
	public static final int NUM_STARS = 20;
	
	public static void reset() {
		player = new Player();
		particles.clear();
		stars.clear();
		for(int i = 0; i < NUM_STARS; i ++)
			stars.add(new Vector2((float)Math.random()*800, (float) (Math.random()*600)));
	}
	
	public static void tick(float deltaT) {
		for(Particle part : particles) {
			if(!part.tick(deltaT)) {
				particles.removeValue(part, true);
			}
		}
		for(Entity ent : entities) {
			if(ent.tick(deltaT)) {
				entities.removeValue(ent, true);
			}
		}
	}
}
