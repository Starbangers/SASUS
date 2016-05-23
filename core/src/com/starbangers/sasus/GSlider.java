package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GSlider extends GElement
{
	private Rectangle rect;
	private Sprite sliderScale;
	private Sprite sliderSlide;
	
	private int value;
	private boolean selected;
	
	
	
	GSlider(int _x, int _y)
	{
		rect = new Rectangle(_x, _y, 300, 50);
		
		sliderScale = new Sprite(Resources.getImage("interface/SliderScale"));
		sliderScale.setX(_x);
		sliderScale.setY(_y);
		
		sliderSlide = new Sprite(Resources.getImage("interface/SliderSlide"));
		sliderSlide.setX(_x + 18/2);
		sliderSlide.setY(_y);
		
		value = 0;
		selected = false;
	}
	
	public int getValue()
	{
		return value;
	}
	
	@Override
	public void draw()
	{
		sliderScale.draw(SASUS.batch);
		sliderSlide.draw(SASUS.batch);
	}

	@Override
	public void update() 
	{
		if (Gdx.input.isTouched())
		{
			Vector2 pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
			if (!selected && rect.contains(pos))
				selected = true;
			if (pos.x < rect.x)
			{
				value = 0;
				sliderSlide.setX(rect.x + 18/2);
			}
			else if (pos.x > rect.x + rect.width)
			{
				value = 100;
				sliderSlide.setX(rect.x + rect.width - 18/2);
			}
			else
			{
				value = ((int)(pos.x) * 100)/((int)(rect.x + rect.width));
				sliderSlide.setX(pos.x);
			}
		}
		else
			selected = false;
	}

}
