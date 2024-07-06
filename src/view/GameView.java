package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.SwitchGameScreen;

public class GameView extends JPanel {
	SwitchGameScreen switchScreen;

	public GameView(SwitchGameScreen switchScreen) {
		this.switchScreen = switchScreen;
		this.init();
		this.setVisible(true);
	}

	public void init() {
		this.setLayout(null);

		// Nút bắt đầu
		JButton startButton = new JButton("Bắt đầu");
		startButton.setBounds(250, 300, 100, 50); //
		startButton.setBackground(Color.lightGray);
		this.add(startButton);

		// Xử lý sự kiện nút bắt đầu
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchScreen.showGameScreen(); // Chuyển sang giao diện InGameScreen
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Thêm hình nền
		ImageIcon background = new ImageIcon("src/model/apple-worm.jpg");
		g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
	}
}
