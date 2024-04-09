package com.example.cyberdoc;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PatientDashboardController implements Initializable {

    @FXML
    private Button appointments_btn;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private AnchorPane appointments_form1;

    @FXML
    private Button chat_btn;

    @FXML
    private Label current_form;

    @FXML
    private Label date_time;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private TextArea profile_address;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle profile_circleImage;

    @FXML
    private TextField profile_doctorID;

    @FXML
    private TextField profile_email;

    @FXML
    private AnchorPane profile_form;

    @FXML
    private ComboBox<String> profile_gender;

    @FXML
    private Button profile_importBtn;

    @FXML
    private Label profile_label_dateCreated;

    @FXML
    private Label profile_label_doctorID;

    @FXML
    private Label profile_label_email;

    @FXML
    private Label profile_label_name;

    @FXML
    private TextField profile_mobileNumber;

    @FXML
    private TextField profile_name;

    @FXML
    private ComboBox<String> profile_specialization;

    @FXML
    private ComboBox<String> profile_status;

    @FXML
    private Button profile_updateBtn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private Button treatment_btn;

    @FXML
    private Button video_btn;

    @FXML
    private Label dashboard_IP;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_description;
    @FXML
    private AnchorPane treatment_form;
    @FXML
    private TextArea treatment_textArea;



    @FXML
    private TextArea appointment_address;

    @FXML
    private TextField appointment_description;

    @FXML
    private ComboBox<?> appointment_gender;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextField appointment_name;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private ComboBox<?> appointment_status;

    @FXML
    private TextField appointments_appointmentID;

//    @FXML
//    private Button appointments_btn;
//
//    @FXML
//    private AnchorPane appointments_form;








    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    private Image image;
    private final AlertMessage alert = new AlertMessage();


















    public void displayAdminIDNumber(){

        String name = Data.patient_name;
        name =  name.substring(0,1).toUpperCase() + name.substring(1);
        nav_username.setText(name);
        nav_adminID.setText(Data.patient_username);
        top_username.setText(name);

    }


    // GET TREATMENT FROM DATABASE
    public void showTreatment(){

        if(Data.patient_treatment == null){
            treatment_textArea.setText("No prescription loaded...");
        }else {
            treatment_textArea.setText(Data.patient_treatment);
        }

        treatment_textArea.setDisable(true);

    }

    public void appointmentGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data: Data.gender){
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);
    }

    public void appointmentInsertBtn(){

        // CHECK IF ANY FIELDS ARE EMPTY
        if(appointments_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_schedule.getValue() == null
        ){
            alert.errorMessage("Please, fill all the blank fields!");
        }
        else{

            // CHECK MOBILE NUMBER IS INTEGER OR NOT

//                try {
//                    Integer.parseInt(appointment_mobileNumber.getText());
//                    // s is a valid integer!
//
//                } catch (Exception e) {
//                    alert.errorMessage("Please, enter a valid Mobile Number!");
//                }






            String checkAppointmentID = "SELECT * FROM appointment WHERE appointment_id = "
                    + appointments_appointmentID.getText();

            connect = Database.connectDB();

            try {
                statement = connect.createStatement();
                result = statement.executeQuery(checkAppointmentID);

                if(result.next()){
                    alert.errorMessage("Appointment id "+appointments_appointmentID.getText() +" is already taken!");
                }else {
                    String getSpecialized="";
                    String getDoctorData = "SELECT * FROM doctor WHERE doctor_id = '"
                            +Data.doctor_id+ "'";
                    statement = connect.createStatement();
                    result = statement.executeQuery(getDoctorData);

                    if(result.next()){
                        getSpecialized = result.getString("specialized");
                    }

                    String insertData = "INSERT INTO appointment (appointment_id, name, gender," +
                            " description, diagnosis, treatment, mobile_number," +
                            " address, date, status, doctor, specialized, schedule) " +
                            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertData);

                    prepare.setString(1,appointments_appointmentID.getText());
                    prepare.setString(2, appointment_name.getText());
                    prepare.setString(3, (String) appointment_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4,appointment_description.getText());
                    prepare.setString(5, null);
                    prepare.setString(6, null);
                    prepare.setString(7, appointment_mobileNumber.getText());
                    prepare.setString(8,appointment_address.getText());


                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(9, ""+ sqlDate);
                    prepare.setString(10, "Active");
                    prepare.setString(11, "DID-1");
                    prepare.setString(12, null);
                    prepare.setString(13, ""+ appointment_schedule.getValue());

                    prepare.executeUpdate();

                    alert.successMessage("Appointment Booking Successful!");

                }
            }catch (Exception e){e.printStackTrace();}
        }
    }


    public void playAudio(){


        if(Data.patient_treatment == null){
            Data.patient_treatment = "No prescription loaded...";
        }


        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");


        Voice voice = VoiceManager.getInstance().getVoice("kevin16");

        // Print all voices
        Voice[] voicelist =  VoiceManager.getInstance().getVoices();

        for(int i = 0; i<voicelist.length; i++){
            System.out.println("# Voice: "+voicelist[i].getName());
        }

        if(voice != null){
            voice.allocate();

//            System.out.println("Voice Rate: "+voice.getRate());
//            System.out.println("Voice Pitch: "+voice.getPitch());
//            System.out.println("Voice Volume: "+voice.getVolume());

            boolean status = voice.speak(Data.patient_treatment);

//            System.out.println("Status: "+status);

            voice.deallocate();

        }else{
            System.out.println("Error in getting voices");
        }



    }



    public void profileUpdateBtn(){
        connect = Database.connectDB();
        if(
                 profile_name.getText().isEmpty()
                || profile_email.getText().isEmpty()
                || profile_gender.getSelectionModel().getSelectedItem() == null
                || profile_mobileNumber.getText().isEmpty()
                || profile_address.getText().isEmpty()
                || profile_status.getSelectionModel().getSelectedItem() == null){
            alert.errorMessage("Please, fill all the blank fields!");
        }else{

            // CHECKS IF USER WANT TO CHANGE PROFILE IMAGE OR NOT
            if(Data.path == null || "".equals(Data.path)){
                String updateData = "UPDATE patient SET full_name = ?, email = ?, gender = ?, mobile_number = ?," +
                        " address = ?, status = ?, date_modify = ?" +
                        " WHERE username = '"
                        + Data.patient_username +"'";


                try {

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5,profile_address.getText());
                    prepare.setString(6,profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(7,String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {

                String updateData = "UPDATE patient SET full_name = ?, email = ?, gender = ?, mobile_number = ?," +
                        " address = ?, image = ?, status = ?, date_modify = ?" +
                        " WHERE username = '"
                        + Data.patient_username +"'";


                try {

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5,profile_address.getText());
                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    Path transfer = Paths.get(path);



                    // ========== LINK DIRECTORY FOLDER =========
                    Path copy = Paths.get("L:\\Trimester-4\\Advance Object Oriented Programming\\Project\\CyberDoc\\src\\main\\resources\\Directory\\"
                            + Data.doctor_id + ".jpg");
                    try {

                        // TO PUT THE IMAGE FILE TO DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    }catch (Exception e){e.printStackTrace();}

                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7,profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8,String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }

    }


    public void profileChangeProfile(){

        FileChooser open = new FileChooser();
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*png", "*jpg", "*jpeg"));

        File file = open.showOpenDialog(profile_importBtn.getScene().getWindow());
        if(file != null){
            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 141, 121, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }


    public void profileLabels(){
        String selectData = "SELECT * FROM patient WHERE username = '"
                + Data.patient_username + "'";

        connect = Database.connectDB();
        try {

            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();


            if(result.next()){
                profile_label_doctorID.setText(result.getString("username"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }


        }catch (Exception e){e.printStackTrace();}

    }

    public void profileFields(){

        String selectData = "SELECT * FROM patient WHERE username = '"
                + Data.patient_username + "'";

        connect = Database.connectDB();
        try {

            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();


            if(result.next()){
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }


        }catch (Exception e){e.printStackTrace();}



    }


    public void profileDisplayImages(){
        String selectData = "SELECT * FROM patient WHERE username = '"
                + Data.patient_username+"'";

        String temp_path1 = "";
        String temp_path2 = "";
        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            if(result.next()){
                temp_path1 = "File:" + result.getString("image");
                temp_path2 = "File:" + result.getString("image");

                if(result.getString("image") != null){
                    image = new Image(temp_path1, 975, 25, false, true);
                    top_profile.setFill(new ImagePattern(image));

                    image = new Image(temp_path2, 141, 121, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }
            }

        }catch (Exception e){e.printStackTrace();}
    }

    public void profileGenderList(){

        List<String> listG = new ArrayList<>();

        for(String data: Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listG);
        profile_gender.setItems(listData);

    }

    public void profileStatusList(){

        List<String> listS = new ArrayList<>();

        for(String data: Data.status){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_status.setItems(listData);

    }


    public void switchForm(ActionEvent event){
        if(event.getSource() == treatment_btn){
            treatment_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
        }else if(event.getSource() == appointments_btn){
            treatment_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
        }
        else if(event.getSource() == profile_btn){
            treatment_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
        }

    }


    public void runTime(){  // SETS DYNAMIC DATE
        new Thread(){
            public void run(){
                SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                while (true){
                    try {
                        Thread.sleep(1000);
                    }catch (Exception e){e.printStackTrace();}
                    Platform.runLater(() -> {
                        date_time.setText(format.format(new Date()));
                    });
                }
            }
        }.start();
    } // END OF RUNTIME



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





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayAdminIDNumber();
        showTreatment();


        // TO SHOW DATA IMMEDIATELY ONCE LOGGED IN
        appointmentGenderList();


        // PROFILE
        profileFields(); // TO DISPLAY ALL PROFILE DETAILS
        profileLabels();
        profileStatusList();
        profileGenderList();
        profileDisplayImages(); // TO DISPLAY PROFILE IMAGE OF DOCTOR


    }
}
