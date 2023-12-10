import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PantryTest {
    Pantry testPantry;

    int testID;
    String testName;

    @Before
    public void setUp()
    {
        testID = 3;
        testName = "testPantry";
        testPantry = new Pantry(testID, testName);
        testPantry.addItem("item1", Calendar.getInstance(), 4, Calendar.getInstance(), 2);
        testPantry.addItem("item2", Calendar.getInstance(), 3, Calendar.getInstance(), 2);
    }

    @Test
    public void testRetrievingPantry() 
    {
        assertEquals(testPantry, testPantry.retrievePantry());
    }

    @Test
    public void testAddingItem() 
    {
        // First addItem Method Tests
        Item compareItem1 = testPantry.getItem(2);
        testPantry.addItem("item3", Calendar.getInstance(), 4, Calendar.getInstance(), 2);
        assertEquals(compareItem1, testPantry.getItem(2));
        assertEquals(compareItem1, testPantry.getItemByIndex(2));
        assertEquals(3, testPantry.getCurrentItemID());

        // Second addItem Method Tests
        Item compareItem2 = new Item(3, "item4", Calendar.getInstance(), 3, Calendar.getInstance());
        testPantry.addItem("item4", Calendar.getInstance(), 3, Calendar.getInstance());
        assertEquals(compareItem2, testPantry.getItem(3));
        assertEquals(compareItem2, testPantry.getItemByIndex(3));
        assertEquals(4, testPantry.getCurrentItemID());

        // Third addItem Method Tests
        Item compareItem3 = new Item(4, "item5", Calendar.getInstance(), 8, Calendar.getInstance());
        testPantry.addItem("item5", Calendar.getInstance(), 8, Calendar.getInstance());
        assertEquals(compareItem3, testPantry.getItem(3));
        assertEquals(compareItem3, testPantry.getItemByIndex(3));
        assertEquals(5, testPantry.getCurrentItemID());

    }

    @After
    public void cleanUp()
    {
        testPantry = null;
    }
}
