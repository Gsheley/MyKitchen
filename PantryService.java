 public class PantryService 
{
    private int nextPantryID = 0;

    public Pantry createPantry(String name)
    {
        return new Pantry(nextPantryID, name);
    }
}
