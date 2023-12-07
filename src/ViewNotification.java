import java.util.Calendar;

public class ViewNotification extends Notification {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Calendar getNotifDate() {
        return this.notifDate;
    }

    public ViewNotification(int notifID, Calendar notifDate, String message) {
        super(notifID, notifDate);
        this.message = message;
    }

    public void displayNotification() {
        Navigation.clearConsole();
        System.out.println("Notification of ID #" + this.notifID);
        System.out.println("Date of Notification: " + this.notifDate.getTime());

        System.out.println("Notification Message: " + this.message);
    }
}