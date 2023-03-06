package machines;

import ingredients.Egg;
import pools.EggPool;

public class EggMaker implements Runnable{
    Task task;
    private int id;
    private int tasksDone = 0;
    private int egg_rate;
    EggPool eggPool;

    public EggMaker(Task task, int id, int egg_rate, EggPool eggPool) {
        this.task = task;
        this.id = id;
        this.egg_rate = egg_rate;
        this.eggPool = eggPool;
    }

    public static void gowork(int n) {
        for (int i = 0; i < n; i++) {
            long m = 300000000;
            while (m>0) {
                m--;
            }
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(task.doEggTask()) {
            Egg egg = new Egg(tasksDone, this.toString());
            // try {
            //     Thread.sleep(egg_rate * 1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            gowork(egg_rate);
            eggPool.putEgg(egg);
            tasksDone++;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //TODO: handle exception
            }
            logger.Printext.log(String.format("%s puts %s",this, egg));
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
}
