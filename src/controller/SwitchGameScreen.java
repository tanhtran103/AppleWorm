package controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.GameView;
import view.InGameScreen;

public class SwitchGameScreen extends JPanel {
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private GameView gameView;
	private InGameScreen inGameScreen;
	private JPanel currentScreen;
	private JFrame frame;

	public SwitchGameScreen(JFrame frame) {
		this.frame = frame;
		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);
		gameView = new GameView(this);
		inGameScreen = new InGameScreen();
		currentScreen = new GameView(this);
		this.add(currentScreen);

		// Thêm các giao diện vào CardLayout
		mainPanel.add(gameView, "GameView");
		mainPanel.add(inGameScreen, "InGameScreen");

		setLayout(new BorderLayout());
		add(mainPanel, BorderLayout.CENTER);
	}

	// Chuyển đổi giao diện ban đầu sang giao diện game
	public void showGameScreen() {
		currentScreen.setVisible(false); // Ẩn màn hình hiện tại
		currentScreen = new InGameScreen(); // Khởi tạo và chuyển sang InGameScreen
		this.add(currentScreen);
		currentScreen.requestFocus(); // Yêu cầu JFrame tập trung vào InGameScreen
		currentScreen.setVisible(true); // Hiển thị InGameScreen
	}

	public JPanel getCurrentScreen() {
		return currentScreen;
	}
}
