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

    public Notification addNotification(Calendar notifDate, String message) { // adding a message is optional if the type is not VIEW
        Notification newNotification = notifFactory.createNotification(currentNotifID, notifDate, NotifType.VIEW, message);
        notificationList.add(newNotification);
        currentNotifID++;
        return newNotification;
    }

    public Notification modifyNotification(int idToModify, String newMessage, Calendar newDate) {
        Notification output = null;
        Iterator<Notification> iterator = notificationList.iterator();
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToModify) {
                notif.setNotifDate(newDate);
                notif.setMessage(newMessage);
                output = notif;
                break;
            }
        }
        return output;
    }

    public Notification removeNotification(int idToRemove) {
        Notification output = null;
        Iterator<Notification> iterator = notificationList.iterator();
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToRemove) {
                output = notif;
                iterator.remove(); // Removes the object that matches the notif ID to remove
                break;
            }
        }
        return output;
    }

    public void setCurrentNotifID(int newID) {
        currentNotifID = newID;
    }

    public void setNotificationList(ArrayList<Notification> newList) {
        notificationList = newList;
    }
}
