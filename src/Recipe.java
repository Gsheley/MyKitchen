import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Recipe {
    public String recipeName;
    public int recipeID;
    public List<String> recipeIngredients;
    public List<String> recipeMeasurements;
    public String recipeSteps;

    public Recipe(String jsonString) {
        //Json setup for each object
        JsonObject jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();
        JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
        JsonObject mealObject = mealsArray.get(0).getAsJsonObject();
        
        //retreive name + ID
        String recipeName = mealObject.get("strMeal").getAsString();
        int recipeID = mealObject.get("idMeal").getAsInt();
        
        //set the name + ID
        this.recipeName = recipeName;
        this.recipeID = recipeID;

        //populate the ingredients list 
        this.recipeIngredients = new ArrayList<>();
        for (int i = 1; i <= 20; i++){
            if(!mealObject.get("strIngredient"+i).isJsonNull())
            {
                String ingredient = mealObject.get(("strIngredient"+i).toString()).getAsString();
                if (ingredient != null && !ingredient.isEmpty())
                {
                    this.recipeIngredients.add(ingredient);
                }
            }
        }

        //populate measurements list
        this.recipeMeasurements = new ArrayList<>();
        for (int i = 1; i <= 20; i++){
            if(!mealObject.get("strMeasure"+i).isJsonNull())
            {
                String measurement = mealObject.get(("strMeasure"+i).toString()).getAsString();
                if (measurement != null && !measurement.isEmpty())
                {
                    this.recipeMeasurements.add(measurement);
                }
            }
        }

        //enter recipe instructions
        this.recipeSteps = mealObject.get("strInstructions").toString();
    }

    public void printRecipe(int id) 
    {
        id = this.recipeID;
        System.out.println("Recipe Name: " + recipeName);
        System.out.println("Recipe ID: " + recipeID);
        System.out.println("Ingredient List: ");
        
        for(int i = 0; i < this.recipeIngredients.size(); i++)
        {
            System.out.println(recipeMeasurements.get(i)+ " : "+recipeIngredients.get(i));
        }
        
        System.out.println("\nInstructions:\n\n"+recipeSteps);
        
    }
    
    public void printRecipe()
    {
        this.printRecipe(this.getID());
    }

    //ID getter
    public int getID()
    {
        return this.recipeID;
    }

    //Meal name getter
    public String getName()
    {
        return this.recipeName;
    }
}
