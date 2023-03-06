package ingredients;

public class Egg extends Ingredient{

    public Egg(int id, String makermachine) {
        super.id = id;
        super.makermachine = makermachine;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "egg " + id;
    }
}
