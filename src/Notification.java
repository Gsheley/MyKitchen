import java.util.Calendar;

public abstract class Notification {
    private int notifID = 0;
    private Calendar notifDate = Calendar.getInstance();

    Notification(int notifID, Calendar notifDate) {
        this.notifID = notifID;
        this.notifDate = notifDate;
    }

    public int getNotifID() {
        return notifID;
    }

    public Calendar getNotifDate() {
        return notifDate;
    }

    public void setNotifDate(Calendar date) {
        this.notifDate = date;
    }

    public abstract void displayNotification();
}
