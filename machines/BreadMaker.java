package machines;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ingredients.Bread;
import pools.BreadPool;

public class BreadMaker implements Runnable {

    private int id;
    private int tasksDone = 0;
    private int bread_rate;
    BreadPool breadPool;
    private volatile static int totalcount = 0;
    private volatile static int tomake;
    static Lock lock = new ReentrantLock(true);


    public BreadMaker(int id, int bread_rate, BreadPool breadPool) {
        this.id = id;
        this.bread_rate = bread_rate;
        this.breadPool = breadPool;
    }

    static void gowork(int n) {
        for (int i = 0; i < n; i++) {
            long m = 300000000;
            while (m-- > 0)
                ;
        }
    }

    @Override
    public void run() {
        while (totalcount < tomake) {
            totalcount++;

            // Critical section
            lock.lock();
            gowork(bread_rate);
            Bread bread = new Bread(tasksDone, this.toString());
            breadPool.putBread(bread, this);
            tasksDone++;
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return "B" + id;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public static int getTomake() {
        return tomake;
    }

    public static void setTomake(int tomake) {
        BreadMaker.tomake = tomake;
    }

   

    
}