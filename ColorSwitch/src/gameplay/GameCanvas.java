package gameplay;
import java.io.IOException;
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
	public static int score = 0;
	 
	
	private static Pane appRoot = new Pane();
	private static Pane gameRoot = new Pane();
	public static ArrayList<Obstacle> obs = new ArrayList<>();
	
	User usr = new User();
	public Label scoreLabel = new Label("Score: "+ score);
	
	
	public static void main(String[] args)
	{
		System.out.println("running1");
		launch(args);	
	}
	
	public void update() 
	{
		//System.out.println("update");
		if(usr.centre.getY() < 5)
		{
			usr.centre = usr.centre.add(0,1);
			//System.out.println("in loop ");
		}
		
		usr.moveY((int)usr.centre.getY());
		scoreLabel.setText("Score: "+ score);
		
		usr.translateXProperty().addListener((obs, old, newValue)->{
			int offset = newValue.intValue();
			if(offset > 200)
				gameRoot.setLayoutX(-(offset-200));
		});
	}
	
	private int checkShapeIntersection() 
	{
		  if (myObstacles.detectCollision()) 
		  {
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
        
		primaryStage.setTitle("I feel like crying");
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
	
		myObstacles = new Obstacle();
		myObstacles.setTranslations(root);
	
		root.getChildren().add(usr);
	
	
		scene.setOnMouseClicked(event->{
			usr.jump();
			System.out.println("clicked");
		});
	
		primaryStage.setScene(scene);
		primaryStage.show();
	
	
		AnimationTimer timer = new AnimationTimer() 
		{
			@Override
			public void handle(long arg0)
			{
				if(checkShapeIntersection() == 1) 
				{
					//blankScreen();
					stop();
				}
			
				update();
			}
		};
	
		timer.start();
	}
	
//	private void showStage() 
//	{
//		  Stage newStage = new Stage();
//		  Button btn = new Button("blagb");
//		  btn.setLayoutX(20);
//		  btn.setLayoutY(20);
//		  
//		  Scene stageScene = new Scene(btn, 300, 300);
//		  newStage.setScene(stageScene);
//		  newStage.show();
//	}
	
	private void showPopUpWindow()
	{
		Stage popUpWindow = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevivalScreen.fxml"));
		RevivalScreenController rv = new RevivalScreenController(scoreLabel);
		rv.setGameCanvas(scoreLabel);


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
}
