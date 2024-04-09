package com.example.cyberdoc;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AIDoctorController implements Initializable {

    @FXML
    private Label ai_diseaseName;

    @FXML
    private FontAwesomeIcon ai_docICON1;

    @FXML
    private FontAwesomeIcon ai_docICON2;

    @FXML
    private FontAwesomeIcon ai_docICON3;

    @FXML
    private Label ai_label1;

    @FXML
    private Label ai_label2;

    @FXML
    private Label ai_label3;

    @FXML
    private Button ai_sendBtn;

    @FXML
    private Label ai_showName;

    @FXML
    private TextArea ai_suggestionField;

    @FXML
    private AnchorPane ai_suggestionForm;

    @FXML
    private TextArea user_textfield1;

    @FXML
    private TextArea user_textfield2;

    @FXML
    private TextArea user_textfield3;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;


    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    public int aiQueryCount = 0;
    String firstMessage = "Hi there, how may I help you?";
    String howLong = "How long you are suffering?";
    String anyOtherIssue = "Any extra symptoms?";

    String suggestion = "Few suggestion for you!";

//    public static String query1;
//    public static String query2;
//    public static String query3;
//    public static String query4;
//    public static String query5;
//    public static String query6;
//
//    public static String field2;
//    public static String field3;
//    public static String query7;


    public static String query1;
    public String query2;
    public String query3;
    public String query4;
    public String query5;
    public String query6;

    public String field2;
    public String field3;
    public String query7;






    public void sendBtn() throws InterruptedException {






       String query = user_textfield1.getText();
       query =  query.toLowerCase();
       aiQueryCount += 1;

        //System.out.println(query);


    // ===================== AI LOGIC START ==================


        String[] queryArray = query.split(" ");

        if(aiQueryCount == 1){
            for(int i=0; i<queryArray.length; i++){

                // ======= normal fever and cold logic ======
                if(queryArray[i].equals("fever")){  // detecting initial symptoms

                    query2 = "fever";

                    // show next message

                    ai_label2.setVisible(true);
                    ai_label2.setText(firstMessage);


                    ai_label1.setText(howLong);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                }
                else if(queryArray[i].equals("stomach") || queryArray[i].equals("stomach pain") || queryArray[i].equals("cramps") || queryArray[i].equals("food") || queryArray[i].equals("food posioning")){
                    query2 = "stomach pain";

                    // show next message

                    ai_label2.setVisible(true);
                    ai_label2.setText(firstMessage);


                    ai_label1.setText(anyOtherIssue);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();
                }

                else if (queryArray[i].equals("headache")){
                    query2 = "headache";

                    // show next message

                    ai_label2.setVisible(true);
                    ai_label2.setText(firstMessage);


                    ai_label1.setText(anyOtherIssue);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();
                }
                System.out.println(queryArray[i]);
            }
        }


        // ====  Checking 2nd symptoms

        if(aiQueryCount==2 && query2.equals("fever")){
            for(int i=0; i<queryArray.length; i++){

                if(queryArray[i].equals("two")
                || queryArray[i].equals("three")
                || queryArray[i].equals("four")
                || queryArray[i].equals("five")
                ){  // detecting initial symptoms

                    query3 = "few days";

                    // show next message
                    ai_label3.setVisible(true);
                    ai_label3.setText(firstMessage);

                    ai_label2.setVisible(true);
                    ai_label2.setText(howLong);

                   // ai_label1.wait(2000);
                    ai_label1.setText(anyOtherIssue);

                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);



                    user_textfield1.clear();

                }
                System.out.println(queryArray[i]);
            }
        }



        if(aiQueryCount==2 && query2.equals("stomach pain")){
            for(int i=0; i<queryArray.length; i++){

                if(queryArray[i].equals("no")){  // detected normal fever...


                    Data.diseaseName = "Gastric Problem.";
                    Data.treatment = "Nothing to worry,\nHere are few suggestion for you,\nTake any paracetamol, \nhave proper foods and rest.\nWishing you a quick recovery.";


                    // show next message
                    ai_label3.setVisible(true);
                    ai_label3.setText(howLong);

                    ai_label2.setVisible(true);
                    ai_label2.setText(anyOtherIssue);

                    ai_label1.setText(suggestion);

                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    setAiData();
                    showSuggestionForm();


                }
                else if(queryArray[i].equals("yes") || queryArray[i].equals("yes,")){



                    // ====== Code for shift boxes START =======
                    field2 = ai_label1.getText();
                    field3 = ai_label2.getText();


                    ai_label2.setText(field2);
                    ai_label3.setText(field3);
                    ai_label1.setText("Please, Explain me briefly...");


                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    // ====== Code for shift boxes START =======


                }


                System.out.println(queryArray[i]);
            }


        }

        if(aiQueryCount==2 && query2.equals("headache")){
            for(int i=0; i<queryArray.length; i++){

                if(queryArray[i].equals("no")){  // detected normal fever...


                    Data.diseaseName = "Tension Headache.";
                    Data.treatment = "Nothing to worry,\nHere are few suggestion for you,\nTake any paracetamol, \nhave proper foods and rest.\nWishing you a quick recovery.";


                    // show next message
                    ai_label3.setVisible(true);
                    ai_label3.setText(howLong);

                    ai_label2.setVisible(true);
                    ai_label2.setText(anyOtherIssue);

                    ai_label1.setText(suggestion);

                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    setAiData();
                    showSuggestionForm();


                }
                else if(queryArray[i].equals("yes") || queryArray[i].equals("yes,")){



                    // ====== Code for shift boxes START =======
                    field2 = ai_label1.getText();
                    field3 = ai_label2.getText();


                    ai_label2.setText(field2);
                    ai_label3.setText(field3);
                    ai_label1.setText("Please, Explain me briefly...");


                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    // ====== Code for shift boxes START =======


                }


                System.out.println(queryArray[i]);
            }


        }


        if(aiQueryCount==3 && query2.equals("stomach pain")){
            for(int i=0; i<queryArray.length; i++) {

                if (queryArray[i].equals("vomiting")
                        || queryArray[i].equals("vomiting,")) {
                    query4 = "vomiting";
                }

                if (queryArray[i].equals("diarrhea")
                        || queryArray[i].equals("diarrhea,")) {
                    query5 = "diarrhea";
                }

                if (queryArray[i].equals("nausea")
                        || queryArray[i].equals("nausea,")) {
                    query6 = "nausea";
                }



            }

            if(query4.equals("vomiting")
                    || query6.equals("nausea")
                    || query5.equals("diarrhea")
                    || query5.equals("diarrhea") && query4.equals("vomiting") && query6.equals("nausea")
            ){

                // Detected DENGUE
                Data.diseaseName = "Food Poisoning";
                Data.treatment = "It seems serious,\nPlease contact with doctor for proper guidence\nWishing you a quick recovery.";


                // ====== Code for shift boxes START =======
                field2 = ai_label1.getText();
                field3 = ai_label2.getText();


                ai_label2.setText(field2);
                ai_label3.setText(field3);
                ai_label1.setText(suggestion);


                user_textfield3.setVisible(true);
                user_textfield3.setText(user_textfield2.getText());
                user_textfield3.setEditable(false);

                user_textfield2.setVisible(true);
                user_textfield2.setText(user_textfield1.getText());
                user_textfield2.setEditable(false);

                user_textfield1.clear();

                // ====== Code for shift boxes START =======

                setAiData();
                showSuggestionForm();



            }

            else{


                Data.diseaseName = "Not Recognised";
                Data.treatment = "Please contact with doctor\nWishing you a quick recovery.";
                // ====== Code for shift boxes START =======
                field2 = ai_label1.getText();
                field3 = ai_label2.getText();


                ai_label2.setText(field2);
                ai_label3.setText(field3);
                ai_label1.setText(suggestion);


                user_textfield3.setVisible(true);
                user_textfield3.setText(user_textfield2.getText());
                user_textfield3.setEditable(false);

                user_textfield2.setVisible(true);
                user_textfield2.setText(user_textfield1.getText());
                user_textfield2.setEditable(false);

                user_textfield1.clear();

                // ====== Code for shift boxes START =======

                setAiData();
                showSuggestionForm();
            }






        }


//        ==================
        if(aiQueryCount==3 && query2.equals("headache")){
            for(int i=0; i<queryArray.length; i++) {

                if (queryArray[i].equals("pain")
                        || queryArray[i].equals("pain,")) {
                    query4 = "pain";
                }

                if (queryArray[i].equals("eyes")
                        || queryArray[i].equals("eyes,")) {
                    query5 = "eyes";
                }

                if (queryArray[i].equals("neck")
                        || queryArray[i].equals("neck,")) {
                    query6 = "neck";
                }



            }

            if(query4.equals("pain")
                    || query6.equals("neck")
                    || query5.equals("eyes")
                    || query5.equals("eyes") && query4.equals("pain") && query6.equals("neck")
            ){

                // Detected DENGUE
                Data.diseaseName = "Migraine";
                Data.treatment = "It seems serious,\nPlease contact with doctor for proper guidence\nWishing you a quick recovery.";


                // ====== Code for shift boxes START =======
                field2 = ai_label1.getText();
                field3 = ai_label2.getText();


                ai_label2.setText(field2);
                ai_label3.setText(field3);
                ai_label1.setText(suggestion);


                user_textfield3.setVisible(true);
                user_textfield3.setText(user_textfield2.getText());
                user_textfield3.setEditable(false);

                user_textfield2.setVisible(true);
                user_textfield2.setText(user_textfield1.getText());
                user_textfield2.setEditable(false);

                user_textfield1.clear();

                // ====== Code for shift boxes START =======

                setAiData();
                showSuggestionForm();



            }

            else{


                Data.diseaseName = "Not Recognised";
                Data.treatment = "Please contact with doctor\nWishing you a quick recovery.";
                // ====== Code for shift boxes START =======
                field2 = ai_label1.getText();
                field3 = ai_label2.getText();


                ai_label2.setText(field2);
                ai_label3.setText(field3);
                ai_label1.setText(suggestion);


                user_textfield3.setVisible(true);
                user_textfield3.setText(user_textfield2.getText());
                user_textfield3.setEditable(false);

                user_textfield2.setVisible(true);
                user_textfield2.setText(user_textfield1.getText());
                user_textfield2.setEditable(false);

                user_textfield1.clear();

                // ====== Code for shift boxes START =======

                setAiData();
                showSuggestionForm();
            }






        }




        System.out.println(aiQueryCount);

        // ====  Checking 3rd symptoms

        if(aiQueryCount==3 && query3.equals("few days")){
            for(int i=0; i<queryArray.length; i++){

                if(queryArray[i].equals("no")){  // detected normal fever...


                    Data.diseaseName = "Normal Fever.";
                    Data.treatment = "Nothing to worry,\nHere are few suggestion for you,\nTake any paracetamol, \nhave proper foods and rest.\nWishing you a quick recovery.";


                    // show next message
                    ai_label3.setVisible(true);
                    ai_label3.setText(howLong);

                    ai_label2.setVisible(true);
                    ai_label2.setText(anyOtherIssue);

                    ai_label1.setText(suggestion);

                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    setAiData();
                    showSuggestionForm();


                }
                else if(queryArray[i].equals("yes")){



                    // ====== Code for shift boxes START =======
                    field2 = ai_label1.getText();
                    field3 = ai_label2.getText();


                    ai_label2.setText(field2);
                    ai_label3.setText(field3);
                    ai_label1.setText("Please, Explain me briefly...");


                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    // ====== Code for shift boxes START =======


                }


                System.out.println(queryArray[i]);
            }


        }


        if(aiQueryCount==4 && query2.equals("fever")){
            for(int i=0; i<queryArray.length; i++) {

                if (queryArray[i].equals("headache")
                || queryArray[i].equals("headache,")) {
                    query4 = "headache";
                }

                if (queryArray[i].equals("eyes")
                || queryArray[i].equals("eyes,")) {
                    query5 = "eyes";
                }

                if (queryArray[i].equals("pain")
                || queryArray[i].equals("pain,")) {
                    query6 = "pain";
                }

                if (queryArray[i].equals("rash")
                || queryArray[i].equals("rash,")) {
                    query7 = "rash";
                }


            }

                if(query4.equals("headache") && query6.equals("pain")
                || query4.equals("headache") && query6.equals("pain") && query5.equals("eyes")
                || query4.equals("headache") && query6.equals("pain") && query5.equals("eyes") && query7.equals("rash")
                ){

                    // Detected DENGUE
                    Data.diseaseName = "DENGUE";
                    Data.treatment = "It seems serious,\nPlease contact with doctor for proper guidence\nWishing you a quick recovery.";


                    // ====== Code for shift boxes START =======
                    field2 = ai_label1.getText();
                    field3 = ai_label2.getText();


                    ai_label2.setText(field2);
                    ai_label3.setText(field3);
                    ai_label1.setText(suggestion);


                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    // ====== Code for shift boxes START =======

                    setAiData();
                    showSuggestionForm();



                }

                else{


                    Data.diseaseName = "Not Recognised";
                    Data.treatment = "Please contact with doctor\nWishing you a quick recovery.";
                    // ====== Code for shift boxes START =======
                    field2 = ai_label1.getText();
                    field3 = ai_label2.getText();


                    ai_label2.setText(field2);
                    ai_label3.setText(field3);
                    ai_label1.setText(suggestion);


                    user_textfield3.setVisible(true);
                    user_textfield3.setText(user_textfield2.getText());
                    user_textfield3.setEditable(false);

                    user_textfield2.setVisible(true);
                    user_textfield2.setText(user_textfield1.getText());
                    user_textfield2.setEditable(false);

                    user_textfield1.clear();

                    // ====== Code for shift boxes START =======

                    setAiData();
                    showSuggestionForm();
                }






        }



    // ===================== AI LOGIC END ==================

    } //end of sendbtn


    public void setAiData(){
        ai_showName.setText(Data.patient_name);
        ai_diseaseName.setText(Data.diseaseName);
        ai_suggestionField.setText(Data.treatment);
    }

    public void hideSuggestionForm(){
        ai_suggestionForm.setVisible(false);
    }


    public void showSuggestionForm(){
        ai_suggestionForm.setVisible(true);
    }

    public void showInitial(){



        ai_label1.setText(firstMessage);
        ai_label2.setVisible(false);
        ai_label3.setVisible(false);
        ai_docICON2.setVisible(false);
        ai_docICON3.setVisible(false);

        user_textfield2.setVisible(false);
        user_textfield3.setVisible(false);
    }




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




    // ANIMATION START =======


    public void moveElement(Label l, int x, int y){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(l);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDelay(Duration.millis(1000));
        translate.setByX(x);
        translate.setByY(y);
        translate.setAutoReverse(true);
        translate.play();
        moveElement2(l2, 0, -4);
    }
    public void moveElement2(Label l, int x, int y){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(l);
        translate.setDuration(Duration.millis(1000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDelay(Duration.millis(1500));
        translate.setByX(x);
        translate.setByY(y);
        translate.setAutoReverse(true);
        translate.play();
        moveElement3(l3, 0, -4);
    }

    public void moveElement3(Label l, int x, int y){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(l);
        translate.setDuration(Duration.millis(1000));
//        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setDelay(Duration.millis(2000));
        translate.setCycleCount(TranslateTransition.INDEFINITE);
        translate.setByX(x);
        translate.setByY(y);
        translate.setAutoReverse(true);
        translate.play();
    }


    // fade

    public void fadeAnim(){
        FadeTransition fade = new FadeTransition();

        fade.setNode(ai_label1);

        fade.setDuration(Duration.millis(1000));
        fade.setDelay(Duration.millis(2500));

        //fade.setCycleCount(TranslateTransition.INDEFINITE);

        fade.setInterpolator(Interpolator.LINEAR);

        fade.setFromValue(0);

        fade.setToValue(1);

        fade.play();
    }




    // ANIMTAION END =======



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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showInitial();
       // hideSuggestionForm();


        rotateElement(img1);
        rotateElement(img2);
        rotateElement(img3);
        rotateElement(img4);



        rotateAnimation(img1);
        rotateAnimation(img2);
        rotateAnimation(img3);
        rotateAnimation(img4);



        moveImage(img1);
        moveImage(img2);
        moveImage(img3);
        moveImage(img4);



        moveElement(img1,5,-1);
        moveElement(img2,-5,1);
        moveElement(img3,-3,2);
        moveElement(img4,5,1);


        //fadeAnim();
        moveElement(l1, 0, -4);
    }




}
