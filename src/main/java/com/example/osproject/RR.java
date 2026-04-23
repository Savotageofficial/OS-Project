package com.example.osproject;
import java.util.*;


public class RR {
    int Processcount,quantum;
    int [] processes;



    public static void RR(process[] processes , int q){
        int n = processes.length;

        // See What java gonna compare between objects
        Arrays.sort(processes , Comparator.comparingInt(p -> p.arrivaltime));


        int time = 0;
        int completed = 0;

        Queue<process> ready = new LinkedList<>();

        //check what processes arrived , aka what processes have arrival time = time and add them to arrived
        for(int i = 0; i < n; i++){
            if (processes[i].arrivaltime <= time){
                ready.add(processes[i]);
            }
        }


    }




}

