package com.example.cyberdoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.cyberdoc.Database.connectDB;

public class PatientOptionsController implements Initializable {

    @FXML
    private TextField apply_address;

    @FXML
    private TextField apply_age;

    @FXML
    private TextField apply_applicantsName;

    @FXML
    private TextField apply_applicationID;

    @FXML
    private ComboBox<?> apply_bloodGroup;

    @FXML
    private DatePicker apply_donationDate;

    @FXML
    private TextField apply_email;

    @FXML
    private ComboBox<?> apply_gender;

    @FXML
    private TextField apply_hospitalLocation;

    @FXML
    private TextField apply_hospitalName;

    @FXML
    private TextField apply_issue;

    @FXML
    private TextField apply_mobileNumber;

    @FXML
    private TextField apply_patientName;

    @FXML
    private TextField apply_relation;

    @FXML
    private Button apply_submitApplicationBtn;

    @FXML
    private Button blood_applyForBloodBtn;

    @FXML
    private Button blood_bloodHomeBtn;

    @FXML
    private Button blood_registerAsDonorBtn;

    @FXML
    private WebView compareWebview;

    @FXML
    private TextField donor_address;

    @FXML
    private TextField donor_age;

    @FXML
    private ComboBox<?> donor_availability;

    @FXML
    private ComboBox<?> donor_bloodGroup;

    @FXML
    private ComboBox<?> donor_gender;

    @FXML
    private TextField donor_id;

    @FXML
    private DatePicker donor_lastDonationDate;

    @FXML
    private TextField donor_mobileNumber;

    @FXML
    private TextField donor_name;

    @FXML
    private TextField donor_preferedArea;

    @FXML
    private Button donor_registerBtn;

    @FXML
    private TextField fund_address;

    @FXML
    private TextField fund_age;

    @FXML
    private TextField fund_amount;

    @FXML
    private TextField fund_applicantsName;

    @FXML
    private TextField fund_applicationId;

    @FXML
    private ComboBox<?> fund_bloodGroup;

    @FXML
    private TextField fund_doctor;

    @FXML
    private TextField fund_email;

    @FXML
    private ComboBox<?> fund_gender;

    @FXML
    private TextField fund_hospitalLocation;

    @FXML
    private TextField fund_hospitalName;

    @FXML
    private TextField fund_issue;

    @FXML
    private TextField fund_mobileNumber;

    @FXML
    private TextField fund_patientName;

    @FXML
    private TextField fund_referedBy;

    @FXML
    private TextField fund_relation;

    @FXML
    private Button fund_submitApplicationBtn;

    @FXML
    private Button funds_applyForDonation;

    @FXML
    private AnchorPane funds_applyForDonationForm;

    @FXML
    private Button funds_donateMoneyBtn;

    @FXML
    private AnchorPane funds_donateMoneyForm;

    @FXML
    private Button funds_fundHomeBtn;

    @FXML
    private AnchorPane funds_fundsHomeForm;

    @FXML
    private WebView google_meet_WebView;

    @FXML
    private AnchorPane option_applyForBlood;

    @FXML
    private AnchorPane option_bloodBankHome;

    @FXML
    private AnchorPane option_bloodBankRegisterForm;

    @FXML
    private AnchorPane options_BloodBank;

    @FXML
    private AnchorPane options_Emergency;

    @FXML
    private AnchorPane options_GetFund;

    @FXML
    private AnchorPane options_Home;

    @FXML
    private AnchorPane options_Upload;

    @FXML
    private Button options_bloodBankBtn;

    @FXML
    private AnchorPane options_chat;

    @FXML
    private Button options_chatBtn;

    @FXML
    private AnchorPane options_compareMedicine;

    @FXML
    private Button options_compareMedicineBtn;

    @FXML
    private Button options_emergencyBtn;

    @FXML
    private AnchorPane options_getFit;

    @FXML
    private Button options_getFitBtn;

    @FXML
    private Button options_getFundBtn;

    @FXML
    private Button options_uploadBtn;

    @FXML
    private AnchorPane options_video;

    @FXML
    private Button options_videoChatBtn;

    @FXML
    private Button uploadFileBtn;

    @FXML
    private Button uploadFileBtn1;

