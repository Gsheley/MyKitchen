import com.google.gson.Gson;

public class Recipe {
    public String recipeName;
    public int recipeID;
    public String[] recipeIngredients;
    public String[] recipeSteps;

    public void printRecipe() 
    {
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Recipe ID: " + recipeID);
        System.out.println("Ingredient List: ");
        for(String i:recipeIngredients)
        {
            System.out.println(i);
        }
        System.out.println("Steps: ");
        for(String i:recipeSteps)
        {
            System.out.println(i);
        }
    }
}
