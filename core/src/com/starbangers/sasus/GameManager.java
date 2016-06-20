package com.starbangers.sasus;

public class GameManager {
	
	public static void beginGame() {
		CurGame.reset();
		SASUS.gmae.setScreen(new GameScreen());
		Resources.playMusic("sanch-disaster");
		GameMaster.startLevel();
	}
}
