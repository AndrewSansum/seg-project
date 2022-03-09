package com.example.segproject;

public class Calculations {
    private int newTORA;
    private int newASDA;
    private int newTODA;
    private int newLDA;

    public String runwayName;
    public String obstacleDirection;
    public String status;
    public int obstacleDistanceFromCenter;
    public int displacementThreshold;
    public int obstacleHeight;
    public int obstacleDistanceFromThreshold;
    public int tora;
    public int asda;
    public int toda;
    public int lda;
    public int resa;
    public int stripEnd;
    public int blastProtection;
    public int als;
    public int tocs;

    public int stopway;
    public int clearway;

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
        this.displacementThreshold = displacement;
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
                takeoffTowards(this.obstacleHeight, this.displacementThreshold, this.obstacleDistanceFromThreshold, this.stripEnd);
            }
        }

    }

    public void takeoffTowards(int obstacleHeight, int displacementThreshold, int obstacleDistanceFromThreshold, int stripEnd) {
        int slopeCalculation;
        slopeCalculation = obstacleHeight * this.tocs;

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
        int slopeCalculation;
        slopeCalculation = obstacleHeight * this.als;

        if (this.resa > slopeCalculation) { // this case shouldn't happen until obstacle length is known
            this.newLDA = this.tora - obstacleDistanceFromThreshold - this.resa - stripEnd;
        } else {
            this.newLDA = this.tora - obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
        }
    }

    public String getRunwayName() {return runwayName;}
    public int getNewTORA() {return this.newTORA;}
    public int getNewASDA() {return this.newASDA;}
    public int getNewTODA() {return this.newTODA;}
    public int getNewLDA() {return this.newLDA;}
    public String getObstacleDirection() {return obstacleDirection;}
    public int getObstacleDistanceFromCenter() {return obstacleDistanceFromCenter;}
    public int getDisplacementThreshold() {return displacementThreshold;}
    public int getObstacleHeight() {return obstacleHeight;}
    public int getObstacleDistanceFromThreshold() {return obstacleDistanceFromThreshold;}
}
