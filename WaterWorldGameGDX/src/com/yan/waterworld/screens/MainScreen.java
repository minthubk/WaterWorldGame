package com.yan.waterworld.screens;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.sun.xml.internal.bind.v2.util.CollisionCheckStack;
import com.yan.waterworld.WaterWorldGame;
import com.yan.waterworld.gameobjects.Bubble;
import com.yan.waterworld.gameobjects.Button;
import com.yan.waterworld.gameobjects.Button.ButtonState;
import com.yan.waterworld.resources.ResourcePaths;
import com.yan.waterworld.resources.TextureNames;

public class MainScreen extends BaseScreen {

	private static final int BUBLES_COUNT = 15;

	private Sprite mBackgroundSprite;
	private Sprite mWaterWorldTitle;
	private Sprite mBuble1Sprite;
	private Sprite mBuble2Sprite;

	private Sprite mStartBtnSprite;
	private Sprite mExitBtnSprite;
	private Sprite mVolumeBtnSprite;

	private Sprite mStartBtnSpritePressed;
	private Sprite mExitBtnSpritePressed;
	private Sprite mVolumeBtnSpritePressed;

	private Button mStartButtonModel;
	private Button mExitButtonModel;
	private Button mVolumeButtonModel;

	private ArrayList<Bubble> mBubblesArrayList;

	public MainScreen(WaterWorldGame game) {
		super(game);

		// At this point asset manager finished loading
		TextureAtlas atlas = mGame.getAssetManager().get(
				ResourcePaths.ATLAS_PACK, TextureAtlas.class);

		// load all sprites that used in these screen
		loadSprites(atlas);
		positionSprites();

		// create bubbles
		mBubblesArrayList = new ArrayList<Bubble>();
		for (int i = 0; i < BUBLES_COUNT; i++) {
			mBubblesArrayList.add(new Bubble(WaterWorldGame.TARGET_WIDTH,
					WaterWorldGame.TARGET_HEIGHT));
		}

		// allocate button models
		mStartButtonModel = new Button(new Vector2(mStartBtnSprite.getX(),
				mStartBtnSprite.getY()));
		mExitButtonModel = new Button(new Vector2(mExitBtnSprite.getX(),
				mExitBtnSprite.getY()));
		mVolumeButtonModel = new Button(new Vector2(mVolumeBtnSprite.getX(),
				mVolumeBtnSprite.getY()));

	}

	private void positionSprites() {

		// position in the middle of the screen
		mBackgroundSprite.setPosition(-mBackgroundSprite.getWidth() / 2,
				-mBackgroundSprite.getHeight() / 2);

		// position on the middle top of the screen
		mWaterWorldTitle.setPosition(0 - mWaterWorldTitle.getWidth() / 2,
				(float) ((WaterWorldGame.TARGET_HEIGHT / 2) - (mWaterWorldTitle
						.getHeight() * 1.1)));

		// position buttons
		mStartBtnSprite.setPosition(
				(float) (-1.3 * mStartBtnSprite.getWidth()), -50);

		mExitBtnSprite
				.setPosition((float) (mStartBtnSprite.getX() + (mExitBtnSprite
						.getWidth() * 1.2)),
						(mStartBtnSprite.getY() - mExitBtnSprite.getHeight()));

		mVolumeBtnSprite
				.setPosition((float) (mExitBtnSprite.getX() + mExitBtnSprite
						.getWidth() * 1.6), -mExitBtnSprite.getHeight() * 2);

		// for pressed sprites position is the same
		mStartBtnSpritePressed.setPosition(mStartBtnSprite.getX(),
				mStartBtnSprite.getY());
		mExitBtnSpritePressed.setPosition(mExitBtnSprite.getX(),
				mExitBtnSprite.getY());
		mVolumeBtnSpritePressed.setPosition(mVolumeBtnSprite.getX(),
				mVolumeBtnSprite.getY());
	}

