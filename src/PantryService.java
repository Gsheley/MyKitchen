import java.util.ArrayList;

public class PantryService 
{
    private static ArrayList<Pantry> PantryList = new ArrayList<Pantry>();
    private static int nextPantryID = 1;

    public static Pantry createPantry(String name)
    {
        Pantry newPantry = new Pantry(nextPantryID,name);
        PantryList.add(newPantry);
        nextPantryID++;

        return newPantry;
    }
}
