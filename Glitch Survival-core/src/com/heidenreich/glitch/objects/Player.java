package com.heidenreich.glitch.objects;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Animation;
import com.heidenreich.glitch.handlers.GlitchInput;

public class Player {

	private Animation idle;
	private Animation walkingleft;
	private Animation walkingright;
	private float gravityMultiplier = 0.075f;
	private Vector2 location;
	private Vector2 velocity;

	public Player() {
		idle = new Animation(GlitchGame.assets.getAnimatedSprite("player-idle",
				2), 1 / 4f);
		walkingright = new Animation(GlitchGame.assets.getAnimatedSprite(
				"player-walk", 4), 1 / 4f);
		Sprite[] s = GlitchGame.assets.getAnimatedSprite("player-walk", 4);
		for (Sprite o : s)
			o.flip(true, false);
		walkingleft = new Animation(s, 1 / 4f);
		location = new Vector2(400, 60);
		velocity = new Vector2(0, 0);
	}

	public void update(float delta) {
		handleInput();
		location.add(velocity);
		idle.update(delta);
		walkingright.update(delta);
		walkingleft.update(delta);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		if (velocity.x > 0 || velocity.y > 0) {
			walkingright.getFrame().setPosition(
					location.x - idle.getFrame().getWidth() / 2, location.y);
			walkingright.getFrame().draw(batch);
		} else if (velocity.x < 0 || velocity.y < 0) {
			walkingleft.getFrame().setPosition(
					location.x - idle.getFrame().getWidth() / 2, location.y);
			walkingleft.getFrame().draw(batch);
		} else {
			idle.getFrame().setPosition(
					location.x - idle.getFrame().getWidth() / 2, location.y);
			idle.getFrame().draw(batch);
		}
		batch.end();
	}

	private void handleInput() {
		if (Gdx.app.getType() == ApplicationType.Android) {

		} else {
			if (GlitchInput.isDown(GlitchInput.BUTTON1))
				velocity.x = -3.5f;
			else if (GlitchInput.isDown(GlitchInput.BUTTON2))
				velocity.x = 3.5f;
			else if (Math.abs(velocity.x) < 0.1f)
				velocity.x = 0;
			else
				velocity.x /= 2;
			if (GlitchInput.isDown(GlitchInput.BUTTON3)
					&& location.y == GlitchGame.FLOOR)
				velocity.y = 12f;
			else if (location.y > GlitchGame.FLOOR)
				velocity.y += GlitchGame.GRAVITY * gravityMultiplier;
			else if (location.y < GlitchGame.FLOOR) {
				location.y = GlitchGame.FLOOR;
				velocity.y = 0;
			}
		}
	}

}
