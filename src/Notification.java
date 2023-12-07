import java.util.Calendar;

public abstract class Notification {
    protected int notifID = 0;
    protected Calendar notifDate = Calendar.getInstance();
    protected String message;


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
