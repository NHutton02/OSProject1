
package os.project;

import java.util.List;

//new abstract file the encapsulates everything
public abstract class Algorithm {
    public abstract void Scheduling(); //override when making a new algorithm
    
    public boolean allFinished(List<Process> processes) { //same all finished just moved here
        boolean returnMe = true; //declare the variable
        for(Process p : processes) { //loop
            if(!p.isFinished) { //if any aren't finished
                returnMe = false; //update
            }
        }
        return returnMe;
    }
    
    //this print table function just prints out the numbers in a table
    public void printTable(List<Process> processes) {
        System.out.println("pID\tat\tbt\tct\tTAT\twt");
        
        //looping through all processes and printing them
        for(Process p : processes) {
            System.out.println(p.id + "\t" + p.at + "\t" + p.bt + "\t" + p.completionTime + 
                    "\t" + p.turnaroundTime + "\t" + p.waitingTime);
        }
    }
    
    //same as above but instead calculating the averages for everything
    public void averages(List<Process> processes) {
        double avgWaitingTime = 0;
        double avgTurnAroundTime = 0;
        
        for(Process p : processes) {
            avgWaitingTime += p.waitingTime;
            avgTurnAroundTime += p.turnaroundTime;
        }
        
        System.out.println("Average waiting time: " + (avgWaitingTime / processes.size()));
        System.out.println("Average Turnaround time: " + (avgTurnAroundTime / processes.size()));
    }
}
