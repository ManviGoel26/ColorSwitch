package gameplay;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
//import java.util.Scanner;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class GameCanvas extends Application 
{
	
	private Obstacle myObstacles;
	private Stars mystars;
	private ColorBall myColorBalls;
	
	public static int score = 0;
	 
	private static Pane appRoot = new Pane();
	private static Pane gameRoot = new Pane();
	
	User usr = new User();
	public Label scoreLabel = new Label("Score: "+ score);
	
	public static void main(String[] args)
	{
		
		System.out.println("running1");
		launch(args);	
	}
	
	public void update(Group root) 
	{
		if(usr.centre.getY() < 5)
		{	
			usr.centre = usr.centre.add(0,1);
		}
		
		usr.moveY((int)usr.centre.getY(), myObstacles, myColorBalls, mystars);
		scoreLabel.setText("Score: "+ score);
	}
	
	private int checkShapeIntersection() 
	{
		  if (myObstacles.detectCollision()) 
		  {
				try {
					serialize("whatever.txt", this.myObstacles);
				} catch (IOException e) {
					e.printStackTrace();
				}

		  	System.out.println("Yay!!!");
//		  	showStage();
//		  	Added the pop Up Window
		  	showPopUpWindow();
		    return 1;
		  } 
		  
		  else 
		  {
		    return 0;
		  }
		  
	}

	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception 
	{
        
		primaryStage.setTitle("Game Canvas");
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
	
		myObstacles = new Obstacle();
		myColorBalls = new ColorBall(root);
		mystars=new Stars(root);
		
		myObstacles.setTranslations(root);
	
		root.getChildren().add(usr);
	
	
		scene.setOnMouseClicked(event->{
			usr.jump();
			//System.out.println("clicked");
		});
	
		primaryStage.setScene(scene);
		primaryStage.show();
	
	
		AnimationTimer timer = new AnimationTimer() 
		{
			@Override
			public void handle(long arg0)
			{
				
				int newCol=myColorBalls.detectCollision();
				if(newCol>0) {
					usr.changeColor(newCol);
				}
				int gainPoints=mystars.detectCollision();
				if(gainPoints>0) usr.addPoints();
				if(checkShapeIntersection() == 1){
					stop();
				}
			
				update(root);
				
			}
		};
	
		timer.start();
	}
	
	private void showPopUpWindow()
	{
		Stage popUpWindow = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevivalScreen.fxml"));
		RevivalScreenController rv = new RevivalScreenController();
		


        try 
        {
            Parent root1 = (Parent) fxmlLoader.load();

    		popUpWindow.initModality(Modality.APPLICATION_MODAL );
//    	    popUpWindow.initStyle(StageStyle.UNDECORATED); //To remove the upper border of class.
    	    popUpWindow.setTitle("PopUpWindow");
    	    popUpWindow.setScene(new Scene(root1)); 
//    	    rv.setTimer();
    	    popUpWindow.show();
        }
        catch (IOException exception) 
        {
            throw new RuntimeException(exception);
        }
		
	}
	
	static void serialize(String file, Object obs) throws IOException
	{
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		
		objectOutputStream.writeObject(obs);

		objectOutputStream.close();	
	}
	
	

}
