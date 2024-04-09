package com.example.cyberdoc;

import java.sql.Date;

public class AppointmentData {

    private Integer appointmentID;
    private Integer patientID;
    private String name;
    private String gender;
    private String description;
    private String diagnosis;
    private String treatment;
    private long mobileNumber;
    private String address;

    private Date date;
    private Date dateModify;
    private Date dateDelete;
    private String status;
    private String doctorID;
    private String specialized;
    private Date schedule;

    public AppointmentData(Integer appointmentID, String name, String gender,
                           Long mobileNumber, String description, String diagnosis, String treatment, String address, Date date,
                           Date dateModify, Date dateDelete, String status, java.sql.Date schedule){
        this.appointmentID = appointmentID;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.description =description;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.address = address;
        this.date =date;
        this.dateModify = dateModify;
        this.dateDelete =dateDelete;
        this.status = status;
        this.schedule = schedule;

    }



    public AppointmentData(Integer appointmentID, String name, String description, Date date, String status ){
        this.appointmentID = appointmentID;
        this.name = name;
        this.description = description;
        this.date = date;
        this.status = status;
    }


    public Integer getAppointmentID(){
        return appointmentID;
    }

    public String getName(){
        return name;
    }
    public String getGender(){
        return gender;
    }
    public Long getMobileNumber(){
        return mobileNumber;
    }
    public Date getDate(){
        return date;
    }
    public Date getDateModify(){
        return dateModify;
    }
    public Date getDateDelete(){
        return dateDelete;
    }

    public String getStatus(){
        return status;
    }

    public String getDescription(){
        return description;
    }
    public String getDiagnosis(){return diagnosis;}

    public String getTreatment(){
        return treatment;
    }

    public String getAddress(){
        return address;
    }
    public Date getSchedule(){
        return schedule;
    }

}
