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

    public Calculations(String name,String tora, String asda, String toda, String lda, String height, String obstacleDistance, String direction, String distance, String displacement){
        runwayName = name;
        int runway = 0; //Placeholder
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
}
