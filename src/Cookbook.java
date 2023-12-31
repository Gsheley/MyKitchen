import java.util.ArrayList;

public class Cookbook {
    protected static String cookbookName = "";
    //Recipe object containing multiple recipes(recipeName: recipeID: recipeIngredients: recipeSteps:)
    public static ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    
    //function that grabs a recipe from the list of Recipes
    public static Recipe getRecipe(String name){
        //checks each recipe in the recipes array list
        Recipe foundRecipe = null;
        for(int i = 0; i < recipes.size(); i++) {
            String curRecipe = (recipes.get(i)).recipeName;
            if(curRecipe.equals(name)){
                foundRecipe = recipes.get(i);
            }
        }

        return foundRecipe;
    }

    //stores the new recipe object in cookbooks recipes
    public static Recipe saveRecipe(Recipe newRecipe) {
        recipes.add(newRecipe);
        Controller.saveJson.create(newRecipe);
        return newRecipe;
    }

    //function that removesa recipe from the cookbook
    public static void removeRecipe(Recipe recipe){
        for(int i = 0; i < recipes.size(); i++) {
            if(recipes.get(i).equals(recipe)){
                recipes.remove(i);
            }
        }
        Controller.saveJson.delete(recipe);
    }
    //function to set the cookbook name
    public static void setName(String newName){
        cookbookName = newName;
    }
}
