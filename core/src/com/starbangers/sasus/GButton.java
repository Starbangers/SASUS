package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GButton
{
	public Rectangle rect;
	public Sprite sprite;
	public boolean isPressed;
	
	public GButton(int _x, int _y, int _w, int _h, String _textureId)
	{
		rect = new Rectangle(_x, _y, _w, _h);
		sprite = new Sprite(Resources.getImage(_textureId), _x, _y, _w, _h);
		isPressed = false;
	}
	
	public void draw()
	{
		if (isPressed)
		{
			Color color = sprite.getColor();
			sprite.setColor(Color.GRAY);
			sprite.draw(SASUS.batch);
			sprite.setColor(color);
		}
		else
			SASUS.batch.draw(sprite.getTexture(), rect.x, rect.y);
	}
	
	public void update()
	{
		isPressed = false;
		
		final int fingers = 4;
		
		outerloop:
		for (int i = 0; i < fingers; i++)
		{
			if (Gdx.input.isTouched(i))
			{
				Vector2 pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
				if (rect.contains(pos))
				{
					isPressed = true;
					break outerloop;
				}
			}
		}
	}
}
