package com.example.osproject;

import java.util.List;

public class ProcessController {
    private List<process> processes;
    private int quantum;

    public void setProcesslist(List<process> processes) {

        this.processes = processes;
    }
    public void setquantum(int quantum) {

        this.quantum = quantum;
    }


}
