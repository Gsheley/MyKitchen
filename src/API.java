import java.util.ArrayList;

public abstract class API {
    abstract Recipe queryByName(String name);
    abstract ArrayList<Recipe> queryByIngredient(String mainIngredient);
    abstract Recipe queryRandom();
    abstract Recipe queryByID(int id);
}
