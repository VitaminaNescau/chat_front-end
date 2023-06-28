package com.example;

import javafx.application.Application;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {
    Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        Parent login = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene scene = new Scene(login);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        this.stage.setScene(scene);
        this.stage.setResizable(false);
        this.stage.show();
        
    }

    public void showMainScreen() throws IOException  {
        // Carrega o arquivo FXML da tela principal
        Parent chat_main = FXMLLoader.load(getClass().getResource("/fxml/chat_main.fxml"));
        Scene mainScene = new Scene(chat_main);
        mainScene.getStylesheets().add("/styles/Styles.css");
        mainScene.getRoot().setStyle("-fx-font-family: 'serif'");
        // Define a cena da tela principal
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setScene(mainScene);
        stage.show();
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
