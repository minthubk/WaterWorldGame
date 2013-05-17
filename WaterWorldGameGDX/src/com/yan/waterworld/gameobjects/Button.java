package com.yan.waterworld.gameobjects;

import com.badlogic.gdx.math.Vector2;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;

public class Button extends StaticGameObject {

	private ButtonState mButtonState;

	public enum ButtonState {
		DEFAULT, PRESSED
	}

	public Button(Vector2 position) {
		super(position);

		// initial state is default
		mButtonState = ButtonState.DEFAULT;
	}

	public ButtonState getButtonState() {
		return mButtonState;
	}

	public void toggleState() {
		if (mButtonState == ButtonState.DEFAULT)
			mButtonState = ButtonState.PRESSED;
		else
			mButtonState = ButtonState.DEFAULT;
	}

	public void changeState(ButtonState newButtonState) {
		if (mButtonState == newButtonState)
			return;

		mButtonState = newButtonState;
	}

	public void setButtonState(ButtonState buttonState) {
		mButtonState = buttonState;
	}

}
