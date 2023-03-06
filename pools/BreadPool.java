package pools;

import ingredients.Bread;
import machines.BreadMaker;

public class BreadPool {
    private volatile Bread[] pool;
    private volatile int start = 0;
    private volatile int end = 0;
    private volatile int item_count = 0;


    public BreadPool(int size) {
        this.pool = new Bread[size];
    }

    public synchronized void putBread(Bread bread, BreadMaker breadMaker) {
        while (item_count == pool.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        pool[end] = bread;
        logger.Printext.log(String.format("%s puts %s", breadMaker, bread));

        end = (end + 1) % pool.length;
        item_count++;
        this.notify();
    }

    public synchronized Bread getBread() {
        while (item_count == 0) {
            try {
                this.wait();
            } catch (Exception e) {
                //TODO: handle exception

            }
        }
        Bread bread = pool[start];
        start = (start + 1) % pool.length;
        item_count--;
        this.notify();
        return bread;
    }

}