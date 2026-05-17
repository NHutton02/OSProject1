
package os.project;

//new class for timeline objects to create the Gantt Chart
public class Timeline {
    int pid;
    int start;
    int end;
    
    public Timeline(int pid, int start, int end) {
        this.pid = pid;
        this.start = start;
        this.end = end;
    }
}
