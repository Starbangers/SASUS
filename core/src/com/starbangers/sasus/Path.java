package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Path
{
	private Array<BezierCurve> curves;
	private int currentCurve;
	private float t;
	
	private boolean finished;

	public Path(Vector2 _A, Vector2 _B, Vector2 _C, Vector2 _D)
	{
		curves = new Array<BezierCurve>();
		curves.add(new BezierCurve(_A, _B, _C, _D));
	}
	
	public Path(BezierCurve _curve)
	{
		curves = new Array<BezierCurve>();
		curves.add(_curve);
	}
	
	private Path(Array<BezierCurve> _curves)
	{
		curves = new Array<BezierCurve>();
		
		for (BezierCurve curve : _curves)
			curves.add(curve);
	}
	
	public void addCurve(Vector2 _B, Vector2 _C, Vector2 _D)
	{
		curves.add(new BezierCurve(curves.peek().getD(), _B, _C, _D));
	}
	
	public void tick(double deltaT)
	{
		if (finished)
			return;
		
		t += deltaT * 0.05f;
			
		if (t >= 1)
		{
			t = 0;
			currentCurve++;
		}
		
		if (currentCurve == curves.size)
		{
			t = 1;
			currentCurve--;
			finished = true;
		}
	}
	
	public Vector2 getCurrentPoint()
	{
		return curves.get(currentCurve).calculatePointForT(t);
	}
	
	public boolean isFinished()
	{
		return finished;
	}
	
	public Path clone()
	{
		return new Path(curves);
	}
}
