package gameplay;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;


class User extends Pane implements Serializable
{
	public Point2D centre;
	public static Rectangle rectangle;
	int score;
	
	public User()
	{
		score = 0;
		centre = new Point2D(0, 0);
		
		Random random = new Random();
		int UserColor = random.nextInt(4);
		
		if(UserColor == 1)
		{
			rectangle = new Rectangle(20,20,Color.RED);
		}
		
		if(UserColor == 2)
		{
			rectangle = new Rectangle(20,20,Color.BLUE);
		}
		
		if(UserColor == 3) 
		{
			rectangle = new Rectangle(20,20,Color.GREEN);
		}
		
		if(UserColor == 4)
		{
			rectangle = new Rectangle(20,20,Color.YELLOW);
		}
		
		
		int centreX = 300;//500;
		int centreY = 300;//640;
		
		centre = new Point2D(centreX, centreY);
		
		setTranslateY(centreY);
		setTranslateX(centreX);
		getChildren().addAll(rectangle);
	}
	
//	setter functions
	private void setColor(Paint color)
	{
		rectangle.setFill(color);
	}
	
	private void setUserCentre(Point2D newCentre)
	{
		this.centre = newCentre;
	}
	
	private void setScore(int newScore)
	{
		this.score = newScore;
	}
	
	
	
	public void moveY(int value, Obstacle myObstacles) {
		
		boolean moveDown = value>0 ? true: false;
		
		if(getTranslateY() < 240) 
		{
			myObstacles.update();
		}
		for(int i = 0; i < Math.abs(value); i++) {
			
			if (getTranslateY() < 0) 
			{
				setTranslateY(0);
			}
			
			if (getTranslateY() > 580) 
			{
				setTranslateY(580);	
			}
			
			setTranslateY(getTranslateY()+ (moveDown? 1:-1));
		}
	}
	
	public void jump() 
	{
		centre = new Point2D(3,  -15);
		score++;
		//System.out.println("jumped");
	}
	
	void changeColor(int newColor)
	{
		switch(newColor)
		{
			case 1:
				rectangle.setFill(Color.RED);
				break;
			case 2:
				rectangle.setFill(Color.BLUE);
				break;
			case 3:
				rectangle.setFill(Color.GREEN);
				break;
			case 4:
				rectangle.setFill(Color.YELLOW);
				break;
		}
		
	}
	
	
//	static void serialize(String file, User user) throws IOException
//	{
//		FileOutputStream fileOutputStream = new FileOutputStream(file);
//		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
//		objectOutputStream.writeObject(user);
//		objectOutputStream.close();	
//	}
	
	
	
	private void writeObject(ObjectOutputStream s) throws IOException
	{
//		Saving score, color, center coordinates
		s.writeInt(score);
		s.writeDouble(this.centre.getX());
		s.writeDouble(this.centre.getY());
		s.writeChars(rectangle.getFill().toString());
	}
	
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException
	{
		int newScore = input.readInt();
		double new_x = input.readDouble();
		double new_y = input.readDouble();
		String newColor = input.readLine(); //need to check this
		Color color = Color.web(newColor); 
		Point2D newCentre = new Point2D(new_x, new_y);
				
		
		User user = new User();
		user.setColor(color);
		user.setScore(newScore);
		user.setUserCentre(newCentre);
	}

}

