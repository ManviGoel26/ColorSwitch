package gameplay;

import java.util.ArrayList;

import javafx.scene.image.Image;

class User 
{
	private Image user;
	private int color;
	private final ArrayList<String> color_images; //= ["/redc.png"];
//	private Point position;
//	Add further parameters when helper classes are ready.
	
	User()
	{
		user = new Image("/redc.png", 15, 15, false, false);
		color = 0;
		
		
		//Making an array for colors
		color_images = new ArrayList<String>();
		color_images.add("/redc.png");
		color_images.add("/greenc.png");
		color_images.add("/yellow.png");
		color_images.add("/bluec.png");
		//New colors cannot be added in middle of game
		// make a separate class for this array
	}
	
	Image getUserImage()
	{
		return this.user;
	}
	
	int getUserColor()
	{
		return this.color;
	}
	
	void changeColor(int newColor)
	{
		this.color = newColor;
		this.user = new Image(color_images.get(newColor), 15, 15, false, false);
	}
	
	
	

}
