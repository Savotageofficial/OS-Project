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
}
