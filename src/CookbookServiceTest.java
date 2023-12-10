import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class CookbookServiceTest 
{
    Cookbook myCookbook;
    CookbookService myService;
    MealDB db;

    @Before
    public void setup()
    {
        myCookbook = new Cookbook();
        myService = new CookbookService();
        db = new MealDB();
    }

    @Test
    public void noRecipeFound()
    {
        String result = "success";
        if(myService.getRecipe("apple") == null);
        {
            result = "fail"; 
        }

        assertEquals("fail", result);
    }

    @Test 
    public void recipeFound()
    {
        
        myService.saveRecipe(db.queryRandom());
        myService.saveRecipe(db.queryRandom());
        myService.saveRecipe(db.queryByName("Bread omelette"));
        Recipe test = myService.getRecipe("Bread omelette");
        assertEquals( "Bread omelette", test.getName());
    }

    @Test
    public void deleteNonExistent()
    {
        Recipe test = myService.getRecipe("Bread omelette");
        myService.deleteRecipe(test);
        
        String result = "success";
        if(myService.getRecipe("Bread omelette") == null);
        {
            result = "fail"; 
        }
        assertEquals("fail", result);

    }

    @Test
    public void deleteExisting()
    {
        myService.saveRecipe(db.queryByName("Bread omelette"));
        Recipe test = myService.getRecipe("Bread omelette");
        myService.saveRecipe(test);
        myService.deleteRecipe(test);
        
        String result = "success";
        if(myService.getRecipe("Bread omelette") == null);
        {
            result = "fail"; 
        }
        assertEquals("fail", result);
    }

    @Test
    public void sortEmpty()
    {
        myService.sortRecipeNames();
        String result = "success";
        if(myService.getRecipe("Bread omelette") == null);
        {
            result = "fail"; 
        }
        assertEquals("fail", result);
    }

    @Test 
    public void sortNotEmpty()
    {
        myService.saveRecipe(db.queryByName("Strawberries Romanoff"));
        myService.saveRecipe(db.queryByName("Blini Pancakes"));
        myService.saveRecipe(db.queryByName("Bread omelette"));
        ArrayList<Recipe> result = myService.sortRecipeNames();
        ArrayList<Recipe> correct = new ArrayList<>();
        correct.add(db.queryByName("Blini Pancakes"));
        correct.add(db.queryByName("Bread omelette"));
        correct.add(db.queryByName("Strawberries Romanoff"));
        assertEquals(result.get(0).getName(), correct.get(0).getName());
        assertEquals(result.get(1).getName(), correct.get(1).getName());
        assertEquals(result.get(2).getName(), correct.get(2).getName());
    }
}
