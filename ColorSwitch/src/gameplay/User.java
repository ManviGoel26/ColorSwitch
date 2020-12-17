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
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;


class User extends Pane implements Serializable
{
	public Point2D centre;
	public static Rectangle rectangle;
	int score;
	
	public User() {
		score=0;
		centre=new Point2D(0, 0);
		
		Random random = new Random();
		int UserColor = random.nextInt(4)+1;
		if(UserColor==1) 
			rectangle=new Rectangle(20,20,Color.RED);
		if(UserColor==2) 
			rectangle=new Rectangle(20,20,Color.BLUE);
		if(UserColor==3) 
			rectangle=new Rectangle(20,20,Color.GREEN);
		if(UserColor==4) 
			rectangle=new Rectangle(20,20,Color.YELLOW);
		
		
		int centreX=300;//500;
		int centreY=300;//640;
		
		centre = new Point2D(centreX, centreY);
		
		setTranslateY(centreY);
		setTranslateX(centreX);
		getChildren().addAll(rectangle);
	}
	
//	public void setSc
	
	public void moveY(int value, Obstacle myObstacles, ColorBall cb, Stars st) {
		
		boolean moveDown = value>0 ? true: false;
		if(getTranslateY()<240) {
			myObstacles.update();
			cb.update();
			st.update();
		}
		for(int i=0;i<Math.abs(value);i++) {
			
			if(getTranslateY()<0) {
				setTranslateY(0);
			}
			
			if(getTranslateY()>580) {
				setTranslateY(580);
				
			}
			setTranslateY(getTranslateY()+ (moveDown? 1:-1));
			
		}
	}
	public void jump() {
		centre=new Point2D(3,  -12);
		//score++;
		//System.out.println("jumped");
	}
	
	public void changeColor(int newColor)
	{
		switch(newColor) {
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

	public void addPoints() {
		score+=50;
		System.out.println(score);
	}
	private void writeObject(ObjectOutputStream s) throws IOException	
	{	
//		Saving score, color, center coordinates	
		s.writeInt(score);	
		s.writeDouble(this.centre.getX());	
		s.writeDouble(this.centre.getY());	
		s.writeObject(rectangle.getFill().toString());	
		System.out.println("something");	
	}
	private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException	
	{	

		score = input.readInt();	
		double new_x = input.readDouble();	
		double new_y = input.readDouble();	
		centre = new Point2D(new_x, new_y);
		
		String newColor = (String)input.readObject(); //need to check this	
		System.out.println(newColor+"fwnd");	
		Color color = Color.web(newColor); 	
		rectangle = new Rectangle(20,20,color);
//		Point2D newCentre = new Point2D(new_x, new_y);	


	}
	
	

}

