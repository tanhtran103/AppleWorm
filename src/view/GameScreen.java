package view;

import javax.swing.JFrame;

import controller.SwitchGameScreen;
import model.DataUser;

public class GameScreen extends JFrame {
	private static GameScreen instance = null;
	private SwitchGameScreen switchScreen;
	private DataUser dataUser;

	public GameScreen() {
		dataUser = new DataUser();
		switchScreen = new SwitchGameScreen(this);
		add(switchScreen);

		setTitle("Apple Worm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(585, 408);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static GameScreen getInstance() {
		if (instance == null) {
			instance = new GameScreen();
		}
		return instance;
	}
}
