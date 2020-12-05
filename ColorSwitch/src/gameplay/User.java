package gameplay;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

class User 
{
	private Shape MyShape;
	private final ArrayList<String> color_images; //Can make a singelton class for this
//	Add further parameters when helper classes are ready.
	
	User(String shape_name)
	{		
		//Making an array for colors
		color_images = new ArrayList<String>();
		color_images.add("/redc.png");
		color_images.add("/greenc.png");
		color_images.add("/yellow.png");
		color_images.add("/bluec.png");
		//New colors cannot be added in middle of game
		// make a separate class for this array
		
		
		
		
		Random random = new Random();
		int UserColor = random.nextInt(4);
		Point UserCentre = new Point(500, 640);
		Image UserImage = new Image(color_images.get(UserColor), 15, 15, false, false);
		
		this.MyShape = new Shape(shape_name, UserCentre, UserColor, UserImage);
				
	}
	
	
//	Getter function
	Shape getUserShape()
	{
		return this.MyShape;
	}
	
	
//	Required functions
	void changeColor(int newColor)
	{
		Image newImage = new Image(color_images.get(newColor), 15, 15, false, false); 
		
		this.MyShape.setShapeColor(newColor);
		this.MyShape.setShapeImage(newImage);
	}
	
	
	

}
