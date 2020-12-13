
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

public class LoadSavedGamePageController extends VBox implements Initializable
{
    @FXML private Button GoToMenuButton;
    @FXML private Button Play;
    
    
    public void GoToMenuScreen(ActionEvent event) throws Exception
    {
//    	Calls the Game finished page;
    	FXMLLoader MenuPageLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent MenuPane = MenuPageLoader.load();
        Scene MenuScene = new Scene(MenuPane, 400, 600);
        
        
    	try
    	{
    		
    		Stage stage = (Stage) GoToMenuButton.getScene().getWindow();
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
		// TODO Auto-generated method stub
		
	}

    
}
