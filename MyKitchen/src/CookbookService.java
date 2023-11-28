public class CookbookService {
    saveRecipe(newRecipe) {
        //stores the new recipe object in cookbooks recipes
        Cookbook.recipes.add(newRecipe);
    }
}
