public abstract class API {
    abstract Recipe queryByName(String name);
    abstract Recipe queryByIngredient(String mainIngredient);
    abstract Recipe queryRandom();
}
