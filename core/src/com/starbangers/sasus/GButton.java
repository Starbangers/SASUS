package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GButton extends GElement
{
	public Rectangle rect;
	private Sprite sprite;
	public boolean isPressed;
	
	public GButton(int _x, int _y, int _w, int _h, String _textureId)
	{
		rect = new Rectangle(_x, _y, _w, _h);
		sprite = new Sprite(Resources.getImage(_textureId), _x, _y, _w, _h);
		isPressed = false;
	}
	@Override
	public void draw()
	{
		if (isPressed)
		{
			sprite.setColor(Color.GRAY);
			sprite.draw(SASUS.batch);
			sprite.setColor(Color.WHITE);
		}
		else
			SASUS.batch.draw(sprite.getTexture(), rect.x, rect.y);
	}
	@Override
	public void update()
	{
		isPressed = false;
		
		final int FINGERS = 4;
		
		for (int i = 0; i < FINGERS; i++)
		{
			if (Gdx.input.isTouched(i))
			{
				Vector2 pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
				if (rect.contains(pos))
				{
					isPressed = true;
					return;
				}
			}
		}
	}
}
