import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;

public class MealDBTest {
   
    MealDB mealDB;
    @Before
    public void setup(){
        mealDB = new MealDB(); 
    }

    @Test
    public void testExistingMeal() {
        mealDB = new MealDB();
        Recipe recipe = mealDB.queryByName("rice");
        assertNotNull("Not null", recipe);
    }

    @Test
    public void testNonExistingMeal() {
        mealDB = new MealDB();
        Recipe recipe = mealDB.queryByName("Peanut butter and sardines sandwich");
        assertNull("Null", recipe);
    }

    @Test
    public void testExistingMealByMainIngredient() {
        mealDB = new MealDB();
        ArrayList <Recipe> recipes = mealDB.queryByIngredient("chicken breast");
        assertNotNull("Not null", recipes);
    }

    @Test
    public void testNonExistingMealByMainIngredient() {
        mealDB = new MealDB();
        ArrayList <Recipe> recipes = mealDB.queryByIngredient("sand");
        assertNull("null", recipes);
    }

    @Test
    public void testRandom() {
        mealDB = new MealDB();
        Recipe randomRecipe = mealDB.queryRandom();
        assertNotNull("not null", randomRecipe);
    }

}