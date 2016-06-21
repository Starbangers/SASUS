package com.starbangers.sasus;

import java.util.Random;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup
{
	private float startTime, cooldownTime = 1;
	private int nextEnemy, curvesCount;
	private boolean isDestroyed;
	
	private Vector2 startingPoint;
	private Array<Path> paths;
	
	private Array<Enemy> enemies;
	
	public EnemyGroup(float _startTime, Array<Enemy> _enemies, Random _random)
	{
		enemies = _enemies;
		startTime = _startTime;
		curvesCount = _random.nextInt(5) + 1;
		
		paths = new Array<Path>();
		
		startingPoint = new Vector2();

		startingPoint.x = _random.nextFloat() > 0.5 ? -100 : 900;
		startingPoint.y = _random.nextFloat() * (700 + 100) - 100;
		
		int size = enemies.get(0).getSize();
		
		Path initialPath = new Path(startingPoint, new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200),
				new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200),
				new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200));
		
		for (int i = curvesCount - 1; i > 0; i--)
			initialPath.addCurve(new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200), 
					new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200),
					new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200));
		
		for (int i = 0; i < enemies.size; i ++)
		{
			Path path = initialPath.clone();
			
			Vector2 pathLastPoint = path.getLastPoint();
			Vector2 endingPoint = new Vector2(_random.nextFloat() * (800 - size), _random.nextFloat() * ((600 - size) - 200) + 200);
			path.addCurve(new Vector2(pathLastPoint.x, pathLastPoint.y + 10), new Vector2(endingPoint.x, endingPoint.y - 10), endingPoint);
			
			paths.add(path);
		}
	}
	
	public void register()
	{
		GameMaster.groups.add(this);
	}
	
	public boolean isDead()
	{
		return isDestroyed;
	}
	
	public void tick(double deltaT)
	{
		cooldownTime -= deltaT;
		
		if (GameMaster.getWaveTime() > startTime && nextEnemy < enemies.size && cooldownTime < 0)
		{
			enemies.get(nextEnemy).setPos(startingPoint.x, startingPoint.y).spawn();
			nextEnemy++;
			
			cooldownTime = 0.5f;
		}
		
		for (int i = nextEnemy - 1; i >= 0; i--)
		{
			if (enemies.get(i).isDead)
			{
				enemies.removeIndex(i);
				paths.removeIndex(i);
				nextEnemy--;
				
				isDestroyed = enemies.size == 0;
			}
			else
			{
				paths.get(i).tick(deltaT);
				Vector2 newPos = paths.get(i).getCurrentPoint();
				enemies.get(i).goalX = newPos.x;
				enemies.get(i).goalY = newPos.y;
			}
		}
	}
}