    @FXML
    private ImageView uploadImageField;

    @FXML
    private AnchorPane webView_meet;

    @FXML
    private AnchorPane webView_meet1;

    @FXML
    private AnchorPane webView_meet11;

    @FXML
    private AnchorPane webView_meet111;

    @FXML
    private AnchorPane webView_meet1111;

    @FXML
    private WebView webview_exercise1;

    @FXML
    private WebView webview_exercise2;

    @FXML
    private WebView webview_exercise3;

    @FXML
    private WebView webview_exercise4;
    @FXML
    private WebView map;


    @FXML
    private Button emergency_ambulance;

    @FXML
    private AnchorPane emergency_ambulanceInfo;

    @FXML
    private Button emergency_hospitals;

    @FXML
    private AnchorPane emergency_map;

    @FXML
    private TableColumn<BloodBankData, String> bb_availability;

    @FXML
    private TableColumn<BloodBankData, String> bb_bloodGroup;

    @FXML
    private TableColumn<BloodBankData, String> bb_lastDonationDate;

    @FXML
    private TableColumn<BloodBankData, String> bb_name;

    @FXML
    private TableColumn<BloodBankData, String> bb_preferedArea;

    @FXML
    private TableView<BloodBankData> bb_tableView;




    @FXML
    private TableColumn<FundData, String> fd_date;

    @FXML
    private TableColumn<FundData, String> fd_donationAmount;

    @FXML
    private TableColumn<FundData, String> fd_name;

    @FXML
    private TableView<FundData> fd_tableView;




    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    private Image image;

