/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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
        logger.info("");

        Scene scene = new Scene(root);
        stage.setTitle("SIGN UP");
        //Ventana no se puede redimensionar
        stage.setResizable(false);

        //Hablitar botones/componentes
        //Enfoca campo username
        buttonSignUp.setOnAction(this::handleSignUp);
        buttonBack.setOnAction(this::handleBack);

        stage.setScene(scene);
        stage.show();
    }

    private void handleSignUp(ActionEvent event) {
        logger.info("");
        
        maxCharacteres();
        userCharacterLimitation();
        confirmPassword();
        emailPattern();
        informedFields();

        User user = new User();
        user.setLogin(fieldUsername.getText());
        user.setEmail(fieldEmail.getText());
        user.setFullName(fieldFullName.getText());
        user.setPassword(fieldPassword.getText());

        //LogicableFactory logicableFactory = new LogicableFactory();
        //logicableFactory.getDataTraffic().signUp(user);
    }

    private void maxCharacteres() {

        if (fieldUsername.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO USERNAME HA SUPERADO LOS 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldEmail.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO EMIAL HA SUPERADO LOS 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldFullName.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO FULL NAME HA SUPERADO LOS 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldPassword.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO PASSWORD HA SUPERADO LOS 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        } else if (fieldConfirmPassword.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO CONFIRM PASSWORD HA SUPERADO LOS 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
        }

    }

    // ^         Start of string
    // [a-z0-9]  a or b or c or ... z or 0 or 1 or ... 9
    // +         one or more times (change to * to allow empty string)
    // $         end of string   
    private void userCharacterLimitation() {

        if (!fieldUsername.getText().matches("^[a-zA-Z0-9_.@]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("EL CAMPO USERNAME NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        } else if (!fieldEmail.getText().matches("^[a-zA-Z0-9_.@]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("EL CAMPO EMAIL NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        } else if (!fieldFullName.getText().matches("^[a-zA-Z0-9_.@]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("EL CAMPO FULLNAME NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        } else if (!fieldPassword.getText().matches("^[a-zA-Z0-9_.@]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("EL CAMPO USERNAME NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        } else if (!fieldConfirmPassword.getText().matches("^[a-zA-Z0-9_.@]+$")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("EL CAMPO USERNAME NO ES ALFANUMERICO");
            alert.setContentText("El campo no es alfanumerico");
            alert.show();
        }

    }

    private void confirmPassword() {

        if (!fieldPassword.getText().equals(fieldConfirmPassword.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("LA CONTRASEÑA NO COINCIDE");
            alert.setContentText("Las contraseñas deben coincidir");
            alert.show();
        }

    }

    private void emailPattern() {
        if (!fieldEmail.getText().matches("[\\w]+@[\\w]+\\.[a-zA-Z]{2,4}")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO EMAIL NO ES VALIDO");
            alert.setContentText("El campo email debe tener este formato example@mail.com");
            alert.show();
        }

    }

    private void informedFields() {

        if (fieldUsername.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO USERNAME VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldEmail.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO EMAIL VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldFullName.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO FULL NAME VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO PASSWORD VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        } else if (fieldConfirmPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("CAMPO CONFIRM PASSWORD VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
        }

    }

    private void handleBack(ActionEvent event) {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));
        try {
            Parent root = root = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(VSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //VSignInController controller = (VSignInController) loader.getController();
        //controller.setStage(stage);
        //controller.initStage(root);
        
    }

}
