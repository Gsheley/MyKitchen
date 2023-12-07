import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class NotificationService {
    private ArrayList<ViewNotification> notificationList = new ArrayList<ViewNotification>();
    private Iterator<ViewNotification> iterator = notificationList.iterator();
    private NotificationFactory notifFactory = new NotificationFactory();
    private int currentNotifID = 1; // keeps track of the next notifID to be instantiated

    public ArrayList<ViewNotification> getNotificationList() {
        return notificationList;
    }

    public boolean checkForNotifications() {
        if (notificationList.isEmpty()) {
            return false;
        } else {
            Calendar currentDate = Calendar.getInstance();
            while (iterator.hasNext()) {
                Notification notif = iterator.next();
                if (notif.getNotifDate().before(currentDate)) { // If any notifications have triggered before the current date/time
                    return true;
                }
            }
            return false;
        }
    }

    public void addNotification(Calendar notifDate, String message) { // adding a message is optional if the type is not VIEW
        notificationList.add(notifFactory.createNotification(currentNotifID, notifDate, NotifType.VIEW, message));
        currentNotifID++;
    }

    public void modifyNotification(int idToModify, Calendar newDate) {
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToModify) {
                notif.setNotifDate(newDate);
            }
        }
    }

    public void removeNotification(int idToRemove) {
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToRemove) {
                iterator.remove(); // Removes the object that matches the notif ID to remove
            }
        }
    }

    public void setCurrentNotifID(int newID) {
        currentNotifID = newID;
    }

    public void setNotificationList(ArrayList<ViewNotification> newList) {
        notificationList = newList;
    }
}
