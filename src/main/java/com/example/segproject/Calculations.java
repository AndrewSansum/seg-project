package com.example.segproject;

public class Calculations {
    private int newTORA;
    private int newASDA;
    private int newTODA;
    private int newLDA;

    private String runwayName;
    private String obstacleDirection;
    private String status;
    private int obstacleDistanceFromCenter;
    private int displacedThreshold;
    private int obstacleHeight;
    private int obstacleDistanceFromThreshold;
    private int tora;
    private int asda;
    private int toda;
    private int lda;
    private int resa;
    private int stripEnd;
    private int blastProtection;
    private int als;
    private int tocs;
    private int slopeCalculation;

    private int stopway;
    private int clearway;

    public Calculations(String name, String status, int tora, int asda, int toda, int lda, int height, int distance, String direction, int obstacleDistance, int displacement, int resa, int stripEnd, int blastProtection){
        this.runwayName = name;
        this.tora = tora;
        this.asda = asda;
        this.toda = toda;
        this.lda = lda;
        this.resa = resa;
        this.stripEnd = stripEnd;
        this.blastProtection = blastProtection;
        this.obstacleDistanceFromCenter = obstacleDistance;
        this.obstacleDirection = direction;
        this.displacedThreshold = displacement;
        this.obstacleHeight = height;
        this.obstacleDistanceFromThreshold = distance;
        this.stopway = asda - tora;
        this.clearway = toda - tora;
        this.als = 50; // future user input
        this.tocs = 50; // future user input
        this.status = status;


        // determine heading towards or away from obstacle
        if (obstacleDistanceFromThreshold <= (tora * 0.5)) { // obstacle is on the near-side
            if (status == "Landing") { // Plane is landing
                landingOver(this.obstacleHeight, this.obstacleDistanceFromThreshold, this.stripEnd);
            } else { // Plane is taking off
                takeoffAway(this.blastProtection, this.obstacleDistanceFromThreshold);
            }

        } else { // obstacle is on the far-side
            if (status == "Landing") { // Plane is landing
                landingTowards(this.obstacleDistanceFromThreshold, this.resa, this.stripEnd);
            } else { // Plane is taking off
                takeoffTowards(this.obstacleHeight, this.displacedThreshold, this.obstacleDistanceFromThreshold, this.stripEnd);
            }
        }

    }

    public void takeoffTowards(int obstacleHeight, int displacementThreshold, int obstacleDistanceFromThreshold, int stripEnd) {
        this.slopeCalculation = obstacleHeight * this.tocs;

        if (this.resa > slopeCalculation) { // this case shouldn't happen until obstacle length is known
            this.newTORA = displacementThreshold + obstacleDistanceFromThreshold - this.resa - stripEnd;
        } else {
            this.newTORA = displacementThreshold + obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
        }

        this.newTODA = this.newTORA;
        this.newASDA = this.newTORA;
    }

    public void landingTowards(int obstacleDistanceFromThreshold, int resa, int stripEnd) {
        this.newLDA = obstacleDistanceFromThreshold - resa - stripEnd;
    }

    public void takeoffAway(int blastProtection, int obstacleDistanceFromThreshold) {
        this.newTORA = this.tora - blastProtection - obstacleDistanceFromThreshold;
        this.newTODA = this.toda - blastProtection - obstacleDistanceFromThreshold;
        this.newASDA = this.asda - blastProtection - obstacleDistanceFromThreshold;
    }

    public void landingOver(int obstacleHeight, int obstacleDistanceFromThreshold, int stripEnd){
        this.slopeCalculation = obstacleHeight * this.als;

        if (this.resa > slopeCalculation) { // this case shouldn't happen until obstacle length is known
            this.newLDA = this.lda - obstacleDistanceFromThreshold - this.resa - stripEnd;
        } else {
            this.newLDA = this.lda - obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
        }
    }

    public String getRunwayName() {return runwayName;}
    public String getStatus() {return status;}
    public int getTORA() {return this.tora;}
    public int getNewTORA() {return this.newTORA;}
    public int getNewASDA() {return this.newASDA;}
    public int getNewTODA() {return this.newTODA;}
    public int getNewLDA() {return this.newLDA;}
    public int getRESA() {return this.resa;}
    public int getStripEnd() {return this.stripEnd;}
    public int getDisplacedThreshold() {return this.displacedThreshold;}
    public int getSlopeCalculation() {return slopeCalculation;}
    public String getObstacleDirection() {return obstacleDirection;}
    public int getObstacleDistanceFromCenter() {return obstacleDistanceFromCenter;}
    public int getObstacleHeight() {return obstacleHeight;}
    public int getObstacleDistanceFromThreshold() {return obstacleDistanceFromThreshold;}
}
