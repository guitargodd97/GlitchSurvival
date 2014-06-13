package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Background;
import com.heidenreich.glitch.handlers.GlitchInput;
import com.heidenreich.glitch.handlers.Scores;

public class HighscoreScreen implements Screen {

	private Background background;
	private GlitchGame game;
	private int buffer;
	private Sprite panel;
	private Sprite titleBack;

	public HighscoreScreen(GlitchGame game) {
		this.game = game;
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		panel = GlitchGame.assets.getSprite("highscorepanel");
		panel.setPosition(25, 0);
		titleBack = GlitchGame.assets.getSprite("button");
		titleBack.setPosition(
				(Gdx.graphics.getWidth() - titleBack.getWidth()) / 2, 380);
		buffer = 0;
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		background.update(delta);
		background.render(game.getBatch());

		game.getBatch().begin();
		panel.draw(game.getBatch());
		titleBack.draw(game.getBatch());
		game.getFont().draw(game.getBatch(), "Highscores", 270, 445);
		game.getFont().draw(game.getBatch(), "1. " + Scores.stringScores[0],
				300, 325);
		game.getFont().draw(game.getBatch(), "2. " + Scores.stringScores[1],
				300, 275);
		game.getFont().draw(game.getBatch(), "3. " + Scores.stringScores[2],
				300, 225);
		game.getFont().draw(game.getBatch(), "4. " + Scores.stringScores[3],
				300, 175);
		game.getFont().draw(game.getBatch(), "5. " + Scores.stringScores[4],
				300, 125);
		game.getBatch().end();

		if (!GlitchInput.isDown() && buffer == 0)
			buffer = 1;
		else if (GlitchInput.isDown() && buffer == 1)
			buffer = 2;
		else if (!GlitchInput.isDown() && buffer == 2)
			game.setScreen(new StartScreen(game));
	}

	public void resize(int width, int height) {
	}

	public void show() {
	}

	public void hide() {
	}

	public void pause() {
	}

	public void resume() {
	}

	public void dispose() {
	}

}
