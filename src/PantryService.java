public class PantryService 
{
    private static int nextPantrykiID = 0;
    private static int nextPantryscID = 10000;
    private static int range = 10000;

    public static Pantry createPantry(PantryType type, String name)
    {
        Pantry newPantry = null;

        if (PantryType.PANTRY == type) { 
            if (nextPantrykiID >= range) {
                System.out.println("Sorry, you have reached the maximum amount of Kitchen Inventories");
            } else {
                newPantry = new Pantry(nextPantrykiID, name);
                nextPantrykiID++;
            }
        } else { 
            newPantry = new Pantry(nextPantryscID, name);
            nextPantryscID++;
        }

        return newPantry;
    }

    public static int getNextKitchenInventoryID() {
        return nextPantrykiID;
    }

    public static int getNextShoppingCartID() {
        return nextPantryscID;
    }

    public static int getRange() {
        return range;
    }
}
