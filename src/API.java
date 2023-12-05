public abstract class API {
    abstract Recipe queryByName(String name);
    abstract String queryByIngredient(String mainIngredient);
    abstract Recipe queryRandom();
}
