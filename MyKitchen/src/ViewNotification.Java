import java.util.Calendar;

public class ViewNotification extends Notification {
    private String message;
    private int notifID;
    private Calendar notifDate;

    public String getMessage() {
        return this.message;
    }

    public Calendar getNotifDate() {
        return this.notifDate;
    }

    ViewNotification(int notifID, Calendar notifDate, String message) {
        super(notifID, notifDate);
        this.message = message;
    }

    public void displayNotification() {
        System.out.println("Notification #" + this.notifID);
        System.out.println("Date of Notification: " 
        + notifDate.get(Calendar.MONTH) + " "
        + notifDate.get(Calendar.DAY_OF_MONTH) + ", "
        + notifDate.get(Calendar.YEAR) + " at "
        + notifDate.get(Calendar.HOUR_OF_DAY) + ":"
        + notifDate.get(Calendar.MINUTE) + "\n");

        System.out.println("Notification Message:\n" + this.message);
    }
}