    AlertMessage alert = new AlertMessage();
    public void selectFileFunction(){

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(uploadFileBtn.getScene().getWindow());
        if(file != null){
            Data.filePath = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 1000, 1000, false, true);
            uploadImageField.setImage(image);
           // profile_circleImage.setFill(new ImagePattern(image));
        }

    }

    public void uploadFileBtn(){
        connect = connectDB();


            // CHECKS IF USER WANT TO CHANGE PROFILE IMAGE OR NOT
            if(Data.filePath == null || "".equals(Data.filePath)){

            }
            else {

                String updateData = "UPDATE patient SET prescription = ?" +
                        " WHERE username = '"
                        + Data.patient_username +"'";


                try {

                    prepare = connect.prepareStatement(updateData);
                    String path = Data.filePath;
                    path = path.replace("\\", "\\\\");

                    Path transfer = Paths.get(path);

                    // ========== LINK DIRECTORY FOLDER =========
                    Path copy = Paths.get("L:\\Trimester-4\\Advance Object Oriented Programming\\Project\\CyberDoc - Design-1 - UserLogin\\src\\main\\resources\\Directory\\"
                            +"prescriptionOf-"+ Data.patient_username + ".jpg");
                    try {

                        // TO PUT THE IMAGE FILE TO DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    }catch (Exception e){e.printStackTrace();}

                    prepare.setString(1, copy.toAbsolutePath().toString());


                    prepare.executeUpdate();

                    alert.successMessage("Uploaded Successfully!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }



    }


    public void registerAsDonorBtn(){

        AlertMessage alert = new AlertMessage();

        //CHECK IF THE FIELD IS EMPTY
        if(donor_name.getText().isEmpty()
                || donor_age.getText().isEmpty()
                || donor_id.getText().isEmpty()

                || donor_bloodGroup.getSelectionModel().getSelectedItem() == null
                || donor_gender.getSelectionModel().getSelectedItem() == null
                || donor_address.getText().isEmpty()
                || donor_mobileNumber.getText().isEmpty()
                || donor_preferedArea.getText().isEmpty()
                || donor_availability.getSelectionModel().getSelectedItem() == null
                ){
            alert.errorMessage("All fields are necessary to be filled");
        }
        else {
            String checkDonorID = "SELECT * FROM blood_bank WHERE donor_id = '"
                    + donor_id.getText() + "'";
            connect = connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkDonorID);

                if(result.next()){
                    alert.errorMessage(donor_id.getText() +" is already registered");
                }
                else{
                    String insertData = "INSERT INTO blood_bank "
                            + "(name, donor_id, age, blood_group, gender, address, mobile_number, prefered_area, availability, last_dontaionDate)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?)"; //TEN (?)

                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, donor_name.getText());
                    prepare.setString(2, donor_id.getText());
                    prepare.setString(3, donor_age.getText());
                    prepare.setString(4, (String) donor_bloodGroup.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String) donor_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(6, donor_address.getText());
                    prepare.setString(7, donor_mobileNumber.getText());
                    prepare.setString(8, donor_preferedArea.getText());
                    prepare.setString(9,(String) donor_availability.getSelectionModel().getSelectedItem());

                    LocalDate date = donor_lastDonationDate.getValue();

                    if(date == null){
                        prepare.setString(10, null);
                    }
                    else {
                        prepare.setString(10,""+ donor_lastDonationDate.getValue());
                    }


                    prepare.executeUpdate();

                    alert.successMessage("Registered Successfully!");
                    registerClearFields();

                }

            }catch (Exception e){
                e.printStackTrace();

            }
        }
    } // end of register

    public void applyForBlood(){

        AlertMessage alert = new AlertMessage();

        LocalDate value = apply_donationDate.getValue();

        //CHECK IF THE FIELD IS EMPTY
        if(apply_applicantsName.getText().isEmpty()
                || apply_email.getText().isEmpty()
                || apply_applicationID.getText().isEmpty()
                || apply_issue.getText().isEmpty()
                || apply_patientName.getText().isEmpty()
                || apply_age.getText().isEmpty()
                || apply_bloodGroup.getSelectionModel().getSelectedItem() == null
                || apply_gender.getSelectionModel().getSelectedItem() == null
                || apply_address.getText().isEmpty()
                || apply_mobileNumber.getText().isEmpty()
                || apply_relation.getText().isEmpty()
                || apply_hospitalName.getText().isEmpty()
                || apply_hospitalLocation.getText().isEmpty()
                || value == null
        ){
            alert.errorMessage("All fields are necessary to be filled");
        }
        else {
            String checkEmail = "SELECT * FROM blood_application WHERE application_id = '"
                    + apply_applicationID.getText() + "'";
            connect = connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkEmail);

                if(result.next()){
                    alert.errorMessage("Application ID " +apply_applicationID.getText()+" is already taken!");
                }
                else{
                    String insertData = "INSERT INTO blood_application "
                            + "(application_id ,applicants_name, email, patient_name, age, blood_group, gender, issue, mobile_number, hospital_name, hospital_location, date_needed, relation_with_patient, status)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //Fourteen (?)

                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, apply_applicationID.getText());
                    prepare.setString(2, apply_applicantsName.getText());
                    prepare.setString(3, apply_email.getText());
                    prepare.setString(4, apply_patientName.getText());
                    prepare.setString(5, apply_age.getText());
                    prepare.setString(6, (String) apply_bloodGroup.getSelectionModel().getSelectedItem());
                    prepare.setString(7, (String) apply_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(8, apply_issue.getText());
                    prepare.setString(9, apply_mobileNumber.getText());
                    prepare.setString(10, apply_hospitalName.getText());
                    prepare.setString(11, apply_hospitalLocation.getText());
                    prepare.setString(12,""+ apply_donationDate.getValue());
                    prepare.setString(13, apply_relation.getText());
                    prepare.setString(14, "Not Approved");


                    prepare.executeUpdate();

                    alert.successMessage("Applied Successfully!\nYou will be contacted soon!");
                    bloodClearForm();
                }

            }catch (Exception e){
                e.printStackTrace();

            }
        }
    } // end of register


    public void bloodClearForm(){
        apply_applicationID.setText("");
        apply_applicantsName.setText("");
        apply_email.setText("");
        apply_patientName.setText("");
        apply_issue.setText("");
        apply_age.setText("");
        apply_bloodGroup.getSelectionModel().clearSelection();
        apply_gender.getSelectionModel().clearSelection();
        apply_address.setText("");
        apply_mobileNumber.setText("");
        apply_relation.setText("");
        apply_hospitalName.setText("");
        apply_hospitalLocation.setText("");
        apply_donationDate.setValue(null);

    }
    public void donorGenderList(){

        List<String> listG = new ArrayList<>();

        for(String data: Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        donor_gender.setItems(listData);
        apply_gender.setItems(listData);
        fund_gender.setItems(listData);

    }

    public void donorBloodGroupList(){
        List<String> listG = new ArrayList<>();

        for(String data: Data.blood_group){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        donor_bloodGroup.setItems(listData);
        apply_bloodGroup.setItems(listData);
        fund_bloodGroup.setItems(listData);
    }


    String[] availibilityOption = {"Available", "Unavailable"};
    public void donorAvailabilityList(){
        List<String> listA = new ArrayList<>();

        for(String data: availibilityOption){
            listA.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listA);
        donor_availability.setItems(listData);
    }

    // TO CLEAR ALL FIELDS OF REGISTRATION FORM
    public void registerClearFields(){
        donor_name.setText("");
        donor_id.setText("");
        donor_age.setText("");
        donor_age.setText("");
        donor_bloodGroup.getSelectionModel().clearSelection();
        donor_gender.getSelectionModel().clearSelection();
        donor_address.setText("");
        donor_mobileNumber.setText("");
        donor_preferedArea.setText("");
        donor_lastDonationDate.setValue(null);
        donor_availability.getSelectionModel().clearSelection();


    }

    public void applyForFund(){

        AlertMessage alert = new AlertMessage();

        //CHECK IF THE FIELD IS EMPTY
        if(fund_applicantsName.getText().isEmpty()
                || fund_applicationId.getText().isEmpty()
                || fund_email.getText().isEmpty()
                || fund_patientName.getText().isEmpty()
                || fund_issue.getText().isEmpty()
                || fund_age.getText().isEmpty()
                || fund_bloodGroup.getSelectionModel().getSelectedItem() == null
                || fund_gender.getSelectionModel().getSelectedItem() == null
                || fund_address.getText().isEmpty()
                || fund_mobileNumber.getText().isEmpty()
                || fund_relation.getText().isEmpty()
                || fund_hospitalName.getText().isEmpty()
                || fund_hospitalLocation.getText().isEmpty()
                || fund_doctor.getText().isEmpty()
                || fund_referedBy.getText().isEmpty()
                || fund_amount.getText().isEmpty()
        ){
            alert.errorMessage("All fields are necessary to be filled");
        }
        else {
            String checkEmail = "SELECT * FROM fund_application WHERE application_id = '"
                    + fund_applicationId.getText() + "'";
            connect = connectDB();
            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkEmail);

                if(result.next()){
                    alert.errorMessage("Application ID " +fund_applicationId.getText()+" is already taken!");
                }
                else{
                    String insertData = "INSERT INTO fund_application "
                            + "(application_id ,applicants_name, email, patient_name," +
                            "issue, age, blood_group, gender, address, mobile_number, relation, hospital_name, hospital_location," +
                            " doctor, refered_by, amount, status)"
                            + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; //SEVENTEEN (?)

                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1, fund_applicationId.getText());
                    prepare.setString(2, fund_applicantsName.getText());
                    prepare.setString(3, fund_email.getText());

                    prepare.setString(4, fund_patientName.getText());
                    prepare.setString(5, fund_issue.getText());
                    prepare.setString(6, fund_age.getText());

                    prepare.setString(7, (String) fund_bloodGroup.getSelectionModel().getSelectedItem());
                    prepare.setString(8, (String) fund_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(9, fund_address.getText());
                    prepare.setString(10, fund_mobileNumber.getText());
                    prepare.setString(11, fund_relation.getText());
                    prepare.setString(12, fund_hospitalName.getText());
                    prepare.setString(13, fund_hospitalLocation.getText());
                    prepare.setString(14, fund_doctor.getText());
                    prepare.setString(15, fund_referedBy.getText());
                    prepare.setString(16, fund_amount.getText());
                    prepare.setString(17, "Not Approved");



                    prepare.executeUpdate();

                    alert.successMessage("Applied Successfully!\nYou will be contacted soon!");
                    clearFundFields();

                }

            }catch (Exception e){
                e.printStackTrace();

            }
        }
    } // end of register


    public void clearFundFields(){
        fund_applicationId.setText("");
        fund_applicantsName.setText("");
        fund_email.setText("");
        fund_patientName.setText("");
        fund_issue.setText("");
        fund_age.setText("");
        fund_bloodGroup.getSelectionModel().clearSelection();
        fund_gender.getSelectionModel().clearSelection();
        fund_address.setText("");
        fund_mobileNumber.setText("");
        fund_relation.setText("");
        fund_hospitalName.setText("");
        fund_hospitalLocation.setText("");
        fund_doctor.setText("");
        fund_referedBy.setText("");
        fund_amount.setText("");
    }

    public void bloodBankSwitchForm(ActionEvent event){
        if(event.getSource() == blood_bloodHomeBtn){
            option_bloodBankHome.setVisible(true);
            option_bloodBankRegisterForm.setVisible(false);
            option_applyForBlood.setVisible(false);
        }else if(event.getSource() == blood_registerAsDonorBtn){
            option_bloodBankHome.setVisible(false);
            option_bloodBankRegisterForm.setVisible(true);
            option_applyForBlood.setVisible(false);
        }
        else if(event.getSource() == blood_applyForBloodBtn){
            option_bloodBankHome.setVisible(false);
            option_bloodBankRegisterForm.setVisible(false);
            option_applyForBlood.setVisible(true);
        }
    }


    public void fundSwitchForm(ActionEvent event){
        if(event.getSource() == funds_fundHomeBtn){
            funds_fundsHomeForm.setVisible(true);
            funds_donateMoneyForm.setVisible(false);
            funds_applyForDonationForm.setVisible(false);
        }else if(event.getSource() == funds_donateMoneyBtn){
            funds_fundsHomeForm.setVisible(false);
            funds_donateMoneyForm.setVisible(true);
            funds_applyForDonationForm.setVisible(false);
        }
        else if(event.getSource() == funds_applyForDonation){
            funds_fundsHomeForm.setVisible(false);
            funds_donateMoneyForm.setVisible(false);
            funds_applyForDonationForm.setVisible(true);
        }
    }

    public void emergencySwitchForm(ActionEvent event){
        if(event.getSource() == emergency_ambulance){
            emergency_ambulanceInfo.setVisible(true);
            emergency_map.setVisible(false);
        }else if(event.getSource() == emergency_hospitals){
            emergency_ambulanceInfo.setVisible(false);
            emergency_map.setVisible(true);
        }

    }


