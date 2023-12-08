import java.util.Calendar;

public class Item {
    public int itemID;
    public String name;
    public Calendar dateAdded;
    public int quantity;
    public Calendar expirationDate;

    public Item(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirationDate) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
    public Item(int itemID, String name, Calendar dateAdded, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = null;
    }

    public int getItemID() {
        return itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Calendar getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }
}
