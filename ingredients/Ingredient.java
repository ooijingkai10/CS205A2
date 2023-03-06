package ingredients;

/**
 * Food
 */
public abstract class Ingredient {

    protected int id;
    protected String makermachine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMakermachine() {
        return makermachine;
    }

    public void setMakermachine(String makermachine) {
        this.makermachine = makermachine;
    }

    

    

}
