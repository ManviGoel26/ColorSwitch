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

public class GameFinishedScreenController extends VBox implements Initializable
{
    @FXML private Label UserScore;
    @FXML private Label HighScore;
    @FXML private Button GoToMainMenuButton;
    @FXML private Button Replay;
    @FXML private Label TotalStars;


    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		// TODO Auto-generated method stub
		
	}

    
}