// ============== HONOR BOARD START ============

    public ObservableList<BloodBankData> appointmentGetData(){
        ObservableList<BloodBankData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM blood_bank WHERE last_dontaionDate IS NOT NULL";

        connect = Database.connectDB();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            BloodBankData appData;

            while (result.next()){

//                Integer appointmentID, String name, String gender,
//                        Long mobileNumber, String description, String diagnosis, String treatment, String address, java.sql.Date
//                date,
//                        java.sql.Date dateModify, java.sql.Date dateDelete, String status, java.sql.Date scedule

                appData = new BloodBankData(

                        result.getString("name"),
                        result.getString("blood_group"),
                        result.getString("prefered_area"),
                        result.getString("last_dontaionDate"),
                        result.getString("availability"));

                // STORE ALL DATA
                listData.add(appData);

            }

        }
        catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public ObservableList<BloodBankData> appointmentListData;
    public void bloodBankShowData(){

        appointmentListData = appointmentGetData();

        bb_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        bb_bloodGroup.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        bb_availability.setCellValueFactory(new PropertyValueFactory<>("availability"));
        bb_preferedArea.setCellValueFactory(new PropertyValueFactory<>("preferedArea"));
        bb_lastDonationDate.setCellValueFactory(new PropertyValueFactory<>("lastDonationDate"));
        bb_tableView.setItems(appointmentListData);

    }


    // ============== HONOR BOARD END ============




