package gameplay;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Game extends Application
{
	static String[] args;

	public static void main(String[] args) throws IOException {
		Game.args = args;
		launch(args);
				
	}
	
	@Override
	public void start(Stage stage1) throws IOException
	{
		FXMLLoader loader  = new FXMLLoader(getClass().getResource("Menu.fxml"));
		Parent LGPane = loader.load();
        
		Scene LGScene = new Scene(LGPane, 400, 600);
		MenuController m = new MenuController();
		m.args = args;
		
        
        
    	try
    	{
    		
    		Stage stage = new Stage();
    		stage.setScene(LGScene);
    		MenuController.Pstage = stage;
    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.show();

        } 
    	
    	catch(Exception e) 
    	{
            e.printStackTrace();
        }

		
	}

	

}
