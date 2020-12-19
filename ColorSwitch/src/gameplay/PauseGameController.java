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

public class PauseGameController extends VBox implements Initializable
{
    
    @FXML
    private Button Resume;
    
    
    @FXML
    private Button MenuButton;//Need to fix
    private static int id;
    
    void setid(int idi)
    {
    	id = idi;
//    	System.out.println(id);
    }
    

    
    public void GoToMenu(ActionEvent event) throws Exception
    {
    	FXMLLoader MenuPageLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent MenuPane = MenuPageLoader.load();
        Scene MenuScene = new Scene(MenuPane, 400, 600);
        
        
    	try
    	{
    		
    		Stage stage = (Stage) MenuButton.getScene().getWindow();
    		stage.setScene(MenuScene);

        } 
    	
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }

    }
    
    public void ResumeGame(ActionEvent event) throws Exception
    {
//    	System.out.println(this.id+"ef");
//    	if (GameNumber.getText())
    	String filename = "C:\\Users\\HP\\eclipse-workspace\\ColorSwitch\\SavedGames\\Game" + this.id + ".txt";
		
    	GameCanvas.deserialize(filename,(Stage) Resume.getScene().getWindow() );
//    	String fileName = "Game" + GameNumber.getText();
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		
	}

    
}
