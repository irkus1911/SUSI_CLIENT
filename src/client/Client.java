/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.controllers.VSignUpController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author 2dam
 */
public class Client extends Application{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("controllers/VSignUp.fxml"));
        Parent root = (Parent)loader.load();
      
        VSignUpController controller = ((VSignUpController)loader.getController());
        controller.setStage(primaryStage);
        controller.initStage(root);
        
    }

   
}
