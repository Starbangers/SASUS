package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;

public class EnemyGroup
{
	private float startTime;
	private int enemiesCount, curvesCount;
	private Vector2 startingPoint;
	
	public EnemyGroup(float _startTime, int _enemiesCount, int _curvesCount)
	{
		startTime = _startTime;
		enemiesCount = _enemiesCount;
		curvesCount = _curvesCount;
		
		startingPoint = new Vector2();
		startingPoint.x = Math.random() > 0.5 ? -100 : 900;
		startingPoint.y = (float) (Math.random() * 800 - 100);
	}
	
	public void register() {
		GameMaster.groups.add(this);
	}
	
}
