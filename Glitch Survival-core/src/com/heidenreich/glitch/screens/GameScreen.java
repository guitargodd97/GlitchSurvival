package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Background;
import com.heidenreich.glitch.handlers.GlitchInput;
import com.heidenreich.glitch.objects.Enemy;
import com.heidenreich.glitch.objects.Platform;
import com.heidenreich.glitch.objects.Player;

public class GameScreen implements Screen {

	private Array<Enemy> enemies;
	private Array<Platform> platforms;
	private Background background;
	private boolean running;
	private boolean buffer;
	private GlitchGame game;
	private int minutes;
	private int seconds;
	private long curTime;
	private long startTime;
	private Player player;
	private Sprite base;
	private SpriteBatch batch;

	public GameScreen(GlitchGame game) {
		this.game = game;
		batch = game.getBatch();
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		base = GlitchGame.assets.getSprite("ground");
		base.setPosition(0, 0);
		player = new Player();
		enemies = new Array<Enemy>();
		enemies.add(new Enemy());
		platforms = new Array<Platform>();
		platforms.add(new Platform(new Vector2(220, 120)));
		platforms.add(new Platform(new Vector2(500, 170)));
		platforms.add(new Platform(new Vector2(260, 260)));
		running = true;
		buffer = false;
		startTime = TimeUtils.millis();
		curTime = TimeUtils.millis();
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (running) {
			background.update(delta);
			background.render(batch);

			batch.begin();
			base.draw(batch);
			batch.end();

			for (Platform platform : platforms)
				platform.render(batch);

			player.update(delta);
			player.render(batch);

			for (Enemy enemy : enemies) {
				enemy.update(delta);
				enemy.render(batch);
			}

			for (Platform platform : platforms) {
				if (!player.isJumping()
						&& platform.getRect().overlaps(player.getRect())) {
					player.setGround(true);
					player.setY(platform.getRect().getY() + 14);
				} else if (player.checkFloor()) {
					player.setGround(true);
					player.setY(GlitchGame.FLOOR);
				} else {
					if (GlitchInput.isReleased())
						player.setGround(false);
				}
			}

			for (Enemy enemy : enemies) {
				for (Platform platform : platforms) {
					if (platform.getRect().overlaps(enemy.getRect())) {
						enemy.setGround(true);
						enemy.setY(platform.getRect().getY() + 14);
					} else if (enemy.checkFloor()) {
						enemy.setGround(true);
						enemy.setY(GlitchGame.FLOOR);
					} else if (!enemy.onFloor()) {
						enemy.setGround(false);
					}
				}
				if (player.getRect().overlaps(enemy.getRect()))
					running = false;
			}

			curTime = TimeUtils.millis();
			int x = (int) ((curTime - startTime) / 1000 + 0.5);
			minutes = (int) (x / 60);
			seconds = x % 60;
			String time = "";
			if (seconds < 10)
				time = minutes + ":0" + seconds;
			else
				time = minutes + ":" + seconds;
			batch.begin();
			game.getFont().draw(batch, time, 330, 55);
			batch.end();

			if (enemies.size * 5 < seconds + (minutes * 60))
				enemies.add(new Enemy());
		} else {
			String time = "";
			if (seconds < 10)
				time = minutes + ":0" + seconds;
			else
				time = minutes + ":" + seconds;
			batch.begin();
			game.getFont().draw(batch, "You survived for " + time, 155, 300);
			game.getFont().draw(batch, "Touch to Continue....", 175, 200);
			batch.end();

			if (GlitchInput.isDown())
				buffer = true;
			else if (!GlitchInput.isDown() && buffer)
				game.setScreen(new StartScreen(game));
		}
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
