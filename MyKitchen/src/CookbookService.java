public class CookbookService {
    //stores the new recipe object in cookbooks recipes
    public Recipe saveRecipe(Recipe newRecipe) {
        Cookbook.recipes.add(newRecipe);
        return newRecipe;
    }

    //removes the input recipe object from cookbooks recipes
    public void deleteRecipe(Recipe recipe) {
        Cookbook.removeRecipe(recipe);
    }
}
