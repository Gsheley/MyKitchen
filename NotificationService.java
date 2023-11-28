import java.util.ArrayList;
import java.util.Iterator;

public class NotificationService {
    private ArrayList<Notification> notificationList = new ArrayList<Notification>();
    private Iterator<Notification> iterator = notificationList.iterator();
    private int currentNotifID = 1;

    public ArrayList<Notification> getNotificationList() {
        return notificationList;
    }

    public boolean checkForNotifications() {
        if (notificationList.isEmpty()) {
            return false;
        } else {
            while (iterator.hasNext()) {
                
                return true;
            }
        }
    }

    public void addNotification() {
        
    }

    public void editNotification() {

    }

    public void deleteNotification(int idToRemove) {
        while (iterator.hasNext()) {
            Notification notif = iterator.next();
            if (notif.getNotifID() == idToRemove) {
                iterator.remove(); // Removes the object that matches the notif ID to remove
            }
        }
    }
}
