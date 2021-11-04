/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.controllers;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Clase controladora para la ventana de LogOut
 * @author UnaiUrtiaga
 */
public class VLogOutController {

    private Stage stage;

    @FXML
    private Button btnLogOut;
    @FXML
    private Button btnExit;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Metodo que se ejecutará una vez se abra la ventana
     * @param root Parametro de tipo padre para pasárselo a la escena
     */
    public void initStage(Parent root) {

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("LogOut");
        stage.setResizable(false);

        this.btnLogOut.setDisable(false);
        this.btnExit.setDisable(false);

        stage.setOnCloseRequest(this::handleCloseRequest);

        stage.show();

    }

    /**
     * Método para cerrar sesión que se ejecutará una vez pulsado el botón LogOut
     * @param event Parámetro del evento enviado por el botón
     * @throws IOException Excepcion pedida por el metodo load
     */
    @FXML
    private void handleLogOutAction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/client/controllers/VSignIn.fxml"));
        
        Parent root = loader.load();
        
        VSignInController controller = ((VSignInController) loader.getController());
        controller.setStage(stage);
        controller.initStage(root);

    }

    /**
     * Método para salir de la aplicación que se ejecutará una vez pulsado el
     * botón Exit confirmando primero su salida
     * @param event Parámetro del evento enviado por el botón
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
     * Método que controla la salida de la aplicación por el botón X de windows
     * preguntando al usuario si de verdad quiere salir
     * @param event Parámtro del evento enviado por la ventana
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
        } else {
            event.consume();
        }

    }

}
