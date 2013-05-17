package com.yan.waterworld.gameobjects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Bubble extends StaticGameObject {

	public static final int BUBBLE_TYPE_1 = 1;
	public static final int BUBBLE_TYPE_0 = 0;
	private static final int MAX_ACCELERATION = 30;
	private static final int MAX_VELOCITY = 70;
	private Vector2 mVelocity;
	private Vector2 mAcceleration;
	private float mMaxX;
	private float mMinX;
	private float mMaxY;
	private float mMinY;
	
	//there are 2 resources for bubble 
	private int mBubbleType;

	public Bubble(Vector2 position) {
		super(position);
	}

	public Bubble(Vector2 position, Vector2 velocity, Vector2 acceleration) {
		super(position);
		mVelocity = velocity;
		mAcceleration = acceleration;
	}

	public Bubble(float targetWidth, float targetHeight) {
		super(new Vector2());

		// create vectors
		mVelocity = new Vector2(0, 0);
		mAcceleration = new Vector2(0, 0);

		mMaxX = targetWidth / 2;
		mMinX = -mMaxX;

		mMaxY = targetHeight / 2;
		mMinY = -mMaxY;

		init();
	}

	private void init() {
		
		//random Type
		mBubbleType = MathUtils.random(BUBBLE_TYPE_0, BUBBLE_TYPE_1);

		// between 0 and middle of the screen
		mPosition.x = MathUtils.random(mMinX, mMaxX);
		mPosition.y = MathUtils.random(mMinY, 0);

		// always positive
		mVelocity.y = MathUtils.random(0, MAX_VELOCITY);
		mAcceleration.y = MathUtils.random(0, MAX_ACCELERATION);

	}

	@Override
	public void update(float delta) {

		// update position
		mPosition.x += (delta * mVelocity.x);
		mPosition.y += (delta * mVelocity.y);
		
		//reset the position
		if(mPosition.y > mMaxY)
			init();

		// update velocity
		mVelocity.x += (delta * mAcceleration.x);
		mVelocity.y += (delta * mAcceleration.y);
		
		
	}

	public Vector2 getVelocity() {
		return mVelocity;
	}

	public void setVelocity(Vector2 velocity) {
		mVelocity = velocity;
	}

	public Vector2 getAcceleration() {
		return mAcceleration;
	}

	public void setAcceleration(Vector2 acceleration) {
		mAcceleration = acceleration;
	}

	public int getBubbleType() {
		return mBubbleType;
	}

}
