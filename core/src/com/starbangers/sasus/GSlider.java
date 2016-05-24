package com.starbangers.sasus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GSlider extends GElement
{
	private Rectangle scaleRect, slideRect;
	private Sprite sliderScale;
	private Sprite sliderSlide;
	
	private int value;
	private boolean selected;
	
	GSlider(int _x, int _y, int _value)
	{
		scaleRect = new Rectangle(_x, _y, 300, 50);
		
		sliderScale = new Sprite(Resources.getImage("interface/SliderScale"));
		sliderScale.setX(scaleRect.x);
		sliderScale.setY(scaleRect.y);
		
		sliderSlide = new Sprite(Resources.getImage("interface/SliderSlide"));
		
		if (_value < 0)
		{
			sliderSlide.setX(scaleRect.x - 18/2);
			value = 0;
		}
		else if (_value > 100)
		{
			sliderSlide.setX(scaleRect.x + scaleRect.width - 18/2);
			value = 100;
		}
		else
		{
			sliderSlide.setX(_value * scaleRect.width / 100 + scaleRect.x - 18/2);
			value = _value;
		}
		
		sliderSlide.setY(scaleRect.y);
		
		slideRect = new Rectangle(sliderSlide.getX(), sliderSlide.getY(), 18, 50);
		
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
		Vector2 pos;
		
		if(Gdx.input.justTouched())
		{
			pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
			
			if (scaleRect.contains(pos) || slideRect.contains(pos))
				selected = true;
		}
		
		if(Gdx.input.isTouched() && selected)
		{
			pos = SASUS.viewport.unproject(new Vector2(Gdx.input.getX(), Gdx.input.getY()));
			
			if (pos.x < scaleRect.x)
			{
				value = 0;
				sliderSlide.setX(scaleRect.x - 18/2);
			}
			else if (pos.x > scaleRect.x + scaleRect.width)
			{
				value = 100;
				sliderSlide.setX(scaleRect.x + scaleRect.width - 18/2);
			}
			else
			{
				value = ((int)(pos.x - scaleRect.x) * 100)/((int)(scaleRect.width));
				sliderSlide.setX(pos.x - 18/2);
			}
				
			slideRect.setX(sliderSlide.getX());
		}
		else
			selected = false;
	}
	
	public int getX()
	{
		return (int)scaleRect.x;
	}
	
	public int getY()
	{
		return (int)scaleRect.y;
	}
}
