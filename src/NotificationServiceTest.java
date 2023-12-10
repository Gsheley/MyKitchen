import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import java.util.Calendar;
import java.util.ArrayList;

public class NotificationServiceTest {
    NotificationService nsTest;
    Notification testNotif;

    @Before
    public void setUp() {
        nsTest = new NotificationService();
    }

    @Test
    public void testCheckForNotifications_EmptyList() {
        ArrayList<Notification> emptyList = new ArrayList<Notification>();
        nsTest.setNotificationList(emptyList); 
        assertFalse(nsTest.checkForNotifications());
    }

    @Test
    public void testCheckForNotifications_NoTriggeredNotifs() {
        Calendar dateInTheFuture = Calendar.getInstance();
        dateInTheFuture.set(Calendar.YEAR, 2999);
        dateInTheFuture.set(Calendar.MONTH, Calendar.JANUARY);
        dateInTheFuture.set(Calendar.DAY_OF_MONTH, 1);

        nsTest.addNotification(dateInTheFuture, "Test");

        assertFalse(nsTest.checkForNotifications());
    }

    @Test
    public void testCheckForNotifications_FoundTriggeredNotifs() {
        Calendar dateInTheFuture = Calendar.getInstance();
        dateInTheFuture.set(Calendar.YEAR, 2005);
        dateInTheFuture.set(Calendar.MONTH, Calendar.JANUARY);
        dateInTheFuture.set(Calendar.DAY_OF_MONTH, 1);
        
        nsTest.addNotification(dateInTheFuture, "Test");

        assertTrue(nsTest.checkForNotifications());
    }

    @Test
    public void testAddNotification() {
        Calendar date = Calendar.getInstance();
        String message = "Test Message";

        nsTest.addNotification(date, message);

        assertFalse(nsTest.getNotificationList().isEmpty());
    }

    @Test
    public void testModifyNotification_FoundNotifToEdit() {
        nsTest.addNotification(Calendar.getInstance(), "Test Message");

        assertNotNull(nsTest.modifyNotification(1, "Modified Test Message", Calendar.getInstance()));
    }

    @Test
    public void testModifyNotification_DidNotFindNotifToEdit() {
        assertNull(nsTest.modifyNotification(1, "Modified Test Message", Calendar.getInstance()));
    }

    @Test
    public void testRemoveNotification_FoundNotifToRemove() {
        nsTest.addNotification(Calendar.getInstance(), "Test Message");

        assertNotNull(nsTest.removeNotification(1));
    }

    @Test
    public void testRemoveNotification_DidNotFindNotifToRemove() {
        assertNull(nsTest.removeNotification(1));
    }
}