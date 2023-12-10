import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MealDBTest {
   
    MealDB mealDB;
    @Before
    public void setup(){
        mealDB = new MealDB(); 
    }

    @Test
    public void testExistingMeal() {
        Recipe recipe = mealDB.queryByName("rice");
        assertNotNull(recipe);
    }

    @Test
    public void testNonExistingMeal() {
        Recipe recipe = mealDB.queryByName("Peanut butter and sardines sandwich");
        assertNull("Null", recipe);
    }
}