package com.example.cyberdoc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class EditBloodRequestController implements Initializable {

    @FXML
    private Button edit_updateBtn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label pd_address;

    @FXML
    private Label pd_age;

    @FXML
    private Label pd_amount;

    @FXML
    private Label pd_doctor;

    @FXML
    private Label pd_email;

    @FXML
    private Label pd_gender;

    @FXML
    private Label pd_hospitalName;

    @FXML
    private Label pd_issue;

    @FXML
    private Label pd_mobile;

    @FXML
    private Label pd_name;

    @FXML
    private Label pd_pName;

    @FXML
    private Label pd_referedBy;

    @FXML
    private Label pd_relation;

    @FXML
    private ComboBox<String> pd_status;

    @FXML
    private TextArea edit_treatment;
    @FXML
    private Label pd_hospitalLocation;

    @FXML
    private Label pd_hospitalDate;




    // DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    AlertMessage alert = new AlertMessage();

    public void updateBtn(){

        if(pd_status.getSelectionModel().getSelectedItem() == null){
            alert.errorMessage("Please, Select Any Status!");
        }else{
            String updateData = "UPDATE blood_application SET status = ? " +
                    "WHERE mobile_number = '"
                    + Data.temp_mobileNumber+"'";
            connect = Database.connectDB();

            try {
                if(alert.confirmationMessage("Are you sure you want to UPDATE Blood Request Status ?") ){
                    prepare = connect.prepareStatement(updateData);

                    prepare.setString(1, (String) pd_status.getSelectionModel().getSelectedItem());


                    prepare.executeUpdate();

                    alert.successMessage("Updated Successfully!");

                }else {
                    alert.errorMessage("Cancelled!");
                }

            }catch (Exception e){e.printStackTrace();}


        }

    }

    public void setField(){

        pd_name.setText(Data.temp_applicantsName);
        pd_email.setText(Data.temp_email);
        pd_pName.setText(Data.temp_patientName);
        pd_gender.setText(Data.temp_gender);
        pd_age.setText(String.valueOf(Data.temp_age));
        pd_issue.setText(Data.temp_issue);
        pd_mobile.setText(Data.temp_mobileNumber);
        pd_relation.setText(Data.temp_relation);
        pd_address.setText(Data.temp_address);
        pd_hospitalName.setText(Data.temp_hospitalName);
        pd_status.getSelectionModel().select(Data.temp_status);
        pd_hospitalLocation.setText(Data.temp_location);
        pd_hospitalDate.setText(Data.temp_date);

    }



    String[] statusArray = {"Approved", "Hold", "Denied"};
    public void statusList(){

        List<String> statusL = new ArrayList<>();
        for(String data: statusArray){
            statusL.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(statusL);
        pd_status.setItems(listData);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setField();
        statusList();

    }
}
