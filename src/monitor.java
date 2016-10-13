import java.lang.ThreadGroup;
import java.lang.Thread;
import java.util.ArrayList;

public class monitor {
    public static void main(String[] args) {
      monitor m = new monitor();
        Thread processes[] = m.activeThreads();
        m.DisplayThreads(processes);



    }
    /*So far only Displays ID, Name and if thread is Daemon or not*/
    public void DisplayThreads(Thread list[]){

        int Count = list.length;


        for (int i = 0; i < Count; i++) {
            System.out.println("Thread ID:"+list[i].getId());
            System.out.println("Thread name: "+list[i].getName());
            System.out.println("The Thread Priority is: "+ list[i].getPriority());
            System.out.println("The Thread state is: " + list[i].getState());
            System.out.println("Is Thread a Daemon: "+list[i].isDaemon());

            System.out.println("");

        }
    }


    public Thread[] activeThreads(){
        int Count = Thread.activeCount();
        System.out.println("number of active Threads " + Count);

        Thread list[] = new Thread[Count];
        Thread.enumerate(list);
        return list;
    }
}