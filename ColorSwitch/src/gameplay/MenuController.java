
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

public class MenuController extends VBox implements Initializable
{
    @FXML private Button GoToLoadSavedGamesScreenButton;
    @FXML private Button Play;
    
    
    public void GoToLoadSavedGamesScreen(ActionEvent event) throws Exception
    {
//    	Calls the Game finished page;
    	FXMLLoader LGPageLoader = new FXMLLoader(getClass().getResource("LoadSavedGamePage.fxml"));
        Parent LGPane = LGPageLoader.load();
        Scene LGScene = new Scene(LGPane, 400, 600);
        
        
    	try
    	{
    		
    		Stage stage = (Stage) GoToLoadSavedGamesScreenButton.getScene().getWindow();
    		stage.setScene(LGScene);

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
