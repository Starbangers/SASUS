package com.starbangers.sasus;

public class EnemyGroup
{
	float startTime, endTime;
	int count;
	
	public void register() {
		GameMaster.groups.add(this);
	}
	
	public EnemyGroup setStartEndTime(float startTime, float endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		return this;
	}
	
	public EnemyGroup setCount(int count) {
		this.count = count;
		return this;
	}
}
