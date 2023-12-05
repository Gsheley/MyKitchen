import java.lang.reflect.Array;
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

    @Before
    public void setup()
    {
        emptyList= new ArrayList<>();
        itemList= new ArrayList<>();
        recipeList= new ArrayList<>();
    }


    @Test
    public void emptyItemList()
    {
        ArrayList<Object> result = Search.search("apple", itemList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
        
    }

    @Test
    public void emptyRecipeList()
    {
        ArrayList<Object> result = Search.search("apple", recipeList);
        assertArrayEquals(emptyList.toArray(), result.toArray());
    }




    @After 
    public void cleanUp()
    {

    }




}


