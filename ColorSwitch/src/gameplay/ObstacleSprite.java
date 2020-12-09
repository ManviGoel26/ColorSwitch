package gameplay;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class ObstacleSprite {
	
	public ArrayList<Shape> nodes;
	boolean onscreen;
	
	public ObstacleSprite() {
		int i=1; //random number
		switch(i) {
			case 1: CircleSprite();
				break;
				
		}
	}
	public ObstacleSprite CircleSprite(){
		
		nodes = new ArrayList<>();
		
		Circle r=new Circle(40, 30, 30);
		r.setFill(Color.RED);
		
		Circle g=new Circle(100, 30, 30);
		g.setFill(Color.GREEN);
		
		Circle b=new Circle(160, 30, 30);
		b.setFill(Color.BLUE);
		
		Circle y=new Circle(220, 30, 30);
		y.setFill(Color.YELLOW);
		
	    nodes.add(r);
	    nodes.add(g);
	    nodes.add(b);
	    nodes.add(y);
	    
	    return this;
	}
	public boolean checkIfCollided() {
		
		System.out.println("checked");
		boolean collisionDetected = false;
		  for (Shape static_bloc : nodes) {
		    Shape intersect = Shape.intersect(User.rectangle, static_bloc);
		      if (intersect.getBoundsInLocal().getWidth() != -1 && User.rectangle.getFill()!=static_bloc.getFill()) {
		        collisionDetected = true;
		      }
		    
		  }
		return collisionDetected;
	}
	public void setTranslation(Group root) {
		for(Shape i: nodes) {
			TranslateTransition transition=new TranslateTransition();
	    	transition.setDuration(Duration.seconds(3));
	    	transition.setToX(500);
	    	transition.setAutoReverse(true);
	    	transition.setCycleCount(Animation.INDEFINITE);
	    	transition.setNode(i);
	    	transition.play();
	    }
		root.getChildren().addAll(nodes);
	}
	
}
