package gameplay;

import javafx.scene.image.Image;

class Shape 
{
	private String name; // we can add final, if we do not want multiple types of ball;
	private Point centre;
	private int color;
	private Image image;
	
	Shape(String n, Point c, int co, Image i)
	{
		this.name = n;
		this.centre = c;
		this.color = co;
		this.image = i; //We can change it to string and size so we can
//		 only make images in the shape class
	}
	
//	Getter functions
	String getShapeName()
	{
		return this.name;
	}
	
	Point getShapeCentre()
	{
		return this.centre;
	}
	
	int getShapeColor()
	{
		return this.color;
	}
	
	Image getShapeImage()
	{
		return this.image;
	}
	
//	Setter functions
	void setShapeCentre(Point p)
	{
		this.centre.setXCoordinate(p.getXCoordinate());
		this.centre.setYCoordinate(p.getYCoordinate());
	}
	
	void setShapeColor(int newColor)
	{
		this.color = newColor;
	}
	
	void setShapeImage(Image newImage)
	{
		this.image = newImage;
	}
	

}
