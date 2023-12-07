import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PantryServiceTest 
{
    Pantry noType;
    Pantry shoppingPantry;
    Pantry kitchenPantry;

    @Test
    public void noTypeSpecified()
    {
        Pantry noType= new Pantry(10001, "noType");
        Pantry result = PantryService.createPantry(null, "noType");
        assertEquals(noType.getPantryName(), result.getPantryName());
        assertEquals(noType.getPantryID(), result.getPantryID());
    }

    @Test
    public void SHOPPING_CARTType()
    {
        Pantry shoppingPantry=  new Pantry(10000, "shoppingCart");
        Pantry result = PantryService.createPantry(PantryType.SHOPPING_CART, "shoppingCart");
        assertEquals(shoppingPantry.getPantryName(), result.getPantryName());
        assertEquals(shoppingPantry.getPantryID(), result.getPantryID());
    }


    @Test
    public void KITCHEN_INVENTORYYType()
    {
        Pantry kitchenPantry= new Pantry(0, "kitchenInventory");
        Pantry result = PantryService.createPantry(PantryType.PANTRY, "kitchenInventory");
        assertEquals(kitchenPantry.getPantryName(), result.getPantryName());
        assertEquals(kitchenPantry.getPantryID(), result.getPantryID());
    }
}
