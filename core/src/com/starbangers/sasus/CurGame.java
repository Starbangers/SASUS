package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class CurGame {
	public static Player player;
	
	public static Array<Vector2> stars = new Array<Vector2>();
	
	public static final int NUM_STARS = 20;
	
	public static void reset() {
		player = new Player();
		
		stars.clear();
		for(int i = 0; i < NUM_STARS; i ++)
			stars.add(new Vector2((float)Math.random()*800, (float) (Math.random()*600)));
	}
}
