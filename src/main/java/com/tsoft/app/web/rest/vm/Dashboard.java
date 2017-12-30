package com.tsoft.app.web.rest.vm;

import java.io.Serializable;

/**
 *
 * @author tchipnangngansopa
 */
public class Dashboard implements Serializable {

    private long patientsScreenedTotal;

    private long patientsScreenedYou;

    private double patientsScreenedAvg;

    private long patientsHighRiskTotal;

    private long patientsHighRiskYou;

    private double patientsHighRiskAvg;

    private long patientsConsulteTotal;

    private long patientsConsulteYou;

    private double patientsConsulteAvg;

    public Dashboard() {
    }

    public long getPatientsScreenedTotal() {
        return patientsScreenedTotal;
    }

    public void setPatientsScreenedTotal(long patientsScreenedTotal) {
        this.patientsScreenedTotal = patientsScreenedTotal;
    }

    public long getPatientsScreenedYou() {
        return patientsScreenedYou;
    }

    public void setPatientsScreenedYou(long patientsScreenedYou) {
        this.patientsScreenedYou = patientsScreenedYou;
    }

    public double getPatientsScreenedAvg() {
        return patientsScreenedAvg;
    }

    public void setPatientsScreenedAvg(double patientsScreenedAvg) {
        this.patientsScreenedAvg = patientsScreenedAvg;
    }

    public long getPatientsHighRiskTotal() {
        return patientsHighRiskTotal;
    }

    public void setPatientsHighRiskTotal(long patientsHighRiskTotal) {
        this.patientsHighRiskTotal = patientsHighRiskTotal;
    }

    public long getPatientsHighRiskYou() {
        return patientsHighRiskYou;
    }

    public void setPatientsHighRiskYou(long patientsHighRiskYou) {
        this.patientsHighRiskYou = patientsHighRiskYou;
    }

    public double getPatientsHighRiskAvg() {
        return patientsHighRiskAvg;
    }

    public void setPatientsHighRiskAvg(double patientsHighRiskAvg) {
        this.patientsHighRiskAvg = patientsHighRiskAvg;
    }

    public long getPatientsConsulteTotal() {
        return patientsConsulteTotal;
    }

    public void setPatientsConsulteTotal(long patientsConsulteTotal) {
        this.patientsConsulteTotal = patientsConsulteTotal;
    }

    public long getPatientsConsulteYou() {
        return patientsConsulteYou;
    }

    public void setPatientsConsulteYou(long patientsConsulteYou) {
        this.patientsConsulteYou = patientsConsulteYou;
    }

    public double getPatientsConsulteAvg() {
        return patientsConsulteAvg;
    }

    public void setPatientsConsulteAvg(double patientsConsulteAvg) {
        this.patientsConsulteAvg = patientsConsulteAvg;
    }
}
