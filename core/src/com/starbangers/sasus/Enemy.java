package com.starbangers.sasus;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class Enemy extends Entity {

	float goalX, goalY, rot, goalRot;
	boolean jiggle;
	float velX, velY;
	float moveTime;
	float jiggleTime;
	float particleTime = 0;
	Sprite sprite;
	
	int health = 4;
	
	public Enemy() {
		sprite = new Sprite(Resources.getImage("enemies/tiny"));
		sprite.setOrigin(31.5f, 31.5f);
	}
	public Enemy setPos(float x, float y) {
		this.x = x;
		this.y = y;
		return this;
	}
	public void spawn() {
		CurGame.entities.add(this);
	}
	public void getHit(int damage, float mx, float my) {
		this.health -= damage;
		Resources.playSound("enemy/hit2");
		//this.velX += mx/3;
		//this.velY += my/3;
		if(this.health <= 0) {
			isDead = true;
			Resources.playSound("enemy/explode");
			for(int i = 0; i < 80; i ++) {
				new Particle(Particle.Shape.SQUARE).setPos(this.x + (float)Math.random()*this.getSize(), (float) (this.y + Math.random()*this.getSize()))
				.setVel((float)Math.random()*200 - 100, (float)Math.random()*100)
				.setFadingColors(1, 1, 0, 0.6f, 0, 0)
				.setSizeAndDecay(8, 4)
				.setGravity(500)
				.spawn();
			}
		}
	}
	
	@Override
	public boolean tick(float deltaT) {
		float aX, aY, jGoalX;
		
		
		if(Math.random()>0.99) {
			CurGame.entities.add(new Projectile(x+31.5f+(float)Math.cos(rot+Math.PI/2)*-25, y+31.5f+(float)Math.sin(rot+Math.PI/2)*-25, (float)Math.cos(rot+Math.PI/2)*-500, (float)Math.sin(rot+Math.PI/2)*-500, 1, 0, 1, 8, false, 1));
		}
		
		
		if(this.isDead) return true;
		//jGoalX = goalX + (jiggle ? 30 : -30);
		jGoalX = goalX;
		//if(Math.abs(jGoalX-x)>10)
		aX = Math.min( Math.max( ((jGoalX-x)-velX), -500), 500);
		//else
		//aX = 0;
		velX += aX*deltaT;
		//if(Math.abs(goalY-y)>10)
		aY = Math.min( Math.max( ((goalY-y)-velY), -500), 500);
		velY += aY*deltaT;
		
		rot += (goalRot - rot)*deltaT*2;
		goalRot = -aX/200;
		
		x += velX*deltaT;
		y += velY*deltaT;
		
		
		
		//RANDOM MOVEMENT, TO BE REPLACED WITH VANYA's STUFF
		/*moveTime -= deltaT;
		if(moveTime < 0) {
			
			moveTime = (float) (1 + Math.random()*3);
			this.goalX = (float) (32+Math.random()*700);
			this.goalY = (float) (150+Math.random()*400);
			//Gdx.app.log("AI", "Moving to "+(int)goalX+":"+(int)goalY);
		}*/
		particleTime++;
		if(particleTime > 1/(Math.abs(velX/200)+Math.abs(velY/200)+0.1)/*&&(Math.sqrt(aX*aX+aY*aY)>100)*/) {
			particleTime = 0;
			new Particle(Particle.Shape.SQUARE).setColor(1, 0, 0.7f+(float)Math.random()*0.2f)
			.setPos(x+31.5f+(float)Math.cos(rot+Math.PI/2+Math.PI/6)*-25, y+31.5f+(float)Math.sin(rot+Math.PI/2+Math.PI/6)*-25)
			.setVel((float)(Math.random()*80-40), -20 - Math.abs(velX)+Math.abs(velY)*1.5f)
			.setSizeAndDecay(6, 8).spawn();
			
			new Particle(Particle.Shape.SQUARE).setColor(1, 0, 0.7f+(float)Math.random()*0.2f)
			.setPos(x+31.5f+(float)Math.cos(rot+Math.PI/2-Math.PI/6)*-25, y+31.5f+(float)Math.sin(rot+Math.PI/2-Math.PI/6)*-25)
			.setVel((float)(Math.random()*80-40), -20 - Math.abs(velX)+Math.abs(velY)*1.5f)
			.setSizeAndDecay(6, 8).spawn();
		}
		
		return false;
	}

	public int getSize() {
		return 64;
	}
	
	public static Array<Enemy> makeArray(int size) {
		Array<Enemy> enem = new Array<Enemy>();
		for(int i = 0; i < size; i ++)
			enem.add(new Enemy());
		return enem;
	}
	
	@Override
	public void draw() {
		sprite.setPosition(x, y);
		sprite.setRotation((float) Math.toDegrees(rot));
		sprite.draw(SASUS.batch);
	}

	@Override
	public void drawShapes() {
		// TODO Auto-generated method stub
		
	}

}
