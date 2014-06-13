package com.heidenreich.glitch.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Animation;

public class Enemy {

	private Animation animation;
	private boolean ground;
	private float curDistance;
	private float distance;
	private float gravityMultiplier = 0.0175f;
	private Rectangle rect;
	private Vector2 location;
	private Vector2 velocity;

	public Enemy() {
		animation = new Animation(GlitchGame.assets.getAnimatedSprite("enemy",
				8), 1 / 20f);
		location = new Vector2(200, 420);
		velocity = new Vector2(1.9f, 0);
		ground = false;
		rect = new Rectangle(location.x + 5, location.y, 20, 20);
		distance = 180;
	}

	public void update(float delta) {
		handleMovement();
		rect.setPosition(location);
		location.add(new Vector2(0, velocity.y));
		animation.update(delta);
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		animation.getFrame().setPosition(
				location.x - animation.getFrame().getWidth() / 2, location.y);
		animation.getFrame().draw(batch);
		batch.end();
	}

	private void handleMovement() {
		if (!ground)
			velocity.y += GlitchGame.GRAVITY * gravityMultiplier;
		else {
			location.add(new Vector2(velocity.x, 0));
			curDistance += Math.abs(velocity.x);
			if (curDistance >= distance || location.x > 790 || location.x < 10) {
				curDistance -= distance;
				velocity.x *= -1;
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

}
