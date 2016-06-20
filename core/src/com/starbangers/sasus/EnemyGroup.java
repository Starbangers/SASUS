package com.starbangers.sasus;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup
{
	private Random random;
	private float startTime, cooldownTime = 1;
	private int enemiesCount, curvesCount;
	
	private Vector2 startingPoint;
	private Array<Path> paths;
	
	private Array<Enemy> enemies;
	
	public EnemyGroup(float _startTime, int _enemiesCount, Random _random)
	{
		enemies = new Array<Enemy>();
		random = _random;
		startTime = _startTime;
		enemiesCount = _enemiesCount;
		curvesCount = random.nextInt(5) + 1;
		
		paths = new Array<Path>();
		
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
		
		for (int i = 0; i < enemiesCount; i ++)
		{
			Path path = initialPath.clone();
			
			Vector2 endingPoint = new Vector2(random.nextFloat() * 800, random.nextFloat() * 600);
			path.addCurve(new Vector2(startingPoint.x, startingPoint.y + 10), new Vector2(endingPoint.x, endingPoint.y - 10), endingPoint);
			
			paths.add(path);
		}
	}
	
	public void register()
	{
		GameMaster.groups.add(this);
	}
	
	public boolean isDead()
	{
		return false;
	}
	
	public void tick(double deltaT)
	{
		cooldownTime -= deltaT;
		
		if (GameMaster.getWaveTime() > startTime && enemies.size < enemiesCount && cooldownTime < 0)
		{
			enemies.add(new Enemy());
			enemies.peek().spawn();
			enemies.peek().setPos(startingPoint.x, startingPoint.y);
			
			cooldownTime = 1;
		}
		
		for (int i = 0; i < enemies.size; i++)
		{
			paths.get(i).tick(deltaT);
			Vector2 newPos = paths.get(i).getCurrentPoint();
			enemies.get(i).goalX = newPos.x;
			enemies.get(i).goalY = newPos.y;
		}
	}
}
