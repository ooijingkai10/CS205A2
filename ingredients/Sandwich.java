package ingredients;


public class Sandwich {
    private int id;
    private Bread bottomBread;
    private Bread topBread;
    private Egg egg;

    public Sandwich(int id, Bread bottomBread, Bread topBread, Egg egg){
        this.id = id;
        this.bottomBread = bottomBread;
        this.topBread = topBread;
        this.egg = egg;
    }

    

    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public Bread getBottomBread() {
        return bottomBread;
    }



    public void setBottomBread(Bread bottomBread) {
        this.bottomBread = bottomBread;
    }



    public Bread getTopBread() {
        return topBread;
    }



    public void setTopBread(Bread topBread) {
        this.topBread = topBread;
    }



    public Egg getEgg() {
        return egg;
    }



    public void setEgg(Egg egg) {
        this.egg = egg;
    }



    @Override
    public String toString() {

        return "sandwich " + id;
    }
}
