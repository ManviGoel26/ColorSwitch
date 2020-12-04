package gameplay;

import java.util.Random;

import javafx.scene.image.Image;

class ColorBall 
{
	private int colorTogive; //The color it will chose, random number
	private Image colorBall_image; //The image of the colorball
	
	
	ColorBall()
	{
		Random random = new Random();
		this.colorTogive = random.nextInt(4);
		System.out.print(colorTogive);
		this.colorBall_image = new Image("/yellow.png", 20, 20, false, false);
	}
	
	Image getColorBallImage()
	{
		return this.colorBall_image;
	}

}
