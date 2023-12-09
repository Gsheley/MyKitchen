import java.util.ArrayList;

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

    // sorting the recipes by their names
    public static ArrayList<Recipe> sortRecipeNames() 
    {
        ArrayList<Recipe> recipes = Cookbook.recipes; 
        for (int i = 1; i < recipes.size(); ++i)
         {
            Recipe curr = recipes.get(i);
            String key = recipes.get(i).getName().toLowerCase();
            int j = i - 1;
            while (j >= 0 && key.compareTo(recipes.get(j).getName().toLowerCase()) < 0) 
            {
                recipes.set(j+1, recipes.get(j));
                j--;
            }
            recipes.set(j+1,curr);
        }
        Cookbook.recipes = recipes;
        return recipes;
    }
}

