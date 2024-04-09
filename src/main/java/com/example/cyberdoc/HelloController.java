package com.example.cyberdoc;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private ImageView capsule;

    @FXML
    private ImageView chat1;

    @FXML
    private ImageView chat2;

    @FXML
    private ImageView chat3;

    @FXML
    private ImageView dna;

    @FXML
    private ImageView doctorVector;

    @FXML
    private ImageView heartRate;

    @FXML
    private ImageView medBox;

    @FXML
    private ImageView sthetoscope;

    @FXML
    private ImageView syringe;


    // ========== Home Animation Start =========
    public void rotateAnimation(ImageView img){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(img);
        rotate.setDuration(Duration.millis(1500));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(5);
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }

    public void rotateElement(ImageView img){
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(img);
        rotate.setDuration(Duration.millis(1500));
        rotate.setCycleCount(TranslateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(15);
        rotate.setAutoReverse(true);
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.play();
    }

    public void moveImage(ImageView img){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(10);
        translate.setByY(-2);
        translate.setAutoReverse(true);
        translate.play();

    }

    public void moveElement(ImageView img, int x, int y){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(img);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(x);
        translate.setByY(y);
        translate.setAutoReverse(true);
        translate.play();
    }

    // ========== Home Animation End =========




    // ========== Scene Shift Start==========

    private Stage stage;
    private Scene scene;
    private Parent root;

    HelloApplication sceneShift = new HelloApplication();
    public void switchToHome(ActionEvent event) throws IOException {
        sceneShift.changeScene("hello-view.fxml");

    }

    public void switchToLogin(ActionEvent event) throws IOException {
        sceneShift.changeScene("login.fxml");
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        sceneShift.changeScene("signUpPage.fxml");
    }

    // ========== Scene Shift End==========



    // ========== Action Listener Start =========



    // ========== Action Listener End =========



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // ======= Home page animation start ====

        rotateElement(capsule);
        rotateElement(syringe);
        rotateElement(heartRate);
        rotateElement(dna);
        rotateElement(medBox);
        rotateElement(sthetoscope);

        rotateAnimation(doctorVector);
        rotateAnimation(chat1);
        rotateAnimation(chat2);
        rotateAnimation(chat3);

        moveImage(doctorVector);
        moveImage(chat1);
        moveImage(chat2);
        moveImage(chat3);


        moveElement(heartRate,5,-1);
        moveElement(capsule,-5,1);
        moveElement(syringe,-3,2);
        moveElement(dna,5,1);
        moveElement(medBox,-5,1);
        moveElement(sthetoscope,5,-1);

        // ======= Home page animation end ====

    }
}