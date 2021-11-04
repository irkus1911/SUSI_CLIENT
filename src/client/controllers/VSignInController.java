/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

//import client.factory.LogicableFactory;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.dataModel.User;
import lib.exceptions.ConnectException;
import lib.exceptions.IncorrectPasswordException;
import lib.exceptions.IncorrectUserException;
import lib.exceptions.PasswordDontMatchException;
import lib.exceptions.TooManyUsersException;
import lib.exceptions.UserDontExistException;

/**
 * Clase controladora para la ventana de SignIn
 * @author UnaiUrtiaga
 */
public class VSignInController {

    private Stage stage;
    @FXML
    private Button btnSignIn;
    @FXML
    private Button btnExit;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private ImageView imgUserIco;
    @FXML
    private ImageView imgPassIco;
    @FXML
    private Hyperlink hlSignUp;
    @FXML
    private Label lblSignIn;
    @FXML
    private ImageView imgTItlePic;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo initStage el cual se va a ejecutar una vez se abra la ventana
     * @param root Parametro tipo padre para la escena
     */
    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        final Tooltip tooltip = new Tooltip();
        
        stage.setScene(scene);
        stage.setTitle("SignIn");
        stage.setResizable(false);

        this.imgUserIco.setDisable(false);
        this.txtUsername.setDisable(false);
        this.txtUsername.requestFocus();
        this.imgPassIco.setDisable(false);
        this.txtPassword.setDisable(false);
        tooltip.setText("Make sure to introduce only letters, numbers and underscores");
        this.txtPassword.setTooltip(tooltip);
        this.btnSignIn.setDisable(false);
        this.hlSignUp.setDisable(false);
        this.btnExit.setDisable(false);

        stage.setOnCloseRequest(this::handleCloseRequest);

        stage.show();

    }

    /**
     * Metodo que se va a ejecutar una vez pulsado el boton signIn en el cual
     * se controlan todos los campos y excepciones posibles
     * @param event Un parametro devuelto de una accion
     */
    @FXML
    private void handleSignInAction(ActionEvent event) {

        try {

            if (informedFields() && maxCharacteres()) {
                User user = new User();
                user.setLogin(txtUsername.getText());
                user.setPassword(txtPassword.getText());

                LogicableFactory dataFac = new LogicableFactory();

                dataFac.getDataTraffic().signIn(user);

                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VLogOut.fxml"));

                Parent root = loader.load();

                VLogOutController controller = ((VLogOutController) loader.getController());
                controller.setStage(stage);
                controller.initStage(root);

            }

        } catch (IncorrectUserException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BAD USER");

            alert.setContentText("Invalid user. Make sure to introduce only letters, numbers and underscores");

        } catch (IncorrectPasswordException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("BAD PASSWORD");

            alert.setContentText("Invalid password");

        } catch (UserDontExistException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("USER DONT EXIST");

            alert.setContentText("This user doesn't exist");

        } catch (PasswordDontMatchException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("INCORRECT PASSWORD");

            alert.setContentText("The password doesn't match");

        } catch (TooManyUsersException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("TOO MANY USERS");

            alert.setContentText("Server is full. Try again later");

        } catch (ConnectException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("CONNECTION");

            alert.setContentText("Server is inaccessible. We are upgrading some things");

        } catch (IOException ex) {
            Logger.getLogger(VSignInController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Metodo que se ejecutara una vez pulsado el boton exit el cual va a confirmar
     * si de verdad quiere salir
     * @param event Un parametro devuelto de una accion
     */
    @FXML
    private void handleExitAction(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Sure you want to go out?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            stage.close();
        }

    }
    
    /**
     * Metodo que se va a ejecutar una vez pulsado el hyperlink de signUp el cual
     * te va a llevar a la ventana SignUp
     * @param event Un parametro devuelto de una accion
     * @throws IOException Excepcion requerida para el load()
     */
    @FXML
    private void handleSignUpAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignUp.fxml"));

        Parent root = loader.load();

        /*VSignUpController controller = ((VSignUpController) loader.getController());
        controller.setStage(stage);
        controller.initStage(root);*/

    }

    /**
     * Metodo que se va a ejecutar una vez pulsado el boton X de la barra superior
     * de la aplicacion el cual va a confirmar si de verdad quiere salir
     * @param event Un parametro devuelto de una accion
     */
    @FXML
    private void handleCloseRequest(WindowEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("EXIT");

        alert.setContentText("Sure you want to go out?");

        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK) {
            stage.close();
        }else{
            event.consume();
        }

    }

    /**
     * Metodo tipo booleano que comprueba si los campos estan informados o no
     * @return Devuelve una variable booleana
     */
    private boolean informedFields() {

        if (txtUsername.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO USERNAME VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else if (txtPassword.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("CAMPO PASSWORD VACIO");
            alert.setContentText("No puedes dejar el campo vacio");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

    /**
     * Metodo tipo booleano que comprueba si los campos contienen mas de 255
     * caracteres
     * @return Devuelve una variable booleana
     */
    private boolean maxCharacteres() {

        if (txtUsername.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
            return false;
        } else if (txtPassword.getText().trim().length() >= 255) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("LIMITE SUPERADO 255 CARACTERES");
            alert.setContentText("No se puede superar los 255 caracteres");
            alert.show();
            return false;
        } else {
            return true;
        }

    }

}