// ============== HONOR BOARD START ============


    public ObservableList<FundData> getFdList(){
        ObservableList<FundData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM fund_donor";

        connect = Database.connectDB();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            FundData appData;

            while (result.next()){

                appData = new FundData(

                        result.getString("name"),
                        result.getString("donation_amount"),
                        result.getString("date"));

                // STORE ALL DATA
                listData.add(appData);

            }

        }
        catch (Exception e){e.printStackTrace();}

        return listData;
    }




    public ObservableList<FundData> fundListData;
    public void ShowFdData(){

        fundListData = getFdList();

        fd_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        fd_donationAmount.setCellValueFactory(new PropertyValueFactory<>("donationAmount"));
        fd_date.setCellValueFactory(new PropertyValueFactory<>("date"));


        fd_tableView.setItems(fundListData);

    }


    // ============== HONOR BOARD END ============




    // ==== SWITCH FORM START =====

    public void switchForm(ActionEvent event) throws IOException {

        if(event.getSource() == options_chatBtn){

            // CREATE FXML FILE FOR EDIT
            Parent root = FXMLLoader.load(getClass().getResource("Client.fxml"));
            Stage stage = new Stage();

            Image icon = new Image("healthy.png");
            stage.getIcons().add(icon);
            stage.setTitle(Data.patient_name+" || CYBERDOC");
            stage.setScene(new Scene(root));
            stage.show();

        }
        else if(event.getSource() == options_videoChatBtn){
            options_Home.setVisible(false);
            options_video.setVisible(true);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_uploadBtn){
            options_Home.setVisible(false);

            options_video.setVisible(false);
            options_Upload.setVisible(true);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_emergencyBtn){
            options_Home.setVisible(false);

            options_video.setVisible(false);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(true);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_bloodBankBtn){
            options_Home.setVisible(false);

            options_video.setVisible(false);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(true);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_getFundBtn){
            options_Home.setVisible(false);

            options_video.setVisible(false);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(true);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_compareMedicineBtn){
            options_Home.setVisible(false);

            options_video.setVisible(false);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(true);
            options_getFit.setVisible(false);
        }
        else if(event.getSource() == options_getFitBtn){
            options_Home.setVisible(false);
            options_video.setVisible(false);
            options_Upload.setVisible(false);
            options_Emergency.setVisible(false);
            options_BloodBank.setVisible(false);
            options_GetFund.setVisible(false);
            options_compareMedicine.setVisible(false);
            options_getFit.setVisible(true);
        }



    }

    // ==== SWITCH FORM START =====




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

    @FXML
    private WebView webviewMap;

    @FXML
    private WebEngine engineVideoChat;

    @FXML
    private WebEngine engineCompareMedicine;

    // ===== EXERCISE START=====


    @FXML
    private WebEngine engineExercise1;



    @FXML
    private WebEngine engineExercise2;



    @FXML
    private WebEngine engineExercise3;


    @FXML
    private WebEngine engineExercise4;


    // ===== EXERCISE END=====

    @FXML
    private WebEngine engineMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        donorGenderList();
        donorBloodGroupList();
        donorAvailabilityList();

        bloodBankShowData();
        ShowFdData();

        engineVideoChat = google_meet_WebView.getEngine();
        engineCompareMedicine = compareWebview.getEngine();

        engineExercise1 = webview_exercise1.getEngine();
        engineExercise2 = webview_exercise2.getEngine();
        engineExercise3 = webview_exercise3.getEngine();
        engineExercise4 = webview_exercise4.getEngine();

        engineMap = map.getEngine();
        loadVideoPage();
        loadCompareMedicinePage();

        loadExercise1();
        loadExercise2();
        loadExercise3();
        loadExercise4();

        loadMap();
    }

    public void loadVideoPage(){
        engineVideoChat.load("https://meet.google.com/");
    }
    public void loadCompareMedicinePage(){
        engineCompareMedicine.load("https://medex.com.bd/");
    }

    public void loadExercise1(){
        engineExercise1.load("https://www.youtube.com/embed/oAPCPjnU1wA");
    }
    public void loadExercise2(){
        engineExercise2.load("https://www.youtube.com/embed/J212vz33gU4");
    }
    public void loadExercise3(){
        engineExercise3.load("https://www.youtube.com/embed/i0H82s70k34");
    }
    public void loadExercise4(){
        engineExercise4.load("https://www.youtube.com/embed/PG2f3GF5RlI");
    }

    public void loadMap(){
        engineMap.load("https://cyberdoc-map.netlify.app/");
    }





}
