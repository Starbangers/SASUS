package com.starbangers.sasus;

public class GameMaster {
	
	int waveNum = 0;
	
	static float waveTime = 0;
	int wave = 0;
	static int level = 1;
	
	
	static boolean countedDown, saidStageNum;
	public static void startLevel() {
		waveTime = -7;
		countedDown = false;
		saidStageNum = false;
		Resources.playSound("vox/stage");
	}
	
	public static void tick(double deltaT) {
		waveTime += deltaT;
		
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
