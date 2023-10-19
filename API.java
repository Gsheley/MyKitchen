public abstract class API {
    abstract void connect();
    abstract Recipe queryByName(String name);
    abstract Recipe queryByIngredient(String mainIngredient);
    abstract Recipe queryRandom();
}
