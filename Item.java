import java.util.Date;

public class Item {
    private int itemID;
    private String name;
    private Date dateAdded;
    private int quantity;
    private Date expirationDate;

    public Item(int itemID, String name, Date dateAdded, int quantity, Date expirationDate) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }
    public Item(int itemID, String name, Date dateAdded, int quantity) {
        this.itemID = itemID;
        this.name = name;
        this.dateAdded = dateAdded;
        this.quantity = quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
