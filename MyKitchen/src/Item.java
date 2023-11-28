import java.util.Calendar;

public class Item {
    private int itemID;
    private String name;
    private Calendar dateAdded;
    private int quantity;
    private Calendar expirationDate;

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
