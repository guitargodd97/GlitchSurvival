package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Background;
import com.heidenreich.glitch.objects.Enemy;
import com.heidenreich.glitch.objects.Player;

public class GameScreen implements Screen {

	private Background background;
	private Enemy enemy;
	private GlitchGame game;
	private Player player;
	private SpriteBatch batch;

	public GameScreen(GlitchGame game) {
		this.game = game;
		batch = game.getBatch();
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		player = new Player();
		enemy = new Enemy();
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		background.update(delta);
		background.render(batch);
		
		player.update(delta);
		player.render(batch);
		
		enemy.update(delta);
		enemy.render(batch);
	}

	public void resize(int width, int height) {
	}

	public void show() {
	}

	public void hide() {
		dispose();
	}

	public void pause() {

	}

	public void resume() {
	}

	public void dispose() {
	}

}
