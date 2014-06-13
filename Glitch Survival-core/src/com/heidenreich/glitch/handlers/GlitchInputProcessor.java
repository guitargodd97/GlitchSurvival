package com.heidenreich.glitch.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class GlitchInputProcessor extends InputAdapter {

	// Saves mouse movement
	public boolean mouseMoved(int x, int y) {
		GlitchInput.x = x;
		GlitchInput.y = Gdx.graphics.getHeight() - y;
		return true;
	}

	// Saves a dragged touch
	public boolean touchDragged(int x, int y, int pointer) {
		if (pointer == 0) {
			GlitchInput.x = x;
			GlitchInput.y = Gdx.graphics.getHeight() - y;
		} else if (pointer == 1) {
			GlitchInput.xx = x;
			GlitchInput.yy = Gdx.graphics.getHeight() - y;
		}
		GlitchInput.down = true;
		return true;
	}

	// Saves a touch
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (pointer == 0) {
			GlitchInput.x = x;
			GlitchInput.y = Gdx.graphics.getHeight() - y;
		} else if (pointer == 1) {
			GlitchInput.xx = x;
			GlitchInput.yy = Gdx.graphics.getHeight() - y;
		}
		GlitchInput.down = true;
		return true;
	}

	// Saves a released touch
	public boolean touchUp(int x, int y, int pointer, int button) {
		if (pointer == 0) {
			GlitchInput.x = x;
			GlitchInput.y = Gdx.graphics.getHeight() - y;
		} else if (pointer == 1) {
			GlitchInput.xx = -100;
			GlitchInput.yy = -100;
		}
		GlitchInput.down = false;
		return true;
	}

	public boolean keyDown(int k) {
		if (k == Keys.LEFT)
			GlitchInput.setKey(GlitchInput.BUTTON1, true);
		if (k == Keys.RIGHT)
			GlitchInput.setKey(GlitchInput.BUTTON2, true);
		if (k == Keys.UP)
			GlitchInput.setKey(GlitchInput.BUTTON3, true);
		return true;
	}

	public boolean keyUp(int k) {
		if (k == Keys.LEFT)
			GlitchInput.setKey(GlitchInput.BUTTON1, false);
		if (k == Keys.RIGHT)
			GlitchInput.setKey(GlitchInput.BUTTON2, false);
		if (k == Keys.UP)
			GlitchInput.setKey(GlitchInput.BUTTON3, false);
		return true;
	}
}
