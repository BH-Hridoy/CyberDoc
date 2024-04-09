package com.example.cyberdoc;

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
import javafx.scene.image.ImageView;
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

public class DoctorMainPanelController implements Initializable {


    @FXML
    private TextArea appointment_address;

    @FXML
    private TextField appointment_description;

    @FXML
    private TextField appointment_diagnosis;

    @FXML
    private ComboBox<String> appointment_gender;

    @FXML
    private TextField appointment_mobileNumber;

    @FXML
    private TextField appointment_name;

    @FXML
    private ComboBox<String> appointment_status;

    @FXML
    private TextField appointment_treatment;

    @FXML
    private TextField appointments_appointmentID;

    @FXML
    private Button appointments_btn;

    @FXML
    private TableColumn<?, ?> appointments_col_action;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_contactNumber;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_date;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateDelete;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_dateModify;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_description;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_gender;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_name;

    @FXML
    private TableColumn<AppointmentData, String> appointments_col_status;

    @FXML
    private AnchorPane appointments_form;

    @FXML
    private TableView<AppointmentData> appointments_tableView;

    @FXML
    private Label current_form;

    @FXML
    private Label dashboard_AD;

    @FXML
    private Label dashboard_AP;

    @FXML
    private Label dashboard_TA;

    @FXML
    private Label dashboard_TP;

    @FXML
    private Button dashboard_btn;

    @FXML
    private BarChart<?, ?> dashboard_chart_DD;

    @FXML
    private AreaChart<?, ?> dashboard_chart_PD;

    @FXML
    private TableColumn<?, ?> dashboard_col_doctorID;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_name;

    @FXML
    private TableColumn<?, ?> dashboard_col_specialized;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_status;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private TableView<AppointmentData> dashboard_tableView;

    @FXML
    private Label date_time;

    @FXML
    private TableColumn<?, ?> doctors_col_action;

    @FXML
    private TableColumn<?, ?> doctors_col_adress;

    @FXML
    private TableColumn<?, ?> doctors_col_contactNumber;

    @FXML
    private TableColumn<?, ?> doctors_col_doctorID;

    @FXML
    private TableColumn<?, ?> doctors_col_email;

    @FXML
    private TableColumn<?, ?> doctors_col_gender;

    @FXML
    private TableColumn<?, ?> doctors_col_name;

    @FXML
    private TableColumn<?, ?> doctors_col_specialization;

    @FXML
    private TableColumn<?, ?> doctors_col_status;

    @FXML
    private AnchorPane doctors_form;

    @FXML
    private TableView<?> doctors_tableView;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private Label patients_PA_dateCreated;

    @FXML
    private Label patients_PA_password;

    @FXML
    private Label patients_PA_patientID;

    @FXML
    private Button patients_PI_addBtn;

    @FXML
    private Label patients_PI_address;

    @FXML
    private Label patients_PI_gender;

    @FXML
    private Label patients_PI_mobileNumber;

    @FXML
    private Label patients_PI_patientName;

    @FXML
    private Button patients_PI_recordBtn;

    @FXML
    private TextArea patients_address;

    @FXML
    private Button patients_btn;

    @FXML
    private Button patients_confirmBtn;

    @FXML
    private AnchorPane patients_form;

    @FXML
    private ComboBox<String> patients_gender;

    @FXML
    private TextField patients_mobileNumber;

    @FXML
    private TextField patients_password;

    @FXML
    private TextField patients_patientID;

    @FXML
    private TextField patients_patientName;

    @FXML
    private Button profile_btn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private DatePicker appointment_schedule;

    @FXML
    private Button appointment_clearBtn;

    @FXML
    private Button appointment_deleteBtn;

    @FXML
    private Button appointment_insertBtn;

    @FXML
    private Button appointment_updateBtn;

    @FXML
    private TextArea profile_address;

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
    private Label dashboard_IP;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_appointmentDate;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_appointmentID;

    @FXML
    private TableColumn<AppointmentData, String> dashboard_col_description;

    @FXML
    private Button reportBtn;

    @FXML
    private Label report_email;

