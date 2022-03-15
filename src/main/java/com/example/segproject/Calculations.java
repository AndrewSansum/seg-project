package com.example.segproject;

public class Calculations {
    private String runwayName;
    private String status;
    private int tora;
    private int asda;
    private int toda;
    private int lda;
    private int obstacleHeight;
    private int obstacleDistanceFromThreshold;
    private int obstacleDistanceFromCenter;
    private String obstacleDirection;
    private int displacementThreshold;
    private int resa;
    private int stripEnd;
    private int blastProtection;
    private int als;
    private int tocs;

    public int stopway;
    public int clearway;

    private int newTORA;
    private int newASDA;
    private int newTODA;
    private int newLDA;

    public void runCalculations () {
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
            this.newLDA = this.lda - obstacleDistanceFromThreshold - this.resa - stripEnd;
        } else {
            this.newLDA = this.lda - obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
        }
    }

    public String getRunwayName() {return runwayName;}
    public int getTORA() {return this.tora;}
    public int getNewTORA() {return this.newTORA;}
    public int getNewASDA() {return this.newASDA;}
    public int getNewTODA() {return this.newTODA;}
    public int getNewLDA() {return this.newLDA;}
    public String getObstacleDirection() {return obstacleDirection;}
    public int getObstacleDistanceFromCenter() {return obstacleDistanceFromCenter;}
    public int getDisplacementThreshold() {return displacementThreshold;}
    public int getObstacleHeight() {return obstacleHeight;}
    public int getObstacleDistanceFromThreshold() {return obstacleDistanceFromThreshold;}
    public int getClearway() {return clearway;}
    public int getStopway() {return stopway;}

    public void setRunwayName (String name) {this.runwayName = name;}
    public void setStatus (String status) {this.status = status;}
    public void setTORA (int tora) {this.tora = tora;}
    public void setASDA (int asda) {this.asda = asda;}
    public void setTODA (int toda) {this.toda = toda;}
    public void setLDA (int lda) {this.lda = lda;}
    public void setObstacleHeight (int h) {this.obstacleHeight = h;}
    public void setObstacleDistanceFromThreshold (int distance) {this.obstacleDistanceFromThreshold = distance;}
    public void setObstacleDistanceFromCenter (int distance) {this.obstacleDistanceFromCenter = distance;}
    public void setObstacleDirection (String direction) {this.obstacleDirection = direction;}
    public void setDisplacementThreshold (int displacementThreshold) {this.displacementThreshold = displacementThreshold;}
    public void setRESA (int resa) {this.resa = resa;}
    public void setStripEnd (int stripEnd) {this.stripEnd = stripEnd;}
    public void setBlastProtection (int blastProtection) {this.blastProtection = blastProtection;}
    public void setALS (int als) {this.als = als;}
    public void setTOCS (int tocs) {this.tocs = tocs;}
    public void setClearway (int clearway) {this.clearway = clearway;}
    public void setStopway (int stopway) {this.stopway = stopway;}
}
