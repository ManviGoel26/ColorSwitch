package gameplay;

import java.util.Random;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;


class User extends Pane
{
	public Point2D centre;
	public static Rectangle rectangle;
	int score;
	
	public User(){
		score=0;
		centre=new Point2D(0, 0);
		
		Random random = new Random();
		int UserColor = random.nextInt(4);
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
	
	public void moveY(int value, Obstacle myObstacles) {
		
		boolean moveDown = value>0 ? true: false;
		if(getTranslateY()<240) {
			myObstacles.update();
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
		centre=new Point2D(3,  -15);
		score++;
		//System.out.println("jumped");
	}
	
	void changeColor(int newColor)
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

}

