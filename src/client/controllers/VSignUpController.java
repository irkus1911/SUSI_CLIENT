/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

/**
 *
 * @author Steven Arce, Irkus De La Fuente
 */
public class VSignUpController {

    private final static Logger logger = Logger.getLogger("client.controllers.VSignUpController");
    private Stage stage;

    @FXML
    private TextField fieldUsername, fieldEmail, fieldFullName;
    //@FXML
    private PasswordField fieldPassword, fieldConfirmPassword;
    //@FXML
    private Button buttonSignUp, buttonBack;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initStage(Parent root) {
        logger.info("Init stage signUp");
        Scene scene = new Scene(root);
        buttonSignUp = new Button();
        buttonSignUp.setOnMouseClicked(this::handleTextChanged);
        //buttonSignUp.addEventHandler(MouseEvent.MOUSE_CLICKED,this::handleTextChanged);
        stage.setTitle("SIGN UP");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleTextChanged() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Error en la aplicacion");
        alert.showAndWait();
    }

}
