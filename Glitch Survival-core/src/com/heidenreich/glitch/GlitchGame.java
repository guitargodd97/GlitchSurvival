package com.heidenreich.glitch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.heidenreich.glitch.handlers.Assets;
import com.heidenreich.glitch.handlers.GlitchInputProcessor;
import com.heidenreich.glitch.screens.StartScreen;

public class GlitchGame extends Game {

	public static final String NAME = "GLITCH SURVIVAL";
	public static final String VERSION = "Alpha 1.0";
	public static final float GRAVITY = -9.81f;
	public static final int FLOOR = 60;

	public static Assets assets;

	private BitmapFont font;
	private BitmapFont buttonFont;
	private SpriteBatch batch;

	public void create() {
		assets = new Assets();
		assets.loadAll();
		font = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		buttonFont = new BitmapFont(Gdx.files.internal("data/font.fnt"));
		batch = new SpriteBatch();
		Gdx.input.setInputProcessor(new GlitchInputProcessor());
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
}
