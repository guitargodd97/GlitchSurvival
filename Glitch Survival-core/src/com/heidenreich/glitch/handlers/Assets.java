package com.heidenreich.glitch.handlers;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.assets.loaders.SoundLoader;
import com.badlogic.gdx.assets.loaders.TextureAtlasLoader;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	public static final String TEXTURE_LOCATION = "data/textures/texture.atlas";
	public static final String SFX_LOCATION = "data/sound/sfx/";
	public static final String MUSIC_LOCATION = "data/sound/music/";

	private AssetManager musicManager;
	private AssetManager soundManager;
	private AssetManager textureManager;

	// Creates the Assets
	public Assets() {
		// Creates the musicManager
		musicManager = new AssetManager();
		musicManager.setLoader(Music.class, new MusicLoader(
				new InternalFileHandleResolver()));

		// Creates the soundManager
		soundManager = new AssetManager();
		soundManager.setLoader(Sound.class, new SoundLoader(
				new InternalFileHandleResolver()));

		// Creates the textureManager
		textureManager = new AssetManager();
		textureManager.setLoader(TextureAtlas.class, new TextureAtlasLoader(
				new InternalFileHandleResolver()));
	}

	// Loads all the sound files
	private void loadSounds() {
		soundManager.load(SFX_LOCATION + "jump.mp3", Sound.class);
		soundManager.load(SFX_LOCATION + "glitch.mp3", Sound.class);
		soundManager.finishLoading();
	}

	// Loads all the music files
	private void loadMusic() {
		musicManager.load(MUSIC_LOCATION + "glitch1.mp3", Music.class);
		musicManager.load(MUSIC_LOCATION + "glitch2.mp3", Music.class);
		musicManager.load(MUSIC_LOCATION + "glitch3.mp3", Music.class);
		musicManager.finishLoading();
	}

	// Loads all the texture files
	private void loadTextures() {
		textureManager.load(TEXTURE_LOCATION, TextureAtlas.class);
		textureManager.finishLoading();
	}

	// Calls all the loading methods
	public void loadAll() {
		loadSounds();
		loadMusic();
		loadTextures();
	}

	// Disposes of all resources
	public void disposeAll() {
		musicManager.dispose();
		soundManager.dispose();
		textureManager.dispose();
	}

	// Unloads a particular asset
	public void disposeAsset(String name, Object type) {
		if (type instanceof Music)
			musicManager.unload(name);
		else if (type instanceof Sound)
			soundManager.unload(name);
		else if (type instanceof TextureAtlas)
			textureManager.unload(name);
	}

	// Returns if the managers are done loading everything
	public boolean doneLoading() {
		return musicManager.update() && soundManager.update()
				&& textureManager.update();
	}

	// Retrieves a sound
	public Sound getSound(String name) {
		return soundManager.get(SFX_LOCATION + name + ".mp3", Sound.class);
	}

	// Retrieves a song
	public Music getMusic(String name) {
		return musicManager.get(MUSIC_LOCATION + name + ".mp3", Music.class);
	}

	// Retrieves a texture
	public TextureAtlas getTexture(String name) {
		return textureManager.get(name, TextureAtlas.class);
	}

	// Retrieves a sprite
	public Sprite getSprite(String name) {
		return textureManager.get(Assets.TEXTURE_LOCATION, TextureAtlas.class)
				.createSprite(name);
	}

	// Retrieves an animated sprite
	public Sprite[] getAnimatedSprite(String name, int numOfFrames) {
		Sprite[] array = new Sprite[numOfFrames];
		for (int i = 0; i < numOfFrames; i++)
			array[i] = textureManager.get(Assets.TEXTURE_LOCATION,
					TextureAtlas.class).createSprite(name, i);
		return array;
	}
}
