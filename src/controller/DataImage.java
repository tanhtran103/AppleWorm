package controller;

import java.awt.Image;

//Tạo hình thù con sâu
public class DataImage {

	public static Image imageHead;
	public static Image imageHead_GoLeft;
	public static Image imageHead_GoRight;
	public static Image imageHead_GoUp;
	public static Image imageHead_GoDown;

	public static Image imageBody;

	public static Image imageApple;
	public static Image imageApple2;
	public static Image imageApple3;

	public static Animation HeadGoUp;
	public static Animation HeadGoDown;
	public static Animation HeadGoRight;
	public static Animation HeadGoLeft;

	public static Animation Apple;

	public static void loadAllAnimation() {
		HeadGoUp = new Animation();
		HeadGoUp.addImage(imageHead);
		HeadGoUp.addImage(imageHead_GoUp);

		HeadGoDown = new Animation();
		HeadGoDown.addImage(imageHead);
		HeadGoDown.addImage(imageHead_GoDown);

		HeadGoRight = new Animation();
		HeadGoRight.addImage(imageHead);
		HeadGoRight.addImage(imageHead_GoRight);

		HeadGoLeft = new Animation();
		HeadGoLeft.addImage(imageHead);
		HeadGoLeft.addImage(imageHead_GoLeft);

		Apple = new Animation();
		Apple.addImage(imageApple);
		Apple.addImage(imageApple2);
		Apple.addImage(imageApple3);
		Apple.addImage(imageApple2);
	}

}
