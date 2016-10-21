

import java.lang.ThreadGroup;
import java.lang.Thread;

public class monitor {


    public static void main(String[] args) {

        monitor m = new monitor();
        ThreadGroup root = m.getRoot();

        /*testing by adding some threads and a threadGroup*/
        ThreadGroup testGroup =new ThreadGroup("ThreadGroup1") ;

        Thread th1 = new Thread(testGroup, "JAVA");
        Thread th2 = new Thread(testGroup, "JDBC");

        long delay =2000;
        th1.start();

        th2.start();

        //System.out.println(th1.getThreadGroup().getName());
        // System.out.println(th2.getThreadGroup().getName());
        // System.out.println(testGroup.activeCount());
        ThreadGroup testGroup1 = new ThreadGroup(testGroup,"B_G");
        Thread th5 = new Thread(testGroup1,"ggg");
        Thread th6 = new Thread(testGroup1,"sadfds");
        th5.start();
        th6.start();

        ThreadGroup testGroup2 =  new ThreadGroup(testGroup,"B_Gr");
        Thread th7 = new Thread(testGroup2,"gdgg");
        Thread th8 = new Thread(testGroup2,"df");
        th7.start();
        th8.start();
        // ThreadGroup[] groups = m.getAllGroups();
        System.out.println("All Active Groups");
        // m.printGroup(groups);
        System.out.println("");

        // System.out.println("ALL Active Threads :");

        m.lauchMonitor();


    }


    public void lauchMonitor(){
        ThreadGroup[] groups = getAllGroups();
        int i=0;
        while(groups[i]!=null){
            System.out.println("Thread Group "+groups[i].getName());
            System.out.println("");
            Thread[] j=getallthreadsInGroup(groups[i]);
            DisplayThread(j);
            i++;
        }

    }





    public Thread[] getallthreadsInGroup( ThreadGroup g){
        ThreadGroup root = g;
        int threadcount = root.activeCount();
        int n;
        Thread[] threads = new Thread[threadcount];
        do{
            threadcount *=2;

            n=root.enumerate(threads,false);
            //System.out.println("num"+n);
        }while(n>=threadcount);

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

        numberOfGroups =numberOfGroups*2;

        ThreadGroup[] groups = new ThreadGroup[numberOfGroups];

        root.enumerate(groups,true);


        ThreadGroup[] groupsAndRoot = new ThreadGroup[groups.length+1];  //putting the root and all other groups into same array
        groupsAndRoot[0]=root;                                          // put into a method to make it more tidy
        System.arraycopy(groups,0,groupsAndRoot,1,groups.length);
        // System.arraycopy(groupsAndRoot);


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
    public void DisplayThread(Thread list[]) {

        int Count = list.length;

        int i = 0;


        while ((i >= 0) && (i < Count )&&(list[i] != null)) {

            System.out.println("Thread ID:" + list[i].getId());
            System.out.println("Thread name: " + list[i].getName());
            System.out.println("The Thread Priority is: " + list[i].getPriority());
            System.out.println("The Thread state is: " + list[i].getState());
            System.out.println("Is Thread a Daemon: " + list[i].isDaemon());

            System.out.println("");
            i++;


        }
    }
}