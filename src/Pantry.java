import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Pantry {
    private int pantryID;
    private int currentItemID = 0;
    private String pantryName;
    public ArrayList<Item> items; 

    public Pantry(int pantryID, String pantryName)
    {
        this.pantryID = pantryID;
        this.currentItemID = 0;
        this.pantryName = pantryName;
        this.items = new ArrayList<Item>();
    }

    public Pantry retrievePantry()
    {
        return this;
    }

    //To add items that have expiration dates
    public void addItem(String name, Calendar dateAdded, int quantity, Calendar expirDate)
    {
        Item newItem = new Item(currentItemID, name, dateAdded, quantity, expirDate);
        items.add(newItem);
        currentItemID++;
    }

    //To add items without expiration dates
    public void addItem(String name, Calendar dateAdded, int quantity) 
    {
        Item newItem = new Item(currentItemID, name, dateAdded, quantity);
        items.add(newItem);
        currentItemID++;
    }
   
    //To edit Items that have an expiration dates
    public void editItem(int itemID, String name, Calendar dateAdded, int quantity, Calendar expirDate)
    {
        for(int i = 0; i < items.size(); i++) {
            int curItemID = (items.get(i)).itemID;
            if(curItemID == (itemID)){        
                (items.get(i)).setName(name);
                (items.get(i)).setDateAdded(dateAdded);
                (items.get(i)).setQuantity(quantity);
                (items.get(i)).setExpirationDate(expirDate);
            }

        }
    }

    //To edit Items without expiration dates
    public void editItem(int itemID, String name, Calendar expirationDate, int quantity)
    {
        for(int i = 0; i < items.size(); i++) {
            int curItemID = (items.get(i)).itemID;
            if(curItemID == (itemID)){        
                (items.get(i)).setName(name);
                (items.get(i)).setExpirationDate(expirationDate);
                (items.get(i)).setQuantity(quantity);
            }

        }
    }

    public void deleteItem(int itemID) {
        Iterator<Item> iterator = this.items.iterator();

        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getItemID() == itemID) {
                iterator.remove();
            }
        }
    }

    public int getPantryID() 
    {
        return pantryID;
    }

    public String getPantryName() 
    {
        return pantryName;
    }

    public void setPantryName(String pantryName) 
    {
        this.pantryName = pantryName;
    }

    public Item getItem(int itemID) {
        return items.get(itemID);
    }

    public void setCurrentItemID(int itemID) { 
        currentItemID = itemID;
    }

    public int getCurrentItemID() {
        return currentItemID;
    }
}
