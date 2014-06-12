package com.heidenreich.glitch.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class GlitchAnimation extends Animation {

	private int shakeState;
	private Vector2 dv;

	public GlitchAnimation(Sprite[] image, float f) {
		super(image, f);
		shakeState = 0;
		dv = new Vector2(0, 0);
	}

	public void update(float dt) {
		if (shakeState > 0) {
			if (shakeState == 1)
				super.getFrame().setPosition(super.getFrame().getX() - dv.x,
						super.getFrame().getY() - dv.y);
			else
				super.getFrame().setPosition(super.getFrame().getX() + dv.x,
						super.getFrame().getY() + dv.y);
			dv.set(dv.x / 2, dv.y / 2);
			if (dv.x < 0.1f || dv.y < 0.1f)
				shakeState = 0;
		} else
			super.getFrame().setPosition(0, 0);
		if ((Math.random() * 1000) < 50) {
			super.update(dt);
			shakeState = 1;
			dv.set((float) (Math.random() * 5), (float) (Math.random() * 5));
		}
	}
}