	private void loadSprites(TextureAtlas atlas) {

		// we will obtain sprite immediately from atlas
		mBackgroundSprite = atlas
				.createSprite(TextureNames.MAIN_SCREEN_BACKGROUND);
		// title
		mWaterWorldTitle = atlas.createSprite(TextureNames.WATER_WORLD_TITLE);

		// bubbles
		mBuble1Sprite = atlas.createSprite(TextureNames.BUBLE, 1);
		mBuble2Sprite = atlas.createSprite(TextureNames.BUBLE, 2);

		// buttons
		mStartBtnSprite = atlas.createSprite(TextureNames.START_BUTTON_DEFAULT);
		mExitBtnSprite = atlas.createSprite(TextureNames.EXIT_BUTTON_DEFAULT);
		mVolumeBtnSprite = atlas
				.createSprite(TextureNames.VOLUME_BUTTON_DEFAULT);

		mStartBtnSpritePressed = atlas
				.createSprite(TextureNames.START_BUTTON_PRESSED);
		mExitBtnSpritePressed = atlas
				.createSprite(TextureNames.EXIT_BUTTON_PRESSED);
		mVolumeBtnSpritePressed = atlas
				.createSprite(TextureNames.VOLUME_BUTTON_PRESSED);

	}

	@Override
	public void render(float delta) {
		super.render(delta);

		// draw begin
		mBatch.begin();

		// draw background
		mBackgroundSprite.draw(mBatch);

		// draw bubbles
		for (Bubble bubble : mBubblesArrayList) {

			// get sprite according to bubble type
			Sprite bubbleSprite = (bubble.getBubbleType() == Bubble.BUBBLE_TYPE_0) ? mBuble1Sprite
					: mBuble2Sprite;

			// update bubble position
			bubble.update(delta);

			// draw bubble using sprite
			bubbleSprite.setPosition(bubble.getPosition().x,
					bubble.getPosition().y);

			bubbleSprite.draw(mBatch);
		}

		// draw title
		mWaterWorldTitle.draw(mBatch);

		// draw buttons according to their state
		Sprite startBtnSprite = (mStartButtonModel.getButtonState() == ButtonState.DEFAULT) ? mStartBtnSprite
				: mStartBtnSpritePressed;

		Sprite exitBtnSprite = (mExitButtonModel.getButtonState() == ButtonState.DEFAULT) ? mExitBtnSprite
				: mExitBtnSpritePressed;

		Sprite volumeBtnSprite = (mVolumeButtonModel.getButtonState() == ButtonState.DEFAULT) ? mVolumeBtnSprite
				: mVolumeBtnSpritePressed;

		startBtnSprite.draw(mBatch);
		exitBtnSprite.draw(mBatch);
		volumeBtnSprite.draw(mBatch);

		// end draw
		mBatch.end();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTapConfirmed(float x, float y) {
		log("tap on : " + x + " : " + y);

		// clear states of all buttons
		mStartButtonModel.changeState(ButtonState.DEFAULT);
		mExitButtonModel.changeState(ButtonState.DEFAULT);

		if (mStartBtnSprite.getBoundingRectangle().contains(x, y)) {
			log("Start Button Pressed");
			// TODO go to game screen
		}

		else if (mExitBtnSprite.getBoundingRectangle().contains(x, y)) {
			log("Exit Button Pressed");
			// TODO exit game 
		}

		else if (mVolumeBtnSprite.getBoundingRectangle().contains(x, y)) {
			log("Volume Button Pressed");
			mVolumeButtonModel.toggleState();
			
			//TODO handle Volume State
		}

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onTouchUpConfirmed(float x, float y) {
		super.onTouchUpConfirmed(x, y);

		// release both buttons
		mStartButtonModel.changeState(ButtonState.DEFAULT);
		mExitButtonModel.changeState(ButtonState.DEFAULT);
	}

	@Override
	public void ononTouchDownConfirmed(float x, float y) {

		if (mStartBtnSprite.getBoundingRectangle().contains(x, y))
			mStartButtonModel.changeState(ButtonState.PRESSED);

		else if (mExitBtnSprite.getBoundingRectangle().contains(x, y))
			mExitButtonModel.changeState(ButtonState.PRESSED);
	}

}
