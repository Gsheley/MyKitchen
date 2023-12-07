import java.util.Calendar;

enum NotifType {
    VIEW
}

public class NotificationFactory {
    NotificationFactory() {
    }

    public ViewNotification createNotification(int id, Calendar notifDate, NotifType type, String message) {
        switch (type) {
            case VIEW:
                return new ViewNotification(id, notifDate, message);
        }
        return null;
    }
}