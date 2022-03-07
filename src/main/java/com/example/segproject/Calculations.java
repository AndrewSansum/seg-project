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
    String obstacleDirection;
    String obstacleDistanceFromCenter;

    public Calculations(String name,String tora, String asda, String toda, String lda, String height, String distance, String direction, String obstacleDistance, String displacement, String resa, String stripEnd, String blastProtection){
        runwayName = name;
        obstacleDistanceFromCenter = obstacleDistance;
        obstacleDirection = direction;
        int runway = Integer.parseInt(tora);
        int stopway = Integer.parseInt(asda) - runway;
        int clearway = Integer.parseInt(toda) - runway;
        int slopeCalculation;
        int buffer;
        if (Integer.parseInt(height)*50 > Integer.parseInt(resa)){
            slopeCalculation = Integer.parseInt(height)*50;
        } else {
            slopeCalculation = Integer.parseInt(resa);
        }
        if (Integer.parseInt(stripEnd) + slopeCalculation > Integer.parseInt(blastProtection)){
            buffer = Integer.parseInt(stripEnd) + slopeCalculation;
        } else {
            buffer = Integer.parseInt(blastProtection);
        }
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
        newAwayLDA = 0; //Placeholder: lda - distance(from incoming direction) - max(slopeCalculation + stripEnd, blastProtectionValue)
     }
}
