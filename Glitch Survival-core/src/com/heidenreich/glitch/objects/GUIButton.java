package com.heidenreich.glitch.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heidenreich.glitch.handlers.GlitchInput;

public class GUIButton {

	private boolean clicked;
	private float height;
	private float width;
	private float x;
	private float y;
	private Sprite[] buttons;

	// Creates a new GUIButton
	public GUIButton(Sprite[] buttons, float x, float y) {
		this.buttons = buttons;
		this.x = x;
		this.y = y;
		this.width = buttons[0].getWidth();
		this.height = buttons[0].getHeight();
		clicked = false;

		for (int i = 0; i < this.buttons.length; i++)
			this.buttons[i].setPosition(this.x
					- (this.buttons[i].getWidth() / 2), this.y
					- (this.buttons[i].getHeight() / 2));
	}

	public GUIButton(Sprite button, float x, float y) {
		this.buttons = new Sprite[2];
		this.buttons[0] = button;
		this.buttons[1] = button;
		this.x = x;
		this.y = y;
		this.width = buttons[0].getWidth();
		this.height = buttons[0].getHeight();
		clicked = false;

		for (int i = 0; i < this.buttons.length; i++)
			this.buttons[i].setPosition(this.x
					- (this.buttons[i].getWidth() / 2), this.y
					- (this.buttons[i].getHeight() / 2));

	}

	// Updates the button
	public void update(float dt) {
		if (GlitchInput.isPressed() && GlitchInput.x > x - width / 2
				&& GlitchInput.x < x + width / 2
				&& GlitchInput.y > y - height / 2
				&& GlitchInput.y < y + height / 2) {
			clicked = true;
		} else {
			clicked = false;
		}
	}

	// Renders the button
	public void render(SpriteBatch batch) {
		batch.begin();
		if (clicked)
			buttons[1].draw(batch);
		else
			buttons[0].draw(batch);
		batch.end();
	}

	// Returns if the button is clicked
	public boolean isClicked() {
		return clicked;
	}
}
