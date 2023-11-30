public class CookbookService {
    // collects a recipe with a the requested name
    public Recipe getRecipe(String recipeName) {
        Recipe receivedRecipe = Cookbook.getRecipe(recipeName);
        return receivedRecipe;
    }
    //stores the new recipe object in cookbooks recipes
    public static Recipe saveRecipe(Recipe newRecipe) {
        Cookbook.saveRecipe(newRecipe);
        return newRecipe;
    }

    //removes the input recipe object from cookbooks recipes
    public void deleteRecipe(Recipe recipe) {
        Cookbook.removeRecipe(recipe);
    }
}

