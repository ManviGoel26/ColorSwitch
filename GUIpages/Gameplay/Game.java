package Gameplay;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


/**
 *
 * @author HP
 */
public class Game extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("PauseGame.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("Menu.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("LoadSavedGamepage.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("GameFinished.fxml"));

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
   
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
