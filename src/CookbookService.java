public class CookbookService {

    public Recipe getRecipe(String recipeName) {
        Recipe receivedRecipe = Cookbook.getRecipe(recipeName);
        return receivedRecipe;
    }
    //stores the new recipe object in cookbooks recipes
    public Recipe saveRecipe(Recipe newRecipe) {
        Cookbook.saveRecipe(newRecipe);
        return newRecipe;
    }

    //removes the input recipe object from cookbooks recipes
    public void deleteRecipe(Recipe recipe) {
        Cookbook.removeRecipe(recipe);
    }
}

