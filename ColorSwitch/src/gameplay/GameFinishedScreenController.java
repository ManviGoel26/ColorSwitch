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

public class GameFinishedScreenController extends VBox implements Initializable
{
    @FXML private Label UserScore;
    @FXML private Label HighScore;
    @FXML private Button GoToMainMenuButton;
    @FXML private Button Replay;
    @FXML private Label TotalStars;
    
    private static int highScore;
    private User usr;
    
    void setUser(User user)
    {
    	usr = user;
    }
    
    public void replay(ActionEvent event)
    {
     	try
     	
     	{
			GameCanvas.start_game((Stage) Replay.getScene().getWindow());
		}
     	catch (Exception e)
     	{
			e.printStackTrace();
		}
    }
    
    public void GoToMenuScreen(ActionEvent event) throws Exception
    {
//    	Calls the Menu Page
    	int x = usr.getScore();
    	UserScore = new Label("" + x );
    	TotalStars = new Label(x/50 + "" );
    	if (x > highScore)
    	{
    		highScore = x;
    	}
    	HighScore = new Label(""+ highScore );
        
    	FXMLLoader MenuPageLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent MenuPane = MenuPageLoader.load();
        Scene MenuScene = new Scene(MenuPane, 400, 600);
        
    	try
    	{
    		
    		Stage stage = (Stage) GoToMainMenuButton.getScene().getWindow();
    		stage.setScene(MenuScene);
    		

        } 
    	
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }

    	
    }

    

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
	}

    
}
