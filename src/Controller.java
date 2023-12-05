import java.util.Calendar;

public class Controller {
    static Kitchen kitchen = new Kitchen();
    NotificationService ns = new NotificationService();
    int numKitchens = 0;
    int numCarts = 0;
    public static void main(String[] args) 
    {
        Navigation nv = new Navigation();
        nv.printHomePage();
    }

    public void deletePantry(int id) 
    {
        kitchen.deletePantry(id);
        numKitchens--;
    }

    public void createPantry() 
    {
        kitchen.createPantry(PantryType.KITCHEN_INVENTORY,Navigation.getUserInputString(false));
        numKitchens++;
    }

    public void createCart() 
    {
        // Call to shared scanner class to ge the name of the new shopping cart
        kitchen.createPantry(PantryType.SHOPPING_CART, Navigation.getUserInputString(false));
        numCarts++;
    }

    public void deleteCart(int id) 
    {
        kitchen.deletePantry(id);
        numCarts--;
    }

    public void addItem(String name, int quantity, Calendar expirationDate) 
    {

    }

    public int getNumKitchens() {
        return numKitchens;
    }

    public int getNumCarts() {
        return numCarts;
    }

    public void setNumKitchens(int num) {
        numKitchens = num;
    }

    public void setNumCarts(int num) {
        numCarts = num;
    }
}