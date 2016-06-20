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
		curvesCount = random.nextInt(5) + 1;
		
		paths = new Array<Path>(enemiesCount);
		
		startingPoint = new Vector2();

		startingPoint.x = random.nextFloat() > 0.5 ? -100 : 900;
		startingPoint.y = random.nextFloat() * (700 + 100) - 100;
		
		Path initialPath = new Path(startingPoint, new Vector2(random.nextFloat() * 800, random.nextFloat() * 600),
				new Vector2(random.nextFloat() * 800, random.nextFloat() * 600),
				new Vector2(random.nextFloat() * 800, random.nextFloat() * 600));
		
		for (int i = curvesCount - 1; i > 0; i--)
			initialPath.addCurve(new Vector2(random.nextFloat() * 800, random.nextFloat() * 600), 
					new Vector2(random.nextFloat() * 800, random.nextFloat() * 600),
					new Vector2(random.nextFloat() * 800, random.nextFloat() * 600));
		
		for (Path path : paths)
		{
			path = initialPath.clone();
			
			Vector2 endingPoint = new Vector2(random.nextFloat() * 800, random.nextFloat() * 600);
			path.addCurve(new Vector2(startingPoint.x, startingPoint.y + 10), new Vector2(endingPoint.x, endingPoint.y - 10), endingPoint);
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
