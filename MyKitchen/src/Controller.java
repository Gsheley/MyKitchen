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

    }

    public void createPantry(int id) 
    {

    }

    public void createCart() 
    {

    }

    public void deleteCart(int id) 
    {

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