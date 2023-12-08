import static org.junit.Assert.assertEquals;
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
        myKitchen = new Kitchen();
        tesKitchen = new Kitchen();
        //tesKitchen.inventory.clear();
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
    public void addItem()
    {
        tesKitchen.inventory.clear();
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.addItem(0, "apple", null, 1);
        assertEquals("apple", tesKitchen.retrievePantry(0).getItem(0).getName());
        assertEquals(1, tesKitchen.retrievePantry(0).getItem(0).getQuantity());
        assertEquals(0, tesKitchen.retrievePantry(0).getItem(0).getItemID());
        
    }

    @Test
    public void editItem()
    {
        tesKitchen.inventory.clear();
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.addItem(tesKitchen.inventory.get(0).getPantryID(), "apple", null, 1);
        tesKitchen.editItem(0, 0, "bannana", null, 5);
        assertEquals("bannana", tesKitchen.retrievePantry(0).getItem(0).getName());
        assertEquals(5, tesKitchen.retrievePantry(0).getItem(0).getQuantity());
        assertEquals(0, tesKitchen.retrievePantry(0).getItem(0).getItemID());
        
       
    }

    public void saveRecipe()
    {

    }


}
    
