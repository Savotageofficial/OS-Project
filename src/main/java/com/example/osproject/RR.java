package com.example.osproject;

import java.util.*;

public class RR {

    private double TotalWaitTime = 0;
    private double TotalTurnaroundTime = 0;
    private double TotalResponseTime = 0;
    static List <process> executionOrder = new ArrayList<>();

    public void Run(process[] processes, int q , HashMap<Integer , Queue> RQs) {
        //Reset all the variables for a fresh start
        TotalWaitTime = 0;
        TotalResponseTime = 0;
        TotalTurnaroundTime = 0;
        int n = processes.length;

        // See What java gonna compare between objects
        Arrays.sort(processes, Comparator.comparingInt(p -> p.arrivaltime));

        int time = 0;
        int completed = 0;

        Queue<process> ready = new LinkedList<>();

        // check what processes arrived , aka what processes have arrival time = time
        // and add them to arrived
        for (int i = 0; i < n; i++) {
            if (processes[i].arrivaltime <= time) {
                ready.add(processes[i]);
            }
        }
        RQs.put(time , ready);

        while (completed < n) {
            while (!(ready.isEmpty())) {

                process p = ready.poll();
                // record first response time if process hasn't started yet
                if (!p.started) {
                    p.responsetime = time - p.arrivaltime;
                    p.started = true;
                }
                // Checking the ready
                if (p.remainingtime > q) {
                    // execute the process completely in its time quantum
                    p.remainingtime -= q;
                    executionOrder.add(p);
                    time += q;
                    // check what processes arrived , aka what processes have arrival time = time
                    // and add them to arrived
                    for (int i = 0; i < n; i++) {
                        if (((processes[i].arrivaltime > (time - q)) && (processes[i].arrivaltime <= time))
                                && (!ready.contains(processes[i])) && (!processes[i].completed)) {
                            ready.add(processes[i]);
                        }
                    }
                    // insert p at the end of the queue
                    ready.add(p);
                    RQs.put(time , ready);
                } else if (p.remainingtime <= q) {
                    // execute p for its remaining time
                    time += p.remainingtime;
                    p.completiontime = time;
                    p.turnaroundtime = time - p.arrivaltime;
                    p.waitingtime = p.turnaroundtime - p.bursttime;
                    p.remainingtime = 0;
                    p.completed = true;
                    executionOrder.add(p);
                    completed++;

                    // check what processes arrived , aka what processes have arrival time = time
                    // and add them to arrived
                    for (int i = 0; i < n; i++) {
                        if (((processes[i].arrivaltime > (time - q)) && (processes[i].arrivaltime <= time))
                                && (!ready.contains(processes[i])) && (!processes[i].completed)) {
                            ready.add(processes[i]);
                        }
                    }
                    RQs.put(time , ready);

                }
            }
            if (completed < n) {
                time = processes[completed].arrivaltime;

                for (int i = 0; i < n; i++) {
                    if (processes[i].arrivaltime <= time && !processes[i].completed && !ready.contains(processes[i])) {
                        ready.add(processes[i]);
                    }
                }
                RQs.put(time , ready);

            }
        }
        for (process p : processes) {
            System.out.println("p" + p.pid + " Completion Time:" + p.completiontime + " Waiting Time:" + p.waitingtime
                    + " Turnaround Time:" + p.turnaroundtime + " Response time:" + p.responsetime);
            TotalTurnaroundTime += p.turnaroundtime;
            TotalWaitTime += p.waitingtime;
            TotalResponseTime += p.responsetime;

        }

    }

}
