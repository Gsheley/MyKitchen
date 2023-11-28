public class Navigation {
    NotificationService ns = new NotificationService();

    public void printHomePage() {
        Controller.print("Welcome to MyKitchen!\n");
        if (ns.checkForNotifications()) {
            Controller.print("You have new notification(s)!");
        } else {
            Controller.print("You have no new notifications.");
        }
    }
}
