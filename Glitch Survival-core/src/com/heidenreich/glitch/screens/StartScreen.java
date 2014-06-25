package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Background;
import com.heidenreich.glitch.objects.GUIButton;

public class StartScreen implements Screen {

	private Background background;
	private GlitchGame game;
	private GUIButton highscore;
	private GUIButton quit;
	private GUIButton start;
	private GUIButton title;
	private SpriteBatch batch;

	public StartScreen(GlitchGame game) {
		this.game = game;
		batch = game.getBatch();
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		start = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 270);
		highscore = new GUIButton(GlitchGame.assets.getAnimatedSprite("button",
				2), 400, 170);
		quit = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 70);
		title = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 410);
		GlitchGame.assets.getMusic("glitch1").setLooping(true);
		GlitchGame.assets.getMusic("glitch2").setLooping(true);
		GlitchGame.assets.getMusic("glitch3").setLooping(true);
		GlitchGame.assets.getMusic(
				"glitch" + ((int) (((Math.random() * 232493291) % 9) % 3 + 1)))
				.play();
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(game.getCam().combined);

		background.update(delta);
		background.render(batch);

		title.update(delta);
		title.render(batch);

		batch.begin();
		game.getFont().draw(batch, "Glitch Survival", 230, 435);
		batch.end();

		start.update(delta);
		start.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Start", 330, 285);
		batch.end();

		float x = Gdx.input.getX();
		float y = Gdx.graphics.getHeight() - Gdx.input.getY();

		if (Gdx.input.isTouched() && x / Gdx.graphics.getWidth() > 0.1875f
				&& x / Gdx.graphics.getWidth() < 0.8125f) {
			if (y / Gdx.graphics.getHeight() > 0.4583f
					&& y / Gdx.graphics.getHeight() < 0.6667f)
				game.setScreen(new GameScreen(game));
			if (y / Gdx.graphics.getHeight() > 0.25f
					&& y / Gdx.graphics.getHeight() < 0.4583f)
				game.setScreen(new HighscoreScreen(game));
			if (y / Gdx.graphics.getHeight() > 0.04167f
					&& y / Gdx.graphics.getHeight() < 0.25f)
				Gdx.app.exit();
		}

		highscore.update(delta);
		highscore.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Highscores", 280, 185);
		batch.end();

		quit.update(delta);
		quit.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Quit", 340, 85);
		batch.end();
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
