package gameplay;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
//import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RevivalScreenController extends VBox implements Initializable
{
    @FXML
    public Label timeRemaining;
    
    @FXML
    private Button CloseButton;
    
    private int startTime = 5;
    

    
    public void GoToGameFinishedScreen(ActionEvent event) throws Exception
    {
//    	Calls the Game finished page;
    	try
    	{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameFinishedScreen.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));  
            stage.show();
//            Platform.exit();
        } 
    	
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }
    	
    }
    
//    public void setTimer() {
//        Timer timer = new Timer();
////        Platform.setImplicitExit(false);
//        timer.scheduleAtFixedRate(new TimerTask() {
//            public void run() {
//                if(startTime > 0)
//                {
//                    Platform.runLater(new Runnable() {
//                    	@Override public void run()
//                    	{
//                    		timeRemaining.setText(" " + startTime);
//                    		System.out.println(startTime);
//                            startTime--;
//                    	}
//                    });
//                }
//                else
//                {
//                    timer.cancel();
//                }
//                	
//            }
//        }, 1000,1000);
//    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}

    
}
