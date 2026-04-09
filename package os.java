package os.project;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PriorityScheduling {
    List<Process> processes;

    public PriorityScheduling(List<Process> processes) {
        this.processes = processes;
    }

    public void Scheduling() {
        List<Process> waitingQueue = new ArrayList<>();
        List<Process> readyQueue = new ArrayList<>();

        int time = 0;

        while (!allFinished(processes)) {

            // add newly arrived processes to ready queue
            for (Process p : processes) {
                if (p.at == time) {
                    readyQueue.add(p);
                }
            }

            // move finished I/O processes back to ready queue
            Iterator<Process> it = waitingQueue.iterator();
            while (it.hasNext()) {
                Process p = it.next();
                if (p.ioEndTime == time) {
                    p.isWaitingQ = false;
                    readyQueue.add(p);
                    it.remove();
                }
            }

            if (!readyQueue.isEmpty()) {
                Process current = getHighestPriorityProcess(readyQueue);

                // run selected process until it either:
                // 1. requests I/O
                // 2. finishes
                while (true) {
                    current.executedTime++;
                    current.remainingTIme--;

                    // process requests I/O
                    if (current.ioTimes != null
                            && current.ioTimes[0] > 0
                            && current.executedTime == current.ioTimes[0]) {
                        current.isWaitingQ = true;
                        current.ioEndTime = (time + 1) + current.ioTimes[1];
                        waitingQueue.add(current);
                        readyQueue.remove(current);
                        time++;
                        break;
                    }

                    // process finishes
                    if (current.remainingTIme <= 0) {
                        current.completionTime = time + 1;
                        current.turnaroundTime = current.completionTime - current.at;
                        current.waitingTime = current.turnaroundTime - current.bt;
                        current.isFinished = true;
                        readyQueue.remove(current);
                        time++;
                        break;
                    }

                    time++;

                    // while process is running, new arrivals may come in
                    for (Process p : processes) {
                        if (p.at == time) {
                            readyQueue.add(p);
                        }
                    }

                    // while process is running, some waiting processes may return from I/O
                    Iterator<Process> innerIt = waitingQueue.iterator();
                    while (innerIt.hasNext()) {
                        Process p = innerIt.next();
                        if (p.ioEndTime == time) {
                            p.isWaitingQ = false;
                            readyQueue.add(p);
                            innerIt.remove();
                        }
                    }
                }

            } else {
                // CPU idle
                time++;
            }
        }

        printResults();
    }

    public Process getHighestPriorityProcess(List<Process> readyQueue) {
        Process highest = readyQueue.get(0);

        for (Process p : readyQueue) {
            // smaller priority number = higher priority
            if (p.priority < highest.priority) {
                highest = p;
            } 
            // tie-breaker: earlier arrival time
            else if (p.priority == highest.priority && p.at < highest.at) {
                highest = p;
            }
            // second tie-breaker: smaller process id
            else if (p.priority == highest.priority && p.at == highest.at && p.id < highest.id) {
                highest = p;
            }
        }

        return highest;
    }

    public boolean allFinished(List<Process> processes) {
        for (Process p : processes) {
            if (!p.isFinished) {
                return false;
            }
        }
        return true;
    }

    public void printResults() {
        System.out.println("Priority Scheduling Results:");
        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT");

        double totalWT = 0;
        double totalTAT = 0;

        for (Process p : processes) {
            System.out.println(
                p.id + "\t" +
                p.at + "\t" +
                p.bt + "\t" +
                p.priority + "\t" +
                p.completionTime + "\t" +
                p.turnaroundTime + "\t" +
                p.waitingTime
            );

            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;
        }

        System.out.printf("Average Waiting Time: %.2f%n", totalWT / processes.size());
        System.out.printf("Average Turnaround Time: %.2f%n", totalTAT / processes.size());
    }
}
