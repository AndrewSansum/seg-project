package com.example.segproject;

public class Calculations {
    int newTowardsTORA;
    int newTowardsASDA;
    int newTowardsTODA;
    int newTowardsLDA;
    int newAwayTORA;
    int newAwayASDA;
    int newAwayTODA;
    int newAwayLDA;
    String runwayName;

    public Calculations(String name,String tora, String asda, String toda, String lda, String height, String distance, String direction, String obstacleDistance, String displacement, String resa, String stripEnd, String blastProtection){
        runwayName = name;
        int runway = Integer.parseInt(tora);
        int stopway = Integer.parseInt(asda) - runway;
        int clearway = Integer.parseInt(toda) - runway;
        int slopeCalculation = 0; //Placeholder: max(RESA, height*50)
        //Resa constant = 240?
        //stripEnd constant = 60?
        takeOffTowardsLandingTowards();
        takeOffAwayLandingOver(stopway, clearway);
    }
    public void takeOffTowardsLandingTowards(){
        newTowardsTORA = 0; //Placeholder: distance(from incoming direction) - slopeCalculation - stripEnd (+displaced threshold sometimes?)
        newTowardsASDA = newTowardsTORA;
        newTowardsTODA = newTowardsTORA;
        newTowardsLDA = 0; //Placeholder: distance(from incoming direction) - RESA - stripEnd
    }

    public void takeOffAwayLandingOver(int stopway, int clearway){
        newAwayTORA = 0; //Placeholder(so many questions)
        newAwayASDA = newAwayTORA + stopway;
        newAwayTODA = newAwayTORA + clearway;
        newAwayLDA = 0; //Placeholder: lda - distance(from incoming direction) - slopeCalculation - max(stripEnd, blastProtectionValue)
    }

    public String getRunwayName() {return runwayName;}
    public String getTowardsTORA() {return String.valueOf(newTowardsTORA);}
    public String getTowardsASDA() {return String.valueOf(newTowardsASDA);}
    public String getTowardsTODA() {return String.valueOf(newTowardsTODA);}
    public String getTowardsLDA() {return String.valueOf(newTowardsLDA);}
    public String getAwayTORA() {return String.valueOf(newTowardsTORA);}
    public String getAwayASDA() {return String.valueOf(newTowardsASDA);}
    public String getAwayTODA() {return String.valueOf(newTowardsTODA);}
    public String getAwayLDA() {return String.valueOf(newTowardsLDA);}
}
