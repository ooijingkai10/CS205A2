package machines;

import pools.*;
import ingredients.*;

public class SandwichPacker implements Runnable {

    Task task;
    private int id;
    private int tasksDone = 0;
    private int packing_rate;
    BreadPool breadPool;
    EggPool eggPool;

    public SandwichPacker(Task task, int id, int packing_rate, BreadPool breadPool, EggPool eggPool) {
        this.task = task;
        this.id = id;
        this.packing_rate = packing_rate;
        this.breadPool = breadPool;
        this.eggPool = eggPool;
    }

    public static void gowork(int n) {
        for (int i = 0; i < n; i++) {
            long m = 300000000;
            while (m > 0) {
                m--;
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (task.doSandwichTask()) {
            // try {
            // Thread.sleep(packing_rate * 1000);
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }
            gowork(packing_rate);
            Bread bottomBread = breadPool.getBread();
            Egg egg = eggPool.getEgg();
            Bread topBread = breadPool.getBread();
            Sandwich sandwich = new Sandwich(tasksDone, bottomBread, topBread, egg);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
            logger.Printext.log(String.format("%s packs %s with %s from %s and %s from %s and %s from %s",
                    this.toString(), sandwich, bottomBread, bottomBread.getMakermachine(), egg, egg.getMakermachine(),
                    topBread, topBread.getMakermachine()));
            tasksDone++;
            // task.finishSandwichTask();
        }

    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "S" + id;
    }

    public int getTasksDone() {
        return tasksDone;
    }
}
