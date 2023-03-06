package machines;

public class Task {
    private volatile int eggsTaskCount;
    // private int finalEggsTaskCount;
    private volatile int breadTaskCount;
    // private int finalBreadTaskCount;
    private volatile int sandwichTaskCount;
    // private int finalSandwichTaskCount;
 

    public Task(int eggsTaskCount, int breadTaskCount, int sandwichTaskCount) {
        this.eggsTaskCount = eggsTaskCount;
        this.breadTaskCount = breadTaskCount;
        this.sandwichTaskCount = sandwichTaskCount;
        // this.finalBreadTaskCount = breadTaskCount;
        // this.finalSandwichTaskCount = sandwichTaskCount;
        // this.finalEggsTaskCount = eggsTaskCount;
    }

    public synchronized boolean doEggTask() {
        if (eggsTaskCount > 0) {
            eggsTaskCount--;
            this.notifyAll();
            return true;
        }
        this.notifyAll();
        return false;
    }

    public synchronized boolean doBreadTask() {
      
        if (breadTaskCount > 0) {
            breadTaskCount--;
            this.notifyAll();
            return true;
        }
        this.notifyAll();
        return false;
    }

    public synchronized boolean doSandwichTask() {
        if (sandwichTaskCount > 0) {
            sandwichTaskCount--;
            this.notifyAll();
            return true;
        }
        this.notifyAll();
        return false;
    }

    // public synchronized void finishEggTask() {
    //     finalEggsTaskCount--;
    //     notifyAll();
    // }
    // public synchronized void finishBreadTask() {
    //     finalBreadTaskCount--;
    //     notifyAll();

    // }

    // public synchronized void finishSandwichTask() {
    //     finalSandwichTaskCount--;
    //     notifyAll();

    // }


    public int getEggsTaskCount() {
        return eggsTaskCount;
    }

    public void setEggsTaskCount(int eggsTaskCount) {
        this.eggsTaskCount = eggsTaskCount;
    }

    public int getBreadTaskCount() {
        return breadTaskCount;
    }

    public void setBreadTaskCount(int breadTaskCount) {
        this.breadTaskCount = breadTaskCount;
    }

    public int getSandwichTaskCount() {
        return sandwichTaskCount;
    }

    public void setSandwichTaskCount(int sandwichTaskCount) {
        this.sandwichTaskCount = sandwichTaskCount;
    }

    // public int getFinalEggsTaskCount() {
    //     return finalEggsTaskCount;
    // }

    // public void setFinalEggsTaskCount(int finalEggsTaskCount) {
    //     this.finalEggsTaskCount = finalEggsTaskCount;
    // }

    // public int getFinalBreadTaskCount() {
    //     return finalBreadTaskCount;
    // }

    // public void setFinalBreadTaskCount(int finalBreadTaskCount) {
    //     this.finalBreadTaskCount = finalBreadTaskCount;
    // }

    // public int getFinalSandwichTaskCount() {
    //     return finalSandwichTaskCount;
    // }

    // public void setFinalSandwichTaskCount(int finalSandwichTaskCount) {
    //     this.finalSandwichTaskCount = finalSandwichTaskCount;
    // }



}
