package com.example.osproject.Algorithms;

import com.example.osproject.Models.process;

import java.util.*;

public class SJF {

    process[] processes;
    private double TotalWaitTime = 0;
    private double TotalTurnaroundTime = 0;
    private List <process> executionOrder = new ArrayList<>();
    private HashMap<Integer , process> executions = new HashMap<>();
    public SJF(List<process> p) {
        process[] p_array = p.toArray(new process[p.size()]);
        this.processes = p_array;
    }

    public SJF(process[] processes) {
        this.processes = processes;
    }
    public void reset(){
        this.TotalWaitTime = 0;
        this.TotalTurnaroundTime = 0;
        //idea : remove pointer/reference from the old arraylist and let the garbage collector do its thing
        this.executionOrder = null;
        //then reassign the reference to a new arraylist
        this.executionOrder = new ArrayList<>();
        this.executions = null;
    }

    public void Run(){
        for (process p : processes) {
            p.reset();
        }


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
                // Track response time (first time on CPU)
                if (!processes[idx].started) {
                    processes[idx].responsetime = currentTime - processes[idx].arrivaltime;
                    processes[idx].started = true;
                    executionOrder.add(processes[idx]);
                }

                processes[idx].remainingtime--;
                executions.put(currentTime , processes[idx]);
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

    public HashMap<Integer, process> getExecutions() {
        LinkedHashMap<Integer, process> result = new LinkedHashMap<>();
        process lastProcess = null;

        List<Integer> sortedKeys = new ArrayList<>(executions.keySet());
        Collections.sort(sortedKeys);

        for (int key : sortedKeys) {
            process current = executions.get(key);
            if (current != lastProcess) {
                result.put(key, current);
                lastProcess = current;
            }
        }

        return result; // added (replaced old return statement)
    }
}
