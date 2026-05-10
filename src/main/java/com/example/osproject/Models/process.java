package com.example.osproject.Models;

public class process {
    public int pid;
    public int arrivaltime;
    public int bursttime;
    public int remainingtime;
    public int waitingtime;
    public int turnaroundtime;
    public int completiontime;
    public int responsetime;
    public boolean started;
    public boolean completed;

    public process(int pid, int arrivaltime, int bursttime) {
        this.pid = pid;
        this.arrivaltime = arrivaltime;
        this.bursttime = bursttime;
        this.remainingtime = bursttime;
        this.responsetime = -1;
        this.started = false;
        this.completed = false;
    }

    public void reset() {
        remainingtime = bursttime;
        waitingtime = 0;
        turnaroundtime = 0;
        completiontime = 0;
        responsetime = -1;
        started = false;
        completed = false;
    }

    public process copy() {
        return new process(this.pid, this.arrivaltime, this.bursttime);
    }

    public int pid(){
        return pid;
    }

    public int getPid() {
        return pid;
    }
    public int getArrivaltime(){
        return arrivaltime;

    }
    public int getBursttime(){
        return bursttime;
    }
    public int getRemainingtime(){
        return remainingtime;
    }
    public int getWaitingtime(){
        return waitingtime;
    }
    public int getTurnaroundtime(){
        return turnaroundtime;
    }
    public int getResponsetime(){
        return responsetime;
    }
    public int getCompletiontime(){
        return completiontime;
    }
    public boolean isCompleted(){
        return completed;
    }


}