public class Recipe {
    public String recipeName;
    public int recipeID;
    public String[] recipeIngredients;
    public String[] recipeSteps;

    public void printRecipe(Recipe recipeName) {
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Recipe Info: " + recipeInfo);
    }
}
