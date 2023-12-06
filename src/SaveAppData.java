public abstract class SaveAppData {
    public Connection conn;

    // Functionalities for Recipe
    public abstract void create(Recipe obj);
    public abstract void update(Recipe obj);
    public abstract void delete(Recipe obj);

    // Functionalities for Pantry
    public abstract void create(Pantry obj);
    public abstract void update(Pantry obj);
    public abstract void delete(Pantry obj);

    // Functionalities for Item
    public abstract void create(Pantry pantryObj, Item itemObj);
    public abstract void update(Pantry pantryObj, Item itemObj);
    public abstract void delete(Pantry pantryObj, Item itemObj);

    // Functionalities for Notification
    public abstract void create(Notification obj);
    public abstract void update(Notification obj);    
    public abstract void delete(Notification obj);

}
