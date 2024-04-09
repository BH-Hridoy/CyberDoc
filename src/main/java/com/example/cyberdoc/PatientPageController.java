package com.example.cyberdoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientPageController implements Initializable {

    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_patientID;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private TextField login_showPassword;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_doctorID;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private TextField register_fullName;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private Button register_signupBtn;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    @FXML
    void loginAccount(ActionEvent event) {

        if(login_patientID.getText().isEmpty()
                || login_password.getText().isEmpty()){
            alert.errorMessage("Please, fill all the fields");
        }
        else {
            String sql = "SELECT * FROM patient WHERE patient_id = ? AND password = ? AND date_delete IS NULL";
            connect = Database.connectDB();

            try{

                if(!login_showPassword.isVisible()){
                    if(!login_showPassword.getText().equals(login_password.getText())){
                        login_showPassword.setText(login_password.getText());
                    }
                }else{
                    if(!login_showPassword.getText().equals(login_password.getText())){
                        login_password.setText(login_showPassword.getText());
                    }
                }

                // CHECK IF THE STATUS OF DOCTOR IS CONFIRMED

                String checkStatus = "SELECT status FROM patient WHERE patient_id = '"
                        + login_patientID.getText() +"' AND password = '"
                        + login_password.getText() +"'AND status = 'Confirm'";

                prepare = connect.prepareStatement(checkStatus);
                result = prepare.executeQuery();

                if(result.next()){

                    if(!login_showPassword.isVisible()){
                        if(!login_showPassword.getText().equals(login_password.getText())){
                            login_showPassword.setText(login_password.getText());
                        }
                    }else{
                        if(!login_showPassword.getText().equals(login_password.getText())){
                            login_password.setText(login_showPassword.getText());
                        }
                    }


                    alert.errorMessage("Need the confirmation of the Admin");


                }
                else {

                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, login_patientID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if(result.next()){
                        alert.successMessage("Login Successfully!");
                    }else{
                        alert.errorMessage("Incorrect Patient ID/Password!");

                    }

                }




            }catch (Exception e){e.printStackTrace();}
        }



    }

    @FXML
    void loginShowPassword(ActionEvent event) {
        if(login_checkBox.isSelected()){
            login_showPassword.setText(login_password.getText());
            login_password.setVisible(false);
            login_showPassword.setVisible(true);
        }else{
            login_password.setText(login_showPassword.getText());
            login_password.setVisible(true);
            login_showPassword.setVisible(false);
        }
    }

    @FXML
    void registerAccount(ActionEvent event) {

    }

    @FXML
    void registerShowPassword(ActionEvent event) {

    }


    // ====== COMMON LOGIN PAGE CHANGE CODE START =======
    public void userList(){

        ArrayList<String> listU = new ArrayList<>();

        for(String data: Users.user){
            listU.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listU);
        login_user.setItems(listData);

    }



    public void switchPage(){
        HelloApplication loginPageChange = new HelloApplication();
        if(login_user.getSelectionModel().getSelectedItem() == "Admin Portal"){

            try{
                loginPageChange.changeScene("login.fxml");
            }catch (Exception e){e.printStackTrace();}
        }
        else if(login_user.getSelectionModel().getSelectedItem() == "Doctor Portal"){

            try{
                loginPageChange.changeScene("DoctorPage2.fxml");
            }catch (Exception e){e.printStackTrace();}

        }else if(login_user.getSelectionModel().getSelectedItem() == "Patient Portal"){
            try{
                loginPageChange.changeScene("PatientPage.fxml");
            }catch (Exception e){e.printStackTrace();}
        }
    }



    // ====== COMMON LOGIN PAGE CHANGE CODE END =======



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

    @FXML
    void switchForm(ActionEvent event) {
        if(event.getSource() == register_loginHere){
            login_form.setVisible(true);
            register_form.setVisible(false);
        }else if(event.getSource() == login_registerHere){
            login_form.setVisible(false);
            register_form.setVisible(true);
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();


    }
}
