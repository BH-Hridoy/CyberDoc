package com.example.cyberdoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import java.util.Date;
import java.util.ResourceBundle;

public class DoctorPageController implements Initializable {


    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;



    private final AlertMessage alert = new AlertMessage();



    @FXML
    private CheckBox login_checkBox;

    @FXML
    private TextField login_doctorID;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_loginBtn;

    @FXML
    private PasswordField login_password;

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

    @FXML
    void loginAccount(ActionEvent event) {
        HelloApplication loadScene = new HelloApplication();

        if(login_doctorID.getText().isEmpty()
            || login_password.getText().isEmpty()){
            alert.errorMessage("Please, fill all the fields");
        }
        else {
            String sql = "SELECT * FROM doctor WHERE doctor_id = ? AND password = ? AND delete_date IS NULL";
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

                String checkStatus = "SELECT status FROM doctor WHERE doctor_id = '"+ login_doctorID.getText() +"' AND password = '"
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
                    prepare.setString(1, login_doctorID.getText());
                    prepare.setString(2, login_password.getText());

                    result = prepare.executeQuery();

                    if(result.next()){

                        Data.doctor_id = result.getString("doctor_id");
                        Data.doctor_name = result.getString("full_name");


                      //  alert.successMessage("Login Successfully!");

                        loadScene.changeScene("DoctorMainPanel.fxml");
                    }else{
                        alert.errorMessage("Incorrect Username/Password!");

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

        if(register_fullName.getText().isEmpty()
                || register_email.getText().isEmpty()
                || register_doctorID.getText().isEmpty()
                || register_password.getText().isEmpty()){

            alert.errorMessage("Please fill all blank fields");

        }else {
            String checkDoctorID = "SELECT * FROM doctor WHERE doctor_id = '"
                    + register_doctorID.getText() + "'";

            connect = Database.connectDB();
            try{


                if(!register_showPassword.isVisible()){
                    if(!register_showPassword.getText().equals(register_password.getText())){
                        register_showPassword.setText(register_password.getText());
                    }
                }else {
                    if(!register_showPassword.getText().equals(register_password.getText())){
                        register_password.setText(register_showPassword.getText());
                    }
                }


                prepare = connect.prepareStatement(checkDoctorID);
                result = prepare.executeQuery();

                if(result.next()){
                    alert.errorMessage(register_doctorID.getText() + "is already taken!");
                }
                else if(register_password.getText().length() < 8){
                    alert.errorMessage("Invalid password, al least 8 character required");
                }
                else{

                    String insertData = "INSERT INTO doctor (full_name, email, doctor_id, password, date, status) "
                            + "VALUES(?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertData);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(1, register_fullName.getText());
                    prepare.setString(2, register_email.getText());
                    prepare.setString(3, register_doctorID.getText());
                    prepare.setString(4, register_password.getText());
                    prepare.setString(5, String.valueOf(sqlDate));
                    prepare.setString(6, "Confirm");

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                }


            }catch (Exception e){e.printStackTrace();}
        }
    }

    @FXML
    void registerShowPassword(ActionEvent event) {

        if(register_checkBox.isSelected()){
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        }
        else{
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);

        }
    }


    public void registerDoctorID(){
        String doctorID = "DID-";
        int tempID = 0;

        String sql = "SELECT MAX(id) FROM doctor";

        connect = Database.connectDB();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                tempID = result.getInt("MAX(id)");
            }
            if(tempID == 0){
                tempID +=1;
                doctorID += tempID;
            }else {
                doctorID += (tempID+1);
            }

            register_doctorID.setText(doctorID);
            register_doctorID.setDisable(true);

        }catch (Exception e){e.printStackTrace();}


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







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userList();
        registerDoctorID();

    }
}
