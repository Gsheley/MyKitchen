import static org.junit.Assert.assertArrayEquals;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SearchTest 
{
    ArrayList<Object> emptyList;
    ArrayList<Object> itemList;
    ArrayList<Object> recipeList;
    ArrayList<Object> emptyItemList;
    ArrayList<Object> emptyRecipeList;
    ArrayList<Object> correct;

    @Before
    public void setup()
    {
        emptyList= new ArrayList<>();
        itemList= new ArrayList<>();
        recipeList= new ArrayList<>();
        emptyItemList= new ArrayList<>();
        emptyRecipeList= new ArrayList<>();
        String[] itemNames = {"apple", "bannana", "taco", "pancake", "tomato"};
        String[] recipeNames = {"Taco", "Pizza", "Steak", "Soup" };
        for(int i =0; i < 5; i++)
        {
            itemList.add(new Item(i, itemNames[i], null, i));
        }
        correct.add(itemList.get(0));


    }


    @Test
    public void emptyItemList()
    {
        ArrayList<Object> result = Search.search("apple", emptyItemList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
        
    }

    @Test
    public void itemFound()
    {
        ArrayList<Object> result = Search.search("apple", itemList);
        assertArrayEquals(correct.toArray(), result.toArray());
        
    }

    @Test
    public void itemNotFound()
    {
        ArrayList<Object> result = Search.search("blueberry", emptyItemList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
    }

    @Test
    public void emptyRecipeList()
    {
        ArrayList<Object> result = Search.search("apple", emptyRecipeList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
    }




    @After 
    public void cleanUp()
    {

    }




}


