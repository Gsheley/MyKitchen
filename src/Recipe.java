import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Recipe {
    public String recipeName;
    public int recipeID;
    public String[] recipeIngredients;
    public String recipeSteps;

    public Recipe() {
        //this.recipeName = recipeName;
        //this.recipeID = recipeID;
        //this.recipeIngredients = recipeIngredients;
        //this.recipeSteps = recipeSteps;
    }
    
    //temporary attribute used to manually test the setup
    public String stringJson = "{\"meals\":[{\r\n" + //
            "    \"idMeal\":\"52771\",\r\n" + //
            "    \"strMeal\":\"Spicy Arrabiata Penne\",\r\n" + //
            "    \"strDrinkAlternate\":null,\r\n" + //
            "    \"strCategory\":\"Vegetarian\",\r\n" + //
            "    \"strArea\":\"Italian\",\r\n" + //
            "    \"strInstructions\":\"Bring a large pot of water to a boil. Add kosher salt to the boiling water, then add the pasta. Cook according to the package instructions, about 9 minutes.\\r\\n" + //
            "In a large skillet over medium-high heat, add the olive oil and heat until the oil starts to shimmer. Add the garlic and cook, stirring, until fragrant, 1 to 2 minutes. Add the chopped tomatoes, red chile flakes, Italian seasoning and salt and pepper to taste. Bring to a boil and cook for 5 minutes. Remove from the heat and add the chopped basil.\\r\\n" + //
            "Drain the pasta and add it to the sauce. Garnish with Parmigiano-Reggiano flakes and more basil and serve warm.\",\r\n" + //
            "    \"strMealThumb\":\"https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/ustsqw1468250014.jpg\",\r\n" + //
            "    \"strTags\":\"Pasta,Curry\",\r\n" + //
            "    \"strYoutube\":\"https:\\/\\/www.youtube.com\\/watch?v=1IszT_guI08\",\r\n" + //
            "    \"strIngredient1\":\"penne rigate\",\r\n" + //
            "    \"strIngredient2\":\"olive oil\",\r\n" + //
            "    \"strIngredient3\":\"garlic\",\r\n" + //
            "    \"strIngredient4\":\"chopped tomatoes\",\r\n" + //
            "    \"strIngredient5\":\"red chile flakes\",\r\n" + //
            "    \"strIngredient6\":\"italian seasoning\",\r\n" + //
            "    \"strIngredient7\":\"basil\",\r\n" + //
            "    \"strIngredient8\":\"Parmigiano-Reggiano\",\r\n" + //
            "    \"strIngredient9\":\"\",\r\n" + //
            "    \"strIngredient10\":\"\",\r\n" + //
            "    \"strIngredient11\":\"\",\r\n" + //
            "    \"strIngredient12\":\"\",\r\n" + //
            "    \"strIngredient13\":\"\",\r\n" + //
            "    \"strIngredient14\":\"\",\r\n" + //
            "    \"strIngredient15\":\"\",\r\n" + //
            "    \"strIngredient16\":null,\r\n" + //
            "    \"strIngredient17\":null,\r\n" + //
            "    \"strIngredient18\":null,\r\n" + //
            "    \"strIngredient19\":null,\r\n" + //
            "    \"strIngredient20\":null,\r\n" + //
            "    \"strMeasure1\":\"1 pound\",\r\n" + //
            "    \"strMeasure2\":\"1\\/4 cup\",\r\n" + //
            "    \"strMeasure3\":\"3 cloves\",\r\n" + //
            "    \"strMeasure4\":\"1 tin \",\r\n" + //
            "    \"strMeasure5\":\"1\\/2 teaspoon\",\r\n" + //
            "    \"strMeasure6\":\"1\\/2 teaspoon\",\r\n" + //
            "    \"strMeasure7\":\"6 leaves\",\r\n" + //
            "    \"strMeasure8\":\"spinkling\",\r\n" + //
            "    \"strMeasure9\":\"\",\r\n" + //
            "    \"strMeasure10\":\"\",\r\n" + //
            "    \"strMeasure11\":\"\",\r\n" + //
            "    \"strMeasure12\":\"\",\r\n" + //
            "    \"strMeasure13\":\"\",\r\n" + //
            "    \"strMeasure14\":\"\",\r\n" + //
            "    \"strMeasure15\":\"\",\r\n" + //
            "    \"strMeasure16\":null,\r\n" + //
            "    \"strMeasure17\":null,\r\n" + //
            "    \"strMeasure18\":null,\r\n" + //
            "    \"strMeasure19\":null,\r\n" + //
            "    \"strMeasure20\":null,\r\n" + //
            "    \"strSource\":null,\r\n" + //
            "    \"strImageSource\":null,\r\n" + //
            "    \"strCreativeCommonsConfirmed\":null,\r\n" + //
            "    \"dateModified\":null}]}";
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
        System.out.println(recipeSteps);
    }
    public static void main(String [] args)
    {
        Recipe Arrabiata = new Recipe();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(Arrabiata.stringJson);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        //Arrabiata.recipeID = jsonObject.get("idMeal").getAsInt();
        System.err.println(Arrabiata.recipeID);
    }
}
