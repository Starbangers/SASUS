package com.starbangers.sasus;

public class GImage extends GElement
{
	private int x, y;
	private String textureId;
	
	GImage(int _x, int _y, String _textureId)
	{
		x = _x;
		y = _y;
		textureId = _textureId;
	}
	
	@Override
	public void draw()
	{
		SASUS.batch.draw(Resources.getImage(textureId), x, y);
	}

	@Override
	public void update(){}

}
