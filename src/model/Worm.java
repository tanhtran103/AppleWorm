package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JOptionPane;

import controller.DataImage;
import view.GameScreen;
import view.InGameScreen;

//thiết kế con sâu và quả táo
public class Worm {

	public void update() {
		modeGame();
		endGame();
		moveHeadWorm();
		moveWorm();
	}

	int doDai = 3;
	int[] x;
	int[] y;

	public static int GO_UP = 1;
	public static int GO_DOWN = -1;
	public static int GO_LEFT = 2;
	public static int GO_RIGHT = -2;

	int vector = Worm.GO_DOWN;

	long t1 = 0;
	long t2 = 0;

	int speed = 300;
	int maxLen = 13;

	boolean updateAfterChangeVecter = true;

	int currentImage = 0;

	public Worm() {
		x = new int[100];
		y = new int[100];

		x[0] = 5;
		y[0] = 4;
		x[1] = 5;
		y[1] = 3;
		x[2] = 5;
		y[2] = 2;
	}

	// Reset game
	public void resetGame() {
		updateAfterChangeVecter = false;
		doDai = 3;
		vector = Worm.GO_DOWN;

		x = new int[100];
		y = new int[100];

		x[0] = 5;
		y[0] = 4;
		x[1] = 5;
		y[1] = 3;
		x[2] = 5;
		y[2] = 2;
		
		getCurrentSpeed();
	}

	public void setVectot(int v) {
		if (vector != -v && updateAfterChangeVecter) {
			vector = v;
		}
	}

	// Tọa độ con sâu
	public boolean modeInWorm(int x1, int y1) {
		for (int i = 0; i < doDai; i++) {
			if (x[i] == x1 && y[i] == y1) {
				return true;
			}
		}
		return false;
	}

	// Tọa độ quả táo, tạo quả táo ở vị trí ngẫu nhiên
	public Point getModeApple() {
		Random r = new Random();
		int x;
		int y;
		do {
			x = r.nextInt(14);
			y = r.nextInt(14);
		} while (modeInWorm(x, y));

		return new Point(x, y);
	}

	// đặt tốc độ mặc định, sau mỗi level thì tốc độ tăng dần lên
	public int getCurrentSpeed() {
		int speed = 300;
		for (int i = 0; i < InGameScreen.CurrentLevel; i++)
			speed *= 0.8;
		return speed;
	}

	public void modeGame() {
		// Chế độ
		if (doDai == maxLen) {
			InGameScreen.isPlaying = false;
			updateAfterChangeVecter = false;
			InGameScreen.CurrentLevel++;
			maxLen += 10;
			speed = getCurrentSpeed();
		}
	}

	public void endGame() {
		// Kết thúc màn chơi
		for (int i = 1; i < doDai; i++) {
			if (x[0] == x[i] && y[0] == y[i]) {

				// Nhập tên người chơi để lưu điểm
				String name = JOptionPane.showInputDialog("Mời bạn nhập tên: ");
				DataUser.users.add(new User(name, String.valueOf(InGameScreen.CurrentLevel)));
				DataUser.updateData();
				InGameScreen.isPlaying = false;
				InGameScreen.isGameOver = true;

				// Reset lại điểm và level khi GameOver
				InGameScreen.point = 0;
				InGameScreen.CurrentLevel = 1;
				resetGame();
			}
		}
	}

	public void moveHeadWorm() {
		// chuyển động đầu ăn
		if (System.currentTimeMillis() - t2 > 300) {

			// Không thể quay đầu 180 độ
			updateAfterChangeVecter = true;

			DataImage.HeadGoUp.update();
			DataImage.HeadGoDown.update();
			DataImage.HeadGoRight.update();
			DataImage.HeadGoLeft.update();

			t2 = System.currentTimeMillis();
		}
	}

	public void moveWorm() {
		// chuyển động của con sâu
		if (System.currentTimeMillis() - t1 > speed) {

//					currentImage++;
			if (currentImage >= 2)
				currentImage = 0;

			if (InGameScreen.bg[x[0]][y[0]] == 2) {
				doDai++;
				InGameScreen.bg[x[0]][y[0]] = 0;
				InGameScreen.bg[getModeApple().x][getModeApple().y] = 2;

				// Điểm
				InGameScreen.point += 10;
			}

			for (int i = doDai - 1; i > 0; i--) {
				x[i] = x[i - 1];
				y[i] = y[i - 1];
			}

			if (vector == Worm.GO_UP)
				y[0]--;
			if (vector == Worm.GO_DOWN)
				y[0]++;
			if (vector == Worm.GO_LEFT)
				x[0]--;
			if (vector == Worm.GO_RIGHT)
				x[0]++;

			if (x[0] < 0)
				x[0] = 14;
			if (x[0] > 14)
				x[0] = 0;
			if (y[0] < 0)
				y[0] = 14;
			if (y[0] > 14)
				y[0] = 0;

			t1 = System.currentTimeMillis();
		}
	}

	// Vẽ con sâu
	public void paintWorm(Graphics g) {
		g.setColor(Color.green);
		for (int i = 0; i < doDai; i++) {
			g.fillRect(x[i] * 23 + 3 + InGameScreen.padding, y[i] * 23 + 5 + InGameScreen.padding, 22, 22);
		}
		
		if (vector == Worm.GO_UP)
			g.drawImage(DataImage.HeadGoUp.getCurrentImage(), x[0] * 25 - 6 + InGameScreen.padding,
					y[0] * 25 - 6 + InGameScreen.padding, null);
		else if (vector == Worm.GO_DOWN)
			g.drawImage(DataImage.HeadGoDown.getCurrentImage(), x[0] * 25 - 6 + InGameScreen.padding,
					y[0] * 25 - 6 + InGameScreen.padding, null);
		else if (vector == Worm.GO_RIGHT)
			g.drawImage(DataImage.HeadGoRight.getCurrentImage(), x[0] * 25 - 6 + InGameScreen.padding,
					y[0] * 25 - 6 + InGameScreen.padding, null);
		else if (vector == Worm.GO_LEFT)
			g.drawImage(DataImage.HeadGoLeft.getCurrentImage(), x[0] * 25 - 6 + InGameScreen.padding,
					y[0] * 25 - 6 + InGameScreen.padding, null);
	}
}
