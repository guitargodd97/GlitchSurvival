package com.heidenreich.glitch.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;
import com.heidenreich.glitch.GlitchGame;
import com.heidenreich.glitch.handlers.Background;
import com.heidenreich.glitch.handlers.GlitchInput;
import com.heidenreich.glitch.handlers.Scores;
import com.heidenreich.glitch.objects.GUIButton;

public class HighscoreScreen implements Screen {

	private Background background;
	private GlitchGame game;
	private int adShown;
	private int buffer;
	private int minutes;
	private int seconds;
	private long curTime;
	private long startTime;
	private Sprite panel;
	private GUIButton title;

	public HighscoreScreen(GlitchGame game) {
		this.game = game;
		background = new Background(GlitchGame.assets.getAnimatedSprite(
				"background", 8));
		panel = GlitchGame.assets.getSprite("highscorepanel");
		panel.setPosition(25, 0);
		title = new GUIButton(GlitchGame.assets.getAnimatedSprite("button", 2),
				400, 420);
		buffer = 0;
		adShown = 0;
		startTime = TimeUtils.millis();
	}

	public void render(float delta) {
		// Clears the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().setProjectionMatrix(game.getCam().combined);
		background.update(delta);
		background.render(game.getBatch());

		title.update(delta);
		title.render(game.getBatch());

		game.getBatch().begin();
		panel.draw(game.getBatch());
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

		if (adShown == 0
				&& (GlitchGame.EASY_DEATH >= 5 || GlitchGame.TIME > 300)) {
			game.launchInterstitial();
			game.getBatch().begin();
			game.getFont().draw(game.getBatch(), "Loading.... Please wait.",
					205, 75);
			game.getBatch().end();
			if (GlitchGame.EASY_DEATH >= 5)
				GlitchGame.EASY_DEATH = 0;
			if (GlitchGame.TIME > 300)
				GlitchGame.TIME = 0;
		} else {
			if (!GlitchInput.isDown() && buffer == 0)
				buffer = 1;
			else if (GlitchInput.isDown() && buffer == 1)
				buffer = 2;
			else if (!GlitchInput.isDown() && buffer == 2) {
				game.setScreen(new StartScreen(game));
				GlitchGame.TIME += seconds + (minutes * 60);
			}
		}
		if (game.isShown())
			adShown++;
		
		curTime = TimeUtils.millis();
		int temp = (int) ((curTime - startTime) / 1000 + 0.5);
		minutes = (int) (temp / 60);
		seconds = temp % 60;
	}

	public void resize(int width, int height) {
	}

	public void show() {
		GlitchGame.assets.getMusic("glitch1").stop();
		GlitchGame.assets.getMusic("glitch2").stop();
		GlitchGame.assets.getMusic("glitch3").stop();
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
