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

    public Calculations(String name,String tora, String lda, String stopway, String clearway, String height, String obstacleDistance, String direction, String distance){
        runwayName = name;
        int runway = Integer.parseInt(tora);
        int runwayStrip = runway + Integer.parseInt(stopway);
        int stripEnd = runwayStrip - runway;
        int slopeCalculation = 0; //Placeholder: if(50*height<RESA) slopeCalculation=RESA else slopeCalculation = 50*height
        takeOffTowardsLandingTowards();
        takeOffAwayLandingOver(stopway, clearway);
    }
     public void takeOffTowardsLandingTowards(){
        newTowardsTORA = 0; //Placeholder(?)
        newTowardsASDA = newTowardsTORA;
        newTowardsTODA = newTowardsTORA;
        newTowardsLDA = 0; //Placeholder: distance - RESA - stripEnd
     }

     public void takeOffAwayLandingOver(String stopway, String clearway){
        newAwayTORA = 0; //Placeholder(?)
        newAwayASDA = newAwayTORA + Integer.parseInt(stopway);
        newAwayTODA = newAwayTORA + Integer.parseInt(clearway);
        newAwayLDA = 0; //Placeholder: lda - slopeCalculation - distance - stripEnd
     }
}
