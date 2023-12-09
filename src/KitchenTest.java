import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class KitchenTest 
{
    Kitchen myKitchen;
    Kitchen tesKitchen;
    Pantry tester1;
    Item apple;

    @Before
    public void setUp()
    {
        tesKitchen = new Kitchen();
    }



    @Test
    public void createPantryTest()
    {
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        assertEquals("tester1", tesKitchen.inventory.get(0).getPantryName());
    }

    @Test
    public void retrievePantryTest()
    {
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        Pantry result = tesKitchen.retrievePantry(0);
        assertEquals(0, result.getPantryID());
    }

    @Test
    public void deletePantryTest()
    {
        
        tesKitchen.inventory.clear();
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.deletePantry(tesKitchen.inventory.get(0).getPantryID());
        assertEquals(true, tesKitchen.inventory.isEmpty());
    }

    @Test
    public void addItemTest()
    {
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.addItem(tesKitchen.inventory.get(0).getPantryID(), "apple", null, 1);
        assertEquals("apple", tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getName());
        assertEquals(1, tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getQuantity());
        assertEquals(0, tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getItemID());
        
    }

    @Test
    public void editItemTest()
    {
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.addItem(tesKitchen.inventory.get(0).getPantryID(), "apple", null, 1);
        tesKitchen.editItem(tesKitchen.inventory.get(0).getPantryID(), 0, "bannana", null, 5);
        assertEquals("bannana", tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getName());
        assertEquals(5, tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getQuantity());
        assertEquals(0, tesKitchen.retrievePantry(tesKitchen.inventory.get(0).getPantryID()).getItem(0).getItemID());
        
       
    }

    @Test
    public void saveRecipeTest()
    {
        MealDB db = new MealDB();
        Recipe test = db.queryRandom();
        tesKitchen.saveRecipe(test);
    }


    @After
    public void cleanUp()
    {
        tesKitchen.inventory.clear();
    }


}
    
