package com.heidenreich.glitch.handlers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Animation {

	private float delay;
	private float time;
	private int currentFrame;
	private int timesPlayed;
	private Sprite[] frames;

	// Empty construction of an animation
	public Animation() {

	}

	// Creates a new animation
	public Animation(Sprite[] frames) {
		this(frames, 1 / 12f);
	}

	// Creates a new animation
	public Animation(Sprite[] frames, float delay) {
		this.frames = frames;
		this.delay = delay;
		time = 0;
		currentFrame = 0;
	}

	// Sets the frame delay
	public void setDelay(float delay) {
		this.delay = delay;
	}

	// Sets the current frame
	public void setCurrentFrame(int i) {
		if (i < frames.length)
			currentFrame = i;
	}

	// Sets the frames
	public void setFrames(Sprite[] frames) {
		setFrames(frames, 1 / 12f);
	}

	// Sets the frames
	public void setFrames(Sprite[] frames, float delay) {
		this.frames = frames;
		time = 0;
		currentFrame = 0;
		timesPlayed = 0;
		this.delay = delay;
	}

	// Sets the location if the frame
	public void setLocation(Vector2 v) {
		frames[currentFrame].setPosition(v.x, v.y);
	}

	// Updates the animation
	public void update(float dt) {
		if (delay <= 0)
			return;
		time += dt;
		while (time >= delay) {
			step();
		}
	}

	// Cycles through animation
	private void step() {
		time -= delay;
		currentFrame++;
		if (currentFrame == frames.length) {
			currentFrame = 0;
			timesPlayed++;
		}
	}

	// Returns the current frame of an animation
	public Sprite getFrame() {
		return frames[currentFrame];
	}

	// Returns how many times an animation has been played
	public int getTimesPlayed() {
		return timesPlayed;
	}

	// Returns whether or not an animation has been played at least once
	public boolean hasPlayedOnce() {
		return timesPlayed > 1;
	}
}
