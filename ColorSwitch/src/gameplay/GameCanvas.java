package gameplay;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.io.ObjectInputStream;
//import java.util.Scanner;
import java.io.Serializable;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
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
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
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
	private static int counter = 0;
	private int id;
	private static Stage Pstage;
	private User usr;
	
	GameCanvas()
	{
		usr = new User();
		myObstacles = new Obstacle(400);
		id = ++counter;
	}
	
	private static Pane appRoot = new Pane();
	private static Pane gameRoot = new Pane();
	
	public Label scoreLabel = new Label(".                   Score: "+ score);
	
	public static void start_game(Stage primaryStage) throws Exception
	{
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
		int x = usr.getScore();
		String s = "";
		
		if (x % 300 == 0)
			{
				s = " Great going!!"; 
			}
		
		scoreLabel.setText("         Score: "+ x + s);
	}
	
	private int checkShapeIntersection(User user) throws IOException 
	{
		  if (myObstacles.detectCollision(user)) 
		  {
			createSaveSlot();
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
		SaveSlot saveData = new SaveSlot(score,this.myObstacles,this.usr, this.id);	
		String filename = "C:\\Users\\HP\\eclipse-workspace\\ColorSwitch\\SavedGames\\Game" + this.id + ".txt";
		serialize(filename, saveData);		
	}
		
	@Override
    public void start(Stage primaryStage) throws Exception 
	{
//		AudioClip note = new AudioClip(this.getClass().getResource("littleidea.mp3").toString());
//		note.play();
		scoreLabel = new Label("       Score: 00" );
		scoreLabel.setFont(new Font("Arial", 24));
		
		primaryStage.setTitle("Game Canvas");
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600);
	
		myColorBalls = new ColorBall(root);
		mystars = new Stars(root);
		usr = new User();
		
		myObstacles.setTranslations(root);
		root.getChildren().add(scoreLabel);
	
		root.getChildren().add(usr);
		Button pause = new Button("Pause");	
		
		pause.setOnAction(value -> {	
			try 
			{	
				callPauseScreen(primaryStage);
				createSaveSlot();	
			} 
			catch (IOException e) 
			{	
				e.printStackTrace();	
			}	
		});	
		root.getChildren().add(pause);	

	
		scene.setOnMouseClicked(event->{
			usr.jump();
		});
	
		primaryStage.setScene(scene);
		primaryStage.show();
		final long startNanoTime = System.nanoTime();
		int flag = 0;
		
		AnimationTimer timer = new AnimationTimer() 
		{		
			@Override
			public void handle(long currentNanoTime)
			{
				int newCol = myColorBalls.detectCollision(usr);
				if(newCol > 0) 
				{
					usr.changeColor(newCol);
				}
				
				int gainPoints = mystars.detectCollision(usr);
				
				if (gainPoints>0) 
					{
						usr.addPoints();
					}
				
				try 
				{
					if(checkShapeIntersection(usr) == 1)
					{
						stop();
					}
					
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
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
		RevivalScreenController rv = new RevivalScreenController(Pstage);
		rv.setUser(this.usr, this.id);
	
      try 
       {
           Parent root1 = (Parent) fxmlLoader.load();

   		popUpWindow.initModality(Modality.APPLICATION_MODAL );
   	    popUpWindow.initStyle(StageStyle.UNDECORATED); //To remove the upper border of class.
  	    popUpWindow.setTitle("PopUpWindow");
   	    popUpWindow.setScene(new Scene(root1)); 
  	    popUpWindow.show();
      }
       catch (IOException exception) 
       {
           throw new RuntimeException(exception);
       }
		
	}
	
	private void callPauseScreen(Stage PrimaryStage) throws IOException
	{
		FXMLLoader GFPageLoader = new FXMLLoader(getClass().getResource("PauseGame.fxml"));
        Parent GFPane = GFPageLoader.load();
        Scene GFScene = new Scene(GFPane, 400, 600);
        PauseGameController g = new PauseGameController();
        g.setid(this.id);
        
        
    	try
    	{
    		
    		Stage stage = PrimaryStage;
    		stage.setScene(GFScene);

        } 
    	
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }
		
	}

	void serialize(String file, SaveSlot saveData) throws IOException 
	{
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
		objectOutputStream.writeObject(saveData);
		objectOutputStream.close();	
	}
	
	public static void deserialize(String file, Stage Pstage) throws Exception	
	{	
		FileInputStream fileInputStream = new FileInputStream(file);	
		BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);	
		ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);	
		SaveSlot savedData = (SaveSlot) objectInputStream.readObject();	
		processSavedData(savedData, Pstage);	
		objectInputStream.close();	
	}	

	private static void processSavedData(SaveSlot slot, Stage pStage) throws Exception	
	{	
		GameCanvas canvas = new GameCanvas();	
		canvas.myObstacles = slot.getObstacle();	
		canvas.usr = slot.getUser();
		canvas.usr.score = 100;
		canvas.start(pStage);	
	}
	
	
	

}
