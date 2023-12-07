import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MealDB extends API{
    //attributes
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    //constructor
    public MealDB(){}

    /*
     * queryByName method
     * takes in the name of the recipe as a string 
     * returns a recipe object
     */
    public Recipe queryByName(String name)
    {
        //recipe name is formatted to be readable by the API
        name.trim();
        name.replaceAll(" ", "_");
        name.toLowerCase();

        //try to query the API
        try{
            //url connection setup
            String queryURL = BASE_URL + "search.php?s="+ name;
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");
            url_connection.connect();

            //verify connection
            int response_code = url_connection.getResponseCode();
            if(response_code != 200)
            {
                return null;
            }

            //read in from the url connection
            InputStream in = url_connection.getInputStream();
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
        //upon failure, null is returned
        catch (Exception e)
        {
            return null;
        }
    }

    /*
     * query by main ingredient method
     * takes in the main ingredient as a string
     * returns array list of recipe objects
     */
    public ArrayList<Recipe> queryByIngredient(String mainIngredient)
    {
        //returnable array list
        ArrayList<Recipe> recipes = new ArrayList<>();

        //mainIngredient formatted to be readable to the API
        mainIngredient.replaceAll(" ", "_");
        mainIngredient.trim();
        mainIngredient.toLowerCase();

        //try to query API
        try{
            //setup url connection based on main ingredient
            String queryURL = BASE_URL + "filter.php?i="+ mainIngredient;
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");
            url_connection.connect();

            //verify connection
            int response_code = url_connection.getResponseCode();
            if(response_code != 200)
            {
                return null;
            }

            //read in from url connection
            InputStream in = url_connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            JsonObject jsonObject = JsonParser.parseString(content.toString()).getAsJsonObject();
            JsonArray mealsArray = jsonObject.getAsJsonArray("meals");

            //parse the json object and re-query the database by ID for each returned recipe
            for (JsonElement mealElement :mealsArray){
                JsonObject mealObject = mealElement.getAsJsonObject();
                int ID = mealObject.get("idMeal").getAsInt();
                Recipe newRecipe = this.queryByID(ID);
                if(newRecipe != null){
                    recipes.add(newRecipe);
                }
            }

            // Close connections
            in.close();
            url_connection.disconnect();

            //return array list
            return recipes;
        }
        //upon failure return null
        catch (Exception e){
            return null;
        }
    }

    /*
     * query random recipe method
     * no arguments 
     * returns random recipe object
     */
    public Recipe queryRandom()
    {
        //try to query API
        try{
            //setup url connection with random.php
            String queryURL = BASE_URL + "random.php";
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");
            url_connection.connect();

            //verify connection
            int response_code = url_connection.getResponseCode();
            if(response_code != 200)
            {
                throw new RuntimeException("HttpResponseCode: " + response_code);
            }

            //read in from url_connection
            InputStream in = url_connection.getInputStream();
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
        //upon failure return null
        catch(Exception e){
            return null;
        }
    }

    /*
     * query recipe by ID method
     * takes in ID as an integer
     * returns recipe object
     */
    public Recipe queryByID(int ID){
        //try to query the API
        try{
            //setup url connection with ID 
            String queryURL = BASE_URL + "lookup.php?i="+ ID;
            URL url = new URL(queryURL);
            HttpURLConnection url_connection = (HttpURLConnection) url.openConnection();
            url_connection.setRequestMethod("GET");
            url_connection.connect();

            //verify connection
            int response_code = url_connection.getResponseCode();
            if(response_code != 200)
            {
                return null;
            }

            //read input from url
            InputStream in = url_connection.getInputStream();
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
        //upon failure return null
        catch (Exception e)
        {
            return null;
        }
    }

}