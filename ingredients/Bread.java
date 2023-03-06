package ingredients;

public class Bread extends Ingredient{
    public Bread(int id, String makermachine) {
        super.id = id;
        super.makermachine = makermachine;
    }

    @Override
    public String toString() {
        return "bread " + id;
    }
}
