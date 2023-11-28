import java.util.Calendar;

public class ViewNotification extends Notification {
    private String message;

    public String getMessage() {
        return message;
    }

    ViewNotification(int notifID, Calendar notifDate, String message) {
        super(notifID, notifDate);
        this.message = message;
    }
}