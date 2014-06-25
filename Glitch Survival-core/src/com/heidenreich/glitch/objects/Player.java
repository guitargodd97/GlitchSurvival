package com.heidenreich.glitch.objects;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Animation;
import com.heidenreich.glitch.handlers.GlitchInput;

public class Player {

	private Animation idle;
	private Animation idleAlt;
	private Animation walkingleft;
	private Animation walkingright;
	private boolean ground;
	private boolean jumping;
	private boolean left;
	private float gravityMultiplier = 0.075f;
	private Rectangle rect;
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
		s = GlitchGame.assets.getAnimatedSprite("player-idle", 2);
		for (Sprite o : s)
			o.flip(true, false);
		idleAlt = new Animation(s, 1 / 4f);
		location = new Vector2(400, 60);
		velocity = new Vector2(0, 0);
		ground = true;
		jumping = false;
		rect = new Rectangle(location.x, location.y, 45, 45);
		left = false;
	}

	public void update(float delta) {
		handleInput();
		rect.setPosition(location);
		location.add(velocity);
		idle.update(delta);
		idleAlt.update(delta);
		walkingright.update(delta);
		walkingleft.update(delta);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		if (velocity.x > 0 || velocity.y > 0 && !ground) {
			walkingright.getFrame().setPosition(
					location.x - idle.getFrame().getWidth() / 2, location.y);
			walkingright.getFrame().draw(batch);
			left = false;
		} else if (velocity.x < 0 || velocity.y < 0 && !ground) {
			walkingleft.getFrame().setPosition(
					location.x - idle.getFrame().getWidth() / 2, location.y);
			walkingleft.getFrame().draw(batch);
			left = true;
		} else {
			if (!left) {
				idle.getFrame()
						.setPosition(
								location.x - idle.getFrame().getWidth() / 2,
								location.y);
				idle.getFrame().draw(batch);
			} else {
				idleAlt.getFrame()
						.setPosition(
								location.x - idle.getFrame().getWidth() / 2,
								location.y);
				idleAlt.getFrame().draw(batch);
			}
		}
		batch.end();
	}

	private void handleInput() {
		if (Gdx.app.getType() == ApplicationType.Android) {
			// if (leftB.isClicked())
			if (Gdx.input.getAccelerometerY() < -1)
				velocity.x = -3.5f;
			// else if (rightB.isClicked())
			else if (Gdx.input.getAccelerometerY() > 1)
				velocity.x = 3.5f;
			else if (Math.abs(velocity.x) < 0.1f)
				velocity.x = 0;
			else
				velocity.x /= 2;

			if (Gdx.input.isTouched() && ground) {
				velocity.y = 12f;
				ground = false;
				jumping = true;
				GlitchGame.assets.getSound("jump").play(0.5f);
			} else
				velocity.y += GlitchGame.GRAVITY * gravityMultiplier;

			if (!Gdx.input.isTouched() && jumping)
				jumping = false;

			if (location.x < 10) {
				velocity.x = 0;
				location.x = 11;
			} else if (location.x > 790) {
				velocity.x = 0;
				location.x = 789;
			}
		} else {
			if (GlitchInput.isDown(GlitchInput.BUTTON1))
				velocity.x = -3.5f;
			else if (GlitchInput.isDown(GlitchInput.BUTTON2))
				velocity.x = 3.5f;
			else if (Math.abs(velocity.x) < 0.1f)
				velocity.x = 0;
			else
				velocity.x /= 2;

			if (GlitchInput.isDown(GlitchInput.BUTTON3) && ground) {
				velocity.y = 12f;
				ground = false;
				jumping = true;
				GlitchGame.assets.getSound("jump").play(0.5f);
			} else
				velocity.y += GlitchGame.GRAVITY * gravityMultiplier;

			if (!GlitchInput.isPressed(GlitchInput.BUTTON3) && jumping)
				jumping = false;

			if (location.x < 10) {
				velocity.x = 0;
				location.x = 11;
			} else if (location.x > 790) {
				velocity.x = 0;
				location.x = 789;
			}
		}
	}

	public void setGround(boolean ground) {
		this.ground = ground;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setY(float y) {
		location.y = y;
		velocity.y = 0;
	}

	public boolean checkFloor() {
		return location.y < GlitchGame.FLOOR;
	}

	public boolean isJumping() {
		return jumping;
	}

	public boolean goingDown(float y) {
		if (y < location.y + 15)
			return true;
		return false;
	}

	public void sendDown() {
		if (velocity.y > 0)
			velocity.y = -0.1f;
	}
}
