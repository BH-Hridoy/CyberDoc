package com.example.cyberdoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;

public class BloodRequestController implements Initializable {



    @FXML
    private TableColumn<PatientsData, String> recordpage_col_address;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_dateCreated;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_dateDeleted;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_dateModified;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_gender;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_mobileNumber;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_name;

    @FXML
    private TableColumn<PatientsData, String> recordpage_col_patientID;

    @FXML
    private AnchorPane recordpage_mainForm;

    @FXML
    private TextField recordpage_search;






    @FXML
    private TableView<BloodRequestData> recordpage_tableView;



    @FXML
    private TableColumn<BloodRequestData, String> fund_address;

    @FXML
    private TableColumn<BloodRequestData, String> fund_age;

    @FXML
    private TableColumn<BloodRequestData, String> fund_date;

    @FXML
    private TableColumn<BloodRequestData, String> fund_applicantsName;


    @FXML
    private TableColumn<BloodRequestData, String> fund_email;

    @FXML
    private TableColumn<BloodRequestData, String> fund_gender;

    @FXML
    private TableColumn<BloodRequestData, String> fund_hospitalName;

    @FXML
    private TableColumn<BloodRequestData, String> fund_issue;
    @FXML
    private TableColumn<BloodRequestData, String> fund_location;

    @FXML
    private TableColumn<BloodRequestData, String> fund_mobile;

    @FXML
    private TableColumn<BloodRequestData, String> fund_patientName;


    @FXML
    private TableColumn<BloodRequestData, String> fund_relation;


    @FXML
    private TableColumn<BloodRequestData, String> recordpage_col_action;




    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;


    AlertMessage alert = new AlertMessage();


    public ObservableList<BloodRequestData> getFundRequest(){
        ObservableList<BloodRequestData> listData = FXCollections.observableArrayList();

        String selectData = "SELECT * FROM blood_application WHERE status = '" +"Not Approved"+"'";

        connect = Database.connectDB();

        try {

            prepare = connect.prepareStatement(selectData);
            result = prepare.executeQuery();

            BloodRequestData fundRequestData ;

            while (result.next()){

                fundRequestData = new BloodRequestData(
                        result.getString("applicants_name"),
                        result.getString("email"),
                        result.getString("patient_name"),
                        result.getString("issue"),
                        result.getInt("age"),
                        result.getString("blood_group"),
                        result.getString("gender"),
                        result.getString("address"),
                        result.getString("mobile_number"),
                        result.getString("relation_with_patient"),
                        result.getString("hospital_name"),
                        result.getString("hospital_location"),
                        result.getString("status"),
                        result.getString("date_needed")
                );

                listData.add(fundRequestData);
            }

        }catch (Exception e){e.printStackTrace();}

        return listData;

    }

    private ObservableList<BloodRequestData> fundData;


    public void displayPatientsData(){
        fundData = getFundRequest();

        fund_applicantsName.setCellValueFactory(new PropertyValueFactory<>("applicantsName"));
        fund_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        fund_patientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        fund_issue.setCellValueFactory(new PropertyValueFactory<>("issue"));
        fund_age.setCellValueFactory(new PropertyValueFactory<>("age"));
        fund_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        fund_address.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
        fund_mobile.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        fund_relation.setCellValueFactory(new PropertyValueFactory<>("relation"));
        fund_hospitalName.setCellValueFactory(new PropertyValueFactory<>("hospitalName"));
        fund_location.setCellValueFactory(new PropertyValueFactory<>("hospitalLocation"));
        fund_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        recordpage_tableView.setItems(fundData);


    }


    HelloApplication changeScene = new HelloApplication();
    public void actionButtons(){

        connect = Database.connectDB();
        fundData = getFundRequest();

        Callback<TableColumn<BloodRequestData, String>, TableCell<BloodRequestData, String>> cellFactory = (TableColumn<BloodRequestData, String> param) -> {
            final TableCell<BloodRequestData, String> cell = new TableCell<BloodRequestData, String>() {
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        Button editButton = new Button("Edit");
                        Button removeButton = new Button("Delete");

                        editButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n" +
                                "    -fx-text-fill: #fff;\n" +
                                "    -fx-font-family: Arial;\n" +
                                "    -fx-cursor: hand;\n" +
                                "    -fx-font-size: 15px;");

                        removeButton.setStyle("-fx-background-color: linear-gradient(to bottom right, #a413a1, #64308e);\n" +
                                "    -fx-text-fill: #fff;\n" +
                                "    -fx-font-family: Arial;\n" +
                                "    -fx-cursor: hand;\n" +
                                "    -fx-font-size: 15px;");

                        editButton.setOnAction((ActionEvent event) -> {
                            try {

                                BloodRequestData pData = recordpage_tableView.getSelectionModel().getSelectedItem();
                                int num = recordpage_tableView.getSelectionModel().getSelectedIndex();

                                if ((num - 1) < -1) {
                                    alert.errorMessage("Please, select item first!");
                                    return;
                                }


                                Data.temp_applicantsName = pData.getApplicantsName();
                                Data.temp_email = pData.getEmail();
                                Data.temp_patientName = pData.getPatientName();
                                Data.temp_issue = pData.getIssue();
                                Data.temp_gender = pData.getGender();
                                Data.temp_hospitalName = pData.getHospitalName();
                                Data.temp_age = pData.getAge();
                                Data.temp_mobileNumber = pData.getMobileNumber();
                                Data.temp_status = pData.getStatus();
                                Data.temp_relation = pData.getRelation();
                                Data.temp_address = pData.getAddress();
                                Data.temp_status = pData.getStatus();
                                Data.temp_location = pData.getHospitalLocation();
                                Data.temp_date = pData.getDate();





                                // CREATE FXML FILE FOR EDIT
                                Parent root = FXMLLoader.load(getClass().getResource("EditBloodRequest.fxml"));
                                Stage stage = new Stage();
                                stage.setTitle("Edit Blood Request Info || CYBERDOC");
                                stage.setScene(new Scene(root));
                                stage.show();
//                                changeScene.changeScene("EditPatientForm.fxml");

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                        removeButton.setOnAction((ActionEvent event) -> {
                            BloodRequestData pData = recordpage_tableView.getSelectionModel().getSelectedItem();
                            int num = recordpage_tableView.getSelectionModel().getSelectedIndex();

                            if((num - 1) < -1){
                                alert.errorMessage("Please, select item first!");
                                return;
                            }

                            String deleteData = "UPDATE fund_application WHERE mobile_number = "
                                    + pData.getMobileNumber();

                            try{
                                if(alert.confirmationMessage("Are you sure you want to delete request: " + pData.getApplicantsName() + "?")){
                                    prepare = connect.prepareStatement(deleteData);
                                    prepare.executeUpdate();

                                    alert.successMessage("Deleted Successfully!");

                                    displayPatientsData();
                                }

                            }catch (Exception e){e.printStackTrace();}
                        });

                        HBox manageBtn = new HBox(editButton);
                        manageBtn.setAlignment(Pos.CENTER);
                        manageBtn.setSpacing(5);
                        setGraphic(manageBtn);
                        setText(null);


                    }
                }
            };


            return cell;
        };

        recordpage_col_action.setCellFactory(cellFactory);
        recordpage_tableView.setItems(fundData);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // TO DISPLAY THE PATIENTS DATA ONCE THE DOCTOR CLICK THE RECORD BUTTON
        displayPatientsData();

        actionButtons();
    }
}
