package com.example.osproject;

    public class process {
        int pid;
        int arrivaltime;
        int bursttime;
        int remainingtime;
        int waitingtime;
        int turnaroundtime;
        int completiontime;
        int responsetime;
        boolean started;
        boolean completed;


    public process(int pid ,int arrivaltime , int bursttime) {
        this.pid = pid;
        this.arrivaltime = arrivaltime;
        this.bursttime = bursttime;
        this.remainingtime = bursttime;
    }

        public process copy() {
            return new process(this.pid, this.arrivaltime, this.bursttime);
        }

    public int getBursttime() {
        return bursttime;
    }

    public int getArrivaltime() {
        return arrivaltime;
    }

    public int getWaitingtime() {
        return waitingtime;
    }}
