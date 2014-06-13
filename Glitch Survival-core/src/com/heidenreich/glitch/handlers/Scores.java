package com.heidenreich.glitch.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class Scores {

	public static int[] scoreValues;
	public static String[] stringScores;

	public Scores() {
		scoreValues = new int[5];
		stringScores = new String[5];
		FileHandle fileLocation = Gdx.files.local("data/scores.txt");
		if (!fileLocation.exists())
			fileLocation.writeString("0|0|0|0|0", false);
		String s = fileLocation.readString();
		for (int i = 0; i < scoreValues.length - 1; i++) {
			scoreValues[i] = Integer.parseInt(s.substring(0, s.indexOf("|")));
			s = s.substring(s.indexOf("|") + 1);
		}
		scoreValues[4] = Integer.parseInt(s);
		updateStringScores();
	}

	public static void sendScore(int minutes, int seconds) {
		updateScoreValues();
		int curValue = (minutes * 60) + seconds;
		int place = -1;
		for (int i = scoreValues.length - 1; i >= 0; i--) {
			if (curValue < scoreValues[i])
				i = -1;
			else
				place = i;
		}
		if (place >= 0) {
			int[] temp = new int[scoreValues.length];
			for (int i = 0; i < place; i++)
				temp[i] = scoreValues[i];
			temp[place] = curValue;
			for (int i = place + 1; i < scoreValues.length; i++)
				temp[i] = scoreValues[i - 1];
			scoreValues = temp;
		}
		updateStringScores();
	}

	private static void updateScoreValues() {
		for (int i = 0; i < stringScores.length; i++) {
			int minutes = 0;
			int seconds = 0;
			minutes = Integer.parseInt(stringScores[i].substring(0,
					stringScores[i].indexOf(":")));
			seconds = Integer.parseInt(stringScores[i]
					.substring(stringScores[i].indexOf(":") + 1));
			scoreValues[i] = (minutes * 60) + seconds;
		}
	}

	private static void updateStringScores() {
		for (int i = 0; i < scoreValues.length; i++) {
			int minutes = (int) (scoreValues[i] / 60);
			int seconds = scoreValues[i] % 60;
			if (seconds < 10)
				stringScores[i] = minutes + ":0" + seconds;
			else
				stringScores[i] = minutes + ":" + seconds;
		}
	}

	public static void saveScores() {
		FileHandle fileLocation = Gdx.files.local("data/scores.txt");
		if (!fileLocation.exists())
			fileLocation.writeString("0|0|0|0|0", false);
		fileLocation.writeString(scoreValues[0] + "|" + scoreValues[1] + "|"
				+ scoreValues[2] + "|" + scoreValues[3] + "|" + scoreValues[4],
				false);
	}
}
