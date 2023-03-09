package pools;

import ingredients.Egg;
import machines.EggMaker;

public class EggPool {
    private volatile Egg[] pool;
    private volatile int start = 0;
    private volatile int end = 0;
    private volatile int item_count = 0;

    /**
     * Constructor for EggPool
     * 
     * @param size
     */
    public EggPool(int size) {
        this.pool = new Egg[size];
    }

    /**
     * Synchronized function to putEgg after
     * the eggmaker done making the egg
     * 
     * @param egg
     * @param eggMaker
     */
    public synchronized void putEgg(Egg egg, EggMaker eggMaker) {
        while (item_count == pool.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }

        pool[end] = egg;
        logger.Printext.log(String.format("%s puts %s", eggMaker, egg));
        end = (end + 1) % pool.length;
        item_count++;
        this.notify();
    }

    /**
     * Synchronized function to
     * retrive egg from the pool by the
     * SandwichPacker
     * 
     * @return Egg
     */
    public synchronized Egg getEgg() {
        while (item_count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        Egg egg = pool[start];
        start = (start + 1) % pool.length;
        item_count--;
        this.notify();
        return egg;
    }

}
