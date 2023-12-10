import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
    // 3 test Items created from the 3 different constructors
    Item testItem1;
    Item testItem2;
    Item testItem3;

    int testID;
    String testName;
    Calendar testCreationDate;
    int testQuantity;
    Calendar testExpirationDate;
    int testLowQuantityNotificationThreshold;

    @Before
    public void setUp()
    {
        testID = 0;

        testName = "apple";

        testCreationDate = Calendar.getInstance();
        testCreationDate.set(Calendar.YEAR, 2015);
        testCreationDate.set(Calendar.MONTH, 3);
        testCreationDate.set(Calendar.DAY_OF_MONTH, 4);
        testCreationDate.set(Calendar.HOUR, 5);

        testQuantity = 3;

        testExpirationDate = Calendar.getInstance();
        testExpirationDate.set(Calendar.YEAR, 2024);
        testExpirationDate.set(Calendar.MONTH, 7);
        testExpirationDate.set(Calendar.DAY_OF_MONTH, 8);
        testExpirationDate.set(Calendar.HOUR, 5);

        testLowQuantityNotificationThreshold = 4;

        testItem1 = new Item(testID, testName, testCreationDate, testQuantity, testExpirationDate, testLowQuantityNotificationThreshold);
        testItem2 = new Item(testID, testName, testCreationDate, testQuantity, testExpirationDate);
        testItem3 = new Item(testID, testName, testCreationDate, testQuantity);
    }

    @Test
    public void testGettingID() {
        assertEquals(testID, testItem1.getItemID());
        assertEquals(testID, testItem2.getItemID());
        assertEquals(testID, testItem3.getItemID());
    }

    @Test 
    public void testGettingName() {
        assertEquals(testName, testItem1.getName());
        assertEquals(testName, testItem2.getName());
        assertEquals(testName, testItem3.getName());
    }

    @Test
    public void testSettingName() {
        testName = "pear";
        testItem1.setName(testName);
        testItem2.setName(testName);
        testItem3.setName(testName);
        assertEquals(testName, testItem1.getName());
        assertEquals(testName, testItem2.getName());
        assertEquals(testName, testItem3.getName());
    }

    @Test
    public void testGettingDateAdded() {
        assertEquals(testCreationDate, testItem1.getDateAdded());
        assertEquals(testCreationDate, testItem2.getDateAdded());
        assertEquals(testCreationDate, testItem3.getDateAdded());
    }

    @Test
    public void testSettingDateAdded() {
        Calendar testDate = Calendar.getInstance();
        testDate.set(Calendar.YEAR, 2017);
        testDate.set(Calendar.MONTH, 7);
        testDate.set(Calendar.DAY_OF_MONTH, 1);
        testDate.set(Calendar.HOUR, 10);

        testItem1.setDateAdded(testDate);
        testItem2.setDateAdded(testDate);
        testItem3.setDateAdded(testDate);

        assertEquals(testDate, testItem1.getDateAdded());
        assertEquals(testDate, testItem2.getDateAdded());
        assertEquals(testDate, testItem3.getDateAdded());
    }

    @Test
    public void testGettingQuantity() {
        assertEquals(testQuantity, testItem1.getQuantity());
        assertEquals(testQuantity, testItem2.getQuantity());
        assertEquals(testQuantity, testItem3.getQuantity());
    }

    @Test
    public void testSettingQuantity() {
        testQuantity = 1;
        testItem1.setQuantity(testQuantity);
        testItem2.setQuantity(testQuantity);
        testItem3.setQuantity(testQuantity);
        assertEquals(testQuantity, testItem1.getQuantity());
        assertEquals(testQuantity, testItem2.getQuantity());
        assertEquals(testQuantity, testItem3.getQuantity());
    }

    @Test
    public void testGettingExpirationDate() {
        assertEquals(testExpirationDate, testItem1.getExpirationDate());
        assertEquals(testExpirationDate, testItem2.getExpirationDate());
        assertNull(testItem3.getExpirationDate());
    }

    @Test
    public void testSettingExpirationDate() {
        Calendar testDate = Calendar.getInstance();
        testDate.set(Calendar.YEAR, 2027);
        testDate.set(Calendar.MONTH, 4);
        testDate.set(Calendar.DAY_OF_MONTH, 26);
        testDate.set(Calendar.HOUR, 12);

        testItem1.setExpirationDate(testDate);
        testItem2.setExpirationDate(testDate);

        assertEquals(testDate, testItem1.getExpirationDate());
        assertEquals(testDate, testItem2.getExpirationDate());
    }

    @Test
    public void testGettingLowQuantityNotificationThreshold() {
        assertEquals(testLowQuantityNotificationThreshold, testItem1.getLowQuantityNotifThreshold());
        assertEquals(-1, testItem2.getLowQuantityNotifThreshold());
        assertEquals(-1, testItem3.getLowQuantityNotifThreshold());
    }

    @Test
    public void testSettingLowQuantityNotificationThreshold() {
        testLowQuantityNotificationThreshold = 7;
        testItem1.setLowQuantityNotifThreshold(testLowQuantityNotificationThreshold);
        assertEquals(testLowQuantityNotificationThreshold, testItem1.getLowQuantityNotifThreshold());
    }

    @After
    public void cleanUp() 
    {
        testItem1 = null;
        testItem2 = null;
        testItem3 = null;
    }
}
