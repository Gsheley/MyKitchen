import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.RuntimeErrorException;

public class MealDB extends API{
    //attributes
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    //constructor
    public MealDB(){}
    
    //query API by recipe name
    public Recipe queryByName(String name)
    {
        try{
            String queryURL = BASE_URL + "search.php?s="+ name;
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
             } else {
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
            Recipe recipe = new Recipe(content.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    //query API by main ingredient
    public Recipe queryByIngredient(String mainIngredient)
    {
        return null;
    }

    //query a random recipe
    public Recipe queryRandom()
    {
        return null;
    }

    //Main
    public static void main(String[] args)
    {
        MealDB myConnection = new MealDB();
        Recipe arrabiata = myConnection.queryByName("Arrabiata");
        arrabiata.printRecipe();
    }
}
