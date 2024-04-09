package com.example.cyberdoc;

public class FundData {
    String name;
    String donationAmount;
    String date;

    public FundData(String name,String donationAmount, String date){
        this.name = name;
        this.donationAmount = donationAmount;
        this.date = date;
    }

    public String getName(){return name;}
    public String getDonationAmount(){return donationAmount;}

    public String getDate(){return date;}
}

