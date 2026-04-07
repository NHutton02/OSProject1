
package os.project;

public class Process {
    int id;
    int at; //arrival time
    int bt; //burst time
    int waitingTime = 0;
    int turnaroundTime = 0;
    int completionTime = 0;
    int ioTimes[] = new int[2]; //has io start time and io end time
    boolean isWaitingQ = false; //whether it is in the waiting queue or not
    int executedTime = 0; //how much time the process has actually executed
    int remainingTIme; //how much time the process has left
    int ioEndTime; //io end time based on the whole scheduling time
    boolean isFinished = false; //whether the process is finished or not
    
    public Process(int id, int at, int bt, int iost, int ioet) {
        this.id = id;
        this.at = at;
        this.bt = bt;
        this.remainingTIme = bt; //burst time and remaining time are equal but burst time shouldn't change
        this.ioTimes[0] = iost;
        this.ioTimes[1] = ioet;
    }
}
