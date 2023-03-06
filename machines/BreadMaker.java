package machines;

import ingredients.Bread;
import pools.BreadPool;

public class BreadMaker implements Runnable{

    Task task;
    private int id;
    private int tasksDone = 0;
    private int bread_rate;
    BreadPool breadPool;

    public BreadMaker(Task task, int id, int bread_rate, BreadPool breadPool) {
        this.task = task;
        this.id = id;
        this.bread_rate = bread_rate;
        this.breadPool = breadPool;
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
        while(task.doBreadTask()) {
            // try {
            //     Thread.sleep(bread_rate * 1000);
            // } catch (InterruptedException e) {
            //     e.printStackTrace();
            // }
            gowork(bread_rate);
            Bread bread = new Bread(tasksDone, this.toString());
            breadPool.putBread(bread);
            tasksDone++;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                //TODO: handle exception
            }
            logger.Printext.log(String.format("%s puts %s",this, bread));
            // task.finishBreadTask();
        }
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "B" + id;
    }

    public int getTasksDone() {
        return tasksDone;
    }



    

}