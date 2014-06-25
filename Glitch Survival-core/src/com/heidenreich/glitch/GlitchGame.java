package com.heidenreich.glitch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heidenreich.glitch.handlers.Assets;
import com.heidenreich.glitch.handlers.GlitchInputProcessor;
import com.heidenreich.glitch.handlers.Scores;
import com.heidenreich.glitch.screens.StartScreen;

public class GlitchGame extends Game {

	public static final String NAME = "GLITCH SURVIVAL";
	public static final String VERSION = "1.1";
	public static final float GRAVITY = -9.81f;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final int FLOOR = 60;

	public static Assets assets;

	private BitmapFont font;
	private BitmapFont buttonFont;
	private boolean ads;
	private IActivityRequestHandler myRequestHandler;
	private OrthographicCamera cam;
	private Scores score;
	private SpriteBatch batch;

	public GlitchGame(IActivityRequestHandler handler) {
		myRequestHandler = handler;
		ads = false;
	}

	public void create() {
		assets = new Assets();
		assets.loadAll();
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		buttonFont = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(new GlitchInputProcessor());
		score = new Scores();

		cam = new OrthographicCamera(WIDTH, HEIGHT);
		cam.setToOrtho(false, WIDTH, HEIGHT);

		this.setScreen(new StartScreen(this));
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public BitmapFont getFont() {
		return font;
	}

	public BitmapFont getButtonFont() {
		return buttonFont;
	}

	public void dispose() {
		Scores.saveScores();
		assets.disposeAll();
	}

	public Scores getScores() {
		return score;
	}

	// Displays the ads
	public void activateAds() {
		if (!ads) {
			myRequestHandler.showAds(true);
			ads = true;
		}
	}

	// Hides the ads
	public void disableAds() {
		if (ads) {
			myRequestHandler.showAds(false);
			ads = false;
		}
	}

	public void launchInterstitial() {
		myRequestHandler.showOrLoadInterstital();
	}

	public boolean isShown() {
		return myRequestHandler.shown();
	}

	public OrthographicCamera getCam() {
		return cam;
	}

}
