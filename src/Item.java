import java.util.Calendar;

public class Item {
    private int itemID;
    private String name;
    private Calendar dateAdded;
    private int quantity;
    private Calendar expirationDate;
    private int lowQuantityNotifThreshold;

    public Item(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirationDate, int lowQuantityNotifThreshold) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.lowQuantityNotifThreshold = lowQuantityNotifThreshold;
    }

    public Item(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirationDate) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.lowQuantityNotifThreshold = -1;
    }

    public Item(int itemID, String name, Calendar dateAdded, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = null;
        this.lowQuantityNotifThreshold = -1;
    }

    public int getItemID() {
        return this.itemID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Calendar dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Calendar getExpirationDate() {
        return this.expirationDate;
    }

    public void setExpirationDate(Calendar expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getLowQuantityNotifThreshold() {
        return this.lowQuantityNotifThreshold;
    }

    public void setLowQuantityNotifThreshold(int lowQuantityNotifThreshold) {
        this.lowQuantityNotifThreshold = lowQuantityNotifThreshold;
    }
}
