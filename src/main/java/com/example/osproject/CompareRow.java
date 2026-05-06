package com.example.osproject;

public class CompareRow {

    private String metric;
    private double rr;
    private double srtf;

    public CompareRow(String metric, double rr, double srtf) {
        this.metric = metric;
        this.rr = rr;
        this.srtf = srtf;
    }

    public String getMetric() {
        return metric;
    }

    public double getRr() {
        return rr;
    }

    public double getSrtf() {
        return srtf;
    }
}