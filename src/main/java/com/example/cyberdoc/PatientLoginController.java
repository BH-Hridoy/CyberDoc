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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatientLoginController implements Initializable {
    @FXML
    private Button changePass_backBtn;

    @FXML
    private Hyperlink changePass_backToLogin;

    @FXML
    private PasswordField changePass_cPassword;

    @FXML
    private AnchorPane changePass_form;

    @FXML
    private PasswordField changePass_password;

    @FXML
    private Button changePass_proceedBtn;

    @FXML
    private TextField forgot_answer;

    @FXML
    private Hyperlink forgot_backToLogin;

    @FXML
    private AnchorPane forgot_form;

    @FXML
    private Button forgot_proceedBtn;

    @FXML
    private ComboBox<?> forgot_selectQuestion;

    @FXML
    private TextField forgot_username;

    @FXML
    private Button login_btn;

    @FXML
    private Button login_createAccount;

    @FXML
    private Hyperlink login_forgotPassword;

    @FXML
    private AnchorPane login_form;

    @FXML
    private PasswordField login_password;

    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private CheckBox login_selectShowPassword;

    @FXML
    private TextField login_showPassword;

    @FXML
    private ComboBox<?> login_user;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField signup_answer;

    @FXML
    private ComboBox<?> signup_bloodGroup;

    @FXML
    private Button signup_btn;

    @FXML
    private TextField signup_email;

    @FXML
    private AnchorPane signup_form;

    @FXML
    private TextField signup_fullName;

    @FXML
    private ComboBox<?> signup_gender;

    @FXML
    private Button signup_loginAccount;

    @FXML
    private Hyperlink signup_loginhere;

    @FXML
    private PasswordField signup_password;

    @FXML
    private ComboBox<?> signup_selectQuestion;

    @FXML
    private TextField signup_username;



    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;



    public Connection connectDB(){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");

            return connect;


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public void login() throws IOException{

        AlertMessage alert = new AlertMessage();
        HelloApplication changeScene = new HelloApplication();

        //CHECK IF ALL OR SOME OF THE FIELDS ARE EMPTY
        if(login_username.getText().isEmpty() || login_password.getText().isEmpty()){
            alert.errorMessage("Incorrect Username/Password");
        }else{
            String selectData = "SELECT * FROM patient WHERE "
                    + "username = ? and password = ?"; //users IS OUR TABLE NAME

            connect = connectDB();

            if(login_selectShowPassword.isSelected()){
                login_password.setText(login_showPassword.getText());
            }
            else{
                login_showPassword.setText(login_password.getText());
            }

            try{
                prepare = connect.prepareStatement(selectData);
                prepare.setString(1, login_username.getText());
                prepare.setString(2, login_password.getText());

                result = prepare.executeQuery();
                if(result.next()){
                    // ONCE ALL DATA INSERTED BY THE USER ARE CORRECT, THEN WE WILL PROCEED TO OUR MAIN FORM

                    Data.patient_name = result.getString("full_name");
                    Data.patient_username = result.getString("username");
                    Data.patient_treatment = result.getString("treatment");
                    Data.patient_email = result.getString("email");

                   // alert.successMessage("Successfully Login!");

                   changeScene.changeScene("PatientHomePage.fxml");


                }else {
                    // ELSE,THEN ERROR MESSAGE WILL APPEAR
                    alert.errorMessage("Incorrect Username/Password");

                }
            }catch (Exception e){
                e.printStackTrace();
            }



        }
    }









    public void showPassword(){

        if(login_selectShowPassword.isSelected()){
            login_showPassword.setText(login_password.getText());
            login_showPassword.setVisible(true);
            login_password.setVisible(false);

        }else{
            login_password.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            login_password.setVisible(true);
        }
    } // end of showPassword()



    public void forgotPassword(){

        AlertMessage alert = new AlertMessage();

        if(forgot_username.getText().isEmpty()
                || forgot_selectQuestion.getSelectionModel().getSelectedItem() == null
                || forgot_answer.getText().isEmpty()
        ){
            alert.errorMessage("Please fill all blank fields");
        }
        else {



            String checkData = "SELECT username, question, answer FROM patient "
                    + "WHERE username = ? AND question = ? AND answer = ?";

            connect = connectDB();

            try{

                prepare = connect.prepareStatement(checkData);
                prepare.setString(1, forgot_username.getText());
                prepare.setString(2, (String)forgot_selectQuestion.getSelectionModel().getSelectedItem());
                prepare.setString(3, forgot_answer.getText());

                result = prepare.executeQuery();

                if(result.next()){

                    // PROCEED TO CHANGE PASSWORD
                    signup_form.setVisible(false);
                    login_form.setVisible(false);
                    forgot_form.setVisible(false);
                    changePass_form.setVisible(true);



                }else{
                    alert.errorMessage("Incorrect information");
                }

            }catch (Exception e){e.printStackTrace();}

        }

    } // end of forgot password


//    private String[] questionList = {"What is your favorite food?", "What is your favorite color?"
//            , "What is the name of your pet?", "What is your favorite sport?"};


    public void forgotListQuestion(){
        ArrayList<String> listQ = new ArrayList<>();

        for (String data: questionList){

            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        forgot_selectQuestion.setItems(listData);

    }





    public void register(){

        AlertMessage alert = new AlertMessage();

        //CHECK IF THE FIELD IS EMPTY
        if(signup_email.getText().isEmpty() || signup_username.getText().isEmpty()
                || signup_password.getText().isEmpty()
                || signup_selectQuestion.getSelectionModel().getSelectedItem() == null
                || signup_answer.getText().isEmpty()
        || signup_bloodGroup.getSelectionModel().getSelectedItem() == null
        || signup_gender.getSelectionModel().getSelectedItem() == null
        || signup_fullName.getText().isEmpty()){
            alert.errorMessage("All fields are necessary to be filled");
        }
        else if(signup_password.getText().length() < 8){
            // CHECK IF THE LENGTH OF PASSWORD IS LESS THAN 8
            alert.errorMessage("Invalid Password, at least 8 character needed");
        }
        else {
            String checkUsername = "SELECT * FROM patient WHERE username = '"
                    + signup_username.getText() + "'";
            connect = connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkUsername);

                if(result.next()){
                    alert.errorMessage(signup_username.getText() +" is already taken");
                }
                else{
                    String insertData = "INSERT INTO patient "
                            + "(email, username, password, question, answer, date, full_name ,gender ,blood_group)"
                            + "VALUES(?,?,?,?,?,?,?,?,?)"; //FIVE (?)

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, signup_email.getText());
                    prepare.setString(2, signup_username.getText());
                    prepare.setString(3, signup_password.getText());
                    prepare.setString(4, (String) signup_selectQuestion.getSelectionModel().getSelectedItem());
                    prepare.setString(5, signup_answer.getText());

                    Date date =  new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(6, String.valueOf(sqlDate));
                    prepare.setString(7, signup_fullName.getText());
                    prepare.setString(8,(String) signup_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(9,(String) signup_bloodGroup.getSelectionModel().getSelectedItem());

                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");

                    registerClearFields();


                    signup_form.setVisible(false);
                    login_form.setVisible(true);
                }

            }catch (Exception e){
                e.printStackTrace();

            }
        }
    } // end of register


    public void patientGenderList(){

        List<String> listG = new ArrayList<>();

        for(String data: Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        signup_gender.setItems(listData);

    }

    public void patientBloodGroupList(){
        List<String> listG = new ArrayList<>();

        for(String data: Data.blood_group){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        signup_bloodGroup.setItems(listData);
    }

    // TO CLEAR ALL FIELDS OF REGISTRATION FORM
    public void registerClearFields(){
        signup_email.setText("");
        signup_username.setText("");
        signup_password.setText("");
        signup_selectQuestion.getSelectionModel().clearSelection();
        signup_answer.setText("");
    }


    public void changePassword(){

        AlertMessage alert = new AlertMessage();

        // CHECK ALL FIELDS IS EMPTY OR NOT
        if(changePass_password.getText().isEmpty() || changePass_cPassword.getText().isEmpty()){
            alert.errorMessage("Please fill all blank fields");
        }
        else if(!changePass_password.getText().equals(changePass_cPassword.getText())){
            //  CHECK IF THE PASSWORD AND CONFIRMED PASSWORD ARE MATCHED OR NOT
            alert.errorMessage("Password does not match");
        }else if(changePass_password.getText().length() < 8){
            // CHECKS PASSWORD LENGTH
            alert.errorMessage("Invalid Password, at least 8 character needed");
        }
        else{
            // USERNAME IS OUR REFERENCE TO UPDATE THE DATA OF THE USER
            String updateData = "UPDATE patient SET password = ?, update_date = ? "
                    + "WHERE username = '" + forgot_username.getText() + "'";

            connect = connectDB();

            try{

                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, changePass_password.getText());

                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                prepare.setString(2, String.valueOf(sqlDate));

                prepare.executeUpdate();
                alert.successMessage("Successfully changed password!");

                // LOGIN FORM WILL APPEAR
                signup_form.setVisible(false);
                login_form.setVisible(true);
                forgot_form.setVisible(false);
                changePass_form.setVisible(false);


                login_username.setText("");
                login_password.setVisible(true);
                login_password.setText("");
                login_showPassword.setVisible(false);
                login_selectShowPassword.setSelected(false);

                changePass_password.setText("");
                changePass_cPassword.setText("");


            }catch (Exception e){e.printStackTrace();}
        }

    }



    public void switchForm(ActionEvent event){

        // REGISTRATION FORM WILL BE VISIBLE
        if(event.getSource() == signup_loginhere || event.getSource() == forgot_backToLogin || event.getSource() ==changePass_backToLogin){
            signup_form.setVisible(false);
            login_form.setVisible(true);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        }else if(event.getSource() == login_registerHere){ // LOGIN FORM WILL BE VISIBLE
            signup_form.setVisible(true);
            login_form.setVisible(false);
            forgot_form.setVisible(false);
            changePass_form.setVisible(false);
        }
        else if(event.getSource() == login_forgotPassword){

            signup_form.setVisible(false);
            login_form.setVisible(false);
            forgot_form.setVisible(true);
            changePass_form.setVisible(false);


            // TO SHOW THE DATA OF COMBOBOX
            forgotListQuestion();

        }
//        else if(event.getSource() == changePass_backToLogin){
//            signup_form.setVisible(false);
//            login_form.setVisible(false);
//            forgot_form.setVisible(true);
//            changePass_form.setVisible(false);
//        }
    }


    private String[] questionList = {"What is your favorite food?", "What is your favorite color?"
            , "What is the name of your pet?", "What is your favorite sport?"};
    public void questions(){
        ArrayList<String> listQ = new ArrayList<>();

        for(String data : questionList){
            listQ.add(data);

        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        signup_selectQuestion.setItems(listData);
    }// end of questions

    // ====== COMMON LOGIN PAGE CHANGE CODE END =======
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
//                loginPageChange.changeScene("userLoginPage.fxml");
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



    @Override
    public void initialize(URL url, ResourceBundle rb){
        questions();
        forgotListQuestion();
        patientGenderList();
        patientBloodGroupList();
        userList();
    }


}


