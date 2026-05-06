package com.example.osproject;

import java.util.*;

public class AlgoEval {

    private process[] rrProcesses;
    private process[] srtfProcesses;
    private int quantum;

    // Averages for RR
    private double rrAvgWT, rrAvgTAT, rrAvgRT;
    // Averages for SRTF
    private double srtfAvgWT, srtfAvgTAT, srtfAvgRT;

    public AlgoEval(List<process> originalProcesses, int quantum) {
        this.quantum = quantum;

        rrProcesses = copyList(originalProcesses);
        srtfProcesses = copyList(originalProcesses);
        HashMap<Integer, Queue> rqs = new HashMap<>();
        rqs.put(0, new LinkedList<process>());

        // Run both algorithms using the existing classes
        RR RR = new RR();
        RR.RR(rrProcesses, quantum,rqs);
        SJF sjf = new SJF(srtfProcesses);
        sjf.Run(srtfProcesses);

        // Calculate averages
        rrAvgWT = avgWT(rrProcesses);
        rrAvgTAT = avgTAT(rrProcesses);
        rrAvgRT = avgRT(rrProcesses);

        srtfAvgWT = avgWT(srtfProcesses);
        srtfAvgTAT = avgTAT(srtfProcesses);
        srtfAvgRT = avgRT(srtfProcesses);
    }

    // Averages calculators

    private double avgWT(process[] procs) {
        double sum = 0;
        for (process p : procs)
            sum += p.waitingtime;
        return sum / procs.length;
    }

    private double avgTAT(process[] procs) {
        double sum = 0;
        for (process p : procs)
            sum += p.turnaroundtime;
        return sum / procs.length;
    }

    private double avgRT(process[] procs) {
        double sum = 0;
        for (process p : procs)
            sum += p.responsetime;
        return sum / procs.length;
    }

    public String getComparisonSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Metric |  RR  | SRTF" + "\n");
        sb.append("Avg Waiting Time= " + rrAvgWT + " | " + srtfAvgWT + "\n");
        sb.append("Avg Turnaround Time= " + rrAvgTAT + " | " + srtfAvgTAT + "\n");
        sb.append("Avg Response Time= " + rrAvgRT + " | " + srtfAvgRT + "\n");
        return sb.toString();
    }

    public String getConclusion() {
        StringBuilder sb = new StringBuilder();

        sb.append(rrAvgWT < srtfAvgWT ? "Lower Avg WT:  Round Robin\n"
                : srtfAvgWT < rrAvgWT ? "Lower Avg WT:  SRTF\n"
                : "Avg WT:        Tied\n");

        sb.append(rrAvgTAT < srtfAvgTAT ? "Lower Avg TAT: Round Robin\n"
                : srtfAvgTAT < rrAvgTAT ? "Lower Avg TAT: SRTF\n"
                : "Avg TAT:       Tied\n");

        sb.append(rrAvgRT < srtfAvgRT ? "Lower Avg RT:  Round Robin\n"
                : srtfAvgRT < rrAvgRT ? "Lower Avg RT:  SRTF\n"
                : "Avg RT:        Tied\n");

        if ((rrAvgWT + rrAvgTAT + rrAvgRT) < (srtfAvgWT + srtfAvgTAT + srtfAvgRT)) {
            sb.append("RR is better.\n");
        } else if ((rrAvgWT + rrAvgTAT + rrAvgRT) > (srtfAvgWT + srtfAvgTAT + srtfAvgRT)) {
            sb.append("SRTF is better.\n");
        } else {
            sb.append("Both are equally good.\n");
        }

        sb.append("Quantum = ").append(quantum)
                .append(" affects RR context-switch overhead.\n");
        return sb.toString();
    }

    private process[] copyList(List<process> list) {
        process[] arr = new process[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i).copy();
        }
        return arr;
    }


    public process[] getRrProcesses() {
        return rrProcesses;
    }

    public process[] getSrtfProcesses() {
        return srtfProcesses;
    }

    public double getRrAvgWT() {
        return rrAvgWT;
    }

    public double getRrAvgTAT() {
        return rrAvgTAT;
    }

    public double getRrAvgRT() {
        return rrAvgRT;
    }

    public double getSrtfAvgWT() {
        return srtfAvgWT;
    }

    public double getSrtfAvgTAT() {
        return srtfAvgTAT;
    }

    public double getSrtfAvgRT() {
        return srtfAvgRT;
    }

}

