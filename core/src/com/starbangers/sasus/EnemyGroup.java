package com.starbangers.sasus;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup
{
	private Random random;
	private float startTime;
	private int enemiesCount, curvesCount;
	
	private Vector2 startingPoint;
	private Array<Path> paths;
	
	public EnemyGroup(float _startTime, int _enemiesCount, Random _random)
	{
		random = _random;
		startTime = _startTime;
		enemiesCount = _enemiesCount;
		curvesCount = random.nextInt() * (5 - 1) + 1;
		
		paths = new Array<Path>(enemiesCount);
		
		startingPoint = new Vector2();
		startingPoint.x = Math.random() > 0.5 ? -100 : 900;
		startingPoint.y = (float) (Math.random() * 800 - 100);
		for (Path path : paths)
		{
			path = new Path(startingPoint, new Vector2(), new Vector2(), new Vector2());
			
		}
	}
	
	public void register() {
		GameMaster.groups.add(this);
	}
	
	public boolean isDead() {
		return false;
	}
	public void tick(double deltaT) {
		
	}
}
