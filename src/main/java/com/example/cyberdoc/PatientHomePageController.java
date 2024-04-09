package com.example.cyberdoc;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientHomePageController {





    // ========== Scene Shift Start==========

    private Stage stage;
    private Scene scene;
    private Parent root;

    HelloApplication sceneShift = new HelloApplication();

    public void switchToHome(ActionEvent event) throws IOException {
        sceneShift.changeScene("PatientHomePage.fxml");
    }

    public void switchToLogout(ActionEvent event) throws IOException {
        sceneShift.changeScene("hello-view.fxml");
    }

    public void switchToAIDoctor(ActionEvent event) throws IOException {
        sceneShift.changeScene("AIDoctorPage.fxml");
    }

    public void switchToDashboard(ActionEvent event) throws IOException {
        sceneShift.changeScene("PatientDashboard.fxml");
    }


    public void switchToBuyMedicine(ActionEvent event) throws IOException {
        sceneShift.changeScene("BuyMedicine.fxml");
    }
    public void switchToPatientOptions(ActionEvent event) throws IOException {
        sceneShift.changeScene("PatintOptions.fxml");
    }


    // ========== Scene Shift End==========
}
