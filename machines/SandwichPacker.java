package machines;

import pools.*;
import ingredients.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SandwichPacker implements Runnable {

    private int id;
    private int tasksDone = 0;
    private int packing_rate;
    BreadPool breadPool;
    EggPool eggPool;
    private volatile static int totalcount = 0;
    private volatile static int tomake;
    static Lock lock = new ReentrantLock(true);

    public SandwichPacker(int id, int packing_rate, BreadPool breadPool, EggPool eggPool) {
        // this.task = task;
        this.id = id;
        this.packing_rate = packing_rate;
        this.breadPool = breadPool;
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
        while (totalcount < tomake) {
            totalcount++;
            // critical section 
            lock.lock();
            gowork(packing_rate);
            Bread bottomBread = breadPool.getBread();
            Egg egg = eggPool.getEgg();
            Bread topBread = breadPool.getBread();
            Sandwich sandwich = new Sandwich(tasksDone, bottomBread, topBread, egg);
            logger.Printext.log(String.format("%s packs %s with %s from %s and %s from %s and %s from %s",
                    this.toString(), sandwich, bottomBread, bottomBread.getMakermachine(), egg, egg.getMakermachine(),
                    topBread, topBread.getMakermachine()));
            tasksDone++;
            lock.unlock();
        }

    }

    @Override
    public String toString() {
        return "S" + id;
    }

    public int getTasksDone() {
        return tasksDone;
    }

    public static int getTomake() {
        return tomake;
    }

    public static void setTomake(int tomake) {
        SandwichPacker.tomake = tomake;
    }

}
