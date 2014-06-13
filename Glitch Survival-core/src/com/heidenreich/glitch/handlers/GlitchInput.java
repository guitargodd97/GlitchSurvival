package com.heidenreich.glitch.handlers;

public class GlitchInput {
	public static boolean down;
	public static boolean pdown;
	public static int x;
	public static int y;
	public static int xx;
	public static int yy;
	
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	private static final int NUM_KEYS = 3;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	public static final int BUTTON3 = 2;

	static {
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}

	// Updates the input
	public static void update() {
		pdown = down;
	}

	// If input is down
	public static boolean isDown() {
		return down;
	}

	// If input is pressed
	public static boolean isPressed() {
		return down && !pdown;
	}

	// If input is released
	public static boolean isReleased() {
		return !down && pdown;
	}

	public static void setKey(int i, boolean b) {
		keys[i] = b;
	}

	public static boolean isDown(int i) {
		return keys[i];
	}

	public static boolean isPressed(int i) {
		return keys[i] && !pkeys[i];
	}
}
