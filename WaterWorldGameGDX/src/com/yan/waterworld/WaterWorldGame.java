package com.yan.waterworld;

import java.util.HashMap;
import java.util.Map.Entry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.yan.waterworld.input.TouchGestureListener;
import com.yan.waterworld.input.TouchUpGestureDetector;
import com.yan.waterworld.screens.BaseScreen;
import com.yan.waterworld.screens.SplashScreen;

public class WaterWorldGame extends Game /* implements GestureListener */{

	private static final String LOG_TAG = WaterWorldGame.class.getSimpleName();
	public static final float TARGET_WIDTH = 480;
	public static final float TARGET_HEIGHT = 800;

	// we need to manage some screens
	private HashMap<Class<? extends BaseScreen>, BaseScreen> mScreensHashMap;
	private AssetManager mAssetManager;
	private OrthographicCamera mCamera;
	private SpriteBatch mBatch;
	private FPSLogger mFpsLogger;

	public WaterWorldGame() {
		super();

		mScreensHashMap = new HashMap<Class<? extends BaseScreen>, BaseScreen>();
		mAssetManager = new AssetManager();

		// start loading assets
		mAssetManager.load("atlases/water_world_atlas.atlas",
				TextureAtlas.class);

	}

	@Override
	public void create() {

		mFpsLogger = new FPSLogger();
		mCamera = new OrthographicCamera(TARGET_WIDTH, TARGET_HEIGHT);
		mBatch = new SpriteBatch();

		setScreen(new SplashScreen(this));

		// instantiation of an input processor
		Gdx.input.setInputProcessor(new TouchUpGestureDetector(new TouchGestureListener(
				this),this));
	}

	@Override
	public void render() {
		super.render();

//		mFpsLogger.log();

		// mBatch.begin();
		// BitmapFont font = new BitmapFont();
		// font.setScale(2f);
		// font.draw(mBatch, "FPS : " + Gdx.graphics.getFramesPerSecond(), -20,
		// 0);
		// mBatch.end();
	}

	@Override
	public void dispose() {
		super.dispose();

		mBatch.dispose();

		// dispose of all screens
		for (Entry<Class<? extends BaseScreen>, BaseScreen> entry : mScreensHashMap
				.entrySet()) {
			entry.getValue().dispose();
		}
	}

	public AssetManager getAssetManager() {
		return mAssetManager;
	}

	public HashMap<Class<? extends BaseScreen>, BaseScreen> getScreensHashMap() {
		return mScreensHashMap;
	}

	public OrthographicCamera getCamera() {
		return mCamera;
	}

	public SpriteBatch getBatch() {
		return mBatch;
	}

}
