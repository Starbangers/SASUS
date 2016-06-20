package com.starbangers.sasus;

import java.util.Random;

import com.badlogic.gdx.utils.Array;

public class GameMaster {
	
	int waveNum = 0;
	
	static float waveTime = 0;
	int wave = 0;
	static int level = 1;
	
	static Array<EnemyGroup> groups = new Array<EnemyGroup>();
	
	static boolean countedDown, saidStageNum;
	public static void startLevel() {
		waveTime = -7;
		countedDown = false;
		saidStageNum = false;
		Resources.playSound("vox/stage");
		
		//REPLACE WITH DIFFICULTY_BASED CHOICE
		for(int i = 0; i < Math.random()*3+1; i ++)
			new EnemyGroup(i*10, (int)Math.random()*9+1, new Random());
	}
	public static float getWaveTime() {
		return waveTime;
	}
	public static void tick(double deltaT) {
		waveTime += deltaT;
		
		for(EnemyGroup group : groups) {
			if(group.isDead())
				groups.removeValue(group, true);
			else
				group.tick(deltaT);
		}
		if(groups.size == 0 && waveTime > 10) {
			level++;
			startLevel();
		}
		
		if(waveTime > -5 && !countedDown) {
			Resources.playSound("vox/countdown");
			countedDown = true;
		}
		if(waveTime > -6.2 && !saidStageNum) {
			Resources.playSound("vox/"+level);
			saidStageNum = true;
		}
	}
}
