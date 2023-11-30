public class PantryService 
{
    public static int nextPantrykiID = 0;
    public static int nextPantryscID = 10000;
    public static int range = 10000;

    public static Pantry createPantry(PantryType type, String name)
    {
        Pantry newPantry = null;

        if (PantryType.KITCHEN_INVENTORY == type) { 
            if (nextPantrykiID >= range) {
                System.out.println("Sorry, you have reached the maximum amount of Kitchen Inventories");
            }
            newPantry = new Pantry(nextPantrykiID, name);
            nextPantrykiID++;
        } else { 
            newPantry = new Pantry(nextPantryscID, name);
            nextPantryscID++;
        }

        return newPantry;
    }
}