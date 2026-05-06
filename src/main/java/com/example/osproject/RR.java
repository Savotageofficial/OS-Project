package com.example.osproject;

import java.util.*;

public class RR {
    int Processcount, quantum;
    int[] processes;
    private static List <process> executionOrder = new ArrayList<>();
    private static HashMap<Integer, Integer> executionDurations = new HashMap<>();
    private static HashMap<Integer, process> executionTimeline = new HashMap<>();


    public static void RR(process[] processes, int q,HashMap<Integer, Queue> RQs) {
        int n = processes.length;
        executionOrder.clear();
        executionDurations.clear();
        executionTimeline.clear();

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

        while (completed < n) {
            while (!(ready.isEmpty())) {

                process p = ready.poll();

                if (!p.started) {
                    p.responsetime = time - p.arrivaltime;
                    p.started = true;
                }
                // Checking the ready
                if (p.remainingtime > q) {
                    // execute the process completely in its time quantum
                    p.remainingtime -= q;
                    executionOrder.add(p);
                    executionTimeline.put(time, p);
                    executionDurations.put(time, q);
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
                } else if (p.remainingtime <= q) {
                    // execute p for its remaining time
                    executionOrder.add(p);
                    executionTimeline.put(time, p);
                    executionDurations.put(time, p.remainingtime);
                    time += p.remainingtime;
                    p.completiontime = time;
                    p.turnaroundtime = time - p.arrivaltime;
                    p.waitingtime = p.turnaroundtime - p.bursttime;
                    p.remainingtime = 0;
                    p.completed = true;
                    completed++;

                    // check what processes arrived , aka what processes have arrival time = time
                    // and add them to arrived
                    for (int i = 0; i < n; i++) {
                        if (((processes[i].arrivaltime > (time - q)) && (processes[i].arrivaltime <= time))
                                && (!ready.contains(processes[i])) && (!processes[i].completed)) {
                            ready.add(processes[i]);
                        }
                    }

                }
            }
            if (completed < n) {
                time = processes[completed].arrivaltime;

                for (int i = 0; i < n; i++) {
                    if (processes[i].arrivaltime <= time && !processes[i].completed && !ready.contains(processes[i])) {
                        ready.add(processes[i]);
                    }
                }

            }
        }



    }
    public HashMap<Integer, Integer> getExecutionDurations() {
        return executionDurations;
    }

    public HashMap<Integer, process> getExecutionTimeline() {
        return executionTimeline;
    }
    public List<process> getExecutionOrder() {
        return executionOrder;
    }

}