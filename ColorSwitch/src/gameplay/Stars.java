package gameplay;

import java.util.ArrayList;
import java.util.Random;


import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Stars{
	ArrayList<Polygon> stars;
	
	//private Shape ColorBall;
	
	Stars(Group root)
	{
		
		stars=new ArrayList<>();
		
		double centrex=300, centrey=400, side=10;
	      
		for(int i=0;i<10;i++) {
			
			Polygon star = new Polygon();
		    star.getPoints().addAll(new Double[]{
		         centrex, centrey,
		         centrex+side, centrey,
		         centrex+3*side/2,centrey+side*1.73/2,
		         centrex+side, centrey+side*1.73,
		         centrex, centrey+side*1.73,
		         centrex-side/2,centrey+side*1.73/2,
		         
		      });
		      star.setFill(Color.CHOCOLATE);
		      star.setStrokeWidth(8.0);
		      star.setStroke(Color.BROWN);
		      
			
			stars.add(star);
			centrey-=500;
		}
		System.out.println("made new ballss");
		root.getChildren().addAll(stars);
		
//		String ShapeName = "Circle";
//		
//		Random random = new Random();
//		int ColorToChange = random.nextInt(4);
//		
//		System.out.print(ColorToChange);
//		
//		Image ColorBallImage = new Image("/redc.png", 20, 20, false, false);
//		Point ColorBallCentre = new Point(500, height);
//		
//		this.ColorBall = new Shape(ShapeName, ColorBallCentre, ColorToChange, ColorBallImage);
//		this.colorBall_image = new Image("/yellow.png", 20, 20, false, false);
		
	}
	public void name() {
		
	}
	
	public int detectCollision() {
		
		for(Polygon star: stars) {
			Shape intersect = Shape.intersect(User.rectangle, star);
			if (star.getFill()!=Color.TRANSPARENT && intersect.getBoundsInLocal().getWidth() != -1) {
				star.setFill(Color.TRANSPARENT);
				star.setStroke(Color.TRANSPARENT);
				return 50;
			}
		}
		return 0;
	}
	public void update() {//Group root) {
		for(Polygon i: stars) {
			TranslateTransition transition=new TranslateTransition();
	    	transition.setDuration(Duration.seconds(0.001));
	    	transition.setToY(i.getLayoutY()+1);
	    	i.setLayoutY(i.getLayoutY()+1);
	    	transition.setNode(i);
	    	transition.play();
		}
	}

	
}
