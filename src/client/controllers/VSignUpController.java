/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import java.awt.event.MouseEvent;
import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import lib.dataModel.User;

/**
 *
 * @author Steven Arce, Irkus De La Fuente
 */
public class VSignUpController {

    private final static Logger logger = Logger.getLogger("client.controllers.VSignUpController");
    private Stage stage;

    @FXML
    private TextField fieldUsername, fieldEmail, fieldFullName;
    @FXML
    private PasswordField fieldPassword, fieldConfirmPassword;
    @FXML
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
        stage.setTitle("SIGN UP");
        stage.setResizable(false);

        buttonSignUp.setOnAction(this::handleSignUp);
        buttonBack.setOnAction(this::handleBack);

        stage.setScene(scene);
        stage.show();
    }

    private void handleSignUp(ActionEvent event) {

        informedFields();
        maxCharacteres();
        //userCharacterLimitation();
        //emailPattern();
        confirmPassword();

        User user = new User();
        user.setLogin(fieldUsername.getText());
        user.setEmail(fieldEmail.getText());
        user.setFullName(fieldFullName.getText());
        user.setPassword(fieldPassword.getText());

        //LogicableFactory logicableFactory = new LogicableFactory();
        //logicableFactory.getDataTraffic().signUp(user);
        
    }

    private void informedFields() {

        if (fieldUsername.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO USERNAME VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldEmail.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO EMAIL VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldFullName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO FULL NAME VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO PASSWORD VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldConfirmPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO CONFIRM PASSWORD VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        }

    }

    //Error en la validacion del email
    private void emailPattern() {
        if (!Pattern.matches(fieldEmail.getText(), "\b[a-zA-Z0-9._]+@[a-z]{2,4}\b")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO EMAIL NO VALIDO");
            alert.setContentText("El campo email debe tener este formato algo@algo.algo");
            alert.show();
        }

    }

    private void maxCharacteres() {

        if (fieldUsername.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldEmail.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldFullName.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldPassword.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldConfirmPassword.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        }

    }

    private void confirmPassword() {

        if (!fieldPassword.getText().equals(fieldConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CONTRASEÑA NO COINCIDE");
            alert.setContentText("Los campos de contraseñas deben coincidir");
            alert.show();
        }

    }

    //Falla el pattern
    private void userCharacterLimitation() {

        if (!Pattern.matches(fieldUsername.getText(), "[^a-zA-Z0-9]")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        }

    }

    private void handleBack(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(VSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VSignInController controller = (VSignInController) loader.getController();
        controller.setStage(stage);
        controller.initStage(root);

    }

}
