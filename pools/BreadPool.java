package pools;

import ingredients.Bread;

public class BreadPool {
    private volatile Bread[] pool;
    private volatile int start = 0;
    private volatile int end = 0;
    private volatile int item_count = 0;
    public volatile int poolsize;


    public BreadPool(int size) {
        this.pool = new Bread[size];
        this.poolsize = size;
    }

    public synchronized void putBread(Bread bread) {
        while (item_count == pool.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        pool[end] = bread;
        end = (end + 1) % pool.length;
        item_count++;
        this.notifyAll();
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
        this.notifyAll();
        return bread;
    }

    public int getItem_count() {
        return item_count;
    }

    public void setItem_count(int item_count) {
        this.item_count = item_count;
    }

    

}