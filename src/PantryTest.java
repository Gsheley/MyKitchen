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
    public void testAddingItemToPantry() 
    {
        // First addItem Method Tests
        Item compareItem1 = new Item(2, "item3", Calendar.getInstance(), 4, Calendar.getInstance(), 2);
        testPantry.addItem("item3", Calendar.getInstance(), 4, Calendar.getInstance(), 2);
        assertEquals(compareItem1.getItemID(), testPantry.getItem(2).getItemID());
        assertEquals(compareItem1.getName(), testPantry.getItemByIndex(2).getName());
        assertEquals(compareItem1.getQuantity(), testPantry.getItemByIndex(2).getQuantity());
        assertEquals(compareItem1.getLowQuantityNotifThreshold(), testPantry.getItemByIndex(2).getLowQuantityNotifThreshold());
        assertEquals(3, testPantry.getCurrentItemID());

        // Second addItem Method Tests
        Item compareItem2 = new Item(3, "item4", Calendar.getInstance(), 3, Calendar.getInstance());
        testPantry.addItem("item4", Calendar.getInstance(), 3, Calendar.getInstance());
        assertEquals(compareItem2.getItemID(), testPantry.getItem(3).getItemID());
        assertEquals(compareItem2.getName(), testPantry.getItemByIndex(3).getName());
        assertEquals(compareItem2.getQuantity(), testPantry.getItemByIndex(3).getQuantity());
        assertEquals(compareItem2.getLowQuantityNotifThreshold(), testPantry.getItemByIndex(3).getLowQuantityNotifThreshold());
        assertEquals(4, testPantry.getCurrentItemID());

        // Third addItem Method Tests
        Item compareItem3 = new Item(4, "item5", Calendar.getInstance(), 8, Calendar.getInstance());
        testPantry.addItem("item5", Calendar.getInstance(), 8);
        assertEquals(compareItem3.getItemID(), testPantry.getItem(4).getItemID());
        assertEquals(compareItem3.getName(), testPantry.getItemByIndex(4).getName());
        assertEquals(compareItem3.getQuantity(), testPantry.getItemByIndex(4).getQuantity());
        assertEquals(compareItem3.getLowQuantityNotifThreshold(), testPantry.getItemByIndex(4).getLowQuantityNotifThreshold());
        assertEquals(5, testPantry.getCurrentItemID());

    }

    @Test
    public void testEditingItemInPantry()
    {
        String newName = "grapes";

        Calendar testCreationDate = Calendar.getInstance();
        testCreationDate.set(Calendar.YEAR, 2015);
        testCreationDate.set(Calendar.MONTH, 3);
        testCreationDate.set(Calendar.DAY_OF_MONTH, 4);
        testCreationDate.set(Calendar.HOUR, 5);

        int newQuantity = 3;

        Calendar testExpirationDate = Calendar.getInstance();
        testExpirationDate.set(Calendar.YEAR, 2024);
        testExpirationDate.set(Calendar.MONTH, 7);
        testExpirationDate.set(Calendar.DAY_OF_MONTH, 8);
        testExpirationDate.set(Calendar.HOUR, 5);

        int newLowQuantityNotificationThreshold = 4;

        testPantry.editItem(0, newName, testCreationDate, newQuantity, testExpirationDate, newLowQuantityNotificationThreshold);
        assertEquals(newName, testPantry.getItem(0).getName());
        assertEquals(testCreationDate, testPantry.getItem(0).getDateAdded());
        assertEquals(newQuantity, testPantry.getItem(0).getQuantity());
        assertEquals(testExpirationDate, testPantry.getItem(0).getExpirationDate());
        assertEquals(newLowQuantityNotificationThreshold, testPantry.getItem(0).getLowQuantityNotifThreshold());
    
        testPantry.editItem(1, newName, testCreationDate, newQuantity);
        assertEquals(newName, testPantry.getItem(1).getName());
        assertEquals(testCreationDate, testPantry.getItem(1).getDateAdded());
        assertEquals(newQuantity, testPantry.getItem(1).getQuantity());
    }

    @Test
    public void testDeletingItemInPantry() {
        testPantry.deleteItem(1);
        assertNull(testPantry.getItem(1));
    }

    @After
    public void cleanUp()
    {
        testPantry = null;
    }
}
