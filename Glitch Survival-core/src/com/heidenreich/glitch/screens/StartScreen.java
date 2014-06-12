package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
	private Sprite titleBack;
	private SpriteBatch batch;

	public StartScreen(GlitchGame game) {
		this.game = game;
		batch = game.getBatch();
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		titleBack = GlitchGame.assets.getSprite("button");
		titleBack.setPosition(
				(Gdx.graphics.getWidth() - titleBack.getWidth()) / 2, 370);
		start = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 270);
		highscore = new GUIButton(GlitchGame.assets.getAnimatedSprite("button",
				2), 400, 170);
		quit = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 70);
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		background.update(delta);
		background.render(batch);

		batch.begin();
		titleBack.draw(batch);
		game.getFont().draw(batch, "Glitch Survival", 230, 435);
		batch.end();

		start.update(delta);
		start.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Start", 330, 285);
		batch.end();
		if (start.isClicked())
			game.setScreen(new GameScreen(game));

		highscore.update(delta);
		highscore.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Highscores", 280, 185);
		batch.end();
		if (highscore.isClicked())
			game.setScreen(new HighscoreScreen(game));

		quit.update(delta);
		quit.render(batch);
		batch.begin();
		game.getFont().draw(batch, "Quit", 340, 85);
		batch.end();
		if (quit.isClicked())
			Gdx.app.exit();
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
