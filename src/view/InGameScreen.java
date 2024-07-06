package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import controller.DataImage;
import model.DataUser;
import model.Worm;

public class InGameScreen extends JPanel implements Runnable, KeyListener {
	// Tạo khung trò chơi
	public static int[][] bg = new int[15][15];
	// Tạo viền
	public static int padding = 10;
	public static int WIDTH = 350;
	public static int HEIGHT = 350;

	// Bắt đầu trò chơi
	public static boolean isPlaying = false;
	public static boolean enableTextStartGame = true;

	// Kết thúc trò chơi
	public static boolean isGameOver = false;

	// Cấp độ
	
	public static int CurrentLevel = 1;
	// Điểm
	public static int point = 0;

	Thread thread;
	public Worm worm;

	public InGameScreen() {
		DataUser dataUser = new DataUser();
		dataUser.readData();
		setFocusable(true);
		worm = new Worm();
		DataImage.loadAllAnimation();

		bg[10][10] = 2;

		thread = new Thread(this);
		thread.start();

		addKeyListener(this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Khi người dùng ấn SPACE BAR thì game sẽ bắt đầu
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			InGameScreen.isPlaying = !InGameScreen.isPlaying;
			if (InGameScreen.isGameOver) {
				InGameScreen.isGameOver = false;
				worm.resetGame();
			}
			break;
		// Xử lý sự kiện từ bàn phím
		case KeyEvent.VK_UP:
			worm.setVectot(Worm.GO_UP);
			break;
		case KeyEvent.VK_DOWN:
			worm.setVectot(Worm.GO_DOWN);
			break;
		case KeyEvent.VK_LEFT:
			worm.setVectot(Worm.GO_LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			worm.setVectot(Worm.GO_RIGHT);
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void run() {
		// TODO Auto-generated method stub
		long t = 0;
		long t2 = 0;
		while (true) {

			// Tốc độ nhấp nháy của chữ
			if (System.currentTimeMillis() - t2 > 900) {
				enableTextStartGame = !enableTextStartGame;
				t2 = System.currentTimeMillis();
			}

			if (isPlaying) {
				if (System.currentTimeMillis() - t > 200) {
					DataImage.Apple.update();
					t = System.currentTimeMillis();
				}
				worm.update();
			}

			repaint();
			try {
				thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

	public void paintBg(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH + padding * 2 + 200, HEIGHT + padding * 2);
		for (int i = 0; i < bg.length; i++) {
			for (int j = 0; j < bg.length; j++)
				if (bg[i][j] == 2) {
					g.setColor(Color.red);
					g.fillRect(i * 23 + 3 + padding, j * 23 + 5 + padding, 22, 22);
					g.setColor(Color.gray);

				}
		}
	}

	private void paintFrame(Graphics g) {
		g.setColor(Color.BLUE);

		g.drawRect(0, 0, WIDTH + padding * 2, HEIGHT + padding * 2);
		g.drawRect(1, 1, WIDTH + padding * 2 - 2, HEIGHT + padding * 2 - 2);
		g.drawRect(2, 2, WIDTH + padding * 2 - 4, HEIGHT + padding * 2 - 4);

		g.drawRect(0, 0, WIDTH + padding * 2 + 200, HEIGHT + padding * 2);
		g.drawRect(1, 1, WIDTH + padding * 2 - 2 + 200, HEIGHT + padding * 2 - 2);
		g.drawRect(2, 2, WIDTH + padding * 2 - 4 + 200, HEIGHT + padding * 2 - 4);

	}

	public void paint(Graphics g) {
		paintBg(g);
		worm.paintWorm(g);
		paintFrame(g);

		// Tạm dừng game
		if (!isPlaying) {
			if (enableTextStartGame) {
				g.setColor(Color.white);
				g.setFont(g.getFont().deriveFont(17.0f));
				g.drawString("Nhấn Phím SPACE Để Chơi Game", 50, 150);
			}
		}

		// Kết thúc trò chơi
		if (isGameOver) {
			g.setColor(Color.white);
			g.setFont(g.getFont().deriveFont(20.0f));
			g.drawString("GAME OVER :(", 120, 200);
		}

		// Vẽ level
		g.setColor(Color.white);
		g.setFont(g.getFont().deriveFont(20.0f));
		g.drawString("LEVEL: " + CurrentLevel, 410, 100);

		// Vẽ điểm
		g.setFont(g.getFont().deriveFont(17.0f));
		g.drawString("Points: " + point, 410, 150);

		insertPlayerDataOnLeaderBoard(g);
	}
	
	public void insertPlayerDataOnLeaderBoard(Graphics g) {
		for (int i = 0; i < DataUser.users.size(); i++) {
			g.drawString(DataUser.users.get(i).toString(), 410, i * 30 + 200);
		}
	}
}
