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
	private static int counter = 0;
	private final int id;
	private static Stage Pstage;
	 
	GameCanvas()
	{
		myObstacles = new Obstacle(400);
		id = ++counter;
	} 
	private static Pane appRoot = new Pane();
	private static Pane gameRoot = new Pane();
	
	User usr = new User();
	public Label scoreLabel;
	
	public static void start_game(Stage primaryStage) throws Exception{
//		System.out.println(primaryStage == null);	
//		Application.launch(args);	
		GameCanvas g = new GameCanvas();	
		g.start(primaryStage);
		
		System.out.println("running1");
//		launch(args);	
	}
	
	public void update(Group root) 
	{
		if(usr.centre.getY() < 5)
		{	
			usr.centre = usr.centre.add(0,1);
		}
		
		usr.moveY((int)usr.centre.getY(), myObstacles, myColorBalls, mystars);
		int x= usr.getScore();
		String s="";
		if(x%300==0) s=" Great going!!"; 
		scoreLabel.setText("Score: "+ x+s);
	}
	
	private int checkShapeIntersection() 
	{
		  if (myObstacles.detectCollision()) 
		  {
//				try {	
//				deserialize("whatever.txt");	
//			} catch (IOException | ClassNotFoundException e) {	
//				// TODO Auto-generated catch block	
//				e.printStackTrace();	
//			}	
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
//		serialize("whatever.txt",saveData);	
		String filename = "C:\\Users\\HP\\eclipse-workspace\\ColorSwitch\\SavedGames\\Game" + this.id + ".txt";
		serialize(filename, saveData);
		
	}
		
	@Override
    public void start(Stage primaryStage) throws Exception 
	{
//        Pstage = primaryStage;
		scoreLabel= new Label("Score: 00" );
		scoreLabel.setFont(new Font("Arial", 24));
		
		primaryStage.setTitle("Game Canvas");
		Group root = new Group();
		root.getChildren().add(scoreLabel);
		Scene scene = new Scene(root, 600, 600);
	
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

	
		scene.setOnMouseClicked(event->{
			usr.jump();
			//System.out.println("clicked");
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
//				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
//				//System.out.println(t);
//				if(t>30 && t<30.3) {
//					myObstacles = new Obstacle(-4000);
//					myObstacles.setTranslations(root);
//					System.out.println("setted translation");
//					flag=1;
//				}
				int newCol=myColorBalls.detectCollision();
				if(newCol>0) {
					usr.changeColor(newCol);
				}
				int gainPoints=mystars.detectCollision();
				
				//System.out.println(gainPoints);
				
				if(gainPoints>0) usr.addPoints();
				if(checkShapeIntersection() == 1){
					stop();
				}
				
				//i++;
				//System.out.println(i);
				update(root);
				
			}
		};
		//java.util.Scanner in=new java.util.Scanner(System.in);
		//int c=in.nextInt();
		TimeUnit.SECONDS.sleep(2);
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
//   	    popUpWindow.initStyle(StageStyle.UNDECORATED); //To remove the upper border of class.
  	    popUpWindow.setTitle("PopUpWindow");
   	    popUpWindow.setScene(new Scene(root1)); 
//   	    rv.setTimer();
  	    popUpWindow.show();
      }
       catch (IOException exception) 
       {
           throw new RuntimeException(exception);
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
		System.out.println((savedData.getUser() == null) + "bwfne");
		processSavedData(savedData, Pstage);	
		objectInputStream.close();	
//		return object;	
	}	

	private static void processSavedData(SaveSlot slot, Stage pStage) throws Exception	
	{	
		GameCanvas canvas = new GameCanvas();	
		canvas.myObstacles = slot.getObstacle();	

		canvas.usr = slot.getUser();
		System.out.println(slot.getUser() == null);	

//		score = slot.getScore();
//		Stage s = pStage;
		canvas.start(pStage);
//		System.out.println(score);	
		
	}
	
//	private void showPauseScreen
	
	

}
