//dfwspackage burj;

/*okay, so- Following is a crappy looking piece of code which contains just one obstacle and does bare minimum obstacle handling. 
 * Press the mouse to make that user (currently rectangle) to jump and as soon as it collides with the obstacle, a screen is popped 
 * up which (currently) contains an ugly button but the user gets frozen on screen after collision 
 * Will add comments, more obstacles etc. in the morning. Meanwhile you try to run this code and see. Also, if you find time, 
 * please replace the popped up screen */
package gameplay

import java.util.ArrayList;
import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


class User extends Pane{
	
	public Point2D velocity;
	Rectangle rectangle;
	
	public User(){
		rectangle=new Rectangle(20,20,Color.RED);
		velocity=new Point2D(0, 0);
		setTranslateY(300);
		setTranslateX(300);
		getChildren().addAll(rectangle);
	}
	
	public void moveY(int value) {
		//System.out.println("moveY");
		boolean moveDown = value>0 ? true: false;
		
		for(int i=0;i<Math.abs(value);i++) {
			
			for(Obstacle ob: po.obs) {
				if(this.getBoundsInParent().intersects(ob.getBoundsInParent())) {
					if(moveDown) {
						setTranslateY(getTranslateY()-1);
						return;
					}
					else {
						setTranslateY(getTranslateY()+1);
						return;
					}
				}
			}
			if(getTranslateY()<0)
				setTranslateY(0);
			
			if(getTranslateY()>580) {
				setTranslateY(580);
			}
			setTranslateY(getTranslateY()+ (moveDown? 1:-1));
			
		}
	}
	
	
	public void jump() {
		velocity=new Point2D(3,  -15);
		System.out.println("jumped");
		
	}
}
class Obstacle extends Pane{
	
	Rectangle rectangle;
	public int width;
	
	public Obstacle(int w){
		width=w;
		rectangle=new Rectangle(20, width);
		
		getChildren().add(rectangle);
	}
	
}
public class po extends Application {

	private ArrayList<Shape> nodes;
	 
	private static Pane appRoot=new Pane();
	private static Pane gameRoot=new Pane();
	
	public static ArrayList<Obstacle> obs=new ArrayList<>();
	
	User usr=new User();
	public static int score =0;
	public Label scoreLabel=new Label("Score: "+score);
	
	
	public void update() {
		//System.out.println("update");
		if(usr.velocity.getY()<5) {
			usr.velocity=usr.velocity.add(0,1);
			//System.out.println("in loop ");
		}
		
		usr.moveY((int)usr.velocity.getY());
		scoreLabel.setText("Score: "+score);
		
		usr.translateXProperty().addListener((obs, old, newValue)->{
			int offset=newValue.intValue();
			if(offset > 200)
				gameRoot.setLayoutX(-(offset-200));
		});
	}
	private int checkShapeIntersection(Shape block) {
		  
		System.out.println("checked");
		boolean collisionDetected = false;
		  for (Shape static_bloc : nodes) {
		    if (static_bloc != block) {
		      static_bloc.setFill(Color.GREEN);

		      Shape intersect = Shape.intersect(block, static_bloc);
		      if (intersect.getBoundsInLocal().getWidth() != -1) {
		        collisionDetected = true;
		      }
		    }
		  }

		  if (collisionDetected) {
		  	System.out.println("Yay!!!");
		  	showStage();
		    block.setFill(Color.BLUE);
		    return 1;
		  } else {
		    block.setFill(Color.GREEN);
		    return 0;
		  }
		}

	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception {
        
		primaryStage.setTitle("I feel like crying");
	    Group root = new Group();
	    Scene scene = new Scene(root, 600, 600);
	
	    nodes = new ArrayList<>();
	    nodes.add(new Circle(40, 30, 30));
	    nodes.add(new Circle(40, 90, 30));
	    nodes.add(new Circle(40, 150, 30));
	    //nodes.add(new Circle(40, 600, 30));
	    
	    
	    ArrayList<TranslateTransition> tarray=new ArrayList<>();
	    
	    for(int i=0;i<3;i++) {
	    	
	    	TranslateTransition transition=new TranslateTransition();
	    	transition.setDuration(Duration.seconds(3));
	    	transition.setToX(500);
	    	transition.setAutoReverse(true);
	    	transition.setCycleCount(Animation.INDEFINITE);
	    	transition.setNode(nodes.get(i));
	    	transition.play();
	    	
	    }
	    root.getChildren().addAll(nodes);
	    root.getChildren().add(usr);
	    
	    
	    scene.setOnMouseClicked(event->{
	    	usr.jump();
	    	System.out.println("clicked");
	    });
	    
	    primaryStage.setScene(scene);
	    primaryStage.show();
	    
	    AnimationTimer timer=new AnimationTimer() {
			
			@Override
			public void handle(long arg0) {
				if(checkShapeIntersection(usr.rectangle)==1) {
					//blankScreen();
					stop();
				}
				update();
			}
		};
		timer.start();
	  }
	
	private void showStage() {
		  Stage newStage = new Stage();
		  Button btn=new Button("blagb");
		  btn.setLayoutX(20);
		  btn.setLayoutY(20);
		  
		  Scene stageScene = new Scene(btn, 300, 300);
		  newStage.setScene(stageScene);
		  newStage.show();
	}
    public static void main(String[] args) { launch(args); }
}

