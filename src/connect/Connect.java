/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connect;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author tuski-Revolve
 */
public class Connect extends Application {
  public static Stage pStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/main/WorkStation2.fxml"));
        primaryStage.setTitle("Connect+");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        pStage=primaryStage;
    }
    //WorkStation   loginForm
  
    public static void main(String[] args) {
        launch(args);
    }
    
}
