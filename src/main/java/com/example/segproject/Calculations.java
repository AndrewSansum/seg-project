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
    int obstacleDistanceFromCenter;
    int displacementThreshold;
    int obstacleHeight;
    int obstacleDistanceFromThreshold;

    public Calculations(String name, int tora, int asda, int toda, int lda, int height, int distance, String direction, int obstacleDistance, int displacement, int resa, int stripEnd, int blastProtection){
        runwayName = name;
        obstacleDistanceFromCenter = obstacleDistance;
        obstacleDirection = direction;
        displacementThreshold = displacement;
        obstacleHeight = height;
        obstacleDistanceFromThreshold = distance;
        int stopway = asda - tora;
        int clearway = toda - tora;
        int slopeCalculation;
        int buffer;
        if (height*50 > resa) {
            slopeCalculation = height*50;
        } else {
            slopeCalculation = resa;
        }
        if (stripEnd + slopeCalculation > blastProtection) {
            buffer = stripEnd + slopeCalculation;
        } else {
            buffer = blastProtection;
        }
        takeOffTowardsLandingTowards(tora, distance, slopeCalculation, stripEnd, lda, resa);
        takeOffAwayLandingOver(tora, distance, blastProtection, stopway, clearway, lda, buffer);
    }
     public void takeOffTowardsLandingTowards(int runway, int distance, int slope, int stripEnd, int lda, int resa){
        newTowardsTORA = runway - (runway - distance) - slope - stripEnd;
        newTowardsASDA = newTowardsTORA;
        newTowardsTODA = newTowardsTORA;
        newTowardsLDA = lda - (lda - distance) - resa - stripEnd;
     }

     public void takeOffAwayLandingOver(int runway, int distance, int bp, int stopway, int clearway, int lda, int buffer){
        newAwayTORA = runway - (runway - distance) - bp;
        newAwayASDA = newAwayTORA + stopway;
        newAwayTODA = newAwayTORA + clearway;
        newAwayLDA = lda - (lda - distance) - buffer;
     }

    public String getRunwayName() {return runwayName;}
    public String getTowardsTORA() {return String.valueOf(newTowardsTORA);}
    public String getAwayTORA() {return String.valueOf(newAwayTORA);}
    public String getTowardsASDA() {return String.valueOf(newTowardsASDA);}
    public String getAwayASDA() {return String.valueOf(newAwayASDA);}
    public String getTowardsTODA() {return String.valueOf(newTowardsTODA);}
    public String getAwayTODA() {return String.valueOf(newAwayTODA);}
    public String getTowardsLDA() {return String.valueOf(newTowardsLDA);}
    public String getAwayLDA() {return String.valueOf(newAwayLDA);}
    public String getObstacleDirection() {return obstacleDirection;}
    public String getObstacleDistanceFromCenter() {return String.valueOf(obstacleDistanceFromCenter);}
    public String getDisplacementThreshold(){ return String.valueOf(displacementThreshold);}
    public String getObstacleHeight() { return String.valueOf(obstacleHeight);}
    public String getObstacleDistanceFromTheshold() { return String.valueOf(obstacleDistanceFromThreshold);}
}