    @FXML
    private AnchorPane report_form;

    @FXML
    private ImageView report_imageView;

    @FXML
    private Button report_loadBtn;

    @FXML
    private Label report_name;

    @FXML
    private TextField report_searchBox;

    @FXML
    private Label report_username;








    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;


    private Image image;
    private final AlertMessage alert = new AlertMessage();




    public void dashboardDisplayIP(){
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Inactive' AND doctor = '"
                +Data.doctor_id+"'";
        connect = Database.connectDB();
        int getIP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if(result.next()){
                getIP = result.getInt("COUNT(id)");
            }
            dashboard_IP.setText(String.valueOf(getIP));
        }catch (Exception e){e.printStackTrace();}
    }

    public void dashboardDisplayTP(){
        String sql = "SELECT COUNT(id) FROM patient WHERE doctor = '"
                +Data.doctor_id+"'";
        connect = Database.connectDB();
        int getTP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if(result.next()){
                getTP = result.getInt("COUNT(id)");
            }
            dashboard_TP.setText(String.valueOf(getTP));
        }catch (Exception e){e.printStackTrace();}
    }


    public void dashboardDisplayAP(){
        String sql = "SELECT COUNT(id) FROM patient WHERE status = 'Active' AND doctor = '"
                +Data.doctor_id+"'";
        connect = Database.connectDB();
        int getAP = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if(result.next()){
                getAP = result.getInt("COUNT(id)");
            }
            dashboard_AP.setText(String.valueOf(getAP));
        }catch (Exception e){e.printStackTrace();}
    }

    public void dashboardDisplayTA(){
        String sql = "SELECT COUNT(id) FROM appointment WHERE status = 'Active' AND doctor = '"
                +Data.doctor_id+"'";
        connect = Database.connectDB();
        int getTA = 0;
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            if(result.next()){
                getTA = result.getInt("COUNT(id)");
            }
            dashboard_TA.setText(String.valueOf(getTA));
        }catch (Exception e){e.printStackTrace();}
    }


    public ObservableList<AppointmentData> dashboardAppointmentTableView(){

        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();


        String sql = "SELECT * FROM appointment WHERE doctor = '"+Data.doctor_id+"' AND date_delete IS NULL";

        connect = Database.connectDB();

        try {
            prepare = connect.prepareStatement(sql);
            result= prepare.executeQuery();

            AppointmentData  aData;

            while(result.next()){
                aData = new AppointmentData(result.getInt("appointment_id")
                        , result.getString("name"), result.getString("description")
                        , result.getDate("date"),result.getString("status"));

                listData.add(aData);


            }

        }catch (Exception e){e.printStackTrace();}

        return listData;

    }


    public ObservableList<AppointmentData> dashboardGetData;

    public void dashboardDisplayData(){

        dashboardGetData = dashboardAppointmentTableView();

        dashboard_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        dashboard_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        dashboard_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        dashboard_col_appointmentDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        dashboard_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        dashboard_tableView.setItems(dashboardGetData);
    }


    public void dashboardNOP(){
        dashboard_chart_PD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM patient WHERE doctor = '"
                +Data.doctor_id+"' GROUP BY TIMESTAMP(date)";

        connect = Database.connectDB();

        try{
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_PD.getData().add(chart);

        }catch (Exception e){e.printStackTrace();}
    }

    public void dashboardNOA(){
        dashboard_chart_DD.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM appointment WHERE doctor = '"
                +Data.doctor_id+"' GROUP BY TIMESTAMP(date)";

        connect = Database.connectDB();

        try{
            XYChart.Series chart = new XYChart.Series<>();
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_chart_DD.getData().add(chart);

        }catch (Exception e){e.printStackTrace();}
    }


    public void patientConfirmBtn(){

        // CHECK IF ANY FIELDS ARE EMPTY
        if(patients_patientID.getText().isEmpty()
        || patients_patientName.getText().isEmpty()
        || patients_gender.getSelectionModel().getSelectedItem() == null
        || patients_mobileNumber.getText().isEmpty()
        || patients_password.getText().isEmpty()
        || patients_address.getText().isEmpty()){
            alert.errorMessage("Please, fill all the blank fields!");
        }
        else{

            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());


            // TO DISPLAY THE DATA OF PATIENTS WHILE CREATING PATIENT ACCOUNT
            patients_PA_patientID.setText(patients_patientID.getText());
            patients_PA_password.setText(patients_password.getText());
            patients_PA_dateCreated.setText(String.valueOf(sqlDate) );

            patients_PI_patientName.setText(patients_patientName.getText());
            patients_PI_gender.setText(patients_gender.getSelectionModel().getSelectedItem());
            patients_PI_mobileNumber.setText(patients_mobileNumber.getText());
            patients_PI_address.setText(patients_address.getText());

        }

    }


    public void patientAddBtn(){

        if(patients_PA_patientID.getText().isEmpty()
        || patients_PA_password.getText().isEmpty()
        || patients_PA_dateCreated.getText().isEmpty()
        || patients_PI_patientName.getText().isEmpty()
        || patients_PI_gender.getText().isEmpty()
        || patients_PI_mobileNumber.getText().isEmpty()
        || patients_PI_address.getText().isEmpty()){
            alert.errorMessage("Something went wrong!");
        }
        else{



            Database.connectDB();


            try{

                String doctorName="";
                String doctorSpecialized = "";

                String getDoctor = "SELECT * FROM doctor WHERE doctor_id = '"
                        + nav_adminID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(getDoctor);

                if(result.next()){
                    doctorName = result.getString("full_name");
                    doctorSpecialized  =result.getString("specialized");
                }


                // CHECK IF THE PATIENT ID ALREADY EXIST
                String checkPatientId = "SELECT * FROM patient WHERE patient_id = '"
                        + patients_PA_patientID.getText()+"'";

                statement = connect.createStatement();
                result = statement.executeQuery(checkPatientId);

                if(result.next()){
                    alert.errorMessage(patients_PA_patientID.getText() + " is already exist!");
                }
                else{
                    String insertData = "INSERT INTO patient (patient_id, password, full_name, gender, mobile_number, " +
                            "address, doctor, specialized, date, " +
                            "status) "
                            + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare = connect.prepareStatement(insertData);


                    connect = prepare.getConnection();
                    prepare.setString(1, patients_PA_patientID.getText());
                    prepare.setString(2, patients_PA_password.getText());
                    prepare.setString(3, patients_PI_patientName.getText());
                    prepare.setString(4, patients_PI_gender.getText());
                    prepare.setString(5, patients_PI_mobileNumber.getText());
                    prepare.setString(6, patients_PI_address.getText());
                    prepare.setString(7, nav_adminID.getText());
                    prepare.setString(8, doctorSpecialized);
                    prepare.setString(9, ""+sqlDate);
                    prepare.setString(10,"Confirm");



                    prepare.executeUpdate();

                    alert.successMessage("Added Successfully!");
                    // TO CLEAR ALL FIELDS AND LABELS
                    patientClearFields();
                }


            }catch (Exception e){e.printStackTrace();}

        }

    }

    HelloApplication changeScene = new HelloApplication();
    public void patientRecordBtn(){
        try{

            // CREATE FXML FILE FOR EDIT
            Parent root = FXMLLoader.load(getClass().getResource("RecordPage.fxml"));
            Stage stage = new Stage();

            stage.setTitle("Patient Records || CYBERDOC");
            stage.setScene(new Scene(root));
            stage.show();
//            changeScene.changeScene("RecordPage.fxml");
        }catch (Exception e){e.printStackTrace();}

    }


    public void patientClearFields(){
        patients_patientID.clear();
        patients_patientName.clear();
        patients_gender.getSelectionModel().clearSelection();
        patients_mobileNumber.clear();
        patients_password.clear();
        patients_address.clear();

        patients_PA_patientID.setText("");
        patients_PA_password.setText("");
        patients_PA_dateCreated.setText("");

        patients_PI_patientName.setText("");
        patients_PI_gender.setText("");
        patients_PI_mobileNumber.setText("");
        patients_PI_address.setText("");
    }


    public void patientGenderList(){

        List<String> listG = new ArrayList<>();
        for(String data: Data.gender){
            listG.add(data);
        }
        ObservableList listData = FXCollections.observableList(listG);
        patients_gender.setItems(listData);


    }


    public void appointmentInsertBtn(){

        // CHECK IF ANY FIELDS ARE EMPTY
        if(appointments_appointmentID.getText().isEmpty()
            || appointment_name.getText().isEmpty()
        || appointment_gender.getSelectionModel().getSelectedItem() == null
        || appointment_mobileNumber.getText().isEmpty()
        || appointment_description.getText().isEmpty()
        || appointment_address.getText().isEmpty()
        || appointment_status.getSelectionModel().getSelectedItem() == null
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
                    prepare.setString(5, appointment_diagnosis.getText());
                    prepare.setString(6, appointment_treatment.getText());
                    prepare.setString(7, appointment_mobileNumber.getText());
                    prepare.setString(8,appointment_address.getText());


                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare.setString(9, ""+ sqlDate);
                    prepare.setString(10, (String) appointment_status.getSelectionModel().getSelectedItem());
                    prepare.setString(11, Data.doctor_id);
                    prepare.setString(12, getSpecialized);
                    prepare.setString(13, ""+ appointment_schedule.getValue());

                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully added!");

                }
            }catch (Exception e){e.printStackTrace();}
        }
    }


    public void appointment_updateBtn(){
        if(appointments_appointmentID.getText().isEmpty()
                || appointment_name.getText().isEmpty()
                || appointment_gender.getSelectionModel().getSelectedItem() == null
                || appointment_mobileNumber.getText().isEmpty()
                || appointment_description.getText().isEmpty()
                || appointment_address.getText().isEmpty()
                || appointment_status.getSelectionModel().getSelectedItem() == null
                || appointment_schedule.getValue() == null
        ){
            alert.errorMessage("Please, fill all the blank fields!");
        }
        else{

            // TO GET TODAY'S DATE
            java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

            String updateData = "UPDATE appointment SET name = '"
                    + appointment_name.getText()+"', gender = '"
                    +appointment_gender.getSelectionModel().getSelectedItem()+"', mobile_number = '"
                    +appointment_mobileNumber.getText()+"', description = '"
                    +appointment_description.getText()+"', address = '"
                    +appointment_address.getText()+"', status = '"
                    +appointment_status.getSelectionModel().getSelectedItem()+"', schedule = '"
                    +appointment_schedule.getValue()+"', date_modify = '"
                    +sqlDate+"' WHERE appointment_id = '"
                    +appointments_appointmentID.getText()+"'";


            connect = Database.connectDB();

            try{
                if(alert.confirmationMessage("Are you sure you want to UPDATE Appointment ID: "
                        + appointments_appointmentID.getText() + "?")){
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();


                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully Updated!");

                }
            }catch (Exception e){e.printStackTrace();}


        }
    }


    public void appointmentDeleteBtn(){

        if(appointments_appointmentID.getText().isEmpty()){
            alert.errorMessage("Please, select the item first!");
        }
        else{
            String updateData = "UPDATE appointment SET date_delete = ? WHERE appointment_id = '"
                    + appointments_appointmentID.getText() +"'";

            connect = Database.connectDB();

            try{

                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                if(alert.confirmationMessage("Are you sure you want to DELETE Appointment ID: "
                        + appointments_appointmentID.getText()+ "?" )){
                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, String.valueOf(sqlDate));
                    prepare.executeUpdate();

                    appointmentShowData();
                    appointmentAppointmentID();
                    appointmentClearBtn();
                    alert.successMessage("Successfully Deleted!");
                }
                else {
                    alert.errorMessage("Cancelled!");
                }


            }catch (Exception e){e.printStackTrace();}

        }

    }



    // TO CLEAR ALL THE FIELDS
    public void appointmentClearBtn(){
        //appointments_appointmentID.clear();
        appointment_name.clear();
        appointment_gender.getSelectionModel().clearSelection();
        appointment_mobileNumber.clear();
        appointment_description.clear();
        appointment_treatment.clear();
        appointment_diagnosis.clear();
        appointment_address.clear();
        appointment_status.getSelectionModel().clearSelection();
        appointment_schedule.setValue(null);
    }


    private Integer appointmentID;
    public void appointmentGetAppointmentID(){
        String sql = "SELECT MAX(appointment_id) FROM appointment";
        connect = Database.connectDB();

        int tempAppID = 0;


        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                tempAppID = result.getInt("MAX(appointment_id)");
            }


            if(tempAppID == 0){
                tempAppID +=1;
            }
            else {
                tempAppID += 1;
            }

            appointmentID = tempAppID;


        }catch (Exception e){e.printStackTrace();}
    }
    public void appointmentAppointmentID(){
        appointmentGetAppointmentID();

        appointments_appointmentID.setText(""+ appointmentID);
        //appointments_appointmentID.setDisable(true);


    }

    public void appointmentGenderList(){
        List<String> listG = new ArrayList<>();

        for(String data: Data.gender){
            listG.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listG);
        appointment_gender.setItems(listData);
    }


    public void appointmentStatusList(){
        List<String> listS = new ArrayList<>();

        for(String data: Data.status){
            listS.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listS);
        appointment_status.setItems(listData);
    }


    public ObservableList<AppointmentData> appointmentGetData(){
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM appointment WHERE date_delete IS NULL";

        connect = Database.connectDB();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            AppointmentData appData;

            while (result.next()){

//                Integer appointmentID, String name, String gender,
//                        Long mobileNumber, String description, String diagnosis, String treatment, String address, java.sql.Date
//                date,
//                        java.sql.Date dateModify, java.sql.Date dateDelete, String status, java.sql.Date scedule

                appData = new AppointmentData(result.getInt("appointment_id"),
                        result.getString("name"), result.getString("gender"),
                        result.getLong("mobile_number"),result.getString("description"),
                        result.getString("diagnosis"),
                        result.getString("treatment"),
                        result.getString("address"),
                        result.getDate("date"), result.getDate("date_modify"),
                        result.getDate("date_delete"), result.getString("status"),
                        result.getDate("schedule"));

                // STORE ALL DATA
                listData.add(appData);

            }

        }
        catch (Exception e){e.printStackTrace();}

        return listData;
    }

    public ObservableList<AppointmentData> appointmentListData;
    public void appointmentShowData(){

        appointmentListData = appointmentGetData();

        appointments_col_appointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appointments_col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        appointments_col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointments_col_contactNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        appointments_col_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointments_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        appointments_col_dateModify.setCellValueFactory(new PropertyValueFactory<>("dateModify"));
        appointments_col_dateDelete.setCellValueFactory(new PropertyValueFactory<>("dateDelete"));
        appointments_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        appointments_tableView.setItems(appointmentListData);

    }


    // TO SELECT THE DATA FROM TABLE
    public void appointentSelect(){

        AppointmentData appData = appointments_tableView.getSelectionModel().getSelectedItem();
        int num = appointments_tableView.getSelectionModel().getSelectedIndex();

        if((num -1) < -1) {
            return;
        }

        appointments_appointmentID.setText("" + appData.getAppointmentID());
        appointment_name.setText(appData.getName());
        appointment_gender.getSelectionModel().select(appData.getGender());
        appointment_mobileNumber.setText("" + appData.getMobileNumber());
        appointment_description.setText(appData.getDescription());
        appointment_diagnosis.setText(appData.getDiagnosis());
        appointment_treatment.setText(appData.getTreatment());
        appointment_address.setText(appData.getAddress());
        appointment_status.getSelectionModel().select(appData.getStatus());


    }

    public void displayAdminIDNumber(){

        String name = Data.doctor_name;
        name =  name.substring(0,1).toUpperCase() + name.substring(1);
        nav_username.setText(name);
        nav_adminID.setText(Data.doctor_id);
        top_username.setText(name);

    }


    public void profileUpdateBtn(){
        connect = Database.connectDB();
        if(profile_doctorID.getText().isEmpty()
        || profile_name.getText().isEmpty()
        || profile_email.getText().isEmpty()
        || profile_gender.getSelectionModel().getSelectedItem() == null
        || profile_mobileNumber.getText().isEmpty()
        || profile_address.getText().isEmpty()
        || profile_specialization.getSelectionModel().getSelectedItem() == null
        || profile_status.getSelectionModel().getSelectedItem() == null){
            alert.errorMessage("Please, fill all the blank fields!");
        }else{

            // CHECKS IF USER WANT TO CHANGE PROFILE IMAGE OR NOT
            if(Data.path == null || "".equals(Data.path)){
                String updateData = "UPDATE doctor SET full_name = ?, email = ?, gender = ?, mobile_number = ?," +
                        " address = ?, specialized = ?, status = ?, modify_date = ?" +
                        " WHERE doctor_id = '"
                                + Data.doctor_id +"'";


                try {

                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

                    prepare = connect.prepareStatement(updateData);
                    prepare.setString(1, profile_name.getText());
                    prepare.setString(2, profile_email.getText());
                    prepare.setString(3, profile_gender.getSelectionModel().getSelectedItem());
                    prepare.setString(4, profile_mobileNumber.getText());
                    prepare.setString(5,profile_address.getText());
                    prepare.setString(6,profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(7,profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(8,String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
            else {

                String updateData = "UPDATE doctor SET full_name = ?, email = ?, gender = ?, mobile_number = ?," +
                        " address = ?, image = ?, specialized = ?, status = ?, modify_date = ?" +
                        " WHERE doctor_id = '"
                        + Data.doctor_id +"'";


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
                    Path copy = Paths.get("L:\\Trimester-4\\Advance Object Oriented Programming\\Project\\CyberDoc - Design-1 - UserLogin\\src\\main\\resources\\Directory"
                            + Data.doctor_id + ".jpg");
                    try {

                        // TO PUT THE IMAGE FILE TO DIRECTORY FOLDER
                        Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
                    }catch (Exception e){e.printStackTrace();}

                    prepare.setString(6, copy.toAbsolutePath().toString());
                    prepare.setString(7,profile_specialization.getSelectionModel().getSelectedItem());
                    prepare.setString(8,profile_status.getSelectionModel().getSelectedItem());
                    prepare.setString(9,String.valueOf(sqlDate));

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
            image = new Image(file.toURI().toString(), 1000, 1000, false, true);
            profile_circleImage.setFill(new ImagePattern(image));
        }

    }


    public void profileLabels(){
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {

            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();


            if(result.next()){
                profile_label_doctorID.setText(result.getString("doctor_id"));
                profile_label_name.setText(result.getString("full_name"));
                profile_label_email.setText(result.getString("email"));
                profile_label_dateCreated.setText(result.getString("date"));
            }


        }catch (Exception e){e.printStackTrace();}

    }

    public void profileFields(){

        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id + "'";

        connect = Database.connectDB();
        try {

            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();


            if(result.next()){
                profile_doctorID.setText(result.getString("doctor_id"));
                profile_name.setText(result.getString("full_name"));
                profile_email.setText(result.getString("email"));
                profile_gender.getSelectionModel().select(result.getString("gender"));
                profile_mobileNumber.setText(result.getString("mobile_number"));
                profile_address.setText(result.getString("address"));
                profile_specialization.getSelectionModel().select(result.getString("specialized"));
                profile_status.getSelectionModel().select(result.getString("status"));
            }


        }catch (Exception e){e.printStackTrace();}



    }


    public void profileDisplayImages(){
        String selectData = "SELECT * FROM doctor WHERE doctor_id = '"
                + Data.doctor_id+"'";

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

                    image = new Image(temp_path2, 1000, 1000, false, true);
                    profile_circleImage.setFill(new ImagePattern(image));
                }
            }

        }catch (Exception e){e.printStackTrace();}
    }




    public void viewReports(){
        String patientUsername = report_searchBox.getText();


        if(patientUsername.equals("")){
            alert.errorMessage("Please enter patient's username!");
        }
        else {
            String selectData = "SELECT * FROM patient WHERE username = '"
                    + patientUsername+"'";

            String temp_path = "";
            connect = Database.connectDB();

            try {
                prepare = connect.prepareStatement(selectData);
                result = prepare.executeQuery();

                if(result.next()){
                    temp_path = "File:" + result.getString("prescription");

                    if(result.getString("prescription") != null){
                        image = new Image(temp_path, 1000, 1000, false, true);
//                    profile_circleImage.setFill(new ImagePattern(image));
                        report_imageView.setImage(image);
                    }

                    report_name.setText(result.getString("full_name"));
                    report_username.setText(result.getString("username"));
                    report_email.setText(result.getString("email"));
                }
                else {
                    alert.errorMessage("No data loaded!");
                }

            }catch (Exception e){e.printStackTrace();}
        }

    }


    private String[] specialization = {"Allergist", "Dermatologist", "Ophthalmologist","Gynecologist","Cardiologist"};

    public void profileSpecializationList(){

        List<String> listS = new ArrayList<>();

        for(String data: specialization){
            listS.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(listS);
        profile_specialization.setItems(listData);

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
        if(event.getSource() == dashboard_btn){
            dashboard_form.setVisible(true);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            report_form.setVisible(false);
        }else if(event.getSource() == patients_btn){
            dashboard_form.setVisible(false);
            patients_form.setVisible(true);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            report_form.setVisible(false);
        }else if(event.getSource() == appointments_btn){
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(true);
            profile_form.setVisible(false);
            report_form.setVisible(false);
        }
        else if(event.getSource() == profile_btn){
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(true);
            report_form.setVisible(false);
        }
        else if(event.getSource() == reportBtn){
            dashboard_form.setVisible(false);
            patients_form.setVisible(false);
            appointments_form.setVisible(false);
            profile_form.setVisible(false);
            report_form.setVisible(true);
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

//    public void switchToHome(ActionEvent event) throws IOException {
//        sceneShift.changeScene("PatientHomePage.fxml");
//    }

    public void switchToChat(ActionEvent event) throws IOException {
        // CREATE FXML FILE FOR EDIT
        Parent root = FXMLLoader.load(getClass().getResource("Server.fxml"));
        Stage stage = new Stage();
        Image icon = new Image("doctor(1).png");
        stage.getIcons().add(icon);
        stage.setTitle(Data.doctor_name+" || CYBERDOC");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void switchToLogout(ActionEvent event) throws IOException {
        sceneShift.changeScene("hello-view.fxml");
    }
//
//    public void switchToAIDoctor(ActionEvent event) throws IOException {
//        sceneShift.changeScene("AIDoctorPage.fxml");
//    }
//
//    public void switchToDashboard(ActionEvent event) throws IOException {
//        sceneShift.changeScene("PatientDashboard.fxml");
//    }
//
//    public void switchToBloodBank(ActionEvent event) throws IOException {
//        sceneShift.changeScene("BloodBank.fxml");
//    }
//
//    public void switchToGetFund(ActionEvent event) throws IOException {
//        sceneShift.changeScene("GetFund.fxml");
//    }


    // ========== Scene Shift End==========







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        runTime();
        displayAdminIDNumber();
        dashboardNOP();
        dashboardNOA();


        dashboardDisplayAP();
        dashboardDisplayTP();
        dashboardDisplayIP();
        dashboardDisplayTA();
        dashboardDisplayData();


        // TO SHOW DATA IMMEDIATELY ONCE LOGGED IN
        appointmentShowData();

        appointmentGenderList();

        appointmentStatusList();

        appointmentAppointmentID();

        patientGenderList();


        // PROFILE
        profileFields(); // TO DISPLAY ALL PROFILE DETAILS
        profileLabels();
        profileStatusList();
        profileGenderList();
        profileSpecializationList();
        profileDisplayImages(); // TO DISPLAY PROFILE IMAGE OF DOCTOR


    }
}
