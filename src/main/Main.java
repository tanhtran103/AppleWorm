package main;

import view.GameScreen;

public class Main {
	public static void main(String[] args) {
		int n = 3;
		for (int i = 0; i < n; i++) {
			GameScreen gameScreen = new GameScreen();
			gameScreen.getInstance();
		}
	}
}
