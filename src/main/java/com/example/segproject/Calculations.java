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
        int runway = Integer.parseInt(tora);
        int stopway = Integer.parseInt(asda) - runway;
        int clearway = Integer.parseInt(toda) - runway;
        obstacleDirection = direction;
        obstacleDistanceFromCenter = obstacleDistance;
        int slopeCalculation;
        int landingOverBuffer;
        if (Integer.parseInt(height)*50 > Integer.parseInt(resa)){
            slopeCalculation = Integer.parseInt(height)*50;
        } else {
            slopeCalculation = Integer.parseInt(resa);
        }
        if (slopeCalculation + Integer.parseInt(stripEnd) > Integer.parseInt(blastProtection)){
            landingOverBuffer = slopeCalculation + Integer.parseInt(stripEnd);
        } else {
            landingOverBuffer = Integer.parseInt(blastProtection);
        }
        takeOffTowardsLandingTowards(distance, slopeCalculation, stripEnd, resa);
        takeOffAwayLandingOver(stopway, clearway, lda, distance, landingOverBuffer, blastProtection);
    }
     public void takeOffTowardsLandingTowards(String distance, int slopeCalculation, String stripEnd, String resa){
        newTowardsTORA = Integer.parseInt(distance) - slopeCalculation - Integer.parseInt(stripEnd);
        newTowardsASDA = newTowardsTORA;
        newTowardsTODA = newTowardsTORA;
        newTowardsLDA = Integer.parseInt(distance) - Integer.parseInt(resa) - Integer.parseInt(stripEnd);
     }

     public void takeOffAwayLandingOver(int stopway, int clearway,String lda, String distance, int buffer, String bp){
        newAwayTORA = Integer.parseInt(lda) - (Integer.parseInt(lda) - Integer.parseInt(distance)) - Integer.parseInt(bp);
        newAwayASDA = newAwayTORA + stopway;
        newAwayTODA = newAwayTORA + clearway;
        newAwayLDA = Integer.parseInt(lda) - (Integer.parseInt(lda) - Integer.parseInt(distance)) - buffer;
     }
}
