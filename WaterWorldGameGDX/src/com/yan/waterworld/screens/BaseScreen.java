package com.yan.waterworld.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.yan.waterworld.WaterWorldGame;
import com.yan.waterworld.input.TouchListener;

public abstract class BaseScreen implements Screen, TouchListener {

	protected static final String LOG_TAG = BaseScreen.class.getSimpleName();

	protected OrthographicCamera mCamera;
	protected SpriteBatch mBatch;
	protected WaterWorldGame mGame;

	public BaseScreen(WaterWorldGame game) {
		mGame = game;
		mCamera = game.getCamera();
		mBatch = game.getBatch();
	}

	protected void log(String msg) {
		Gdx.app.log(LOG_TAG, msg);
	}

	@Override
	public void resize(int width, int height) {
		// No Resize
	}

	@Override
	public void render(float delta) {
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// set Projection Matrix
		mBatch.setProjectionMatrix(mCamera.combined);
	}

	@Override
	public void onTap(float x, float y) {

		// TODO create vector Pool

		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		mCamera.unproject(vec);

		// delegate to derived screen
		onTapConfirmed(vec.x, vec.y);
	}
	
	@Override
	public void onTouchDown(float x, float y) {
		// TODO create vector Pool

		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		mCamera.unproject(vec);

		// delegate to derived screen
		ononTouchDownConfirmed(vec.x, vec.y);
	}
	
	@Override
	public void onTouchUp(float x, float y) {
		// TODO create vector Pool

		// translate to world coordinates
		Vector3 vec = new Vector3(x, y, 0);
		mCamera.unproject(vec);

		// delegate to derived screen
		onTouchUpConfirmed(vec.x, vec.y);
		
	}

	protected void onTouchUpConfirmed(float x, float y) {
	}

	protected void ononTouchDownConfirmed(float x, float y) {
	}

	protected abstract void onTapConfirmed(float x, float y);

}