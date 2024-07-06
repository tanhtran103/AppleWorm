package controller;

import java.awt.Image;

//Hiệu ứng khi sâu tăng lên 1 đơn vị độ dài
public class Animation {
	public int n;
	public Image[] images;
	public int currentImage;

	public Animation() {
		super();
		n = 0;
		currentImage = 0;
	}

	public void addImage(Image image) {
		Image[] ar = images;
		images = new Image[n + 1];
		for (int i = 0; i < n; i++)
			images[i] = ar[i];
		images[n] = image;
		n++;
	}

	public void update() {
		currentImage++;
		if (currentImage >= n) {
			currentImage = 0;
		}
	}

	public Image getCurrentImage() {
		return images[currentImage];
	}
}
