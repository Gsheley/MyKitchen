import java.io.File;

public abstract class Connection {
    // Object for the file
    File file;

    public abstract void open();

    public abstract void save();
    
}
