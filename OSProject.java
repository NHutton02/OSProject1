
package os.project;

import java.util.ArrayList;
import java.util.List;

public class OSProject {

    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        
        //adding processes
        processes.add(new Process(1,0,7,2,3));
        processes.add(new Process(2,1,5,2,1));
        processes.add(new Process(3,2,8,3,5));
        processes.add(new Process(4,3,10,5,1));
        processes.add(new Process(5,4,2,1,1));
        
        FCFS fcfsSchedule = new FCFS(processes);
        fcfsSchedule.Scheduling();
        
    }
    
}
