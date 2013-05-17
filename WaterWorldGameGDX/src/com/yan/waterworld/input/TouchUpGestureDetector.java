package com.yan.waterworld.input;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.input.GestureDetector;

public class TouchUpGestureDetector extends GestureDetector {

	private Game mGame;

	public TouchUpGestureDetector(float halfTapSquareSize,
			float tapCountInterval, float longPressDuration,
			float maxFlingDelay, GestureListener listener, Game game) {
		super(halfTapSquareSize, tapCountInterval, longPressDuration,
				maxFlingDelay, listener);

		mGame = game;
	}

	public TouchUpGestureDetector(GestureListener listener, Game game) {
		super(listener);
		mGame = game;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		//delegate to current screen
		((TouchListener) mGame.getScreen()).onTouchUp(x, y);
		return super.touchUp(x, y, pointer, button);
	}

}
