import java.lang.ThreadGroup;
import java.lang.Thread;
import java.util.ArrayList;

public class monitor {


    public static void main(String[] args) {
        monitor m = new monitor();
        ThreadGroup root = m.getRoot();

        /*testing by adding some threads and a threadGroup*/
        ThreadGroup testGroup =new ThreadGroup("ThreadGroup1") ;
        Thread th1 = new Thread(testGroup, "JAVA");
        Thread th2 = new Thread(testGroup, "JDBC");
        Thread th3 = new Thread(testGroup, "EJB");
        Thread th4 = new Thread(testGroup, "XML");

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        ThreadGroup[] groups = m.getAllGroups();
        System.out.println("All Active Groups");
        m.printGroup(groups);
        System.out.println("");

        System.out.println("ALL Active Threads :");
        m.getAllThreads(groups);



    }


    public void getAllThreads(ThreadGroup[] g){
        int i=0;
            Thread[] t = threadsInGroup(getRoot());
            DisplayThread(t);
            i++;


    }

    public Thread[] threadsInGroup(ThreadGroup g){
        int threadcount = g.activeCount();
        threadcount *=2;
        Thread[] threads = new Thread[threadcount];
        g.enumerate(threads);
        return(java.util.Arrays.copyOf(threads,threadcount));
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

        numberOfGroups =numberOfGroups*2;

        ThreadGroup[] groups = new ThreadGroup[numberOfGroups];

        root.enumerate(groups,true);


        ThreadGroup[] groupsAndRoot = new ThreadGroup[groups.length+1];  //putting the root and all other groups into same array
        groupsAndRoot[0]=root;                                          // put into a method to make it more tidy
        System.arraycopy(groups,0,groupsAndRoot,1,groups.length);



        return groupsAndRoot; // now includes the root
    }

    public void printGroup(ThreadGroup[] g){
        int count  = g.length;
        int i =0;


        while(g[i]!=null) {
            System.out.println(g[i].getName());
            i++;
        }
    }
    /*Displays each Thread Group once and ID, Name,Priority, State and if thread is Daemon or not*/
    public void DisplayThread(Thread list[]){

        int Count = list.length;
        int i=0;
        ThreadGroup currentGroup = null;

        while (list[i]!=null) {
            if(currentGroup!=list[i].getThreadGroup()){
                System.out.println("");
                System.out.println("Thread Group :"+list[i].getThreadGroup().getName());
                System.out.println("");
                currentGroup=list[i].getThreadGroup();
            }
            System.out.println("Thread ID:"+list[i].getId());
            System.out.println("Thread name: "+list[i].getName());
            System.out.println("The Thread Priority is: "+ list[i].getPriority());
            System.out.println("The Thread state is: " + list[i].getState());
            System.out.println("Is Thread a Daemon: "+list[i].isDaemon());

            System.out.println("");
            i++;
        }
    }
  
}