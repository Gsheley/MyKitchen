import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javax.management.RuntimeErrorException;
import com.google.gson.JsonParser;

public class MealDB extends API{
    //attributes
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    //constructor
    public MealDB(){}

    //query API by recipe name
    public Recipe queryByName(String name)
    {
        //recipe name is formatted to be readable by the API
        name.trim();
        name.replaceAll(" ", "_");
        name.toLowerCase();

        try{
            String queryURL = BASE_URL + "search.php?s="+ name;
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");

            url_connection.connect();

            int response_code = url_connection.getResponseCode();

            if(response_code != 200)
            {
                //throw new RuntimeException("HttpResponseCode: " + response_code);
                return null;
            }
            else{
                System.out.println("Connection successful with response code: " + response_code);
            }
            InputStream in;
            if (response_code >= 200 && response_code < 300) {
                in = url_connection.getInputStream();
             }
             else {
                in = url_connection.getErrorStream();
             }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            // Close connections
            in.close();
            url_connection.disconnect();

            // Convert the JSON string into a Recipe object
            return new Recipe(content.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    //query API by main ingredient
    public ArrayList<Recipe> queryByIngredient(String mainIngredient)
    {
        ArrayList<Recipe> recipes = new ArrayList<>();
        //mainIngredient must be formatted to be readable to the database
        mainIngredient.replaceAll(" ", "_");
        mainIngredient.trim();
        mainIngredient.toLowerCase();

        try{
            String queryURL = BASE_URL + "filter.php?i="+ mainIngredient;
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");

            url_connection.connect();

            int response_code = url_connection.getResponseCode();

            if(response_code != 200)
            {
                throw new RuntimeException("HttpResponseCode: " + response_code);
            }
            else{
                System.out.println("Connection successful with response code: " + response_code);
            }
            InputStream in;
            if (response_code >= 200 && response_code < 300) {
                in = url_connection.getInputStream();
             }
             else {
                in = url_connection.getErrorStream();
             }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            line = content.toString();
            JsonObject jsonObject = JsonParser.parseString(line).getAsJsonObject();
            JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
            StringBuilder mealNames = new StringBuilder();
            
            for (JsonElement mealElement :mealsArray){
                JsonObject mealObject = mealElement.getAsJsonObject();
                String mealName = mealObject.get("strMeal").getAsString();
                Recipe newRecipe = this.queryByName(mealName);
                if(newRecipe != null){
                    recipes.add(newRecipe);
                }
            }


            // Close connections
            in.close();
            url_connection.disconnect();

            // Convert the JSON string into a Recipe object
            //String return_string = "\n\nThese are the recipes you are able to cook with "+ mainIngredient+": \n";
            return recipes;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //query a random recipe
    public Recipe queryRandom()
    {
        try{
            String queryURL = BASE_URL + "random.php";
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");

            url_connection.connect();

            int response_code = url_connection.getResponseCode();

            if(response_code != 200)
            {
                throw new RuntimeException("HttpResponseCode: " + response_code);
            }
            else{
                System.out.println("Connection successful with response code: " + response_code);
            }
            InputStream in;
            if (response_code >= 200 && response_code < 300) {
                in = url_connection.getInputStream();
             }
             else {
                in = url_connection.getErrorStream();
             }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            // Close connections
            in.close();
            url_connection.disconnect();

            // Convert the JSON string into a Recipe object
            return new Recipe(content.toString());
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //Main
    public static void main(String[] args)
    {
        MealDB myConnection = new MealDB();
        Recipe recipeByName = myConnection.queryByName("chicken_ham_and_leek_pie");
        System.out.println("\n\nRecipe by name 'CHLPie': \n\n");
        recipeByName.printRecipe();

        System.out.println("\n\nRecipe by main ingredient 'chicken_breast': ");
        System.out.println(myConnection.queryByIngredient("chicken_breast"));
        
        //System.out.println("\n\nRecipe by random: \n\n");
        //Recipe random = myConnection.queryRandom();
        //random.printRecipe();
    }
}