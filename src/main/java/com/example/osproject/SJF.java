package com.example.osproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SJF {

    process[] processes;
    private double TotalWaitTime = 0;
    private double TotalTurnaroundTime = 0;

    public SJF(List<process> p) {
        process[] p_array = p.toArray(new process[p.size()]);
        this.processes = p_array;
    }

    public SJF(process[] processes) {
        this.processes = processes;
    }

    public void Run(process[] processes){
        int n = processes.length;

        Arrays.sort(processes , Comparator.comparingInt(p -> p.arrivaltime));


        //starting the sim
        int currentTime = 0 , completed = 0;

        while (completed < n){ //checking if all elements are completed
            int idx = -1;

            for (int i = 0; i < n; i++){
                if (processes[i].arrivaltime <= currentTime && processes[i].remainingtime > 0 && (idx == -1 || processes[i].remainingtime < processes[idx].remainingtime)){
                    idx = i;
                }
            }
            if (idx != -1){
                processes[idx].remainingtime--;
                currentTime++;

                if(processes[idx].remainingtime == 0){
                    processes[idx].completiontime = currentTime;
                    processes[idx].turnaroundtime = currentTime - processes[idx].arrivaltime;
                    processes[idx].waitingtime = processes[idx].turnaroundtime - processes[idx].bursttime;
                    completed++;
                }
            }else{
                currentTime++;
            }
        }
        double total_turnaround_time = 0 , total_waiting_time = 0;
        for(process p : processes){
            total_waiting_time += p.waitingtime;
            total_turnaround_time += p.turnaroundtime;

            // use this for the actual table ↓↓↓
            System.out.println("p" + p.pid + " Completion Time:" + p.completiontime + " Waiting Time:" + p.waitingtime + " Turnaround Time:" + p.turnaroundtime);
        }

        this.TotalWaitTime = total_waiting_time;
        this.TotalTurnaroundTime = total_turnaround_time;
    }


    public process[] getProcesses() {
        return processes;
    }

    public double getTotalWaitTime() {
        return TotalWaitTime;
    }

    public double getTotalTurnaroundTime() {
        return TotalTurnaroundTime;
    }
}
