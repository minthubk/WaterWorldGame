package com.yan.waterworld.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yan.waterworld.WaterWorldGame;
import com.yan.waterworld.resources.ResourcePaths;
import com.yan.waterworld.resources.TextureNames;

public class SplashScreen extends BaseScreen {

	protected static final String LOG_TAG = SplashScreen.class.getSimpleName();

	// duration in seconds
	private static final int SPLASH_SCREEN_DURATION = 3;

	// private Texture texture;
	private Sprite mBackgroundSprite;
	private float mUptimeCounter;
	private boolean mSpriteInitialized;

	public SplashScreen(WaterWorldGame game) {
		super(game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		mUptimeCounter += delta;

		if (mGame.getAssetManager().update()) {
			log("Resource Loaded");

			if (!mSpriteInitialized) {
				initSprite();
			}

			// draw sprite
			mBatch.begin();
			mBackgroundSprite.draw(mBatch);
			mBatch.end();

			// show splash screen for a few seconds
			if (mUptimeCounter < SPLASH_SCREEN_DURATION)
				return;

			// now we are ready to go to the next screen
			BaseScreen mainScreen = new MainScreen(mGame);

			// TODO transition to the next Screen and
			mGame.setScreen(mainScreen);

			// We will keep reference to allocated MainScreen for future reuse
			mGame.getScreensHashMap().put(getClass(), mainScreen);

			// we don't need current screen anymore
			this.dispose();

		}
	}

	private void initSprite() {
		// At this point asset manager finished loading
		TextureAtlas atlas = mGame.getAssetManager().get(
				ResourcePaths.ATLAS_PACK, TextureAtlas.class);

		mBackgroundSprite = atlas.createSprite(TextureNames.SPLASH_TEXTURE);

		// set origin in the middle
		mBackgroundSprite.setOrigin(mBackgroundSprite.getWidth() / 2,
				mBackgroundSprite.getHeight() / 2);

		// position in the middle of the screen
		mBackgroundSprite.setPosition(-mBackgroundSprite.getWidth() / 2,
				-mBackgroundSprite.getHeight() / 2);

		mSpriteInitialized = true;
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		// texture.dispose();
	}

	@Override
	protected void onTapConfirmed(float x, float y) {
		log("Unprojected on x:y " + x + ":" + y);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}



}
