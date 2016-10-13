import java.lang.ThreadGroup;
import java.lang.Thread;
import java.util.ArrayList;

public class monitor {


    public static void main(String[] args) {
        monitor m = new monitor();
        ThreadGroup root = m.getRoot();
        ThreadGroup[] groups = m.getAllGroups();

        System.out.println("All Active Groups");
        System.out.println(m.getRoot().getName());
        m.printGroup(groups);

        System.out.println("");
        System.out.println("ALL Active Threads :");
        m.getAllThreads(groups);



    }


    public void getAllThreads(ThreadGroup[] g){
        int NoGroups=g.length;
        for(int i =0; i<NoGroups; i++){
           Thread[] t = threadsInGroup(g[i]);
            int threadsInGroup=t.length;
            for(int a=0; a<threadsInGroup; a++){
                System.out.println(t[a].getName());
            }
        }

    }
    public Thread[] threadsInGroup(ThreadGroup g){
        int threadcount = g.activeCount();
        Thread[] threads = new Thread[threadcount];
        g.enumerate(threads);
        return(threads);
    }


    public ThreadGroup getRoot(){
        if( Thread.currentThread().getThreadGroup().getParent() == null){
            return Thread.currentThread().getThreadGroup().getParent() ;// if the current thread doesn't have a parent the current thread group is the root
        }
        ThreadGroup currentGroup = Thread.currentThread().getThreadGroup(); // this is getting the current group of a thread
        ThreadGroup ParentGroup;
        while ((ParentGroup = currentGroup.getParent())!=null){
            currentGroup=ParentGroup;
        }
        return currentGroup;
    }

    public ThreadGroup[] getAllGroups(){
        ThreadGroup root = getRoot();
        int numberOfGroups = root.activeGroupCount();
        ThreadGroup[] groups = new ThreadGroup[numberOfGroups];

        root.enumerate(groups,true);

        return groups; // doesnt include the root
    }
    public void printGroup(ThreadGroup[] g){
        int count  = g.length;
        for(int i=0; i<count;i++) {
            System.out.println(g[i].getName());
        }
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
    public void ThreadGroup(Thread list[]){
        int Count = list.length;
        for (int i = 0; i < Count; i++) {
            System.out.println("The Thread "+list[i].getName()+" belongs to group :" + list[i].getThreadGroup());
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