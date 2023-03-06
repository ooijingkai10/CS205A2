import java.util.List;

import java.util.ArrayList;
import logger.*;
import machines.*;
import pools.*;

public class SandwichManager {

    public volatile BreadPool breadPool;
    public volatile EggPool eggPool;

    List<BreadMaker> breadMakers = new ArrayList<>();
    List<EggMaker> eggMakers = new ArrayList<>();
    List<SandwichPacker> sandwichPackers = new ArrayList<>();

    public SandwichManager(int sandwiches, int bread_capacity, int egg_capacity, int bread_makers, int egg_makers,
            int sandwich_packers, int bread_rate, int egg_rate, int packing_rate) {

        logger.Printext.log("sandwiches: " + sandwiches);
        logger.Printext.log("bread capacity: " + bread_capacity);
        logger.Printext.log("egg capacity: " + egg_capacity);
        logger.Printext.log("bread makers: " + bread_makers);
        logger.Printext.log("egg makers: " + egg_makers);
        logger.Printext.log("sandwich packers: " + sandwich_packers);
        logger.Printext.log("bread rate: " + bread_rate);
        logger.Printext.log("egg rate: " + egg_rate);
        logger.Printext.log("packing rate: " + packing_rate);
        logger.Printext.log("");

        breadPool = new BreadPool(bread_capacity);
        eggPool = new EggPool(egg_capacity);
        BreadMaker.setTomake(sandwiches * 2);
        EggMaker.setTomake(sandwiches);
        SandwichPacker.setTomake(sandwiches);
        for (int i = 0; i < bread_makers; i++) {
            BreadMaker breadMaker = new BreadMaker(i, bread_rate, this.breadPool);
            breadMakers.add(breadMaker);
        }

        for (int i = 0; i < egg_makers; i++) {
            EggMaker eggMaker = new EggMaker(i, egg_rate, this.eggPool);
            eggMakers.add(eggMaker);
        }

        for (int i = 0; i < sandwich_packers; i++) {
            SandwichPacker sandwichPacker = new SandwichPacker(i, packing_rate, this.breadPool, this.eggPool);
            sandwichPackers.add(sandwichPacker);
        }

    }

    public static void main(String[] args) {

        if (args.length < 9) {
            System.out.println("Please enter the right arguements");
            return;
        }

        int sandwiches = Integer.parseInt(args[0]);
        int bread_capacity = Integer.parseInt(args[1]);
        int egg_capacity = Integer.parseInt(args[2]);
        int bread_makers = Integer.parseInt(args[3]);
        int egg_makers = Integer.parseInt(args[4]);
        int sandwich_packers = Integer.parseInt(args[5]);
        int bread_rate = Integer.parseInt(args[6]);
        int egg_rate = Integer.parseInt(args[7]);
        int packing_rate = Integer.parseInt(args[8]);

        SandwichManager sandwichManager = new SandwichManager(sandwiches, bread_capacity, egg_capacity, bread_makers,
                egg_makers, sandwich_packers, bread_rate, egg_rate, packing_rate);

        List<Thread> threads = new ArrayList<>();

        // start threads
        for (BreadMaker breadMaker : sandwichManager.breadMakers) {
            Thread thread = new Thread(breadMaker);
            threads.add(thread);
            thread.start();
        }

        for (EggMaker eggMaker : sandwichManager.eggMakers) {
            Thread thread = new Thread(eggMaker);
            threads.add(thread);
            thread.start();
        }

        for (SandwichPacker sandwichPacker : sandwichManager.sandwichPackers) {
            Thread thread = new Thread(sandwichPacker);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }

        logger.Printext.log("");
        logger.Printext.log("Summary:");
        for (BreadMaker breadMaker : sandwichManager.breadMakers) {
            Printext.log(String.format("%s makes %s", breadMaker, breadMaker.getTasksDone()));
        }

        for (EggMaker eggMaker : sandwichManager.eggMakers) {
            Printext.log(String.format("%s makes %s", eggMaker, eggMaker.getTasksDone()));
        }
        for (SandwichPacker sandwichPacker : sandwichManager.sandwichPackers) {
            Printext.log(String.format("%s makes %s", sandwichPacker, sandwichPacker.getTasksDone()));
        }

        System.exit(0);
    }
}
