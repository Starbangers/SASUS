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
	private int currentFinger;
	private boolean justReleased;
	
	public GButton(int _x, int _y, int _w, int _h, String _textureId)
	{
		rect = new Rectangle(_x, _y, _w, _h);
		sprite = new Sprite(Resources.getImage(_textureId));
		sprite.setX(_x);
		sprite.setY(_y);
		currentFinger = -1;
		justReleased = false;
	}
	
	@Override
	public void draw()
	{
		if (currentFinger != -1)
		{
			sprite.setColor(Color.GRAY);
			sprite.draw(SASUS.batch);
			sprite.setColor(Color.WHITE);
		}
		else
			SASUS.batch.draw(sprite.getTexture(), rect.x, rect.y);
	}
	
	public boolean isJustReleased()
	{
		return justReleased;
	}
	
	public boolean isPointedAt()
	{
		Vector2 pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
		return rect.contains(pos);
	}
	
	public float getX()
	{
		return rect.x;
	}
	
	public float getY()
	{
		return rect.y;
	}
	
	@Override
	public void update()
	{
		justReleased = false;
		
		final int FINGERS = 4;
		
		if (currentFinger != -1)
		{
			if (!Gdx.input.isTouched(currentFinger))
			{
				currentFinger = -1;
				justReleased = true;
			}
		}
		else
		{
			if (Gdx.input.justTouched())
			{
				for (int i = 0; i < FINGERS; i++)
				{
					if (Gdx.input.isTouched(i))
					{
						Vector2 pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
						if (rect.contains(pos))
						{
							currentFinger = i;
							break;
						}
					}
				}
			}
		}
	}
}
