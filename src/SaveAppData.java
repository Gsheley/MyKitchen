public abstract class SaveAppData {
    public static Connection conn;

    // Functionalities for Recipe
    public abstract void create(Recipe obj);
    public abstract void update(Recipe obj);
    public abstract void delete(Recipe obj);

    // Functionalities for Pantry
    public abstract void create(Pantry obj);
    public abstract void update(Pantry obj);
    public abstract void delete(Pantry obj);

    // Functionalities for Notification
    public abstract void create(Notification obj);
    public abstract void update(Notification obj);    
    public abstract void delete(Notification obj);

    // Communicating with the Connection Class
    public abstract void open();

    public abstract void save();

}
