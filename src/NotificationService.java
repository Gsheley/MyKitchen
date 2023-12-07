import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class NotificationService {
    private ArrayList<Notification> notificationList = new ArrayList<Notification>();
    private NotificationFactory notifFactory = new NotificationFactory();
    private int currentNotifID = 1; // keeps track of the next notifID to be instantiated

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }

    public boolean checkForNotifications() {
        Iterator<Notification> iterator = notificationList.iterator();
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
        Iterator<Notification> iterator = notificationList.iterator();
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToModify) {
                notif.setNotifDate(newDate);
            }
        }
    }

    public void removeNotification(int idToRemove) {
        Iterator<Notification> iterator = notificationList.iterator();
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

    public void setNotificationList(ArrayList<Notification> newList) {
        notificationList = newList;
    }
}
