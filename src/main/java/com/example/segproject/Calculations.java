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
    private int slopeCalculation;

    public int stopway;
    public int clearway;

    private int newTORA;
    private int newASDA;
    private int newTODA;
    private int newLDA;

    private String TORACalc;
    private String ASDACalc;
    private String TODACalc;
    private String LDACalc;
    private String SlopeCalc;

    public Calculations() {}

    public Calculations(String name, String status, int tora, int asda, int toda, int lda, int obstacleHeight, int obstacleDistanceFromThreshold, String obstacleDirection, int obstacleDistanceFromCenter, int displacementThreshold, int resa, int stripEnd, int blastProtection) {
        this.runwayName = name;
        this.status = status;
        this.tora = tora;
        this.asda = asda;
        this.toda = toda;
        this.lda = lda;
        this.obstacleHeight = obstacleHeight;
        this.obstacleDistanceFromThreshold = obstacleDistanceFromThreshold;
        this.obstacleDirection = obstacleDirection;
        this.obstacleDistanceFromCenter = obstacleDistanceFromCenter;
        this.displacementThreshold = displacementThreshold;
        this.resa = resa;
        this.stripEnd = stripEnd;
        this.blastProtection = blastProtection;
        runCalculations();
    }

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

        this.stopway = this.asda - this.tora;
        this.clearway = this.toda - this.tora;
    }

    public void takeoffTowards(int obstacleHeight, int displacementThreshold, int obstacleDistanceFromThreshold, int stripEnd) {
        slopeCalculation = obstacleHeight * this.tocs;
        this.SlopeCalc = "Slope Calculation: " + obstacleHeight + " * " + this.tocs + " = " + slopeCalculation;

        if (this.resa > slopeCalculation) { // this case shouldn't happen until obstacle length is known
            this.newTORA = displacementThreshold + obstacleDistanceFromThreshold - this.resa - stripEnd;
            this.TORACalc = "TORA: " + displacementThreshold + " + " + obstacleDistanceFromThreshold + " - " + this.resa + " - " + stripEnd + " = " + this.newTORA;
        } else {
            this.newTORA = displacementThreshold + obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
            this.TORACalc = "TORA: " + displacementThreshold + " + " + obstacleDistanceFromThreshold + " - " + slopeCalculation + " - " + stripEnd + " = " + this.newTORA;
        }

        this.newTODA = this.newTORA;
        this.newASDA = this.newTORA;

        this.TODACalc = "TODA: " + this.newTODA + " = " + this.newTORA;
        this.ASDACalc = "ASDA: " + this.newASDA + " = " + this.newTORA;
    }

    public void landingTowards(int obstacleDistanceFromThreshold, int resa, int stripEnd) {
        this.newLDA = obstacleDistanceFromThreshold - resa - stripEnd;
        this.LDACalc = "LDA: " + obstacleDistanceFromThreshold + " - " + resa + " - " + stripEnd + " = " + this.newLDA;
    }

    public void takeoffAway(int blastProtection, int obstacleDistanceFromThreshold) {
        this.newTORA = this.tora - blastProtection - obstacleDistanceFromThreshold;
        this.newTODA = this.toda - blastProtection - obstacleDistanceFromThreshold;
        this.newASDA = this.asda - blastProtection - obstacleDistanceFromThreshold;

        this.TORACalc = "TORA: " + this.tora + " - " + blastProtection + " - " + obstacleDistanceFromThreshold + " = " + this.newTORA;
        this.TODACalc = "TODA: " + this.toda + " - " + blastProtection + " - " + obstacleDistanceFromThreshold + " = " + this.newTODA;
        this.ASDACalc = "ASDA: " + this.asda + " - " + blastProtection + " - " + obstacleDistanceFromThreshold + " = " + this.newASDA;
    }

    public void landingOver(int obstacleHeight, int obstacleDistanceFromThreshold, int stripEnd){
        slopeCalculation = obstacleHeight * this.als;
        this.SlopeCalc = "Slope Calculation: " + obstacleHeight + " * " + this.als + " = " + slopeCalculation;

        if (this.resa > slopeCalculation) { // this case shouldn't happen until obstacle length is known
            if (this.resa + stripEnd < this.blastProtection) {
                this.newLDA = this.lda - obstacleDistanceFromThreshold - this.resa - stripEnd;
                this.LDACalc = "LDA: " + this.lda + " - " + obstacleDistanceFromThreshold + " - " + this.resa + " - " + stripEnd + " = " + this.newLDA;
            } else {
                this.newLDA = this.lda - obstacleDistanceFromThreshold - this.blastProtection;
                this.LDACalc = "LDA: " + this.lda + " - " + obstacleDistanceFromThreshold + " - " + this.blastProtection + " = " + this.newLDA;
            }

        } else {
            if (slopeCalculation + stripEnd < this.blastProtection) {
                this.newLDA = this.lda - obstacleDistanceFromThreshold - slopeCalculation - stripEnd;
                this.LDACalc = "LDA: " + this.lda + " - " + obstacleDistanceFromThreshold + " - " + slopeCalculation + " - " + stripEnd + " = " + this.newLDA;
            } else {
                this.newLDA = this.lda - obstacleDistanceFromThreshold - this.blastProtection;
                this.LDACalc = "LDA: " + this.lda + " - " + obstacleDistanceFromThreshold + " - " + this.blastProtection + " = " + this.newLDA;
            }
        }
    }

    public String getRunwayName() {return runwayName;}
    public int getTORA() {return this.tora;}
    public int getRESA() {return this.resa;}
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
    public int getSlopeValue() {return this.slopeCalculation;}
    public String getSlopeCalc() {return this.SlopeCalc;}
    public String getTORACalc() {return this.TORACalc;}
    public String getTODACalc() {return this.TODACalc;}
    public String getASDACalc() {return this.ASDACalc;}
    public String getLDACalc() {return this.LDACalc;}
    public int getStripEnd() {return this.stripEnd;}
	public int getBlastProtection() {return this.blastProtection;}
	public String getStatus() {return this.status;}

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
