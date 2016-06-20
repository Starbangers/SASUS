package com.starbangers.sasus;

import com.badlogic.gdx.math.Vector2;

public class BezierCurve // Cubic Bezier curve
{
	private Vector2 A, B, C, D;
	private Vector2 tempPoint1, tempPoint2, tempPoint3;
	
	BezierCurve(Vector2 _A, Vector2 _B, Vector2 _C, Vector2 _D)
	{
		A = _A;
		B = _B;
		C = _C;
		D = _D;
	}
	
	Vector2 calculatePointForT(float t)
	{
		tempPoint1 = divideLineSegment(A, B, t);
		tempPoint2 = divideLineSegment(B, C, t);
		tempPoint3 = divideLineSegment(C, D, t);
		
		tempPoint1 = divideLineSegment(tempPoint1, tempPoint2, t);
		tempPoint2 = divideLineSegment(tempPoint2, tempPoint3, t);
		
		tempPoint1 = divideLineSegment(tempPoint1, tempPoint2, t);
		
		return tempPoint1;
	}
	
	Vector2 divideLineSegment(Vector2 _start, Vector2 _end, float k)
	{
		return new Vector2(_start.x + k * (_end.x - _start.x), _start.y + k * (_end.y - _start.y));
	}
	
	public void setA(Vector2 _A)
	{
		A = _A;
	}
	
	public void setB(Vector2 _B)
	{
		B = _B;
	}
	
	public void setC(Vector2 _C)
	{
		C = _C;
	}
	
	public void setD(Vector2 _D)
	{
		D = _D;
	}
	
	public Vector2 getA()
	{
		return A;
	}
	
	public Vector2 getB()
	{
		return B;
	}
	
	public Vector2 getC()
	{
		return C;
	}
	
	public Vector2 getD()
	{
		return D;
	}
	
	public BezierCurve clone()
	{
		return new BezierCurve(A, B, C, D);
	}
}
