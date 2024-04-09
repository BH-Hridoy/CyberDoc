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

public class loginController implements Initializable {


    @FXML
    private ComboBox<?> login_user;

    @FXML
    private CheckBox login_checkBox;

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
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private TextField register_email;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private TextField register_showPassword;

    @FXML
    private Button register_signupBtn;

    @FXML
    private TextField register_username;

    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    private AlertMessage alert = new AlertMessage();


    // ========= Login System Start ========


    public void loginAccount(){

        HelloApplication m = new HelloApplication();

        if(login_username.getText().isEmpty()
                || login_password.getText().isEmpty()){
            alert.errorMessage("Incorrect Username/Password");
        }else{




            String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";

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



                prepare = connect.prepareStatement(sql);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());
                result = prepare.executeQuery();


                if(result.next()){

                    // TO GET THE USERNAME
                    Data.admin_username = login_username.getText();


                    Data.admin_fullName = result.getString("full_name");

                    // IF USERNAME AND PASSWORD IS CORRECT
                   // alert.successMessage("Login Successfully!");
                    m.changeScene("AdminMainForm.fxml");
                }else{
                    // IF USERNAME OR PASSWORD IS WRONG
                    alert.errorMessage("Incorrect Username/Password");
                }

            }catch (Exception e){e.printStackTrace();}


        }


    }

    public void loginShowPassword(){

        if(login_checkBox.isSelected()){
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);
        }else{
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }

    }



    public void registerAccount(){
        if(register_email.getText().isEmpty()
                || register_username.getText().isEmpty()
                || register_password.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }
        else if(register_password.getText().length() < 8){
            alert.errorMessage("Invalid Password, al least 8 characters required!"); // CHECKS IF THE PASSWORD LENGHT IS LESS THAN 8
        }

        else {

            // CHECK IF THE USERNAME THAT IS ENTERED IS ALREADY EXIST TO OUR DATABASE
            String checkUsername = "SELECT * FROM admin WHERE username = '"
                    + register_username.getText() + "'";

            connect = Database.connectDB();


            try {


                if(!register_showPassword.isVisible()){
                    if(!register_showPassword.getText().equals(register_password.getText())){
                        register_showPassword.setText(register_password.getText());
                    }
                }else{
                    if(!register_showPassword.getText().equals(login_password.getText())){
                        register_password.setText(register_showPassword.getText());
                    }
                }



                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if(result.next()){
                    alert.errorMessage(register_username.getText() + " is already exist!");
                }
                else{

                    // TO INSERT OUR DATA TO DATABASE
                    String insertData = "INSERT INTO admin (email, username, password, date) VALUES(?, ?, ?, ?)";

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, register_email.getText());
                    prepare.setString(2, register_username.getText());
                    prepare.setString(3, register_password.getText());
                    prepare.setString(4, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");
                    registerClear();// CLEARS ALL FIELDS AFTER SUCCESSFUL REGISTER

                    // TO SWITCH REGISTRATION FORM TO LOGIN FORM
                    login_form.setVisible(true);
                    register_form.setVisible(false);
                }

            }catch (Exception e){e.printStackTrace();}

        }

    }


    public void registerClear(){
        register_email.clear();
        register_username.clear();
        register_password.clear();
        register_showPassword.clear();
    }

    public void registerShowPassword(){
        if(register_checkBox.isSelected()){
            register_showPassword.setText(register_password.getText());
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
        }else{
            register_password.setText(register_showPassword.getText());
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
        }


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

    public void switchForm(ActionEvent event){

        if(event.getSource() == login_registerHere){
            // REGISTER FORM WILL BE SHOWN
            login_form.setVisible(false);
            register_form.setVisible(true);
        }else if(event.getSource() == register_loginHere){
            // LOGIN FORM WILL BE SHOWN
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }

    // ========= Login System End ========






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

//    public void login(ActionEvent event) throws IOException {
//        HelloApplication sceneChange = new HelloApplication();
//        if(login_email.getText().toString().equals("admin") && login_password.getText().toString().equals("admin")){
//
//            sceneChange.changeScene("after-login-Home.fxml");
//
//
//        }
//    }



    // ========== Action Listener End =========



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        userList();


    }
}
