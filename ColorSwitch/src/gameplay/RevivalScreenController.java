package gameplay;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

//closeButton-GoToGameFinishedScreen
//time remaining
public class RevivalScreenController extends VBox implements Initializable
{
    @FXML private Label timeRemaining;
    @FXML private Button CloseButton;
    private int startTime = 5;
    

    
    public void GoToGameFinishedScreen(ActionEvent event) throws Exception
    {
//    	Add the call after adding the new program;
    	try
    	{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GameFinishedScreen.fxml"));
            Parent root2 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root2));  
            stage.show();
        } 
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    
}
