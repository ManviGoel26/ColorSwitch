package gameplay;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

//closeButton-GoToGameFinishedScreen
//time remaining
public class RevivalScreenController extends VBox implements Initializable
{
    @FXML private Label timeRemaining;
    @FXML private Button CloseButton;
    private int startTime = 5;
    
//    @FXML private 

    public RevivalScreenController() 
    {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RevivalScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try 
        {
            fxmlLoader.load();
        }
        catch (IOException exception) 
        {
            throw new RuntimeException(exception);
        }
    }

    
    public void GoToGameFinishedScreen(ActionEvent event)
    {
//    	Add the call after adding the new program;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

    
}
