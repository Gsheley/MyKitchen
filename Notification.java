import java.util.Date;

public abstract class Notification {
    private int notifID = 0;
    private Date notificationDate = null;

    public int getNotifID() {
        return notifID;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }
}
