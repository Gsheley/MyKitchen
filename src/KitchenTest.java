import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

public class KitchenTest 
{
    Kitchen myKitchen;
    Kitchen tesKitchen;
    Pantry tester1;
    public void setUp()
    {
        myKitchen = new Kitchen();
        tesKitchen = new Kitchen();
        tester1 = PantryService.createPantry(PantryType.PANTRY, "tester1");
        
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
        tesKitchen.createPantry(PantryType.PANTRY, "tester1");
        tesKitchen.deletePantry(0);
        assertEquals(true, tesKitchen.inventory.isEmpty());
    }

    public void addItem()
    {

    }

    public void editItem()
    {

    }

    public void saveRecipe()
    {

    }

    public void getPantryIndex()
    {

    }
}
    
