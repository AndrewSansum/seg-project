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
        takeOffTowardsLandingTowards(runway, distance, slopeCalculation, stripEnd, lda, resa);
        takeOffAwayLandingOver(runway, distance, blastProtection, stopway, clearway, lda, buffer);
    }
     public void takeOffTowardsLandingTowards(int runway, String distance, int slope, String stripEnd, String lda, String resa){
        newTowardsTORA = runway - (runway - Integer.parseInt(distance)) - slope - Integer.parseInt(stripEnd);
        newTowardsASDA = newTowardsTORA;
        newTowardsTODA = newTowardsTORA;
        newTowardsLDA = Integer.parseInt(lda) - (Integer.parseInt(lda) - Integer.parseInt(distance)) - Integer.parseInt(resa) - Integer.parseInt(stripEnd);
     }

     public void takeOffAwayLandingOver(int runway, String distance, String bp, int stopway, int clearway, String lda, int buffer){
        newAwayTORA = runway - (runway - Integer.parseInt(distance)) - Integer.parseInt(bp);
        newAwayASDA = newAwayTORA + stopway;
        newAwayTODA = newAwayTORA + clearway;
        newAwayLDA = Integer.parseInt(lda) - (Integer.parseInt(lda) - Integer.parseInt(distance)) - buffer;
     }

    public String getRunwayName() {
        return runwayName;
    }

    public int getTowardsTORA() {
        return newTowardsTORA;
    }

    public int getAwayTORA() {
        return newAwayTORA;
    }

    public int getTowardsASDA() {
        return newTowardsASDA;
    }

    public int getAwayASDA() {
        return newAwayASDA;
    }

    public int getTowardsTODA() {
        return newTowardsTODA;
    }

    public int getAwayTODA() {
        return newAwayTODA;
    }

    public int getTowardsLDA() {
        return newTowardsLDA;
    }

    public int getAwayLDA() {
        return newAwayLDA;
    }

    public String getObstacleDirection(){
        return obstacleDirection;
    }

    public String getObstacleDistanceFromCenter(){
        return obstacleDistanceFromCenter;
    }
}
