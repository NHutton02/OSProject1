package os.project;

import java.util.ArrayList;
import java.util.List;

public class OSProject {

    public static void main(String[] args) {
        List<Process> fcfsProcesses = new ArrayList<>();
        List<Process> rrProcesses = new ArrayList<>();
        List<Process> priorityProcesses = new ArrayList<>();
        
        // id, arrival time, burst time, priority, io start, io duration
               fcfsProcesses.add(new Process(1, 0, 7, 2, 3, 2));
        fcfsProcesses.add(new Process(2, 1, 4, 1, 0, 0));
        fcfsProcesses.add(new Process(3, 2, 9, 4, 4, 3));
        fcfsProcesses.add(new Process(4, 3, 5, 2, 0, 0));
        fcfsProcesses.add(new Process(5, 4, 3, 1, 1, 2));
        fcfsProcesses.add(new Process(6, 5, 8, 5, 0, 0));
        fcfsProcesses.add(new Process(7, 6, 2, 3, 0, 0));
        fcfsProcesses.add(new Process(8, 7, 6, 2, 2, 1));
        fcfsProcesses.add(new Process(9, 8, 10, 4, 5, 2));
        fcfsProcesses.add(new Process(10, 9, 4, 1, 0, 0));
        fcfsProcesses.add(new Process(11, 10, 7, 3, 3, 2));
        fcfsProcesses.add(new Process(12, 11, 5, 2, 0, 0));
        fcfsProcesses.add(new Process(13, 12, 9, 5, 4, 1));
        fcfsProcesses.add(new Process(14, 13, 3, 1, 0, 0));
        fcfsProcesses.add(new Process(15, 14, 6, 2, 2, 2));
        fcfsProcesses.add(new Process(16, 15, 8, 4, 0, 0));
        fcfsProcesses.add(new Process(17, 16, 2, 1, 0, 0));
        fcfsProcesses.add(new Process(18, 17, 11, 5, 6, 3));
        fcfsProcesses.add(new Process(19, 18, 4, 2, 0, 0));
        fcfsProcesses.add(new Process(20, 19, 7, 3, 3, 1));

        rrProcesses.add(new Process(1, 0, 7, 2, 3, 2));
        rrProcesses.add(new Process(2, 1, 4, 1, 0, 0));
        rrProcesses.add(new Process(3, 2, 9, 4, 4, 3));
        rrProcesses.add(new Process(4, 3, 5, 2, 0, 0));
        rrProcesses.add(new Process(5, 4, 3, 1, 1, 2));
        rrProcesses.add(new Process(6, 5, 8, 5, 0, 0));
        rrProcesses.add(new Process(7, 6, 2, 3, 0, 0));
        rrProcesses.add(new Process(8, 7, 6, 2, 2, 1));
        rrProcesses.add(new Process(9, 8, 10, 4, 5, 2));
        rrProcesses.add(new Process(10, 9, 4, 1, 0, 0));
        rrProcesses.add(new Process(11, 10, 7, 3, 3, 2));
        rrProcesses.add(new Process(12, 11, 5, 2, 0, 0));
        rrProcesses.add(new Process(13, 12, 9, 5, 4, 1));
        rrProcesses.add(new Process(14, 13, 3, 1, 0, 0));
        rrProcesses.add(new Process(15, 14, 6, 2, 2, 2));
        rrProcesses.add(new Process(16, 15, 8, 4, 0, 0));
        rrProcesses.add(new Process(17, 16, 2, 1, 0, 0));
        rrProcesses.add(new Process(18, 17, 11, 5, 6, 3));
        rrProcesses.add(new Process(19, 18, 4, 2, 0, 0));
        rrProcesses.add(new Process(20, 19, 7, 3, 3, 1));

        priorityProcesses.add(new Process(1, 0, 7, 2, 3, 2));
        priorityProcesses.add(new Process(2, 1, 4, 1, 0, 0));
        priorityProcesses.add(new Process(3, 2, 9, 4, 4, 3));
        priorityProcesses.add(new Process(4, 3, 5, 2, 0, 0));
        priorityProcesses.add(new Process(5, 4, 3, 1, 1, 2));
        priorityProcesses.add(new Process(6, 5, 8, 5, 0, 0));
        priorityProcesses.add(new Process(7, 6, 2, 3, 0, 0));
        priorityProcesses.add(new Process(8, 7, 6, 2, 2, 1));
        priorityProcesses.add(new Process(9, 8, 10, 4, 5, 2));
        priorityProcesses.add(new Process(10, 9, 4, 1, 0, 0));
        priorityProcesses.add(new Process(11, 10, 7, 3, 3, 2));
        priorityProcesses.add(new Process(12, 11, 5, 2, 0, 0));
        priorityProcesses.add(new Process(13, 12, 9, 5, 4, 1));
        priorityProcesses.add(new Process(14, 13, 3, 1, 0, 0));
        priorityProcesses.add(new Process(15, 14, 6, 2, 2, 2));
        priorityProcesses.add(new Process(16, 15, 8, 4, 0, 0));
        priorityProcesses.add(new Process(17, 16, 2, 1, 0, 0));
        priorityProcesses.add(new Process(18, 17, 11, 5, 6, 3));
        priorityProcesses.add(new Process(19, 18, 4, 2, 0, 0));
        priorityProcesses.add(new Process(20, 19, 7, 3, 3, 1));
        
        System.out.println("-----FCFS-----");
        Algorithm schedule = new FCFS(fcfsProcesses);
        schedule.Scheduling();
        
        System.out.println("-----RoundRobin-----");
        schedule = new RoundRobin(rrProcesses, 4);
        schedule.Scheduling();
        
        System.out.println("-----Priority-----");
        schedule = new PriorityScheduling(priorityProcesses);
        schedule.Scheduling();
    }
}