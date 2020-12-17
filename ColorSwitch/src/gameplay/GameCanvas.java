package gameplay;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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

public class GameCanvas extends Application implements Serializable
{
	
	private Obstacle myObstacles;
	private Stars mystars;
	private ColorBall myColorBalls;
	
	public static int score = 0;
	 
	private static Pane appRoot = new Pane();
	private static Pane gameRoot = new Pane();
	
	User usr = new User();
	public Label scoreLabel = new Label("Score: "+ score);
	
	public static void start_game(Stage primaryStage) throws Exception
	{	
//		System.out.println(primaryStage == null);
//		Application.launch(args);
		GameCanvas g = new GameCanvas();
		g.start(primaryStage);
		
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
				
		  	System.out.println("Yay!!!");
//		  	showStage();
//		  	Added the pop Up Window
		  	try {
				deserialize("whatever.txt");
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  	showPopUpWindow();
		    return 1;
		  } 
		  
		  else 
		  {
		    return 0;
		  }
		  
	}
	
	public void createSaveSlot() throws IOException 
	{
		SaveSlot saveData = new SaveSlot(score,this.myObstacles,this.usr);
		serialize("whatever.txt",saveData);
	}
	
	
	
	@Override
    public void start(Stage primaryStage) throws Exception 
	{
        
//		primaryStage.setTitle("Game Canvas");
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
	
		myObstacles = new Obstacle();
		myColorBalls = new ColorBall(root);
		mystars = new Stars(root);
		
		myObstacles.setTranslations(root);
	
		root.getChildren().add(usr);
		
		Button pause = new Button("Pause");
		pause.setOnAction(value -> {
			try {
				createSaveSlot();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			
		});
		root.getChildren().add(pause);
	
//		root.getChildren().add(depause);
	
	
		scene.setOnMouseClicked(event->{
			usr.jump();
			//System.out.println("clicked");
		});
	
		primaryStage.setScene(scene);
//		primaryStage.show();
	
	
		AnimationTimer timer = new AnimationTimer() 
		{
			@Override
			public void handle(long arg0)
			{
				
				int newCol = myColorBalls.detectCollision();
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
	
	void serialize(String file, SaveSlot saveData) throws IOException
	{
//		GameCanvas canvas = this;
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		
		objectOutputStream.writeObject(saveData);

		objectOutputStream.close();	
	}
	
	void deserialize(String file) throws ClassNotFoundException, IOException
	{
		FileInputStream fileInputStream = new FileInputStream(file);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
		SaveSlot savedData = (SaveSlot) objectInputStream.readObject();
		processSavedData(savedData);
		objectInputStream.close();
//		return object;
	}
	
	private void processSavedData(SaveSlot slot)
	{
		GameCanvas canvas = new GameCanvas();
		canvas.myObstacles = slot.getObstacle();
		
		canvas.usr = slot.getUser();
		score = slot.getScore();
		System.out.println(score);
//		System.out.println(canvas.usr.score+"well");
		
	}
	
	
	
	
	
	
	
	
	
	

}
