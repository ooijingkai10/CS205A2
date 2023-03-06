package pools;

import ingredients.Egg;

public class EggPool {
    private volatile Egg[] pool;
    private volatile int start = 0;
    private volatile int end = 0;
    private volatile int item_count = 0;

    public EggPool(int size) {
        this.pool = new Egg[size];
    }

    public synchronized void putEgg(Egg egg) {
        while (item_count == pool.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        pool[end] = egg;
        end = (end + 1) % pool.length;
        item_count++;
        this.notifyAll();
    }

    public synchronized Egg getEgg() {
        while (item_count == 0) {
            try {
                this.wait();
            } catch (Exception e) {
                // TODO: handle exception

            }
        }

        Egg egg = pool[start];
        start = (start + 1) % pool.length;
        item_count--;
        this.notifyAll();
        return egg;
    }

}
