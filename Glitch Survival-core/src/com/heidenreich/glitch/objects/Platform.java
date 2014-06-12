package com.heidenreich.glitch.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.heidenreich.glitch.GlitchGame;

public class Platform {

	private Rectangle rect;
	private Sprite plat;

	public Platform(Vector2 position) {
		plat = GlitchGame.assets.getSprite("platform");
		plat.setPosition(position.x - plat.getWidth() / 2,
				position.y - plat.getHeight() / 2);
		rect = new Rectangle((position.x - plat.getWidth() / 2) + 45, position.y
				- plat.getHeight() / 2, plat.getWidth() - 45, plat.getHeight());
	}

	public void render(SpriteBatch batch) {
		batch.begin();
		plat.draw(batch);
		batch.end();
	}

	public Rectangle getRect() {
		return rect;
	}

}
