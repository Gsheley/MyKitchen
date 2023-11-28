import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class NotificationService {
    private ArrayList<Notification> notificationList = new ArrayList<Notification>();
    private Iterator<Notification> iterator = notificationList.iterator();
    private NotificationFactory notifFactory = new NotificationFactory();
    private int currentNotifID = 1;

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }

    public boolean checkForNotifications() {
        if (notificationList.isEmpty()) {
            return false;
        } else {
            Date currentDate = new Date();
            while (iterator.hasNext()) {
                Notification notif = iterator.next();
                if (notif.getNotificationDate().before(currentDate)) { // If any notifications have triggered before the current date/time
                    return true;
                }
            }
            return false;
        }
    }

    public void addNotification() {
        notifFactory.createNotification(currentNotifID);
    }

    public void modifyNotification(int idToModify, Date newDate) {
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToModify) {
                // TODO -- iterator.set(new Notification(notif.getMessage(), notif.getAge() + 1));
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
}
