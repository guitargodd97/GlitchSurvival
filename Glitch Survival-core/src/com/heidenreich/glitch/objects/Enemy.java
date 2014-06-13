package com.heidenreich.glitch.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Animation;

public class Enemy {

	private Animation animation;
	private boolean ground;
	private float gravityMultiplier = 0.0175f;
	private Vector2 location;
	private Vector2 velocity;

	public Enemy() {
		animation = new Animation(GlitchGame.assets.getAnimatedSprite("enemy",
				8), 1 / 20f);
		location = new Vector2(200, 420);
		velocity = new Vector2(0, 0);
		ground = false;
	}

	public void update(float delta) {
		handleMovement();
		location.add(velocity);
		animation.update(delta);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		animation.getFrame().setPosition(location.x, location.y);
		animation.getFrame().draw(batch);
		batch.end();
	}

	private void handleMovement() {
		if (!ground)
			velocity.y += GlitchGame.GRAVITY * gravityMultiplier;
	}

}
