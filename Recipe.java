public class Recipe {
    public String recipeName;
    public String recipeInfo;
    public int recipeID;

    public void printRecipe(Recipe recipeName) {
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Recipe Info: " + recipeInfo);
    }
}
