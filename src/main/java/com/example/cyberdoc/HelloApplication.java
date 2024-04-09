package com.example.cyberdoc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static Stage stg;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("userLoginPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //stage.setResizable(false);
        stage.setTitle("CyberDoc");

        stg = stage;

        stage.setMaximized(true);
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }


    public void changeScene(String fxml) throws IOException{
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.setMaximized(true);
    }




    public static void main(String[] args) {
        launch();
    }
}