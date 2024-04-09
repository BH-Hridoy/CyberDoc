package com.example.cyberdoc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyMedicineController implements Initializable {

    @FXML
    private WebView buyMedicineWebView;

    @FXML
    private WebEngine engine;


    HelloApplication sceneShift = new HelloApplication();

    public void switchToHome(ActionEvent event) throws IOException {
        sceneShift.changeScene("PatientHomePage.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = buyMedicineWebView.getEngine();
        loadPage();
    }


    public void loadPage(){
        engine.load("http://cyberdoc.mdshahin.me/");
    }

}
