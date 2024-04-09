package com.example.cyberdoc;
public class BloodRequestData {


    private String applicantsName;
    private String email;
    private String patientName;

    private String date;

    private String issue;
    private int age;
    private String bloodGroup;
    private String gender;
    private String address;
    private String mobileNumber;
    private String relation;
    private String hospitalName;
    private String hospitalLocation;
    private String status;

    public BloodRequestData(String applicantsName, String email, String patientName, String issue, int age, String bloodGroup, String gender,
                           String address, String mobileNumber, String relation, String hospitalName, String hospitalLocation, String status, String date){
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
        this.status = status;
        this.date = date;
    }




    public String getApplicantsName(){return  applicantsName; }
    public String getEmail(){return  email;}
    public String getDate(){return date;}
    public String getPatientName(){return patientName;}
    public String getIssue(){return issue;}
    public String getBloodGroup(){return bloodGroup;}
    public String getRelation(){return relation;}
    public String getHospitalName(){return hospitalName;}
    public String getHospitalLocation(){return hospitalLocation;}
    public String getStatus(){return  status;}
    public String getGender(){return gender;}
    public String getAddress(){return address;}
    public String getMobileNumber(){return mobileNumber;}
    public int getAge(){return age;}







}
