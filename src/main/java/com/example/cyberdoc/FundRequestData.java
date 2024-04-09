package com.example.cyberdoc;

import java.sql.Date;

public class FundRequestData {


    private String applicantsName;
    private String email;
     private String patientName;

     private String issue;
    private int age;
    private String bloodGroup;
    private String gender;
    private String address;
    private String mobileNumber;
    private String relation;
    private String hospitalName;
    private String hospitalLocation;
    private String doctor;
    private String referedBy;
    private String amount;
    private String status;

    public FundRequestData(String applicantsName, String email, String patientName, String issue, int age, String bloodGroup, String gender,
                           String address, String mobileNumber, String relation, String hospitalName, String hospitalLocation,
    String doctor, String referedBy, String amount, String status){
        this.applicantsName = applicantsName;
        this.email = email;
        this.patientName = patientName;
        this.issue = issue;
        this.age = age;
        this.bloodGroup = bloodGroup;
        this.gender = gender;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.relation = relation;
        this.hospitalName = hospitalName;
        this.hospitalLocation = hospitalLocation;
        this.doctor = doctor;
        this.referedBy =referedBy;
        this.amount = amount;
        this.status = status;
    }




    public String getApplicantsName(){return  applicantsName; }
    public String getEmail(){return  email;}
    public String getPatientName(){return patientName;}
    public String getIssue(){return issue;}
    public String getBloodGroup(){return bloodGroup;}
    public String getRelation(){return relation;}
    public String getHospitalName(){return hospitalName;}
    public String getHospitalLocation(){return hospitalLocation;}
    public String getReferedBy(){return referedBy;}
    public String getAmount(){return amount;}
    public String getDoctor(){return doctor;}
    public String getStatus(){return  status;}
    public String getGender(){return gender;}
    public String getAddress(){return address;}
    public String getMobileNumber(){return mobileNumber;}
    public int getAge(){return age;}







}
