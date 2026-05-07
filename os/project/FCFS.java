
package os.project;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class FCFS extends Algorithm{
    List<Process> processes;
    
    public FCFS(List<Process> Processes) {
        this.processes = Processes; //initialize with the list from main
    }
    
    @Override
    public void Scheduling() {
        processes.sort(Comparator.comparingInt(p -> p.at)); //sort by arrival time
        List<Process> waitingQueue = new ArrayList<>();
        LinkedList<Process> readyQueue = new LinkedList<>();
        
        int time = 0; //time for the whole scheduling process
        
        while(!allFinished(processes)){ //while processes aren't finished keep running
            
            for(Process p : processes) { //loop through all processes
                if(p.at == time) { //if they arrive at this time
                    readyQueue.add(p); //add to ready queue
                }
            }
                    
            Iterator<Process> it = waitingQueue.iterator(); //have to keep instantiating the iterator
                    
            while(it.hasNext()) {
                Process p = it.next(); //make temp process equal to process in waiting queue
                if(p.ioEndTime == time) { //if the process io end time == the global time variable
                    p.isWaitingQ = false; //change the boolean because it is no longer in waiting queue
                    readyQueue.addLast(p); //add it back to the end of the ready queue
                    it.remove(); //remove it from the waiting queue
                }
            }
            
            if(!readyQueue.isEmpty()) { //if the ready queue isn't empty
                Process temp = readyQueue.getFirst(); //temp process
                
                temp.executedTime++; //increment the time by 1
                temp.remainingTime--; //decrement the time left by 1
                
                //if the process has io times and if the start time equals the time that the process has
                //executued then proceed
                if(temp.ioTimes != null && temp.executedTime == temp.ioTimes[0]) {
                    temp.isWaitingQ = true; //its now in waiting queue so flip the bool
                    //io end time is current global time + the end time for io
                    temp.ioEndTime = (time + 1) + temp.ioTimes[1]; //time updates at the end so add 1
                    waitingQueue.add(temp); //add to waiting queue
                    readyQueue.removeFirst(); //remove from ready queue
                } 
                else if(temp.remainingTime <= 0){ //if the process is done
                    temp.completionTime = time + 1; //add 1 becuase time updates at the end
                    temp.turnaroundTime = temp.completionTime - temp.at; //given calculations
                    temp.waitingTime = temp.turnaroundTime - temp.bt; //same as above
                    temp.isFinished = true; //update that the process is finished
                    readyQueue.removeFirst(); //remove from the ready queue
                }
            }
            time++; //update time
        }
        printTable(processes);
        averages(processes);

    }
    
}
