package com.yan.waterworld.gameobjects;

import com.badlogic.gdx.math.Vector2;

public abstract class StaticGameObject {

	protected Vector2 mPosition;

	public StaticGameObject(Vector2 position) {
		mPosition = position;
	}

	public void setPosition(float x, float y) {
		mPosition.x = x;
		mPosition.y = y;
	}
	
	protected void update(float delta){
		//no default implementation
	}

	public Vector2 getPosition() {
		return mPosition;
	}

	public void setPosition(Vector2 position) {
		mPosition = position;
	}

}