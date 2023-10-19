import java.io.IOException;
import java.net.URL;

public class MealDB extends API{
    //constructor
    public MealDB()
    {
    }

    //connect
    public void connect()
    {
        System.out.println("MealDB has been connected to...");
    }

    //query API by recipe name
    public Recipe queryByName(String name)
    {
        try{
            String query = "https://www.themealdb.com/api/json/v1/1/search.php?s="+ name;
            URL url = new URL(query);
            System.out.println(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Recipe();
    }

    //query API by main ingredient
    public Recipe queryByIngredient(String mainIngredient)
    {
        return new Recipe();
    }

    //query a random recipe
    public Recipe queryRandom()
    {
        return new Recipe();
    }

    //Main
    public static void main(String[] args)
    {
        MealDB myConnection = new MealDB();
        myConnection.queryByName("Spaghetti");
    }
}
