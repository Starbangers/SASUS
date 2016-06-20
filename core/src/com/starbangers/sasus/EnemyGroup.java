package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class EnemyGroup
{
	private int enemiesNumber;
	private float spawnT;
	private BezierCurve path;
	
	private Vector2 start;
	
	private Array<Enemy> createdEnemies;
	private Array<Float> enemyT;
	
	private int lastSpawned;
	
	public EnemyGroup(int _enemiesNumber, float _spawnT, BezierCurve _path)
	{
		enemiesNumber = _enemiesNumber;
		spawnT = _spawnT;
		path = _path;
	
		start = path.calculatePointForT(0);
		createdEnemies = new Array<Enemy>();
		enemyT = new Array<Float>();
		
		lastSpawned = -1;
		createEnemy();
	}
	
	public void tick(double deltaT)
	{
		for (int i = 0; i < createdEnemies.size; i++)
		{
			if (enemyT.get(i) + deltaT <= 1)
				enemyT.set(i, (float) (enemyT.get(i) + deltaT));			
			
			Vector2 newPos = path.calculatePointForT(enemyT.get(i));
			createdEnemies.get(i).goalX = newPos.x;
			createdEnemies.get(i).goalY = newPos.y;
		}
		
		if ((enemyT.get(lastSpawned) >= spawnT) && (lastSpawned < enemiesNumber - 1))
			createEnemy();
	}
	
	private void createEnemy()
	{
		createdEnemies.add(new Enemy().setPos(start.x, start.y));
		createdEnemies.peek().spawn();
		enemyT.add(0f);
		
		lastSpawned++;
	}
}
