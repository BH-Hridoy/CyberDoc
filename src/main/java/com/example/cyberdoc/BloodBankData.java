package com.example.cyberdoc;

public class BloodBankData {
    String name;
    String bloodGroup;
    String preferedArea;
    String lastDonationDate;
    String availability;


    public BloodBankData(String name, String bloodGroup, String preferedArea, String lastDonationDate, String availability){
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.preferedArea = preferedArea;
        this.lastDonationDate = lastDonationDate;
        this.availability = availability;
    }

    public String getName(){return name;}
    public String getBloodGroup(){return bloodGroup;}
    public String getPreferedArea(){return preferedArea;}
    public String getAvailability(){return availability;}
    public String getLastDonationDate(){return lastDonationDate;}

}
