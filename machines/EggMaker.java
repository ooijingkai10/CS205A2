package machines;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ingredients.Egg;
import pools.EggPool;

public class EggMaker implements Runnable{
    private int id;
    private int tasksDone = 0;
    private int egg_rate;
    EggPool eggPool;
    private volatile static int totalcount = 0;
    private volatile static int tomake;
    static Lock lock = new ReentrantLock(true);


    public EggMaker(int id, int egg_rate, EggPool eggPool) {
        this.id = id;
        this.egg_rate = egg_rate;
        this.eggPool = eggPool;
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
        // TODO Auto-generated method stub
        while(totalcount < tomake) {
            totalcount++;

            //Critical section
            lock.lock();
            gowork(egg_rate);
            Egg egg = new Egg(tasksDone, this.toString());
            eggPool.putEgg(egg, this);
            tasksDone++;
            lock.unlock();
            // task.finishBreadTask();
        }
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "E" + id;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public static int getTomake() {
        return tomake;
    }

    public static void setTomake(int tomake) {
        EggMaker.tomake = tomake;
    }

}
