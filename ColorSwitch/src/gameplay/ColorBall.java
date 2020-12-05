package gameplay;

import java.util.Random;

import javafx.scene.image.Image;

class ColorBall 
{
	private Shape ColorBall;
	
	
	ColorBall(int height)
	{
		String ShapeName = "Circle";
		
		Random random = new Random();
		int ColorToChange = random.nextInt(4);
		
		System.out.print(ColorToChange);
		
		Image ColorBallImage = new Image("/redc.png", 20, 20, false, false);
		Point ColorBallCentre = new Point(500, height);
		
		this.ColorBall = new Shape(ShapeName, ColorBallCentre, ColorToChange, ColorBallImage);
//		this.colorBall_image = new Image("/yellow.png", 20, 20, false, false);
		
	}
	
	Shape getColorBall()
	{
		return this.ColorBall;
	}

}
