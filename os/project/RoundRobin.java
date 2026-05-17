
package os.project;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


public class RoundRobin extends Algorithm{
    List<Process> processes;
    int quantum; //need a quantum variable for RR
    
    //constructor
    public RoundRobin(List<Process> processes, int Quantum) {
        this.processes = processes;
        this.quantum = Quantum;
    }
    
    //overriding the scheduling function from algorithm class
    @Override
    public void Scheduling() {
        List<Process> waitingQueue = new ArrayList<>();
        LinkedList<Process> readyQueue = new LinkedList<>();
        List<Timeline> timeline = new ArrayList<>();
        
        int time = 0;
        int startTime;
        
        //looping through the processes
        for(Process p : processes) {
            if(time == p.at) {
                readyQueue.add(p);
            }
        }
        
        //using the same function as before just from abstract class now
        while(!allFinished(processes)){    
            
            //if the readyQueue is not empty
            if(!readyQueue.isEmpty()) {
                //remove the first one and process, it will either be added back
                //at the end of its quantum time or moved to the waitingQueue
                Process temp = readyQueue.removeFirst(); //first in readyQueue
                    
                //checking which is lower quantum or remaining time
                int lowest = Math.min(quantum, temp.remainingTime); 
                    
                //goes until it reaches the lower of the two above values
                for(int i = 0; i < lowest; i++) {

                    Iterator<Process> it = waitingQueue.iterator(); //have to keep instantiating the iterator
                    
                    while(it.hasNext()) {
                        Process p = it.next(); //make temp process equal to process in waiting queue
                        if(p.ioEndTime == time) { //if the process io end time == the global time variable
                            p.isWaitingQ = false; //change the boolean because it is no longer in waiting queue
                            readyQueue.addLast(p); //add it back to the end of the ready queue
                            it.remove(); //remove it from the waiting queue
                        }
                    }
                  
                    startTime = time;
                    temp.executedTime++;
                    temp.remainingTime--;
                    //if timeline isn't empty and its the same process 1 cycle later
                    if(!timeline.isEmpty() && 
                            timeline.get(timeline.size() - 1).pid == temp.id &&
                            timeline.get(timeline.size() - 1).end == startTime) {
                        //make the time of the process increase by 1
                        timeline.get(timeline.size() - 1).end = time + 1;
                    }
                    else {
                        //otherwise add a new object to the list that holds the initial time
                        //this process was first active
                        timeline.add(new Timeline(temp.id, startTime, time + 1));
                    }
                    
                    time++;
                                
                    //processes can come in during a RR cycle so gotta do this
                    for(Process p : processes) {
                        if(time == p.at) {
                            readyQueue.add(p);
                        }
                    }
                        
                    //if its time for waitingQueue add it to the waitingQueue and leave the loop
                    if(temp.ioTimes[1] != 0 && temp.ioTimes[0] == temp.executedTime) {
                        waitingQueue.add(temp);
                        temp.ioEndTime = time + temp.ioTimes[1];
                        temp.isWaitingQ = true;
                        break;
                    }
                    
                }
                //if the process is done
                if(temp.remainingTime <= 0) {
                    temp.completionTime = time;
                    temp.turnaroundTime = temp.completionTime - temp.at; //given calculations
                    temp.waitingTime = temp.turnaroundTime - temp.bt; //same as above
                    temp.isFinished = true; //update that the process is finished
                }
                //if it didn't go to the waitingQueue add back to the end of ReadyQueue
                else if(!temp.isWaitingQ){
                    readyQueue.addLast(temp);
                }
            }
            //increment the time while cpu is inactive and processes the waitingQueue
            else{
                Iterator<Process> it = waitingQueue.iterator(); //have to keep instantiating the iterator
                    
                while(it.hasNext()) {
                    Process p = it.next(); //make temp process equal to process in waiting queue
                    if(p.ioEndTime == time) { //if the process io end time == the global time variable
                        p.isWaitingQ = false; //change the boolean because it is no longer in waiting queue
                        readyQueue.addLast(p); //add it back to the end of the ready queue
                        it.remove(); //remove it from the waiting queue
                    }
                }
                
                //same logic as above but for the cpu idling 
                if(!timeline.isEmpty() &&
                        timeline.get(timeline.size() - 1).pid == 0 &&
                        timeline.get(timeline.size() - 1).end == time) {
                    timeline.get(timeline.size() - 1).end = time + 1;
                }
                else {
                    timeline.add(new Timeline(0, time, time + 1));
                }
                
                time++;
                
                //looping through the processes
                for(Process p : processes) {
                    if(time == p.at) {
                        readyQueue.add(p);
                    }
                }
            }
        }
        //print values from the Algorithm class
        printTable(processes);
        averages(processes);
        printGanttChart(timeline);
    }
}
